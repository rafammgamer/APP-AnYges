package com.example.projanyges;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class TelaAcessib extends AppCompatActivity {

    TextView accesso, escuro, txon;
    ImageView lua;
    String d1 = "claro";
    Dados dd = new Dados();
    View main;
    Context a = this;
    Class<?> b = TelaHome.class;
    Intent c;
    CheckBox ch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_acessib);
        accesso=findViewById(R.id.txAcessibilidade);
        escuro=findViewById(R.id.txEscuro);
        main=findViewById(R.id.main2);
        lua=findViewById(R.id.lua2);
        ch=findViewById(R.id.check1);
        ch.setChecked(false);
        //txon=findViewById(R.id.txOn);
        //txon.setText("X");
    }
    public void DarkMode(View v){
        if(d1.equals("claro")){
            d1="escuro";
            dd.recebeEscuro(d1);
            accesso.setTextColor(Color.parseColor("#FFFFFF"));
            //escuro.setTextColor(Color.parseColor("#FFFFFF"));
            escuro.setBackgroundColor(Color.GRAY);
            lua.setBackgroundColor(Color.GRAY);
            ch.setBackgroundColor(Color.GRAY);
            main.setBackgroundColor(Color.parseColor("#000000"));
            ch.setChecked(true);
            //txon.setText("O");
        }else{
            d1="claro";
            dd.recebeEscuro(d1);
            accesso.setTextColor(Color.parseColor("#000000"));
            //escuro.setTextColor(Color.parseColor("#000000"));
            escuro.setBackgroundColor(Color.parseColor("#D9D9D9"));
            lua.setBackgroundColor(Color.parseColor("#D9D9D9"));
            ch.setBackgroundColor(Color.parseColor("#D9D9D9"));
            main.setBackgroundColor(Color.parseColor("#FFFFFF"));
            ch.setChecked(false);
            //txon.setText("X");
        }
    }

    public void Voltar(View v){
        dd.recebeAcesso(a,b);
        c=dd.enviaAcesso();
        startActivity(c);
        finish();
    }
}