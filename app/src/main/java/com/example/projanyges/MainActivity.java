package com.example.projanyges;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imV;
    Dados dd = new Dados();
    Context a = MainActivity.this;
    Class<?> b = Index.class;
    Intent c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imV = findViewById(R.id.imV);
        imV.setImageResource(R.drawable.logodefault1);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dd.recebeAcesso(a,b);
                c = dd.enviaAcesso();
                startActivity(c);
                finish();
            }
        }, 3000);
    }
}