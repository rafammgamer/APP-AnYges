package com.example.projanyges;

import java.sql.Date;
import java.util.List;

public class Pedido {
    private int idPedido;
    private String dataPedido;
    private List<Cupom> cupons;

    public Pedido(int idPedido, String dataPedido, List<Cupom> cupons) {
        this.idPedido = idPedido;
        this.dataPedido = dataPedido;
        this.cupons = cupons;
    }

    public int getIdPedido() { return idPedido; }
    public void setIdPedido(int idPedido){
        this.idPedido = idPedido;
    }
    public String getDataPedido() { return dataPedido; }
    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }
    public List<Cupom> getCupons() { return cupons;}
    public void setCupons(List<Cupom> listaCupons){
        this.cupons = listaCupons;
    }

    }
