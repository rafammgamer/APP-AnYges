package com.example.projanyges;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Locale;

public class TelaCupons extends AppCompatActivity {

    Context a = TelaCupons.this;
    Class<?> b;
    Intent c;
    Dados dd = new Dados();
    Conexao con = new Conexao();
    LinearLayout layoutCpm;
    int idUsu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cupons);
        layoutCpm = findViewById(R.id.layoutCpm);
        idUsu = dd.enviaIdUsu();
        carregarCupons();
    }
    public void carregarCupons(){
        con.entBanco(a);
        try{
            String sql = String.format(Locale.US,"SELECT codigo_resgate, dt_expiracao, utilizado FROM tblResgate " +
                            "WHERE ID_pedido IN (SELECT ID_pedido FROM tblPedido WHERE ID_usuario = %d)", idUsu);
            con.RS = con.stmt.executeQuery(sql);
            while (con.RS.next()) {
                String codigo = con.RS.getString("codigo_resgate");
                String expiracao = con.RS.getString("dt_expiracao");
                String utilizado = con.RS.getString("utilizado");

                addCupons(codigo, expiracao, utilizado);
            }
            con.RS.close();
        }catch(Exception ex){

        }
    }

    public void addCupons(String codigo, String expiracao, String utilizado){
        TextView txtCod, txtExp, txtUsa;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 0, 0, 20);
        LinearLayout caixa = new LinearLayout(a);
        caixa.setOrientation(LinearLayout.VERTICAL);
        caixa.setBackgroundColor(getResources().getColor(R.color.gray_back));
        caixa.setPadding(16,16,16,16);
        caixa.setLayoutParams(params);

        txtCod = new TextView(a);
        txtCod.setText("Código de resgate: "+ codigo);
        txtCod.setTextSize(16);

        txtExp = new TextView(a);
        txtExp.setText("Data de expiração: "+ expiracao);

        txtUsa = new TextView(a);
        txtUsa.setText("Utilizado: "+ (utilizado.equals("S") ? "Sim" : "Não"));

        caixa.addView(txtCod);
        caixa.addView(txtExp);
        caixa.addView(txtUsa);
        layoutCpm.addView(caixa);
    }

    public void Voltar(View v){
        b = TelaHome.class;
        dd.recebeAcesso(a,b);
        c=dd.enviaAcesso();
        startActivity(c);
        finish();
    }
}