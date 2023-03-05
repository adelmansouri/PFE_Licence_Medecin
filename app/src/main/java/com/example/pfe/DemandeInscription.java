package com.example.pfe;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class DemandeInscription extends AppCompatActivity {

    TextView TV_NomPrenom, TV_Specialite, TV_AdrMdc, TV_DateDeNaissance, TV_AdrMail, TV_Sexe, TV_NumTelMdc;
    ImageView ImageView_ImageMdc;
    Intent intent;
    String IDMdc_DemandeInscrip, AdrMdc_DemandeInscrip, WilayaMdc_DemandeInscrip, SpecialiteMdc_DemandeInscrip, ImageMdc_DemandeInscrip, IDCmpUsr_DemandeInscrip, AdrMail_DemandeInscrip, NomUsr_DemandeInscrip, PrenomUsr_DemandeInscrip, DateDeNaissanceUsr_DemandeInscrip, SexeUsr_DemandeInscrip, NumTelUsr_DemandeInscrip;
    ArrierePlanDemandeInscrip mArrierePlanDemandeInscrip;
    RelativeLayout ProgressBarDemandeInscrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demande_inscription);

        ProgressBarDemandeInscrip = findViewById(R.id.ProgressBarDemandeInscrip);

        Toolbar toolbar_RDV_valide = findViewById(R.id.toolbar_DemandeInscrip);
        setSupportActionBar(toolbar_RDV_valide);
        getSupportActionBar().setTitle("Demande d'inscription");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.gradStart)));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        TV_NomPrenom = findViewById(R.id.TV_NomPrenom);
        TV_Specialite = findViewById(R.id.TV_Specialite);
        TV_AdrMdc = findViewById(R.id.TV_AdrMdc);
        TV_DateDeNaissance = findViewById(R.id.TV_DateDeNaissance);
        TV_AdrMail = findViewById(R.id.TV_AdrMail);
        TV_Sexe = findViewById(R.id.TV_Sexe);
        TV_NumTelMdc = findViewById(R.id.TV_NumTelMdc);

        ImageView_ImageMdc = findViewById(R.id.ImageView_ImageMdc);

        //getInformation from menuAdm
        intent = getIntent();
        IDMdc_DemandeInscrip = intent.getStringExtra(Menu_Adm.IDMdc_DemandeInscrip);
        AdrMdc_DemandeInscrip = intent.getStringExtra(Menu_Adm.AdrMdc_DemandeInscrip);
        WilayaMdc_DemandeInscrip = intent.getStringExtra(Menu_Adm.WilayaMdc_DemandeInscrip);
        SpecialiteMdc_DemandeInscrip = intent.getStringExtra(Menu_Adm.SpecialiteMdc_DemandeInscrip);
        ImageMdc_DemandeInscrip = intent.getStringExtra(Menu_Adm.ImageMdc_DemandeInscrip);
        IDCmpUsr_DemandeInscrip = intent.getStringExtra(Menu_Adm.IDCmpUsr_DemandeInscrip);
        AdrMail_DemandeInscrip = intent.getStringExtra(Menu_Adm.AdrMail_DemandeInscrip);
        NomUsr_DemandeInscrip = intent.getStringExtra(Menu_Adm.NomUsr_DemandeInscrip);
        PrenomUsr_DemandeInscrip = intent.getStringExtra(Menu_Adm.PrenomUsr_DemandeInscrip);
        DateDeNaissanceUsr_DemandeInscrip = intent.getStringExtra(Menu_Adm.DateDeNaissanceUsr_DemandeInscrip);
        SexeUsr_DemandeInscrip = intent.getStringExtra(Menu_Adm.SexeUsr_DemandeInscrip);
        NumTelUsr_DemandeInscrip = intent.getStringExtra(Menu_Adm.NumTelUsr_DemandeInscrip);

        //set Information

        String NomPrenom = NomUsr_DemandeInscrip + " " + PrenomUsr_DemandeInscrip;
        TV_NomPrenom.setText(NomPrenom);
        TV_Specialite.setText(SpecialiteMdc_DemandeInscrip);
        String AdresseMdc = AdrMdc_DemandeInscrip + ", " + WilayaMdc_DemandeInscrip;
        TV_AdrMdc.setText(AdresseMdc);
        TV_DateDeNaissance.setText(DateDeNaissanceUsr_DemandeInscrip);
        TV_AdrMail.setText(AdrMail_DemandeInscrip);
        TV_Sexe.setText(SexeUsr_DemandeInscrip);
        TV_NumTelMdc.setText(NumTelUsr_DemandeInscrip);

        //setImage
        byte[] ImageByte = Base64.decode(ImageMdc_DemandeInscrip, Base64.DEFAULT);
        Bitmap Dec = BitmapFactory.decodeByteArray(ImageByte, 0, ImageByte.length);
        ImageView_ImageMdc.setImageBitmap(Dec);


    }

    public void Confirmer_Inscrip(View view) {
        mArrierePlanDemandeInscrip = new ArrierePlanDemandeInscrip();
        mArrierePlanDemandeInscrip.execute("Confirmer_Inscrip", IDMdc_DemandeInscrip);

    }


    public void Refuser_Inscrip(View view) {
        mArrierePlanDemandeInscrip = new ArrierePlanDemandeInscrip();
        mArrierePlanDemandeInscrip.execute("Refuser_Inscrip", IDMdc_DemandeInscrip);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    class ArrierePlanDemandeInscrip extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            ProgressBarDemandeInscrip.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }


        @Override
        protected String doInBackground(String... strings) {
            String Operation = strings[0];
            switch (Operation) {
                case "Confirmer_Inscrip":
                    try {
                        String IDMdc = strings[1];
                        String Url_Confirmer_Inscrip = "http://192.168.1.9:8080/Confirmer_Inscrip.php";
                        URL ConfirmerInscrip_Url = new URL(Url_Confirmer_Inscrip);
                        HttpURLConnection mHttpUrlConnection = (HttpURLConnection) ConfirmerInscrip_Url.openConnection();
                        mHttpUrlConnection.setRequestMethod("POST");
                        mHttpUrlConnection.setDoOutput(true);
                        mHttpUrlConnection.setDoInput(true);
                        OutputStreamWriter mOutputStreamWriter = new OutputStreamWriter(mHttpUrlConnection.getOutputStream());
                        BufferedWriter mBufferedWriter = new BufferedWriter(mOutputStreamWriter);
                        String Post_Date = URLEncoder.encode("IDMdc", "UTF-8") + "=" + URLEncoder.encode(IDMdc, "UTF-8");
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

                case "Refuser_Inscrip" :
                    try {
                        String IDMdc = strings[1];
                        String Url_Refuser_Inscrip = "http://192.168.1.9:8080/Refuser_Inscrip.php";
                        URL RefuserInscrip_Url = new URL(Url_Refuser_Inscrip);
                        HttpURLConnection mHttpUrlConnection = (HttpURLConnection) RefuserInscrip_Url.openConnection();
                        mHttpUrlConnection.setRequestMethod("POST");
                        mHttpUrlConnection.setDoOutput(true);
                        mHttpUrlConnection.setDoInput(true);
                        OutputStreamWriter mOutputStreamWriter = new OutputStreamWriter(mHttpUrlConnection.getOutputStream());
                        BufferedWriter mBufferedWriter = new BufferedWriter(mOutputStreamWriter);
                        String Post_Date = URLEncoder.encode("IDMdc", "UTF-8") + "=" + URLEncoder.encode(IDMdc, "UTF-8");
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
            try {
                ProgressBarDemandeInscrip.setVisibility(View.GONE);

                JSONObject JOresultat = new JSONObject(result);
                String resultat = JOresultat.getString("resultat");
                switch (resultat) {
                    case "Medecin valide":
                        Toast.makeText(DemandeInscription.this, "Le médecin est validé", Toast.LENGTH_LONG).show();
                        onBackPressed();
                        break;

                    case "Medecin refuse" :
                        Toast.makeText(DemandeInscription.this, "Le médecin est refusé", Toast.LENGTH_LONG).show();
                        onBackPressed();
                        break;


                    default:
                        Toast.makeText(getApplicationContext(), "Opération échoué", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
