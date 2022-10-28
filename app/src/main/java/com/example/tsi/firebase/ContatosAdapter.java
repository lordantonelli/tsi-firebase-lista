package com.example.tsi.firebase;

import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;

public class ContatosAdapter extends FirebaseListAdapter<Contato> {
    // Recebe um objeto do tipo FirebaseListOptions, e passa ele para o construtor da classe mãe.
    public ContatosAdapter(FirebaseListOptions options) {
        super(options);
    }

    // Método que coloca dados ("povoa") a View (o desenho) do item da lista.
    // Recebe o objeto com os dados (vindos do Firebase), e a View já inflada.
    // Basta acessarmos os dados (nome e email) e colocarmos nos objetos corretos dentro da View.
    @Override
    protected void populateView(View v, Contato c, int position) {
        // Acessa os objetos gráficos dentro do desenho do item da lista:
        TextView lblNome  = v.findViewById(R.id.lblNome);
        TextView lblEmail = v.findViewById(R.id.lblEmail);
        // Coloca dados do objeto Contato (c) nesses objetos gráficos:
        lblNome.setText( c.getNome() );
        lblEmail.setText(c.getEmail());
    }
}


