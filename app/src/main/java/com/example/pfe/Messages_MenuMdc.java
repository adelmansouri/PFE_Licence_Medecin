package com.example.pfe;

public class Messages_MenuMdc {

    String IDUsr;
    String IDmsg;
    String ContenueMsg;
    String Date;
    String Heure;
    //String IDCmpUsr;
    //String IDMdc;
    String Nom_Prenom_Mdc;



    public Messages_MenuMdc(String IDusr, String IDMsg, String contenueMsg, String date, String heure, String nom_Prenom_Mdc) {
        IDUsr = IDusr;
        IDmsg = IDMsg;
        ContenueMsg = contenueMsg;
        Date = date;
        Heure = heure;
        Nom_Prenom_Mdc = nom_Prenom_Mdc;
    }
    public String getIDmsg() {
        return IDmsg;
    }

    public void setIDmsg(String IDmsg) {
        this.IDmsg = IDmsg;
    }


    public String getContenueMsg() {
        return ContenueMsg;
    }

    public void setContenueMsg(String contenueMsg) {
        ContenueMsg = contenueMsg;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getHeure() {
        return Heure;
    }

    public void setHeure(String heure) {
        Heure = heure;
    }


    public String getNom_Prenom_Mdc() {
        return Nom_Prenom_Mdc;
    }

    public void setNom_Prenom_Mdc(String nom_Prenom_Mdc) {
        Nom_Prenom_Mdc = nom_Prenom_Mdc;
    }

    public String getIDUsr() {
        return IDUsr;
    }

    public void setIDUsr(String IDUsr) {
        this.IDUsr = IDUsr;
    }
}
