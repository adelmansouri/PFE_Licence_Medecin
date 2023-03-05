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
import android.widget.Button;
import android.widget.EditText;
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

public class Message_ProfilMdc extends AppCompatActivity {

    TextView NomPrenomMdc_Msg,SpecialiteMdc_Msg,Contenue_Msg,Date_Msg,Heure_Msg;
    String IDUsr_MessageMdc,IDMdc_MessageMdc;
    Dialog Dialog_RepondreMsg;
    ArrierePlan_Tasks Tasks;
    RelativeLayout ProgressBarMenuUsr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message__profil_mdc);

        ProgressBarMenuUsr = findViewById(R.id.ProgressBarMenuUsr);

        //toolbar

        Toolbar toolbar_Message = findViewById(R.id.toolbar_Message);
        setSupportActionBar(toolbar_Message);
        getSupportActionBar().setTitle("Message");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.gradStop)));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NomPrenomMdc_Msg = findViewById(R.id.Nom_Prenom_MessageMdc);
        Contenue_Msg = findViewById(R.id.Contenue_Msg);
        Date_Msg = findViewById(R.id.Date_Msg);
        Heure_Msg = findViewById(R.id.Heure_Msg);

        //get Information
        Intent intent = getIntent();
        String NomPrenomMdc_MessageMdc = intent.getStringExtra(Menu_Mdc.NomPrenomMdc_Msg);
        //String SpeacialiteMdc_MessageMdc = intent.getStringExtra(Menu_Usr.SpecialiteMdc_Msg);
        String Contenue_MessageMdc = intent.getStringExtra(Menu_Mdc.Contenue_Msg);
        String Date_MessageMdc = intent.getStringExtra(Menu_Mdc.Date_Msg);
        String Heure_MessageMdc = intent.getStringExtra(Menu_Mdc.Heure_Msg);
        IDUsr_MessageMdc = intent.getStringExtra(Menu_Mdc.IDUsr_Msg);
        IDMdc_MessageMdc = intent.getStringExtra(Menu_Mdc.IDMdc_Msg);

        NomPrenomMdc_Msg.setText(NomPrenomMdc_MessageMdc);
        Contenue_Msg.setText(Contenue_MessageMdc);
        Date_Msg.setText(Date_MessageMdc);
        Heure_Msg.setText(Heure_MessageMdc);


        //repondre aux messages
        Dialog_RepondreMsg = new Dialog(this);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void RepondreAuMsg(View view){
        Dialog_RepondreMsg.setContentView(R.layout.window_envoi_msg_mdc_search);
        TextView Fermer_Envoi_Mdg;
        Button Envoiyer_Msg_btn;
        Fermer_Envoi_Mdg = (TextView) Dialog_RepondreMsg.findViewById(R.id.Fermer_Envoi_Msg);
        Envoiyer_Msg_btn = (Button) Dialog_RepondreMsg.findViewById(R.id.Btn_Envoiyer_Msg);

        Fermer_Envoi_Mdg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_RepondreMsg.dismiss();
            }
        });

        Envoiyer_Msg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText EditText_Envoi_Mdg = Dialog_RepondreMsg.findViewById(R.id.EditText_Envoi_Msg);
                TextView TV_Envoi_Msg = Dialog_RepondreMsg.findViewById(R.id.TV_Envoi_Msg);
                if (!EditText_Envoi_Mdg.getText().toString().trim().equals("")){
                    Tasks = new ArrierePlan_Tasks();
                    Tasks.execute("RepondreMsg",IDMdc_MessageMdc,IDUsr_MessageMdc,EditText_Envoi_Mdg.getText().toString());
                    Dialog_RepondreMsg.dismiss();
                }else{
                    TV_Envoi_Msg.setVisibility(View.VISIBLE);
                }

            }
        });

        Dialog_RepondreMsg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Dialog_RepondreMsg.show();

    }


    class ArrierePlan_Tasks extends AsyncTask<String, Void, String>{

        @Override
        protected void onPreExecute() {
            ProgressBarMenuUsr.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            String Operation = strings[0];
            switch (Operation){
                case "RepondreMsg" :
                    try {
                        String IDMdc = strings[1];
                        String IDUsr = strings[2];
                        String Msg = strings[3];
                        String Envoi_Msg_Url = "http://192.168.1.9:8080/RepondMsg_Mdc.php";
                        URL url_Envoi_Msg = new URL(Envoi_Msg_Url);
                        HttpURLConnection mhttpUrlConnection = (HttpURLConnection) url_Envoi_Msg.openConnection();
                        mhttpUrlConnection.setRequestMethod("POST");
                        mhttpUrlConnection.setDoInput(true);
                        mhttpUrlConnection.setDoOutput(true);
                        OutputStreamWriter mOutputStreamWriter = new OutputStreamWriter(mhttpUrlConnection.getOutputStream());
                        BufferedWriter mBufferedWriter = new BufferedWriter(mOutputStreamWriter);
                        String Post_Data = URLEncoder.encode("IDMdc","UTF-8")+"="+URLEncoder.encode(IDMdc,"UTF-8")+"&&"+
                                URLEncoder.encode("IDUsr","UTF-8")+"="+URLEncoder.encode(IDUsr,"UTF-8")+"&&"+
                                URLEncoder.encode("Msg","UTF-8")+"="+URLEncoder.encode(Msg,"UTF-8");
                        mBufferedWriter.write(Post_Data);
                        mBufferedWriter.close();
                        mOutputStreamWriter.close();
                        InputStreamReader mInputStreamReader = new InputStreamReader(mhttpUrlConnection.getInputStream());
                        BufferedReader mBufferedReader = new BufferedReader(mInputStreamReader);
                        String result = "";
                        String ligne = "";
                        while((ligne = mBufferedReader.readLine()) != null){
                            result += ligne;
                        }

                        mBufferedReader.close();
                        mInputStreamReader.close();
                        mhttpUrlConnection.disconnect();

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
            try{
                ProgressBarMenuUsr.setVisibility(View.GONE);


                JSONObject JOresultat = new JSONObject(result);
                String resultat = JOresultat.getString("resultat");

                switch (resultat){
                    case "Message envoyer":
                        Toast.makeText(Message_ProfilMdc.this, "Message envoyé", Toast.LENGTH_LONG).show();
                        break;

                    case "Message non envoyer":
                        Toast.makeText(Message_ProfilMdc.this, "Problème dans l'envoi de message", Toast.LENGTH_LONG).show();
                        break;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


}
