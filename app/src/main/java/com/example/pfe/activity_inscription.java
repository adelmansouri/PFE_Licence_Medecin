package com.example.pfe;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

public class activity_inscription extends AppCompatActivity {


    CheckBox mcheckbox_inscrip;
    Spinner mspinner_wilaya , mspinner_specialite,mspinner_sexe,mJour_inscription,mMois_inscription,mAnnee_inscription;
    TextView mtextView_wilaya,mtextView_specialite,mtextView_Adresse,TV_Diplome,TV_Obligatoire;
    EditText mAdrMail_inscription,mMdp_inscription,mConfermation_mdp_inscription,mNom_inscription,mPrenom_inscription,mNumTel_inscription,mAdresse_inscription;
    String ImageString = "";

    public static final int Image_Pick_Code = 1000;
    public static final int Permission_Code = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);


        //toolbar
        Toolbar toolbar_inscription = findViewById(R.id.toolbar_inscription);
        setSupportActionBar(toolbar_inscription);
        getSupportActionBar().setTitle("Inscription");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.transparent)));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //hide keybord
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //Initialisation

        mcheckbox_inscrip = (CheckBox) findViewById(R.id.checkbox_mdc_inscrip);

        mtextView_wilaya = (TextView) findViewById(R.id.TV_Wilaya);
        mtextView_specialite = (TextView) findViewById(R.id.TV_Specilite);
        mtextView_Adresse = findViewById(R.id.TV_Adresse);
        TV_Obligatoire = findViewById(R.id.TV_Obligatoire);
        TV_Diplome = findViewById(R.id.TV_Diplome);


        mspinner_wilaya = (Spinner) findViewById(R.id.spinner_wilaya);
        mspinner_specialite = (Spinner) findViewById(R.id.spinner_spécialité);
        mspinner_sexe = (Spinner) findViewById(R.id.spinner_sexe);
        mJour_inscription =  findViewById(R.id.Spinner_jour_inscription);
        mMois_inscription =  findViewById(R.id.Spinner_mois_inscription);
        mAnnee_inscription =  findViewById(R.id.Spinner_annee_inscription);

        mAdrMail_inscription = (EditText) findViewById(R.id.EditText_AdrMail_inscription);
        mMdp_inscription = (EditText) findViewById(R.id.EditText_mdp_inscrip);
        mConfermation_mdp_inscription = (EditText) findViewById(R.id.EditText_confirmation_mdp_inscrip);
        mNom_inscription = (EditText) findViewById(R.id.EditText_nom_inscription);
        mPrenom_inscription = (EditText) findViewById(R.id.EditText_prenom_inscription);
        mNumTel_inscription = (EditText) findViewById(R.id.EditText_numTel_inscription);
        mAdresse_inscription = (EditText) findViewById(R.id.EditText_Adresse_inscription);

        //underline string

        String Underline_Diplome = "<u> Cliquer ici pour ajouter une photo de votre diplome </u>";
        TV_Diplome.setText(Html.fromHtml(Underline_Diplome));

    }


    //checkbox is checked
    public void show_mdc_params (View view){
        if (mcheckbox_inscrip.isChecked()){
            mspinner_wilaya.setVisibility(View.VISIBLE);
            mspinner_specialite.setVisibility(View.VISIBLE);
            mtextView_wilaya.setVisibility(View.VISIBLE);
            mtextView_specialite.setVisibility(View.VISIBLE);
            mAdresse_inscription.setVisibility(View.VISIBLE);
            mtextView_Adresse.setVisibility(View.VISIBLE);
            TV_Diplome.setVisibility(View.VISIBLE);
            TV_Obligatoire.setVisibility(View.VISIBLE);
        }
        else {
            mspinner_wilaya.setVisibility(View.GONE);
            mspinner_specialite.setVisibility(View.GONE);
            mtextView_wilaya.setVisibility(View.GONE);
            mtextView_specialite.setVisibility(View.GONE);
            mAdresse_inscription.setVisibility(View.GONE);
            mtextView_Adresse.setVisibility(View.GONE);
            TV_Diplome.setVisibility(View.GONE);
            TV_Obligatoire.setVisibility(View.GONE);
        }
    }

    public void Ajouter_Image(View view){
        //check permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){

                String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
                requestPermissions(permission, Permission_Code);

            }else{
                Choisir_Image_Gallery();
            }
        }else{
            Choisir_Image_Gallery();
        }


    }

    private void Choisir_Image_Gallery(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, Image_Pick_Code);

    }

    //Verification de permission


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == Permission_Code){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Choisir_Image_Gallery();
            }else{
                Toast.makeText(this, "L'application a besoin de votre permission", Toast.LENGTH_LONG).show();
            }
        }
    }

    //get resultat after pick


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && requestCode == Image_Pick_Code){
            try {
                Uri ImageUri = data.getData();
                InputStream InputStramImage = getContentResolver().openInputStream(ImageUri);
                Bitmap bitmap = BitmapFactory.decodeStream(InputStramImage);
                ByteArrayOutputStream mByteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,50,mByteArrayOutputStream);
                byte[] ImageByte = mByteArrayOutputStream.toByteArray();
                ImageString = Base64.encodeToString(ImageByte, Base64.DEFAULT);
                //Log.d("resultat", "onActivityResult: "+ImageString);
                //ImageString = "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";
                /*ImageByte = Base64.decode(ImageString,Base64.DEFAULT);
                Bitmap Dec = BitmapFactory.decodeByteArray(ImageByte,0,ImageByte.length);
                ImageView i = (ImageView) findViewById(R.id.test);
                i.setImageBitmap(Dec);*/
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //clicklistner
    public int Inscription_app(View view){
        String sMdp_inscription_hashe,sDateDeNaissance_inscription;
        String sAdresse_inscription = "";
        String sSpinner_wilaya = "";
        String sSpinner_specialite = "";
        String Inscription = "";

        String sAdrMail_inscription = mAdrMail_inscription.getText().toString();
        String sMdp_inscription = mMdp_inscription.getText().toString();
        String sConfermation_mdp_inscription = mConfermation_mdp_inscription.getText().toString();
        String sNom_inscription = mNom_inscription.getText().toString();
        String sPrenom_inscription = mPrenom_inscription.getText().toString();
        String sJour_inscription = mJour_inscription.getSelectedItem().toString();
        String sMois_inscription = mMois_inscription.getSelectedItem().toString();
        String sAnnee_inscription = mAnnee_inscription.getSelectedItem().toString();
        String sSpinner_sexe = mspinner_sexe.getSelectedItem().toString();
        String sNumTel_inscription = mNumTel_inscription.getText().toString();
        if (mcheckbox_inscrip.isChecked()){
            Inscription = "Medecin";
            sAdresse_inscription = mAdresse_inscription.getText().toString();
            sSpinner_wilaya = mspinner_wilaya.getSelectedItem().toString();
            sSpinner_specialite = mspinner_specialite.getSelectedItem().toString();
            if (!sAdrMail_inscription.equals("") && !sMdp_inscription.equals("") && !sConfermation_mdp_inscription.equals("") && !sNom_inscription.equals("") && !sPrenom_inscription.equals("") && !sJour_inscription.equals("") && !sMois_inscription.equals("") && !sAnnee_inscription.equals("") && !sSpinner_sexe.equals("") && !sNumTel_inscription.equals("") && !sAdresse_inscription.equals("") && !sSpinner_wilaya.equals("") && !sSpinner_specialite.equals("") && !ImageString.equals("")){
                //Inscription medecin
                boolean Verif_Date = Verif(Integer.parseInt(sJour_inscription),sMois_inscription.toLowerCase(),Integer.parseInt(sAnnee_inscription));
                if (Verif_Date){
                    sDateDeNaissance_inscription = sJour_inscription +" "+ sMois_inscription +" "+ sAnnee_inscription;
                    if (sMdp_inscription.equals(sConfermation_mdp_inscription)){
                        byte[] bMdp_inscription_byte = sMdp_inscription.getBytes();
                        BigInteger bMdp_inscription_byte_data = null;

                        try {
                            bMdp_inscription_byte_data = new BigInteger(1,MD5.encryptMD5(bMdp_inscription_byte));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        sMdp_inscription_hashe = bMdp_inscription_byte_data.toString();
                    }
                    else {
                        Toast.makeText(this,"Le mot de passe est pas identique.",Toast.LENGTH_LONG).show();
                        return 0;
                    }
                }else{
                    Toast.makeText(this, "Date Incorrecte", Toast.LENGTH_LONG).show();
                    return 0;
                }

            }
            else {
                Toast.makeText(this, "veuillez remplir tous les champs", Toast.LENGTH_LONG).show();
                return 0;
            }
        }
        else {
            Inscription = "Utilisateur";
            if (!sAdrMail_inscription.equals("") && !sMdp_inscription.equals("") && !sConfermation_mdp_inscription.equals("") && !sNom_inscription.equals("") && !sPrenom_inscription.equals("") && !sJour_inscription.equals("") && !sMois_inscription.equals("") && !sAnnee_inscription.equals("") && !sSpinner_sexe.equals("") && !sNumTel_inscription.equals("")){
                //Inscription User
                boolean Verif_Date = Verif(Integer.parseInt(sJour_inscription),sMois_inscription.toLowerCase(),Integer.parseInt(sAnnee_inscription));
                if (Verif_Date){
                    sDateDeNaissance_inscription = sJour_inscription +" "+ sMois_inscription +" "+ sAnnee_inscription;
                    if (sMdp_inscription.equals(sConfermation_mdp_inscription)){
                        byte[] bMdp_inscription_byte = sMdp_inscription.getBytes();
                        BigInteger bMdp_inscription_byte_data = null;

                        try {
                            bMdp_inscription_byte_data = new BigInteger(1,MD5.encryptMD5(bMdp_inscription_byte));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        sMdp_inscription_hashe = bMdp_inscription_byte_data.toString();
                    }
                    else {
                        Toast.makeText(this,"Le mot de passe est pas identique.",Toast.LENGTH_LONG).show();
                        return 0;
                    }
                }else{
                    Toast.makeText(this, "Date Incorrecte", Toast.LENGTH_LONG).show();
                    return 0;
                }

            }
            else {
                Toast.makeText(this, "veuillez remplir tous les champs", Toast.LENGTH_LONG).show();
                return 0;
            }
        }


        String OperationMdc = "Inscription";
        ArrierePlanCnx mArrierePlanCnx_Mdc = new ArrierePlanCnx(this);
        //Log.d("resultat", "Inscription_app: "+ImageString);
        mArrierePlanCnx_Mdc.execute(OperationMdc,Inscription,sAdrMail_inscription,sMdp_inscription_hashe,sNom_inscription,sPrenom_inscription,sDateDeNaissance_inscription,sSpinner_sexe,sNumTel_inscription,sAdresse_inscription,sSpinner_wilaya,sSpinner_specialite,ImageString);


        return 1;

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
}
