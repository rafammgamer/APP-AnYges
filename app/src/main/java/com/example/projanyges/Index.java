package com.example.projanyges;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Index extends AppCompatActivity {

    //BottomNavigationView btview;
    Context a = Index.this;
    Class<?> b;
    Intent c;
    Dados dd = new Dados();
    String usu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        usu = dd.enviaDados();
    }
    public void Produto(View v){
        b = TelaProduto.class;
        Intent intent = new Intent(a, b);
        if(v.getId() == R.id.prod1){
            dd.recebeItem("prod1");
            intent.putExtra("imagem", R.drawable.rivotril);
            intent.putExtra("descricao", "Rivotril Gotas 2,5g\n20ml Líquido");
            intent.putExtra("nome", "Rivotril");
        } else if(v.getId() == R.id.prod2){
            dd.recebeItem("prod2");
            intent.putExtra("imagem", R.drawable.paracetamol);
            intent.putExtra("descricao", "Paracetamol Prati\n10 comprimidos 750mg");
            intent.putExtra("nome", "Paracetamol 750mg");
        }
        startActivity(intent);
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