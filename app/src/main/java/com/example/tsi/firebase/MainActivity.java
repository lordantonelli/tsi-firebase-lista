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

        /* ---------> Código AQUI <--------- */

        /* --------------------------------- */

        // Criando e associando o escutador de cliques na lista:
        listaPessoas.setOnItemClickListener(new EscutadorCliqueLista());
    }

    // Classe interna do escutador do botão adicionar:
    private class EscutadorBotaoAdicionar implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            /* ---------> Código AQUI <--------- */

            /* --------------------------------- */
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

