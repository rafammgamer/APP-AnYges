package com.example.projanyges;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class TelaHome extends AppCompatActivity {

    View main2;
    Button bt, bt2;
    TextView Tx1,Tx2,TxNot,TxAccs,TxAid,TxFdback;
    String nome, email;
    int idUsu;
    String d1="claro";
    Dados dd = new Dados();
    Context a = this;
    Class<?> b;
    Intent c;
    Conexao con = new Conexao();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_home);
        Tx1 = findViewById(R.id.tx1);
        Tx2 = findViewById(R.id.tx2);
        TxNot = findViewById(R.id.txNot);
        TxAccs = findViewById(R.id.txAccs);
        TxAid = findViewById(R.id.txAid);
        TxFdback = findViewById(R.id.txFdback);
        main2 = findViewById(R.id.main2);
        bt=findViewById(R.id.button4);
        bt2=findViewById(R.id.button5);
        d1 = dd.enviaEscuro();
        email = dd.enviaDados();
        nome = dd.enviaNome();
        idUsu = dd.enviaIdUsu();
        con.entBanco(a);
        try{
            String sql = "SELECT ISNULL((SELECT SUM(pontuacao) FROM tblDoacao WHERE ID_usuario = "+idUsu+"), 0) - " +
                    "ISNULL((SELECT SUM(c.valor) FROM tblResgate r " +
                    "INNER JOIN tblPedido p ON r.ID_pedido = p.ID_pedido " +
                    "INNER JOIN tblCupom c ON r.ID_cupom = c.ID_cupom " +
                    "WHERE p.ID_usuario = "+idUsu+"), 0) AS saldo_pontos";
            con.RS = con.stmt.executeQuery(sql);
            int saldoPontos = 0;
            if(con.RS.next()){
                saldoPontos = con.RS.getInt("saldo_pontos");
                if(email==null||email.equals("")){
                    Tx1.setText("Olá, visitante!");
                    Tx2.setVisibility(View.INVISIBLE);
                    bt.setVisibility(View.INVISIBLE);
                    bt2.setVisibility(View.VISIBLE);
                }else{
                    Tx1.setText("Olá, "+nome+"!");
                    Tx2.setText(saldoPontos +" Dp");
                    Tx2.setVisibility(View.VISIBLE);
                    bt.setVisibility(View.VISIBLE);
                    bt2.setVisibility(View.INVISIBLE);
                }
            }
        }catch(Exception ex){
            //Toast.makeText(this, "Erro ao buscar pontos do usuário", Toast.LENGTH_SHORT).show();
            ex.printStackTrace();
        }



        /*if(d1.equals("escuro")){
            Tx1.setTextColor(Color.parseColor("#FFFFFF"));
            TxNot.setTextColor(Color.parseColor("#FFFFFF"));
            TxAccs.setTextColor(Color.parseColor("#FFFFFF"));
            TxAid.setTextColor(Color.parseColor("#FFFFFF"));
            TxFdback.setTextColor(Color.parseColor("#FFFFFF"));
            TxNot.setBackgroundColor(Color.GRAY);
            TxAccs.setBackgroundColor(Color.GRAY);
            TxAid.setBackgroundColor(Color.GRAY);
            TxFdback.setBackgroundColor(Color.GRAY);
            main2.setBackgroundColor(Color.parseColor("#000000"));
        }else{
            Tx1.setTextColor(Color.parseColor("#000000"));
            TxNot.setTextColor(Color.parseColor("#000000"));
            TxAccs.setTextColor(Color.parseColor("#000000"));
            TxAid.setTextColor(Color.parseColor("#000000"));
            TxFdback.setTextColor(Color.parseColor("#000000"));
            TxNot.setBackgroundColor(Color.parseColor("#D9D9D9"));
            TxAccs.setBackgroundColor(Color.parseColor("#D9D9D9"));
            TxAid.setBackgroundColor(Color.parseColor("#D9D9D9"));
            TxFdback.setBackgroundColor(Color.parseColor("#D9D9D9"));
            main2.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }*/
    }

    public void Acesso(View V){
        b = TelaOpcao.class;
        dd.recebeAcesso(a,b);
        c=dd.enviaAcesso();
        startActivity(c);
        finish();
    }

    public void Sair(View v){
        dd.pegaDados("");
        b=MainActivity.class;
        dd.recebeAcesso(a,b);
        c=dd.enviaAcesso();
        startActivity(c);
        finish();
    }

    public void Voltar(View v){
        b=Index.class;
        dd.recebeAcesso(a,b);
        c=dd.enviaAcesso();
        startActivity(c);
        finish();
    }

    public void Acessibilidade(View v){
        b=TelaAcessib.class;
        dd.recebeAcesso(a,b);
        c=dd.enviaAcesso();
        startActivity(c);
        finish();
    }

    public void Cupons(View v){
        b = TelaCupons.class;
        dd.recebeAcesso(a,b);
        c = dd.enviaAcesso();
        startActivity(c);
        finish();
    }

    public void Historico(View v){
        b = TelaHist.class;
        dd.recebeAcesso(a,b);
        c = dd.enviaAcesso();
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

    public void Perfil(View v){
        b=TelaHome.class;
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