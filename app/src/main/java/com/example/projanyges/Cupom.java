package com.example.projanyges;

public class Cupom {
    public int id, desconto;
    public String nome, tipo, imagem, descricao;
    public double valor;

    public Cupom(int id, String nome, String tipo, String imagem, String desc, double valor, int desconto){
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.imagem = imagem;
        this.descricao = desc;
        this.valor = valor;
        this.desconto = desconto;
    }
    public int getId(){
        return id;
    }

    public String getImagem() {
        return imagem;
    }

    public String getNome() {
        return nome;
    }

    public String getDesc() {
        return descricao;
    }

    public String getTipo() {return tipo;}

    public double getValor(){
        return valor;
    }
}
