package com.example.projanyges;

import android.content.Context;
import android.content.Intent;
import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public class Dados {
    public static String sh1,sh2, sh3;
    public Context ctx1;
    public static Class<?> ctx2;
    public static int idU = -1, idC = -1;

    public void pegaDados(String sg){
        sh1 = sg;
    }

    public String enviaDados(){
        return sh1;
    }

    public void recebeEscuro(String sg){
        sh2 = sg;
    }

    public String enviaEscuro(){
        return sh2;
    }

    public void recebeItem(String sg){
        sh3 = sg;
    }

    public String enviaItem(){
        return sh3;
    }

    private static HashMap<String, ArrayList<Pair<Integer, String>>> carrinhos = new HashMap<>();

    public static void adicionaAoCarrinho(String usu, int imgId, String desc) {
        if (!carrinhos.containsKey(usu)) {
            carrinhos.put(usu, new ArrayList<>());
        }
        carrinhos.get(usu).add(new Pair<>(imgId, desc));
    }

    public static ArrayList<Pair<Integer, String>> getCarrinho(String usu) {
        ArrayList<Pair<Integer, String>> lista = carrinhos.get(usu);
        if (lista == null) {
            return new ArrayList<>();
        }
        return lista;
    }

    public static void limparCarrinho(String usu) {
        if (carrinhos.containsKey(usu)) {
            carrinhos.get(usu).clear();
        }
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
