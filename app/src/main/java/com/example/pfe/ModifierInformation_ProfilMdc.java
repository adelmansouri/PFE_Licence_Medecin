package com.example.pfe;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class ModifierInformation_ProfilMdc extends AppCompatActivity {
    Toolbar Toolbar_Modifier_ProfilUsr;
    String IDUsr_ProfilUsr,Adresse_Courriel_ProfilUsr,Nom_ProfilUsr,Prenom_ProfilUsr,DateDeNaissance_ProfilUsr,Sexe_ProfilUsr,NumTel_ProfilUsr,MDP_ProfilUsr,AdrPostale_ProfilMdc,Wilaya_ProfilMdc,Specialite_ProfilMdc;
    EditText Adresse_Courriel_MdfUsr,Nom_MdfUsr,Prenom_MdfUsr,NumTel_MdfUsr,AdressePostale_MdfMdc;
    Spinner DDN_jour_MdfUsr,DDN_Mois_MdfUsr,DDN_Annee_MdfUsr,Sexe_MdfUsr,Wilaya_MdfMdc,Specialite_MdfMdc;

    ArrierePlan ArrierePlanAsyncTask;

    RelativeLayout ProgressBarModifierInfo;

    Dialog Dialog_Modifier_Mdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_information__profil_mdc);

        //hide keybord
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //progressbar
        ProgressBarModifierInfo = findViewById(R.id.ProgressBarModifierInfo);

        //set toolbar
        Toolbar_Modifier_ProfilUsr = findViewById(R.id.toolbar_ModifierProfilUsr);
        setSupportActionBar(Toolbar_Modifier_ProfilUsr);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //get Information profil Usr

        Intent intent = getIntent();
        IDUsr_ProfilUsr = intent.getStringExtra(Menu_Mdc.IDUsr_MdfUsr);
        Adresse_Courriel_ProfilUsr = intent.getStringExtra(Menu_Mdc.Adresse_Courriel_MdfUsr);
        Nom_ProfilUsr = intent.getStringExtra(Menu_Mdc.Nom_MdfUsr);
        Prenom_ProfilUsr = intent.getStringExtra(Menu_Mdc.Prenom_MdfUsr);
        DateDeNaissance_ProfilUsr = intent.getStringExtra(Menu_Mdc.DateDeNaissannce_MdfUsr);
        Sexe_ProfilUsr = intent.getStringExtra(Menu_Mdc.Sexe_MdfUsr);
        NumTel_ProfilUsr = intent.getStringExtra(Menu_Mdc.NumTel_MdfUsr);
        MDP_ProfilUsr = intent.getStringExtra(Menu_Mdc.MDP_MdfUsr);
        AdrPostale_ProfilMdc = intent.getStringExtra(Menu_Mdc.AdressePostale_MdfMdc);
        Wilaya_ProfilMdc = intent.getStringExtra(Menu_Mdc.Wilaya_MdfMdc);
        Specialite_ProfilMdc = intent.getStringExtra(Menu_Mdc.Specialite_MdfMdc);


        Adresse_Courriel_MdfUsr = findViewById(R.id.Adresse_Courriel_MdfUsr);
        Nom_MdfUsr = findViewById(R.id.Nom_MdfUsr);
        Prenom_MdfUsr = findViewById(R.id.Prenom_MdfUsr);
        NumTel_MdfUsr = findViewById(R.id.NumTel_MdfUsr);
        DDN_jour_MdfUsr = findViewById(R.id.DDN_jour_MdfUsr);
        DDN_Mois_MdfUsr = findViewById(R.id.DDN_Mois_MdfUsr);
        DDN_Annee_MdfUsr = findViewById(R.id.DDN_Annee_MdfUsr);
        Sexe_MdfUsr = findViewById(R.id.Sexe_MdfUsr);
        AdressePostale_MdfMdc = findViewById(R.id.AdressePostale_MdfMdc);
        Wilaya_MdfMdc = findViewById(R.id.Wilaya_MdfMdc);
        Specialite_MdfMdc = findViewById(R.id.Specialite_MdfMdc);

        Adresse_Courriel_MdfUsr.setText(Adresse_Courriel_ProfilUsr);
        Nom_MdfUsr.setText(Nom_ProfilUsr);
        Prenom_MdfUsr.setText(Prenom_ProfilUsr);
        NumTel_MdfUsr.setText(NumTel_ProfilUsr);
        AdressePostale_MdfMdc.setText(AdrPostale_ProfilMdc);







    }



    //Button Modifier MDP

    public void Modifier_Mdp(View view){
        TextView Fermer_Modifier_Mdp;
        final EditText Window_Modifier_Mdp,Window_Modifier_Mdp_New,Window_Modifier_Mdp_Conf_New;
        Button Modifier_Mdp_Modifier;
        Button Modifier_Mdp_Annuler;

        Dialog_Modifier_Mdp = new Dialog(this);

        Dialog_Modifier_Mdp.setContentView(R.layout.window_modifier_mdp);

        Fermer_Modifier_Mdp = Dialog_Modifier_Mdp.findViewById(R.id.Fermer_Modifier_Mdp);
        Window_Modifier_Mdp = Dialog_Modifier_Mdp.findViewById(R.id.Window_Modifier_Mdp);
        Window_Modifier_Mdp_New = Dialog_Modifier_Mdp.findViewById(R.id.Window_Modifier_Mdp_New);
        Window_Modifier_Mdp_Conf_New = Dialog_Modifier_Mdp.findViewById(R.id.Window_Modifier_Mdp_Conf_New);
        Modifier_Mdp_Modifier = Dialog_Modifier_Mdp.findViewById(R.id.Window_Modifier_Mdp_Modifier);
        Modifier_Mdp_Annuler = Dialog_Modifier_Mdp.findViewById(R.id.Window_Modifier_Mdp_Annuler);

        Fermer_Modifier_Mdp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_Modifier_Mdp.dismiss();
            }
        });

        Modifier_Mdp_Annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_Modifier_Mdp.dismiss();
            }
        });

        Modifier_Mdp_Modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Window_Modifier_Mdp.getText().toString().trim().equals("") && !Window_Modifier_Mdp_New.getText().toString().trim().equals("") && !Window_Modifier_Mdp_Conf_New.getText().toString().trim().equals("")){
                    String MDP_Actuel = Window_Modifier_Mdp.getText().toString();
                    byte[] bMdp_inscription_byte = MDP_Actuel.getBytes();
                    BigInteger bMdp_inscription_byte_data = null;

                    try {
                        bMdp_inscription_byte_data = new BigInteger(1,MD5.encryptMD5(bMdp_inscription_byte));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    MDP_Actuel = bMdp_inscription_byte_data.toString();

                    if (MDP_Actuel.equals(MDP_ProfilUsr)){
                        if (Window_Modifier_Mdp_New.getText().toString().equals(Window_Modifier_Mdp_Conf_New.getText().toString())){
                            String New_Mdp = Window_Modifier_Mdp_New.getText().toString();
                            byte[] bMdp_inscription_byte_new = New_Mdp.getBytes();
                            BigInteger bMdp_inscription_byte_data_new = null;

                            try {
                                bMdp_inscription_byte_data_new = new BigInteger(1,MD5.encryptMD5(bMdp_inscription_byte_new));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            New_Mdp = bMdp_inscription_byte_data_new.toString();

                            ArrierePlanAsyncTask = new ArrierePlan();
                            ArrierePlanAsyncTask.execute("Modifier_MDP",IDUsr_ProfilUsr,New_Mdp);
                            Dialog_Modifier_Mdp.dismiss();


                        }else{
                            Toast.makeText(ModifierInformation_ProfilMdc.this, "Nouveau mot de passe est pas identique", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(ModifierInformation_ProfilMdc.this, "Mot de passe erroné", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(ModifierInformation_ProfilMdc.this, "Veuillez remplire tous les champs.", Toast.LENGTH_LONG).show();
                }

            }
        });




        Dialog_Modifier_Mdp.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Dialog_Modifier_Mdp.show();


    }


    //Button Modifier Information

    public void Modifier_Information(View view){
        if (!Adresse_Courriel_MdfUsr.getText().toString().equals(Adresse_Courriel_ProfilUsr) || !Nom_MdfUsr.getText().toString().equals(Nom_ProfilUsr) || !Prenom_MdfUsr.getText().toString().equals(Prenom_ProfilUsr) || !NumTel_MdfUsr.getText().toString().equals(NumTel_ProfilUsr) || !Sexe_MdfUsr.getSelectedItem().toString().equals("- -") || !DDN_jour_MdfUsr.getSelectedItem().toString().equals("- -") || !DDN_Mois_MdfUsr.getSelectedItem().toString().equals("- -") || !DDN_Annee_MdfUsr.getSelectedItem().toString().equals("- -") || !AdressePostale_MdfMdc.getText().toString().equals(AdrPostale_ProfilMdc) || !Wilaya_MdfMdc.getSelectedItem().toString().equals("- -") || !Specialite_MdfMdc.getSelectedItem().toString().equals("- -")){
            if (!DDN_jour_MdfUsr.getSelectedItem().toString().equals("- -") && !DDN_Mois_MdfUsr.getSelectedItem().toString().equals("- -") && !DDN_Annee_MdfUsr.getSelectedItem().toString().equals("- -")){
                Boolean Verif_Date = Verif(Integer.parseInt(DDN_jour_MdfUsr.getSelectedItem().toString()),DDN_Mois_MdfUsr.getSelectedItem().toString().toLowerCase(),Integer.parseInt(DDN_Annee_MdfUsr.getSelectedItem().toString()));
                if (Verif_Date){
                    DateDeNaissance_ProfilUsr = DDN_jour_MdfUsr.getSelectedItem().toString()+" "+DDN_Mois_MdfUsr.getSelectedItem().toString()+" "+DDN_Annee_MdfUsr.getSelectedItem().toString();
                }else{
                    Toast.makeText(this, "Date De Naissance Incorrecte", Toast.LENGTH_LONG).show();
                }
            }
            if (!Sexe_MdfUsr.getSelectedItem().toString().equals("- -")){
                Sexe_ProfilUsr = Sexe_MdfUsr.getSelectedItem().toString();
            }
            Adresse_Courriel_ProfilUsr = Adresse_Courriel_MdfUsr.getText().toString();
            Nom_ProfilUsr = Nom_MdfUsr.getText().toString();
            Prenom_ProfilUsr = Prenom_MdfUsr.getText().toString();
            NumTel_ProfilUsr = NumTel_MdfUsr.getText().toString();
            AdrPostale_ProfilMdc = AdressePostale_MdfMdc.getText().toString();
            if (!Wilaya_MdfMdc.getSelectedItem().toString().equals("- -")){
                Wilaya_ProfilMdc = Wilaya_MdfMdc.getSelectedItem().toString();
            }
            if (!Specialite_MdfMdc.getSelectedItem().toString().equals("- -")){
                Specialite_ProfilMdc = Specialite_MdfMdc.getSelectedItem().toString();
            }


            ArrierePlanAsyncTask = new ArrierePlan();
            ArrierePlanAsyncTask.execute("Modifier_Information_Mdc",IDUsr_ProfilUsr,Adresse_Courriel_ProfilUsr,Nom_ProfilUsr,Prenom_ProfilUsr,DateDeNaissance_ProfilUsr,Sexe_ProfilUsr,NumTel_ProfilUsr, AdrPostale_ProfilMdc, Wilaya_ProfilMdc, Specialite_ProfilMdc);


        }else{
            Toast.makeText(this, "Vous n'avez rien modifier.", Toast.LENGTH_LONG).show();
        }


    }

    //verifi Bessextile

    boolean Bissextile(int annee){
        if(annee%400!=0)
            return false;
        else if(annee%100!=0)
            return false;
        else
            return true;
    }





    //verif Date si correcte

    boolean Verif(int jour,String mois,int annee){
        switch(mois)
        {case "janvier":case "mars":case "mai":case "juillet":case "aout":case "octobre":case "decembre":
            if(jour>0 && jour<=31 && annee>1900 && annee<2030)
                return true;
            else
                return false;


            case "fevrier":
                if(this.Bissextile(annee))
                    if(jour>0 && jour<=29 && annee>1900 && annee<2019)
                        return true;
                    else
                        return false;
                else
                if(jour>0 && jour<=28 && annee>1900 && annee<2019)
                    return true;
                else
                    return false;


            case "avril":case "juin":case "septembre":case "novembre":
            if(jour>0 && jour<=30 && annee>1900 && annee<2019)
                return true;
            else
                return false;
            default:
                return false;
        }
    }


    //toolbar return arrow
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    //comeback and refresh activity




    class ArrierePlan extends AsyncTask<String, Void , String>{

        @Override
        protected void onPreExecute() {
            ProgressBarModifierInfo.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            String Operation = strings[0];
            switch (Operation){
                case "Modifier_Information_Mdc" :
                    try {
                        String IDMdc = strings[1];
                        String AdrMailMdc = strings[2];
                        String NomMdc = strings[3];
                        String PrenomMdc = strings[4];
                        String DateDeNaissanceMdc = strings[5];
                        String SexeMdc = strings[6];
                        String NumTelMdc = strings[7];
                        String AdrPostaleMdc = strings[8];
                        String WilyaMdc = strings[9];
                        String SpecialiteMdc = strings[10];
                        String Url_ModifierInfo = "http://192.168.1.9:8080/ModifierInformation_ProfilMdc.php";
                        URL ModifierInfo_Url = new URL(Url_ModifierInfo);
                        HttpURLConnection mHttpUrlConnection = (HttpURLConnection) ModifierInfo_Url.openConnection();
                        mHttpUrlConnection.setRequestMethod("POST");
                        mHttpUrlConnection.setDoOutput(true);
                        mHttpUrlConnection.setDoInput(true);
                        OutputStreamWriter mOutputStreamWriter = new OutputStreamWriter(mHttpUrlConnection.getOutputStream());
                        BufferedWriter mBufferedWriter = new BufferedWriter(mOutputStreamWriter);
                        String Post_Date = URLEncoder.encode("IDUsr", "UTF-8") + "=" + URLEncoder.encode(IDMdc, "UTF-8")+"&&"+
                                URLEncoder.encode("AdrMail", "UTF-8") + "=" + URLEncoder.encode(AdrMailMdc, "UTF-8")+"&&"+
                                URLEncoder.encode("NomUsr", "UTF-8") + "=" + URLEncoder.encode(NomMdc, "UTF-8")+"&&"+
                                URLEncoder.encode("PrenomUsr", "UTF-8") + "=" + URLEncoder.encode(PrenomMdc, "UTF-8")+"&&"+
                                URLEncoder.encode("DateDeNaissanceUsr", "UTF-8") + "=" + URLEncoder.encode(DateDeNaissanceMdc, "UTF-8")+"&&"+
                                URLEncoder.encode("SexeUsr", "UTF-8") + "=" + URLEncoder.encode(SexeMdc, "UTF-8")+"&&"+
                                URLEncoder.encode("NumTelUsr", "UTF-8") + "=" + URLEncoder.encode(NumTelMdc, "UTF-8")+"&&"+
                                URLEncoder.encode("AdrPostaleMdc", "UTF-8") + "=" + URLEncoder.encode(AdrPostaleMdc, "UTF-8")+"&&"+
                                URLEncoder.encode("WilyaMdc", "UTF-8") + "=" + URLEncoder.encode(WilyaMdc, "UTF-8")+"&&"+
                                URLEncoder.encode("SpecialiteMdc", "UTF-8") + "=" + URLEncoder.encode(SpecialiteMdc, "UTF-8");
                        mBufferedWriter.write(Post_Date);
                        mBufferedWriter.close();
                        mOutputStreamWriter.close();
                        InputStreamReader mInputStreamReader = new InputStreamReader(mHttpUrlConnection.getInputStream());
                        BufferedReader mBufferedReader = new BufferedReader(mInputStreamReader);
                        String result = "";
                        String ligne = "";
                        while ((ligne = mBufferedReader.readLine()) != null) {
                            result += ligne;
                        }
                        mBufferedReader.close();
                        mInputStreamReader.close();
                        mHttpUrlConnection.disconnect();


                        return result;


                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    break;

                case "Modifier_MDP" :

                    try {
                        String IDUsr = strings[1];
                        String New_Mdp_profilUsr = strings[2];
                        String Url_ModifierMDP_ProfilUsr = "http://192.168.1.9:8080/ModifierMDP_ProfilUsr.php";
                        URL ModifierMDP_ProfilUsr_Url = new URL(Url_ModifierMDP_ProfilUsr);
                        HttpURLConnection mHttpUrlConnection = (HttpURLConnection) ModifierMDP_ProfilUsr_Url.openConnection();
                        mHttpUrlConnection.setRequestMethod("POST");
                        mHttpUrlConnection.setDoOutput(true);
                        mHttpUrlConnection.setDoInput(true);
                        OutputStreamWriter mOutputStreamWriter = new OutputStreamWriter(mHttpUrlConnection.getOutputStream());
                        BufferedWriter mBufferedWriter = new BufferedWriter(mOutputStreamWriter);
                        String Post_Date = URLEncoder.encode("IDUsr", "UTF-8") + "=" + URLEncoder.encode(IDUsr, "UTF-8")+"&&"+
                                URLEncoder.encode("MDP", "UTF-8") + "=" + URLEncoder.encode(New_Mdp_profilUsr, "UTF-8");
                        mBufferedWriter.write(Post_Date);
                        mBufferedWriter.close();
                        mOutputStreamWriter.close();
                        InputStreamReader mInputStreamReader = new InputStreamReader(mHttpUrlConnection.getInputStream());
                        BufferedReader mBufferedReader = new BufferedReader(mInputStreamReader);
                        String result = "";
                        String ligne = "";
                        while ((ligne = mBufferedReader.readLine()) != null) {
                            result += ligne;
                        }
                        mBufferedReader.close();
                        mInputStreamReader.close();
                        mHttpUrlConnection.disconnect();


                        return result;


                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    break;
            }
            return null;
        }


        @Override
        protected void onPostExecute(String result) {
            ProgressBarModifierInfo.setVisibility(View.GONE);
            try {
                JSONObject JOresultat = new JSONObject(result);
                String resultat = JOresultat.getString("resultat");
                switch (resultat){
                    case "Information Profil Modifier" :
                        Toast.makeText(ModifierInformation_ProfilMdc.this, "Informations Modifiées", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                        break;

                    case "MDP Modifier" :
                        Toast.makeText(ModifierInformation_ProfilMdc.this, "Mot de passe modifié", Toast.LENGTH_SHORT).show();

                        break;
                }





            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }




}



