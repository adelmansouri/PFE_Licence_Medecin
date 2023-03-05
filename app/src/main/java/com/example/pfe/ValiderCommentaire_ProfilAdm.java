package com.example.pfe;

public class ValiderCommentaire_ProfilAdm {
    private String IDCom;
    private String ContenueCom;
    private String NomMdc;
    private String PrenomMdc;
    private String NomUsr;
    private String PrenomUsr;

    public ValiderCommentaire_ProfilAdm(String IDCom, String contenueCom, String nomMdc, String prenomMdc, String nomUsr, String prenomUsr) {
        this.IDCom = IDCom;
        ContenueCom = contenueCom;
        NomMdc = nomMdc;
        PrenomMdc = prenomMdc;
        NomUsr = nomUsr;
        PrenomUsr = prenomUsr;
    }

    public String getIDCom() {
        return IDCom;
    }

    public void setIDCom(String IDCom) {
        this.IDCom = IDCom;
    }

    public String getContenueCom() {
        return ContenueCom;
    }

    public void setContenueCom(String contenueCom) {
        ContenueCom = contenueCom;
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

    public String getNomUsr() {
        return NomUsr;
    }

    public void setNomUsr(String nomUsr) {
        NomUsr = nomUsr;
    }

    public String getPrenomUsr() {
        return PrenomUsr;
    }

    public void setPrenomUsr(String prenomUsr) {
        PrenomUsr = prenomUsr;
    }
}
