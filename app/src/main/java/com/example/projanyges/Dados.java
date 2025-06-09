package com.example.projanyges;

import android.content.Context;
import android.content.Intent;
import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public class Dados {
    Conexao con = new Conexao();
    public static String sh1,sh2, sh3, nome;
    public Context ctx1;
    public static Class<?> ctx2;
    public static int idU = -1, idC = -1, idPr = -1;

    public void pegaDados(String sg){
        sh1 = sg;
    }

    public String enviaDados(){
        return sh1;
    }

    public void pegaNome(String sg){
        nome = sg;
    }

    public String enviaNome(){
        return nome;
    }

    public void recebeEscuro(String sg){
        sh2 = sg;
    }

    public String enviaEscuro(){
        return sh2;
    }

    public void pegaIdUsu(int id){
        idU = id;
    }

    public int enviaIdUsu(){
        return idU;
    }

    public void pegaIdCpm(int id){
        idC = id;
    }

    public int enviaIdCpm(){
        return idC;
    }

    public void recebeItem(String sg){
        sh3 = sg;
    }

    public String enviaItem(){
        return sh3;
    }

    public static class itemCart{
        public int imgId;
        public String desc, nomeCpm;
        public int idCpm;
        public double valor;
        public itemCart(int idCpm, int imgId, String nomeCpm, String desc, double valor){
            this.idCpm = idCpm;
            this.imgId = imgId;
            this.nomeCpm = nomeCpm;
            this.desc = desc;
            this.valor = valor;
        }
    }

    private static HashMap<String, ArrayList<itemCart>> carrinhos = new HashMap<>();

    public ArrayList<itemCart> getCarrinho(String usu) {
        if (!carrinhos.containsKey(usu)) {
            carrinhos.put(usu, new ArrayList<>());
        }
        return carrinhos.get(usu);
    }

    public void limparCarrinho(String usu) {
        if (carrinhos.containsKey(usu)) {
            carrinhos.get(usu).clear();
        }
    }

    public boolean Verificar(String sg,String ss) {
        sh1 = sg;
        sh2 = ss;
        if (sh1.equals("ETESP") && sh2.equals("12345")) {
            String a = "ETESP";
            pegaDados(a);
            return true;
        } else {
            return false;
        }
    }

    public void recebeAcesso(Context tl1, Class<?> tl2){
        ctx1=tl1;
        ctx2=tl2;
    }

    public Intent enviaAcesso(){
        Intent trocar=new Intent(ctx1,ctx2);
        return trocar;
    }
}
