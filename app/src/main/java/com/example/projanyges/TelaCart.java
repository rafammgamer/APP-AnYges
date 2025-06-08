package com.example.projanyges;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.util.ArrayList;

public class TelaCart extends AppCompatActivity {

    Context a = TelaCart.this;
    Class<?> b;
    Intent c;
    Dados dd = new Dados();
    Conexao cone = new Conexao();
    Rand rd = new Rand();
    LinearLayout mainScroll;
    String usu, itm, cdRes;
    ImageView imgV;
    TextView txtNome, txtDesc, txtValor, btnRemover;
    int idUsu, idCupom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cart);
        mainScroll = findViewById(R.id.mainScroll);
        itm = dd.enviaItem();
        usu = dd.enviaDados();
        idUsu = dd.enviaIdUsu();
        recriarCarrinho();
        //Toast.makeText(this, "Itens no carrinho: " + carrinho.size(), Toast.LENGTH_LONG).show();
    }
    public void recriarCarrinho(){
        ArrayList<Dados.itemCart> carrinho = dd.getCarrinho(usu);
        for (int i = 0; i < carrinho.size(); i++) {
            Dados.itemCart item = carrinho.get(i);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 0, 0, 20);
            LinearLayout linha = new LinearLayout(a);
            linha.setOrientation(LinearLayout.VERTICAL);
            linha.setPadding(16, 16, 16, 16);
            linha.setLayoutParams(params);
            linha.setBackgroundColor(getResources().getColor(R.color.gray_back));

            imgV = new ImageView(this);
            imgV.setImageResource(item.imgId);
            imgV.setLayoutParams(new LinearLayout.LayoutParams(300, 300));

            txtNome = new TextView(this);
            txtNome.setText(item.nomeCpm);
            txtNome.setTextSize(18);
            txtNome.setPadding(32, 10, 0, 0);

            txtDesc = new TextView(this);
            txtDesc.setText(item.desc);
            txtDesc.setTextSize(16);
            txtDesc.setPadding(32, 4, 0, 0);

            txtValor = new TextView(this);
            txtValor.setText(item.valor + " Dp");
            txtValor.setTextSize(16);
            txtValor.setPadding(32, 4, 0, 10);

            btnRemover = new TextView(this);
            btnRemover.setText("X");
            btnRemover.setTextSize(18);
            btnRemover.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
            btnRemover.setPadding(32, 0, 0, 10);
            btnRemover.setOnClickListener(view -> {
                dd.getCarrinho(usu).remove(item);
                mainScroll.removeAllViews();
                recriarCarrinho();
            });
            linha.addView(btnRemover);
            linha.addView(imgV);
            linha.addView(txtNome);
            linha.addView(txtDesc);
            linha.addView(txtValor);
            mainScroll.addView(linha);
        }
    }

    public void finalizar (View v){
        cone.entBanco(a);
        if(usu == null){
            Toast.makeText(a.getApplicationContext(), "Necessário fazer login", Toast.LENGTH_SHORT).show();
        } else {
            new AlertDialog.Builder(a)
                    .setTitle("Confirmar Pedido")
                    .setMessage("Deseja realizar o pedido?")
                    .setPositiveButton("Sim",(dialog, wich) -> {
                // Se o usuário confirmar, executa a finalização e vai para TelaCupons
                    gasto();
                    selecao();
                    dd.limparCarrinho(usu);
                    mainScroll.removeAllViews();
                    b = TelaCupons.class;
                    dd.recebeAcesso(a, b);
                    c = dd.enviaAcesso();
                    startActivity(c);
                    finish();
                })
                    .setNegativeButton("Não",(dialog, wich) -> {
                        dialog.dismiss();
                    })
                    .show();
        }
    }
    public void selecao(){
        ArrayList<Dados.itemCart> carrinho = dd.getCarrinho(usu);
        if (carrinho == null || carrinho.isEmpty()) {
            Toast.makeText(this, "Carrinho está vazio!", Toast.LENGTH_SHORT).show();
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
            if(idPedido != -1){
                for(Dados.itemCart item : carrinho){
                    cdRes = rd.codigoResgate(8);
                    String sqlResgate = "INSERT INTO tblResgate (ID_cupom, ID_pedido, codigo_resgate, dt_expiracao, utilizado)" +
                            "VALUES ('"+item.idCpm+"','"+idPedido+"','"+cdRes+"','01-01-2026','N')";
                    cone.stmt.executeUpdate(sqlResgate);
                }
                Toast.makeText(this, "Pedido finalizado!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Erro ao gerar ID do pedido.", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception ex){
            Toast.makeText(this, "Erro ao finalizar pedido.", Toast.LENGTH_SHORT).show();
        }

    }
    public void gasto(){
        cone.entBanco(a);
        try{
            String sqlDoacoes = "SELECT ISNULL(SUM(pontuacao), 0) AS pontosDoacao FROM tblDoacao WHERE ID_usuario = ?";
            ResultSet rsDoa = cone.stmt.executeQuery(sqlDoacoes);
            int pontosDoa = 0;
            if(rsDoa.next()){
                pontosDoa = cone.RS.getInt("pontosDoacao");
            }
            rsDoa.close();

            String sqlPedidos = "SELECT ISNULL(SUM(c.valor_cupom), 0) AS pontosPedidos " +
                    "FROM tblPedido p " +
                    "JOIN tblResgate r ON p.ID_pedido = r.ID_pedido " +
                    "JOIN tblCupom c ON r.ID_cupom = c.ID_cupom " +
                    "WHERE p.ID_usuario = " + idUsu;
            ResultSet rsPed = cone.stmt.executeQuery(sqlPedidos);
            int pontosPed = 0;
            if(rsPed.next()){
                pontosPed = cone.RS.getInt("pontosPedidos");
            }
            rsPed.close();

            int pontosDisp = pontosDoa - pontosPed;
            ArrayList<Dados.itemCart> cart = dd.getCarrinho(usu);
            int totalCart = 0;
            for(Dados.itemCart item : cart){
                totalCart += item.valor;
            }

            if(totalCart > pontosDisp){
                Toast.makeText(this, "Você não possui pontos suficientes para concluir o pedido.", Toast.LENGTH_LONG);
            }

        }catch(Exception ex){
            Toast.makeText(a, "Erro ao consultar pontuação: " + ex.getMessage(), Toast.LENGTH_LONG).show();
            ex.printStackTrace();
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