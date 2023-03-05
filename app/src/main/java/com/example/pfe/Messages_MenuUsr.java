package com.example.pfe;

import android.widget.CheckBox;

public class Messages_MenuUsr {

    String IDMdc;
    String IDmsg;
    String ContenueMsg;
    String Date;
    String Heure;
    //String IDCmpUsr;
    //String IDMdc;
    String Nom_Prenom_Mdc;
    String SpecialitéMdc;



    public Messages_MenuUsr(String IDmdc, String IDMsg, String contenueMsg, String date, String heure, String nom_Prenom_Mdc, String specialitéMdc) {
        IDMdc = IDmdc;
        IDmsg = IDMsg;
        ContenueMsg = contenueMsg;
        Date = date;
        Heure = heure;
        Nom_Prenom_Mdc = nom_Prenom_Mdc;
        SpecialitéMdc = specialitéMdc;
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

    public String getSpecialitéMdc() {
        return SpecialitéMdc;
    }

    public void setSpecialitéMdc(String specialitéMdc) {
        SpecialitéMdc = specialitéMdc;
    }

    public String getIDMdc() {
        return IDMdc;
    }

    public void setIDMdc(String IDMdc) {
        this.IDMdc = IDMdc;
    }
}
