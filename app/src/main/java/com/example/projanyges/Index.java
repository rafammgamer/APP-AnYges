package com.example.projanyges;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Index extends AppCompatActivity {

    //BottomNavigationView btview;
    Context a = Index.this;
    Class<?> b;
    Intent c;
    EditText pesquisa;
    ImageView btnMedicamento, btnConsulta, btnHigiene;
    Dados dd = new Dados();
    Conexao con = new Conexao();
    String usu;
    private String categoriaAtual = null;
    private String textoBuscaAtual = "";
    RecyclerView recyView;
    ArrayList<Cupom> listaCupom = new ArrayList<>();
    ArrayList<Cupom> todosCupons = new ArrayList<>();
    CupomAdap adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        pesquisa = findViewById(R.id.editPesquisa);
        pesquisa.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textoBuscaAtual = s.toString();
                filtrar();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        btnMedicamento = findViewById(R.id.imgBtnMed);
        btnConsulta = findViewById(R.id.imgBtnCon);
        btnHigiene = findViewById(R.id.imgBtnHig);
        btnMedicamento.setOnClickListener(v -> filtrarCategoria("medicamento"));
        btnConsulta.setOnClickListener(v -> filtrarCategoria("consulta"));
        btnHigiene.setOnClickListener(v -> filtrarCategoria("beleza/higiene"));
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
                todosCupons.add(cupom);
                listaCupom.add(cupom);
            }
            adapter.notifyDataSetChanged();
            con.RS.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void filtrar(){
        listaCupom.clear();
        for(Cupom cupom : todosCupons){
            boolean correspondeCategoria = (categoriaAtual == null || cupom.getTipo().equalsIgnoreCase(categoriaAtual));
            boolean correspondeBusca = cupom.getNome().toLowerCase().contains(textoBuscaAtual.toLowerCase());

            if (correspondeCategoria && correspondeBusca) {
                listaCupom.add(cupom);
            }
        }
        adapter.notifyDataSetChanged();
    }

    public void filtrarCategoria(String categoria){
        if(categoria.equalsIgnoreCase(categoriaAtual)){
            categoriaAtual = null;
        }else{
            categoriaAtual = categoria;
        }
        filtrar();
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