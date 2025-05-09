package com.example.projanyges;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TelaOpcao extends AppCompatActivity {

    Context a = this;
    Class<?> b;
    Intent c;
    Dados dd=new Dados();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_opcao);
    }
    public void Acesso(View V){
        b = TelaLogin.class;
        dd.recebeAcesso(a,b);
        c=dd.enviaAcesso();
        startActivity(c);
        finish();
    }

    public void Voltar(View V){
        b = TelaHome.class;
        dd.recebeAcesso(a,b);
        c=dd.enviaAcesso();
        startActivity(c);
        finish();
    }
}