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
    Conexao cone = new Conexao();
    Rand rd = new Rand();
    LinearLayout mainScroll, itemL;
    String usu, itm, cdRes;
    ImageView imgV;
    TextView txtNome, btnRemover;
    int idUsu, idCupom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cart);
        mainScroll = findViewById(R.id.mainScroll);
        itm = dd.enviaItem();
        usu = dd.enviaDados();
        idUsu = dd.enviaIdUsu();
        idCupom = dd.enviaIdCpm();
        recriarCarrinho();
        //Toast.makeText(this, "Itens no carrinho: " + carrinho.size(), Toast.LENGTH_LONG).show();
    }
    public void recriarCarrinho(){
        ArrayList<Pair<Integer, String>> carrinho = dd.getCarrinho(usu);
        for (int i = 0; i < carrinho.size(); i++) {
            Pair<Integer, String> item = carrinho.get(i);
            int index = i;

            LinearLayout linha = new LinearLayout(a);
            linha.setOrientation(LinearLayout.VERTICAL);
            linha.setPadding(16, 16, 16, 16);

            imgV = new ImageView(this);
            imgV.setImageResource(item.first);
            imgV.setLayoutParams(new LinearLayout.LayoutParams(300, 300));

            txtNome = new TextView(this);
            txtNome.setText(item.second);
            txtNome.setTextSize(18);
            txtNome.setPadding(32, 10, 0, 0);

            btnRemover = new TextView(this);
            btnRemover.setText("X");
            btnRemover.setTextSize(18);
            btnRemover.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
            btnRemover.setPadding(32, 0, 0, 10);
            btnRemover.setOnClickListener(view -> {
                dd.getCarrinho(usu).remove(index);
                mainScroll.removeAllViews();
                recriarCarrinho();
            });
            linha.addView(btnRemover);
            linha.addView(imgV);
            linha.addView(txtNome);
            mainScroll.addView(linha);
        }
    }

    Conexao bd = new Conexao();
    public void finalizar (View v){
        bd.entBanco(a);
        if(usu == null){
            Toast.makeText(a.getApplicationContext(), "Não há produtos no carrinho", Toast.LENGTH_SHORT).show();
        } else {
            selecao();
            dd.limparCarrinho(usu);
            mainScroll.removeAllViews();
        }
    }
    public void selecao(){
        if (idCupom == -1) {
            Toast.makeText(this, "Cupom inválido", Toast.LENGTH_SHORT).show();
            return;
        }
        cone.entBanco(a);
        try{
            String sqlPedido = "INSERT INTO tblPedido (ID_usuario, dt_pedido) VALUES ('"+idUsu+"',GETDATE())";
            cone.stmt.executeUpdate(sqlPedido, java.sql.Statement.RETURN_GENERATED_KEYS);
            cone.RS = cone.stmt.getGeneratedKeys();
            int idPedido = -1;
            if(cone.RS.next()){
                idPedido = cone.RS.getInt(1);
            }
            cdRes = rd.codigoResgate(8);
            if(idPedido != -1){
                String sqlResgate = "INSERT INTO tblResgate (ID_cupom, ID_pedido, codigo_resgate, dt_expiracao, utilizado)" +
                        "VALUES ('"+idCupom+"','"+idPedido+"','"+cdRes+"','01-01-2026','N')";
                cone.stmt.executeUpdate(sqlResgate);
                Toast.makeText(this, "Pedido finalizado!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Erro ao gerar ID do pedido.", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception ex){

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