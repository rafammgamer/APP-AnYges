package com.example.projanyges;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Index extends AppCompatActivity {

    //BottomNavigationView btview;
    Context a = Index.this;
    Class<?> b;
    Intent c;
    Dados dd = new Dados();
    Conexao con = new Conexao();
    String usu;
    RecyclerView recyView;
    ArrayList<Cupom> listaCupom = new ArrayList();
    CupomAdap adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        usu = dd.enviaDados();
        recyView = findViewById(R.id.recyCupons);
        recyView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new CupomAdap(listaCupom,this, false, cupom -> {
            Intent i = new Intent(Index.this, TelaProduto.class);
            i.putExtra("id",cupom.getId());
            i.putExtra("nome", cupom.getNome());
            i.putExtra("descricao", cupom.getDesc());
            i.putExtra("imagem", cupom.getImagem());
            i.putExtra("valor", cupom.getValor());
            startActivity(i);
        });
        recyView.setAdapter(adapter);

        carregaCupons();
    }
    public void carregaCupons(){
        con.entBanco(a);
        try{
            String query = "SELECT ID_cupom, nome_cupom, tipo, imagem, descricao_cupom, valor, desconto FROM tblCupom WHERE aprovado = 'S'";
            con.RS = con.stmt.executeQuery(query);
            while (con.RS.next()) {
                Cupom cupom = new Cupom(
                        con.RS.getInt("ID_cupom"),
                        con.RS.getString("nome_cupom").trim(),
                        con.RS.getString("tipo").trim(),
                        con.RS.getString("imagem").trim(),
                        con.RS.getString("descricao_cupom").trim(),
                        con.RS.getDouble("valor"),
                        con.RS.getInt("desconto")
                );
                listaCupom.add(cupom);
            }
            adapter.notifyDataSetChanged();
            con.RS.close();
        }catch(Exception ex){
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