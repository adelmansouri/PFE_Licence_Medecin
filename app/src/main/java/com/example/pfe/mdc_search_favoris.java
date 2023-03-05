package com.example.pfe;

public class mdc_search_favoris {



    private String IDFav;
    private String IDMdc;
    private String Nom_Prenom;
    private String Specialite;
    private String Sexe;

    public mdc_search_favoris(String idfav, String idmdc, String nom_Prenom, String specialite, String sexe) {
        IDFav = idfav;
        IDMdc = idmdc;
        Nom_Prenom = nom_Prenom;
        Specialite = specialite;
        Sexe = sexe;
    }
    public String getIDFav() {
        return IDFav;
    }

    public void setIDFav(String IDFav) {
        this.IDFav = IDFav;
    }

    public String getIDMdc() {
        return IDMdc;
    }

    public void setIDMdc(String IDMdc) {
        this.IDMdc = IDMdc;
    }


    public String getNom_Prenom() {
        return Nom_Prenom;
    }

    public void setNom_Prenom(String nom_Prenom) {
        Nom_Prenom = nom_Prenom;
    }

    public String getSpecialite() {
        return Specialite;
    }

    public void setSpecialite(String specialite) {
        Specialite = specialite;
    }

    public String getSexe() {
        return Sexe;
    }

    public void setSexe(String sexe) {
        Sexe = sexe;
    }
}
