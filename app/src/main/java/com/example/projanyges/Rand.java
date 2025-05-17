package com.example.projanyges;

public class Rand {

    public String codigoResgate(int tam) {
        String carac = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder codigo = new StringBuilder();
        for (int i = 0; i < tam; i++) {
            int index = (int) (Math.random() * carac.length());
            codigo.append(carac.charAt(index));
        }
        return codigo.toString();
    }

}
