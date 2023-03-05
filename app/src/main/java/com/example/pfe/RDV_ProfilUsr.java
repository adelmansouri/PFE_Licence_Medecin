package com.example.pfe;

public class RDV_ProfilUsr {
    String Nom_Prenom_Rdv;
    String Specialite_Rdv;
    String Heure_Rdv;
    String Date_Rdv;

    public RDV_ProfilUsr(String nom_Prenom_Rdv, String specialite_Rdv, String heure_Rdv, String date_Rdv) {
        Nom_Prenom_Rdv = nom_Prenom_Rdv;
        Specialite_Rdv = specialite_Rdv;
        Heure_Rdv = heure_Rdv;
        Date_Rdv = date_Rdv;
    }

    public String getNom_Prenom_Rdv() {
        return Nom_Prenom_Rdv;
    }

    public void setNom_Prenom_Rdv(String nom_Prenom_Rdv) {
        Nom_Prenom_Rdv = nom_Prenom_Rdv;
    }

    public String getSpecialite_Rdv() {
        return Specialite_Rdv;
    }

    public void setSpecialite_Rdv(String specialite_Rdv) {
        Specialite_Rdv = specialite_Rdv;
    }

    public String getHeure_Rdv() {
        return Heure_Rdv;
    }

    public void setHeure_Rdv(String heure_Rdv) {
        Heure_Rdv = heure_Rdv;
    }

    public String getDate_Rdv() {
        return Date_Rdv;
    }

    public void setDate_Rdv(String date_Rdv) {
        Date_Rdv = date_Rdv;
    }
}
