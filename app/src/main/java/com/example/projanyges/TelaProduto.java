package com.example.projanyges;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
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
    TextView txtNome, txtVal, txtDesc;
    String envia, conta, descCpm, nomeCpm, nomeImg;
    int imgSele, idCpm, resId;
    double valor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_produto);
        imgProd = findViewById(R.id.imgProd);
        txtNome = findViewById(R.id.txtProd);
        txtDesc = findViewById(R.id.txtDesc);
        txtVal = findViewById(R.id.txtVal);
        envia = dd.enviaItem();
        conta = dd.enviaDados();

        intent = getIntent();
        imgSele = intent.getIntExtra("imagem", R.drawable.estrela);
        idCpm = intent.getIntExtra("id",-1);
        nomeCpm = intent.getStringExtra("nome");
        descCpm = intent.getStringExtra("descricao");
        nomeImg = mapaImagem.mapearImagem(nomeCpm);
        resId = getResources().getIdentifier(nomeImg, "drawable", getPackageName());
        valor = intent.getDoubleExtra("valor", 0);
//        String tipo = intent.getStringExtra("tipo");
//        int desconto = intent.getIntExtra("desconto", 0);

        imgProd.setImageResource(resId != 0 ? resId : R.drawable.estrela);
        txtNome.setText(nomeCpm);
        txtDesc.setText(descCpm);
        txtVal.setText(valor + " Dp");
    }
    public void Voltar(View v){
        b = Index.class;
        dd.recebeAcesso(a,b);
        c=dd.enviaAcesso();
        startActivity(c);
        finish();
    }

    public void Adicionar(View v){
        if(conta == null){
            Toast.makeText(this, "Necessário fazer o login.", Toast.LENGTH_SHORT).show();
            return;
        }

        Dados.itemCart novoCupom = new Dados.itemCart(idCpm, resId, nomeCpm, descCpm, valor);
        dd.getCarrinho(conta).add(novoCupom);
        b = TelaCart.class;
        dd.recebeAcesso(a, b);
        c = dd.enviaAcesso();
        startActivity(c);
        finish();
    }
}