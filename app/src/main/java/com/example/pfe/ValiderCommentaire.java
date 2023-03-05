package com.example.pfe;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
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

public class ValiderCommentaire extends AppCompatActivity {

    RelativeLayout ProgressBarValiderCommentaire;
    TextView TV_NomPrenomUsr,TV_NomPrenomMdc,TV_ContenueCom;

    Intent intent;
    String IDCom_ValiderCommentaire,ContenueCom_ValiderCommentaire,NomMdc_ValiderCommentaire,PrenomMdc_ValiderCommentaire,NomUsr_ValiderCommentaire,PrenomUsr_ValiderCommentaire;
    String NomPrenomMdc,NomPrenomUsr;

    ArrierePlanValiderCommentaire mArrierePlanValiderCommentaire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valider_commentaire);

        ProgressBarValiderCommentaire = findViewById(R.id.ProgressBarValiderCommentaire);

        Toolbar toolbar_RDV_valide = findViewById(R.id.toolbar_ValiderCommentaire);
        setSupportActionBar(toolbar_RDV_valide);
        getSupportActionBar().setTitle("Validation de commentaire");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.gradStart)));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        TV_NomPrenomUsr = findViewById(R.id.TV_NomPrenomUsr);
        TV_NomPrenomMdc = findViewById(R.id.TV_NomPrenomMdc);
        TV_ContenueCom = findViewById(R.id.TV_ContenueCom);

        intent = getIntent();
        IDCom_ValiderCommentaire = intent.getStringExtra(Menu_Adm.IDCom_ValiderCommentaire);
        ContenueCom_ValiderCommentaire = intent.getStringExtra(Menu_Adm.ContenueCom_ValiderCommentaire);
        NomMdc_ValiderCommentaire = intent.getStringExtra(Menu_Adm.NomMdc_ValiderCommentaire);
        PrenomMdc_ValiderCommentaire = intent.getStringExtra(Menu_Adm.PrenomMdc_ValiderCommentaire);
        NomUsr_ValiderCommentaire = intent.getStringExtra(Menu_Adm.NomUsr_ValiderCommentaire);
        PrenomUsr_ValiderCommentaire = intent.getStringExtra(Menu_Adm.PrenomUsr_ValiderCommentaire);

        NomPrenomMdc = NomMdc_ValiderCommentaire+" "+PrenomMdc_ValiderCommentaire;
        NomPrenomUsr = NomUsr_ValiderCommentaire+" "+PrenomUsr_ValiderCommentaire;

        TV_NomPrenomUsr.setText(NomPrenomUsr);
        TV_NomPrenomMdc.setText(NomPrenomMdc);
        TV_ContenueCom.setText(ContenueCom_ValiderCommentaire);






    }

    public void Confirmer_Commentaire(View view){
        mArrierePlanValiderCommentaire = new ArrierePlanValiderCommentaire();
        mArrierePlanValiderCommentaire.execute("Confirmer commentaire",IDCom_ValiderCommentaire);

    }

    public void Refuser_Commentaire(View view){
        mArrierePlanValiderCommentaire = new ArrierePlanValiderCommentaire();
        mArrierePlanValiderCommentaire.execute("Refuser commentaire",IDCom_ValiderCommentaire);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public class ArrierePlanValiderCommentaire extends AsyncTask<String, Void, String>{

        @Override
        protected void onPreExecute() {
            ProgressBarValiderCommentaire.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            String Operation = strings[0];
            switch (Operation) {
                case "Confirmer commentaire":
                    try {
                        String IDCom = strings[1];
                        String Url_Confirmer_Commentaire = "http://192.168.1.9:8080/Confirmer_Commentaire.php";
                        URL ConfirmerCommentaire_Url = new URL(Url_Confirmer_Commentaire);
                        HttpURLConnection mHttpUrlConnection = (HttpURLConnection) ConfirmerCommentaire_Url.openConnection();
                        mHttpUrlConnection.setRequestMethod("POST");
                        mHttpUrlConnection.setDoOutput(true);
                        mHttpUrlConnection.setDoInput(true);
                        OutputStreamWriter mOutputStreamWriter = new OutputStreamWriter(mHttpUrlConnection.getOutputStream());
                        BufferedWriter mBufferedWriter = new BufferedWriter(mOutputStreamWriter);
                        String Post_Date = URLEncoder.encode("IDCom", "UTF-8") + "=" + URLEncoder.encode(IDCom, "UTF-8");
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

                case "Refuser commentaire" :
                    try {
                        String IDCom = strings[1];
                        String Url_Refuser_Commentaire = "http://192.168.1.9:8080/Refuser_Commentaire.php";
                        URL RefuserCommentaire_Url = new URL(Url_Refuser_Commentaire);
                        HttpURLConnection mHttpUrlConnection = (HttpURLConnection) RefuserCommentaire_Url.openConnection();
                        mHttpUrlConnection.setRequestMethod("POST");
                        mHttpUrlConnection.setDoOutput(true);
                        mHttpUrlConnection.setDoInput(true);
                        OutputStreamWriter mOutputStreamWriter = new OutputStreamWriter(mHttpUrlConnection.getOutputStream());
                        BufferedWriter mBufferedWriter = new BufferedWriter(mOutputStreamWriter);
                        String Post_Date = URLEncoder.encode("IDCom", "UTF-8") + "=" + URLEncoder.encode(IDCom, "UTF-8");
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
                ProgressBarValiderCommentaire.setVisibility(View.GONE);

                JSONObject JOresultat = new JSONObject(result);
                String resultat = JOresultat.getString("resultat");
                Log.d("resultat", "onPostExecute: " + resultat);
                switch (resultat) {
                    case "Commentaire valide":
                        Toast.makeText(ValiderCommentaire.this, "Le Commentaire est validé", Toast.LENGTH_LONG).show();
                        onBackPressed();
                        break;

                    case "Commentaire non valide":
                        Toast.makeText(ValiderCommentaire.this, "Probleme dans la validation du commentaire", Toast.LENGTH_LONG).show();
                        break;

                    case "Commentaire refuse" :
                        Toast.makeText(ValiderCommentaire.this, "Le Commentaire est refusé", Toast.LENGTH_LONG).show();
                        onBackPressed();
                        break;

                    case "Commentaire non refuse" :
                        Toast.makeText(ValiderCommentaire.this, "Probleme dans le refus de commentaire", Toast.LENGTH_LONG).show();
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
