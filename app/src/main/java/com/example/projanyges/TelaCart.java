package com.example.projanyges;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TelaCart extends AppCompatActivity {

    Context a = TelaCart.this;
    Class<?> b;
    Intent c;
    Dados dd = new Dados();
    Conexao con;
    LinearLayout mainScroll, itemL;
    String usu;
    ImageView imgV;
    TextView txtNome;
    int imgProd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cart);
        mainScroll = findViewById(R.id.mainScroll);
        usu = dd.enviaDados();
        ArrayList<Pair<Integer, String>> carrinho = dd.getCarrinho(usu);
        Toast.makeText(this, "Itens no carrinho: " + carrinho.size(), Toast.LENGTH_LONG).show();
        for(Pair<Integer, String> item : carrinho){
            LinearLayout linha = new LinearLayout(a);
            linha.setOrientation(LinearLayout.HORIZONTAL);
            linha.setPadding(16, 16, 16, 16);

            ImageView img = new ImageView(this);
            img.setImageResource(item.first);
            img.setLayoutParams(new LinearLayout.LayoutParams(300, 300));

            TextView txt = new TextView(this);
            txt.setText(item.second);
            txt.setTextSize(18);
            txt.setPadding(32, 0, 0, 0);

            linha.addView(img);
            linha.addView(txt);
            mainScroll.addView(linha);
        }
    }
    public void finalizar (View v){
        con = new Conexao();
        if(usu == null){
            Toast.makeText(a.getApplicationContext(), "Não há produtos no carrinho", Toast.LENGTH_SHORT).show();
        }
    }
    public void Perfil(View v){
        b=TelaHome.class;
        dd.recebeAcesso(a,b);
        c=dd.enviaAcesso();
        startActivity(c);
        finish();
    }

    public void Home(View v){
        b=Index.class;
        dd.recebeAcesso(a,b);
        c=dd.enviaAcesso();
        startActivity(c);
        finish();
    }

    public void Cart(View v){
        b=TelaCart.class;
        dd.recebeAcesso(a,b);
        c=dd.enviaAcesso();
        startActivity(c);
        finish();
    }

    public void Scann(View v){
        b=TelaScan.class;
        dd.recebeAcesso(a,b);
        c=dd.enviaAcesso();
        startActivity(c);
        finish();
    }

}