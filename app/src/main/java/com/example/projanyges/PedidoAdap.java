package com.example.projanyges;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PedidoAdap extends RecyclerView.Adapter<PedidoAdap.PedidoViewHolder> {
    private List<Pedido> lista;
    private Context context;

    public PedidoAdap(Context context, List<Pedido> lista) {
        this.context = context;
        this.lista = lista;
    }

    public static class PedidoViewHolder extends RecyclerView.ViewHolder {
        TextView txtDataPedido;
        RecyclerView rvCupons;

        public PedidoViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDataPedido = itemView.findViewById(R.id.txtDataPed);
            rvCupons = itemView.findViewById(R.id.recyCpmResgatados);
        }
    }

    @NonNull
    @Override
    public PedidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pedido, parent, false);
        return new PedidoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PedidoViewHolder holder, int position) {
        Pedido pedido = lista.get(position);
        holder.txtDataPedido.setText("Pedido em: " + pedido.getDataPedido());

        CupomAdap adapter = new CupomAdap(context, pedido.getCupons());
        holder.rvCupons.setLayoutManager(new LinearLayoutManager(context));
        holder.rvCupons.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
