package com.example.pfe;

public class Demande_RDV_Mdc {
    private String IDRdv;
    private String NomUsr;
    private String PrenomUsr;
    private String SexeUsr;

    public Demande_RDV_Mdc(String IDRdv, String nomUsr, String prenomUsr, String sexeUsr) {
        this.IDRdv = IDRdv;
        NomUsr = nomUsr;
        PrenomUsr = prenomUsr;
        SexeUsr = sexeUsr;
    }

    public String getIDRdv() {
        return IDRdv;
    }

    public void setIDRdv(String IDRdv) {
        this.IDRdv = IDRdv;
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

    public String getSexeUsr() {
        return SexeUsr;
    }

    public void setSexeUsr(String sexeUsr) {
        SexeUsr = sexeUsr;
    }
}
