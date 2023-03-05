package com.example.pfe;

public class Commentaires {
    String NomPrenom;
    String Contenue;

    public Commentaires(String nomPrenom, String contenue) {
        NomPrenom = nomPrenom;
        Contenue = contenue;
    }

    public String getNomPrenom() {
        return NomPrenom;
    }

    public void setNomPrenom(String nomPrenom) {
        NomPrenom = nomPrenom;
    }

    public String getContenue() {
        return Contenue;
    }

    public void setContenue(String contenue) {
        Contenue = contenue;
    }
}
