package com.example.pfe;

public class Messages_MenuUsr_CheckBox {
    String ContenueMsg;
    String Date;
    String Heure;
    String IDmsg;
    String Nom_Prenom_Mdc;
    String SpecialitéMdc;
    Boolean Selected;
    //String IDCmpUsr;
    //String IDMdc;




    public Messages_MenuUsr_CheckBox(String IDMsg, String contenueMsg, String date, String heure, String nom_Prenom_Mdc, String specialitéMdc, Boolean selected) {
        IDmsg = IDMsg;
        ContenueMsg = contenueMsg;
        Date = date;
        Heure = heure;
        Nom_Prenom_Mdc = nom_Prenom_Mdc;
        SpecialitéMdc = specialitéMdc;
        Selected = selected;
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

    public Boolean getSelected() {
        return Selected;
    }

    public void setSelected(Boolean selected) {
        Selected = selected;
    }
}
