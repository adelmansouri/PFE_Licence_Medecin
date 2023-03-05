package com.example.pfe;

public class DemandeInscrip_ProfilAdm {
    private String IDMdc;
    private String AdrMdc;
    private String WilayaMdc;
    private String SpecialiteMdc;
    private String ImageMdc;
    private String IDCmpUsr;
    private String AdrMail;
    private String NomUsr;
    private String PrenomUsr;
    private String DateDeNaissanceUsr;
    private String SexeUsr;
    private String NumTelUsr;

    public DemandeInscrip_ProfilAdm(String IDMdc, String adrMdc, String wilayaMdc, String specialiteMdc, String imageMdc, String IDCmpUsr, String adrMail, String nomUsr, String prenomUsr, String dateDeNaissanceUsr, String sexeUsr, String numTelUsr) {
        this.IDMdc = IDMdc;
        AdrMdc = adrMdc;
        WilayaMdc = wilayaMdc;
        SpecialiteMdc = specialiteMdc;
        ImageMdc = imageMdc;
        this.IDCmpUsr = IDCmpUsr;
        AdrMail = adrMail;
        NomUsr = nomUsr;
        PrenomUsr = prenomUsr;
        DateDeNaissanceUsr = dateDeNaissanceUsr;
        SexeUsr = sexeUsr;
        NumTelUsr = numTelUsr;
    }

    public String getIDMdc() {
        return IDMdc;
    }

    public void setIDMdc(String IDMdc) {
        this.IDMdc = IDMdc;
    }

    public String getAdrMdc() {
        return AdrMdc;
    }

    public void setAdrMdc(String adrMdc) {
        AdrMdc = adrMdc;
    }

    public String getWilayaMdc() {
        return WilayaMdc;
    }

    public void setWilayaMdc(String wilayaMdc) {
        WilayaMdc = wilayaMdc;
    }

    public String getSpecialiteMdc() {
        return SpecialiteMdc;
    }

    public void setSpecialiteMdc(String specialiteMdc) {
        SpecialiteMdc = specialiteMdc;
    }

    public String getImageMdc() {
        return ImageMdc;
    }

    public void setImageMdc(String imageMdc) {
        ImageMdc = imageMdc;
    }

    public String getIDCmpUsr() {
        return IDCmpUsr;
    }

    public void setIDCmpUsr(String IDCmpUsr) {
        this.IDCmpUsr = IDCmpUsr;
    }

    public String getAdrMail() {
        return AdrMail;
    }

    public void setAdrMail(String adrMail) {
        AdrMail = adrMail;
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

    public String getDateDeNaissanceUsr() {
        return DateDeNaissanceUsr;
    }

    public void setDateDeNaissanceUsr(String dateDeNaissanceUsr) {
        DateDeNaissanceUsr = dateDeNaissanceUsr;
    }

    public String getSexeUsr() {
        return SexeUsr;
    }

    public void setSexeUsr(String sexeUsr) {
        SexeUsr = sexeUsr;
    }

    public String getNumTelUsr() {
        return NumTelUsr;
    }

    public void setNumTelUsr(String numTelUsr) {
        NumTelUsr = numTelUsr;
    }
}
