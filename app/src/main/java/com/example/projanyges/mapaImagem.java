package com.example.projanyges;

public class mapaImagem {
    public static String mapearImagem(String nomeCupom){
        switch(nomeCupom.trim()){
            case "Rivotril":
                return "rivotril";
            case "Paracetamol 750mg":
                return "paracetamol";
            case "Pediatra":
                return "unimed";
            case "Psicóloga":
                return "hp";
            case "Amil One 1000":
                return "amil";
            case "Gastroenterologista":
                return "sirio";
            case "Dove 72h 150ml":
                return "dove";
            case "Sabonete Líquido Infantil":
                return "sabonete";
            case "Fralda Pampers XG":
                return "fraldapampers";
            case "Ginecologista":
                return "unimed";
            case "Dermacitá":
                return "cremedermacita";
            case "Omegafor Plus":
                return "omegafor";
            case "Principia GL-01":
                return "principiagl";
            case "Acetilcisteína 600mg":
                return "acetilcisteina";
            case "Cimegripe":
                return "cimegripe";
            case "Plenitud Plus G/XG":
                return "plenitudplus";
            case "Amil 400":
                return "amil";
            case "Amil 700":
                return "amil";
            case "Cardiologista":
                return "hp";
            case "Ortopedista":
                return "hp";
            case "Endocrinologista":
                return "sirio";
            case "Geriatra":
                return "sirio";
            case "Melatonina Melyx":
                return "melatoninamelyx";
            case "Leite de Magnésia":
                return "leitemagnesia";
            case "Sulfato Ferroso":
                return "sulfatoferroso";
            default:
                return "estrela";
        }
    }
}
