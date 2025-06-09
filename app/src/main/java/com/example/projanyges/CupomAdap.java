package com.example.projanyges;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projanyges.Cupom;

import java.util.List;

public class CupomAdap extends RecyclerView.Adapter<CupomAdap.ViewHolder> {
    private Context context;
    private List<Cupom> lista;
    private OnItemClickListener listener;
    private boolean mostrarDadosResgate;

    public interface OnItemClickListener {
        void onItemClick(Cupom cupom);
    }

        public CupomAdap(List<Cupom> lista, Context ctx, boolean mostrarDadosResgate, OnItemClickListener listener) {
        this.context = ctx;
        this.lista = lista;
        this.mostrarDadosResgate = mostrarDadosResgate;
        this.listener = listener;
    }

        public CupomAdap(Context context, List<Cupom> lista) {
        this.context = context;
        this.lista = lista;
        this.listener = null; // ou algum valor padrão
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cupom, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cupom cupom = lista.get(position);
        holder.nome.setText(cupom.nome);
        holder.tipo.setText(cupom.tipo);
        holder.descricao.setText(cupom.descricao);
        holder.valor.setText(cupom.getValor() + " Dp");

        String nomeImagem = mapaImagem.mapearImagem(cupom.getNome());
        int resId = context.getResources().getIdentifier(nomeImagem, "drawable", context.getPackageName());
        holder.imagem.setImageResource(resId != 0 ? resId : R.drawable.estrela);

        holder.itemView.setOnClickListener(v -> {if(listener != null) listener.onItemClick(cupom);});
    }
    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nome, tipo, descricao, valor;
        ImageView imagem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.textNomeCpm);
            tipo = itemView.findViewById(R.id.textTipo);
            descricao = itemView.findViewById(R.id.textDescricao);
            valor = itemView.findViewById(R.id.textValor);
            imagem = itemView.findViewById(R.id.imgCupom);
        }
    }
}
