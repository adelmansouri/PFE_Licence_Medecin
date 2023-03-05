package com.example.pfe;

public class mdc_search {

    private String IDMdc;
    private String NomMdc;
    private String PrenomMdc;
    private String Specialite;
    private String Sexe;

    public mdc_search(String idmdc, String nommdc, String prenommdc, String specialite, String sexe) {
        IDMdc = idmdc;
        NomMdc = nommdc;
        PrenomMdc = prenommdc;
        Specialite = specialite;
        Sexe = sexe;
    }

    public String getIDMdc() {
        return IDMdc;
    }

    public void setIDMdc(String IDMdc) {
        this.IDMdc = IDMdc;
    }

    public String getNomMdc() {
        return NomMdc;
    }

    public void setNomMdc(String nomMdc) {
        NomMdc = nomMdc;
    }

    public String getPrenomMdc() {
        return PrenomMdc;
    }

    public void setPrenomMdc(String prenomMdc) {
        PrenomMdc = prenomMdc;
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
