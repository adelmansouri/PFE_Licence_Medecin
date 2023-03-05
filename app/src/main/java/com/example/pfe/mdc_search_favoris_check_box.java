package com.example.pfe;

public class mdc_search_favoris_check_box {



    private String IDFav;
    private String IDMdc;
    private String Nom_Prenom;
    private String Specialite;
    private String Sexe;
    private Boolean Selected;

    public mdc_search_favoris_check_box(String idfav, String idmdc, String nom_Prenom, String specialite, String sexe, Boolean selected) {
        IDFav = idfav;
        IDMdc = idmdc;
        Nom_Prenom = nom_Prenom;
        Specialite = specialite;
        Sexe = sexe;
        Selected = selected;
    }

    public Boolean getSelected() {
        return Selected;
    }

    public void setSelected(Boolean selected) {
        Selected = selected;
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
