package com.example.projanyges;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TelaProduto extends AppCompatActivity {

    Context a = TelaProduto.this;
    Class<?> b;
    Intent c, intent;
    Dados dd = new Dados();
    ImageView imgProd;
    TextView txtProd;
    String envia, conta, txtSele, nomeSele;
    int imgSele;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_produto);
        imgProd = findViewById(R.id.imgProd);
        txtProd = findViewById(R.id.txtProd);
        envia = dd.enviaItem();
        conta = dd.enviaDados();
        intent = getIntent();
        imgSele = intent.getIntExtra("imagem", R.drawable.estrela);
        txtSele = intent.getStringExtra("descricao");
        nomeSele = intent.getStringExtra("nome");

        imgProd.setImageResource(imgSele);
        txtProd.setText(txtSele);
    }
    public void Voltar(View v){
        b = Index.class;
        dd.recebeAcesso(a,b);
        c=dd.enviaAcesso();
        startActivity(c);
        finish();
    }

    public void Adicionar(View v){
        b = TelaCart.class;
        int idCpm = dd.buscaCpmNome(nomeSele, a);
        if(conta != null){
            dd.recebeAcesso(a, b);
            c = dd.enviaAcesso();

            if ("prod1".equals(envia)) {
                dd.adicionaAoCarrinho(conta, R.drawable.rivotril, txtSele, idCpm);
                dd.pegaIdCpm(idCpm);
            } else if ("prod2".equals(envia)) {
                dd.adicionaAoCarrinho(conta, R.drawable.paracetamol, txtSele, idCpm);
                dd.pegaIdCpm(idCpm);
            }

            startActivity(c);
            finish();
        } else {
            Toast.makeText(a.getApplicationContext(), "Necessário fazer o login", Toast.LENGTH_SHORT).show();
        }
    }
}