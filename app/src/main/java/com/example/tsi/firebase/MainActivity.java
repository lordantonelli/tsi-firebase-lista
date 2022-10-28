package com.example.tsi.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    // Atributos representando os objetos gráficos da interface:
    private EditText txtNome;
    private EditText txtEmail;
    private Button btnAdicionar;
    private ListView listaPessoas;
    // Atributo que é a referência para o nó raiz do BD no Firebase:
    private DatabaseReference BD = FirebaseDatabase.getInstance().getReference();
    // Atributo que é a referência para o adapter do Firebase:
    private ContatosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ligando os atributos com os objetos na interface gráfica:
        txtNome = findViewById(R.id.txtNome);
        txtEmail = findViewById(R.id.txtEmail);
        btnAdicionar = findViewById(R.id.btnAdicionar);
        listaPessoas = findViewById(R.id.listaPessoas);
        // Criando e associando o escutador de cliques do botão "adicionar":
        btnAdicionar.setOnClickListener(new EscutadorBotaoAdicionar());
        // Referência para o nó principal deste exemplo:
        DatabaseReference dbContatos = BD.child("contatos");
        // Criando objeto com parâmetros para o adapter:
        FirebaseListOptions<Contato> options = new FirebaseListOptions.Builder<Contato>()
                .setLayout(R.layout.item_lista)
                .setQuery(dbContatos, Contato.class)
                .setLifecycleOwner(this)
                .build();
        // Criando o objeto adapter (usando os parâmetros criados acima):
        adapter = new ContatosAdapter(options);
        // Colocando o adapter no ListView:
        listaPessoas.setAdapter(adapter);
        // Criando e associando o escutador de cliques na lista:
        listaPessoas.setOnItemClickListener(new EscutadorCliqueLista());
    }

    // Classe interna do escutador do botão adicionar:
    private class EscutadorBotaoAdicionar implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            // Variáveis auxiliares:
            String nome, email;
            // Referência para o nó principal deste exemplo:
            DatabaseReference dbContatos = BD.child("contatos");
            // Pegando os dados digitados nas caixas.
            nome = txtNome.getText().toString();
            email = txtEmail.getText().toString();
            // Criando o objeto Contato, com os dados acima:
            Contato c = new Contato(nome, email);
            // Gerando um nó aleatório, que será utilizado como "chave" para
            // os dados deste usuário (como se fosse a "chave" do registro na tabela.
            // OBS:  - método push() :   gera o valor aleatório.
            //       - método getKey() : devolve o valor gerado, pra podermos usar.
            String chave = dbContatos.push().getKey();
            // Enfim, gravando os dados deste usuário "debaixo" deste nó gerado:
            dbContatos.child(chave).setValue(c);
            // Limpando as caixas, para próxima digitação:
            txtNome.setText("");
            txtEmail.setText("");
            // Colocando o cursor (o foco) de volta na caixa nome:
            txtNome.requestFocus();
        }
    }

    // Classe interna do escutador de clicks na lista:
    private class EscutadorCliqueLista implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            // Recuperando o objeto que está na posição "i" da lista:
            Contato c = adapter.getItem(i);
            // Exibindo seus dados em um Toast:
            Toast.makeText(
                    getApplicationContext(),
                    "Nome: " + c.getNome() + "\nEmail: " + c.getEmail(),
                    Toast.LENGTH_SHORT
            ).show();
        }
    }
}

