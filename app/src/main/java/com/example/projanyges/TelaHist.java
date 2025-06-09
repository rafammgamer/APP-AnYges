package com.example.projanyges;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TelaHist extends AppCompatActivity {

    Context a = TelaHist.this;
    Class<?> b;
    Intent c;
    Dados dd = new Dados();
    Conexao con = new Conexao();
    private RecyclerView recyclerViewPedidos;
    private PedidoAdap pedidoAdapter;
    private List<Pedido> listaPedidos;
    int idUsu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_hist);
        recyclerViewPedidos = findViewById(R.id.recyHist);
        recyclerViewPedidos.setLayoutManager(new LinearLayoutManager(this));
        idUsu = dd.enviaIdUsu();
        listaPedidos = carregarHistorico(dd.enviaIdUsu());
        pedidoAdapter = new PedidoAdap(this, listaPedidos);
        recyclerViewPedidos.setAdapter(pedidoAdapter);
    }
    public ArrayList<Pedido> carregarHistorico(int idUsuario){
        ArrayList<Pedido> pedidos = new ArrayList<>();
        con.entBanco(a);
        try{
            String sql = "SELECT p.ID_pedido, p.dt_pedido, " +
                    "c.ID_cupom, c.nome_cupom, c.tipo, c.valor, c.imagem, " +
                    "c.descricao_cupom, c.desconto " +
                    "FROM tblPedido p " +
                    "JOIN tblResgate r ON p.ID_pedido = r.ID_pedido " +
                    "JOIN tblCupom c ON r.ID_cupom = c.ID_cupom " +
                    "WHERE p.ID_usuario = ? " +
                    "ORDER BY p.dt_pedido DESC";

            PreparedStatement stmt = con.con.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            con.RS = stmt.executeQuery();

            Map<Integer, Pedido> mapaPedidos = new LinkedHashMap<>();

            while (con.RS.next()) {
                int idPedido = con.RS.getInt("ID_pedido");
                String dataPedido = con.RS.getString("dt_pedido");

                Cupom cupom = new Cupom(
                        con.RS.getInt("ID_cupom"),
                        con.RS.getString("nome_cupom"),
                        con.RS.getString("tipo"),
                        con.RS.getString("imagem"),
                        con.RS.getString("descricao_cupom"),
                        con.RS.getDouble("valor"),
                        con.RS.getInt("desconto")
                );

                if (!mapaPedidos.containsKey(idPedido)) {
                    Pedido pedido = new Pedido(idPedido, dataPedido, new ArrayList<>());
                    mapaPedidos.put(idPedido, pedido);
                }
                mapaPedidos.get(idPedido).getCupons().add(cupom);
            }

            pedidos.addAll(mapaPedidos.values());

        }catch(Exception ex){
            Toast.makeText(this, "Erro ao carregar histórico", Toast.LENGTH_SHORT).show();
            ex.printStackTrace();
        }
        return pedidos;
    }

    public void Voltar(View v){
        b = TelaHome.class;
        dd.recebeAcesso(a,b);
        c=dd.enviaAcesso();
        startActivity(c);
        finish();
    }
}