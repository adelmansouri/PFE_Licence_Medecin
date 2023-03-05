package com.example.pfe;

import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
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
import java.util.Random;

public class MotDePasse_Oublier extends AppCompatActivity {

    EditText AdrMail_MdpOublier;
    ArrierePlan_MdpOublier AsyncTask_ArrierePlan_MdpOublier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mot_de_passe__oublier);

        //toolbar

        Toolbar toolbar_inscription = findViewById(R.id.toolbar_MdpOublier);
        setSupportActionBar(toolbar_inscription);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.transparent)));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        AdrMail_MdpOublier = findViewById(R.id.AdrMail_MdpOublier);


    }


    //toolbar return arrow
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }



    public void Envoyer_Email(View view){

        if (AdrMail_MdpOublier.getText().toString().trim().length() != 0){
            String Random_mdp = GenerateString(8);
            byte[] bRandom_Mdp = Random_mdp.getBytes();
            BigInteger bRandom_mdp_byte_data = null;

            try {
                bRandom_mdp_byte_data = new BigInteger(1,MD5.encryptMD5(bRandom_Mdp));
            } catch (Exception e) {
                e.printStackTrace();
            }

            String Random_mdp_Hash = bRandom_mdp_byte_data.toString();

            AsyncTask_ArrierePlan_MdpOublier = new ArrierePlan_MdpOublier();
            AsyncTask_ArrierePlan_MdpOublier.execute("ModifierMdp", AdrMail_MdpOublier.getText().toString(),Random_mdp,Random_mdp_Hash);


        }else{
            Toast.makeText(this, "Veuillez donner votre Email", Toast.LENGTH_LONG).show();
        }

    }


    private String GenerateString(int length){

        char[] Caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
        StringBuilder random_mdp = new StringBuilder();
        Random random = new Random();
        for (int i=0;i<length;i++){
            char c = Caracteres[random.nextInt(Caracteres.length)];
            random_mdp.append(c);
        }

        return random_mdp.toString();
    }


    class ArrierePlan_MdpOublier extends AsyncTask<String, Void, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            String Operation = strings[0];
            switch (Operation){
                case "ModifierMdp" :
                    try {
                        String AdrMail = strings[1];
                        String NewMdp = strings[2];
                        String NewMdpHash = strings[3];
                        String Url_MdpOublier = "http://192.168.1.9:8080/send_email.php";
                        URL MdpOublier_url = new URL(Url_MdpOublier);
                        HttpURLConnection mhttpURLConnection = (HttpURLConnection) MdpOublier_url.openConnection();
                        mhttpURLConnection.setRequestMethod("POST");
                        mhttpURLConnection.setDoOutput(true);
                        mhttpURLConnection.setDoInput(true);
                        OutputStreamWriter mOutPutStreamWriter = new OutputStreamWriter(mhttpURLConnection.getOutputStream());
                        BufferedWriter mBufferWriter = new BufferedWriter(mOutPutStreamWriter);
                        String Post_Data = URLEncoder.encode("AdrMail", "UTF-8") + "=" + URLEncoder.encode(AdrMail, "UTF-8")+"&"+
                                URLEncoder.encode("NewMdp", "UTF-8") + "=" + URLEncoder.encode(NewMdp, "UTF-8")+"&"+
                                URLEncoder.encode("NewMdpHash", "UTF-8") + "=" + URLEncoder.encode(NewMdpHash, "UTF-8");
                        mBufferWriter.write(Post_Data);
                        mBufferWriter.flush();
                        mBufferWriter.close();
                        mOutPutStreamWriter.close();
                        InputStreamReader mInputStreamReader = new InputStreamReader(mhttpURLConnection.getInputStream());
                        BufferedReader mBufferReader = new BufferedReader(mInputStreamReader);
                        String ligne = "";
                        String result = "";
                        while ((ligne = mBufferReader.readLine()) != null) {
                            result += ligne;
                        }
                        mBufferReader.close();
                        mInputStreamReader.close();
                        mhttpURLConnection.disconnect();

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

            JSONObject JOresultat = null;
            try {
                JOresultat = new JSONObject(result);
                String resultat = JOresultat.getString("resultat");
                switch (resultat){
                    case "Email Envoye" :
                        Toast.makeText(MotDePasse_Oublier.this, "Email Envoyé.", Toast.LENGTH_SHORT).show();
                        break;

                    case "Email non Envoye" :
                        Toast.makeText(MotDePasse_Oublier.this, "Erreur a l'envoi d'email.", Toast.LENGTH_LONG).show();
                        break;

                    case "Modification echoue" :
                        Toast.makeText(MotDePasse_Oublier.this, "Erreur l'operation est échouée", Toast.LENGTH_LONG).show();
                        break;

                    case "Adresse Courriel n'existe pas." :
                        Toast.makeText(MotDePasse_Oublier.this, "Adresse Courriel n'existe pas.", Toast.LENGTH_SHORT).show();
                        break;
                }





            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }
}
