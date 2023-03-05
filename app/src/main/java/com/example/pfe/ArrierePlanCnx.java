package com.example.pfe;

import android.app.Dialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class ArrierePlanCnx extends AsyncTask<String,Void,String> {

    private Dialog Dialog_Cmp_Des;
    String DesCmp_AdrMail;
    String DesCmp_MDP;
    Context mContext;
    public static final String ID = "com.exemple.pfe.EXTRA_ID";
    public static final String EXTRA_Operation = "com.exemple.pfe.EXTRA_Operation";
    JSONArray valeurs;
    Intent intent;
    ArrierePlanCnx mArrierePlanCnx;
    ProgressDialog DialogProgress;

    private void ShowDiag(){
        DialogProgress.setCancelable(false);
        DialogProgress.setTitle("Chargement");
        DialogProgress.setMessage("Veuillez patienter.");
        DialogProgress.show();
    }


    ArrierePlanCnx(Context Ctxt){
        mContext=Ctxt;
    }

//same
    @Override
    protected String doInBackground(String... params) {
        String Operation = params[0];
        switch(Operation){
            case "Connexion" :
                try {
                    String Cnx_URL = "http://192.168.1.9:8080/login_json.php";
                    String AdrMail = params[1];
                    String MDP = params[2];
                    DesCmp_AdrMail = AdrMail;
                    DesCmp_MDP = MDP;
                    String result="";
                    URL url = new URL(Cnx_URL);
                    HttpURLConnection mhttpUrlConnection = (HttpURLConnection) url.openConnection();
                    mhttpUrlConnection.setRequestMethod("POST");
                    mhttpUrlConnection.setDoInput(true);
                    mhttpUrlConnection.setDoOutput(true);
                    OutputStreamWriter mOutputStreamWriter = new OutputStreamWriter(mhttpUrlConnection.getOutputStream());
                    BufferedWriter bufferedWriter = new BufferedWriter(mOutputStreamWriter);
                    String Post_Data = URLEncoder.encode("AdrMail", "UTF-8")+"="+URLEncoder.encode(AdrMail, "UTF-8")+"&"+URLEncoder.encode("MDP", "UTF-8")+"="+URLEncoder.encode(MDP, "UTF-8");
                    bufferedWriter.write(Post_Data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    mOutputStreamWriter.close();
                    InputStreamReader mInputStreamReader = new InputStreamReader(mhttpUrlConnection.getInputStream());
                    BufferedReader mBufferReader = new BufferedReader(mInputStreamReader);
                    String ligne ;
                    while ((ligne = mBufferReader.readLine()) != null){
                        result += ligne;

                    }
                    mBufferReader.close();
                    mInputStreamReader.close();
                    mhttpUrlConnection.disconnect();
                    return result;



                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;



            case "Inscription" :
                try {
                    String Inscription_URL = "http://192.168.1.9:8080/inscription.php";
                    String Inscription = params[1];
                    String AdrMail = params[2];
                    String MDP = params[3];
                    String Nom = params[4];
                    String Prenom = params[5];
                    String DateDeNaissance = params[6];
                    String Sexe = params[7];
                    String NumTel = params[8];
                    String AdresseMdc = params[9];
                    String WilayaMdc = params[10];
                    String SpecialiteMdc = params[11];
                    String ImageString = params[12];

                    URL Inscription_url = new URL(Inscription_URL);
                    HttpURLConnection mhttpURLConnection = (HttpURLConnection) Inscription_url.openConnection();
                    mhttpURLConnection.setRequestMethod("POST");
                    mhttpURLConnection.setDoInput(true);
                    mhttpURLConnection.setDoOutput(true);
                    OutputStreamWriter mOutputStreamWriter = new OutputStreamWriter(mhttpURLConnection.getOutputStream());
                    BufferedWriter mBufferWriter = new BufferedWriter(mOutputStreamWriter);
                    String Post_Data = URLEncoder.encode("Inscription","UTF-8")+"="+URLEncoder.encode(Inscription,"UTF-8")+"&"+
                            URLEncoder.encode("AdrMail","UTF-8")+"="+URLEncoder.encode(AdrMail,"UTF-8")+"&"+
                            URLEncoder.encode("MDP","UTF-8")+"="+URLEncoder.encode(MDP,"UTF-8")+"&"+
                            URLEncoder.encode("Nom","UTF-8")+"="+URLEncoder.encode(Nom,"UTF-8")+"&"+
                            URLEncoder.encode("Prenom","UTF-8")+"="+URLEncoder.encode(Prenom,"UTF-8")+"&"+
                            URLEncoder.encode("DateDeNaissance","UTF-8")+"="+URLEncoder.encode(DateDeNaissance,"UTF-8")+"&"+
                            URLEncoder.encode("Sexe","UTF-8")+"="+URLEncoder.encode(Sexe,"UTF-8")+"&"+
                            URLEncoder.encode("NumTel","UTF-8")+"="+URLEncoder.encode(NumTel,"UTF-8")+"&"+
                            URLEncoder.encode("Adresse","UTF-8")+"="+URLEncoder.encode(AdresseMdc,"UTF-8")+"&"+
                            URLEncoder.encode("Wilaya","UTF-8")+"="+URLEncoder.encode(WilayaMdc,"UTF-8")+"&"+
                            URLEncoder.encode("Specialite","UTF-8")+"="+URLEncoder.encode(SpecialiteMdc,"UTF-8")+"&"+
                            URLEncoder.encode("ImageString","UTF-8")+"="+URLEncoder.encode(ImageString,"UTF-8");
                    mBufferWriter.write(Post_Data);
                    mBufferWriter.flush();
                    mBufferWriter.close();
                    mOutputStreamWriter.close();
                    InputStreamReader mInputStreamReader = new InputStreamReader(mhttpURLConnection.getInputStream());
                    BufferedReader mBufferReader = new BufferedReader(mInputStreamReader);
                    String result = "";
                    String ligne = "";
                    while((ligne = mBufferReader.readLine())!=null){
                        result += ligne;
                    }
                    mBufferReader.close();
                    mInputStreamReader.close();
                    mhttpURLConnection.disconnect();
                    return result;



                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "Activer" :
                try {
                    String Cnx_URL = "http://192.168.1.9:8080/Activer_Compte.php";
                    String AdrMail = params[1];
                    String MDP = params[2];
                    DesCmp_AdrMail = AdrMail;
                    DesCmp_MDP = MDP;
                    String result="";
                    URL url = new URL(Cnx_URL);
                    HttpURLConnection mhttpUrlConnection = (HttpURLConnection) url.openConnection();
                    mhttpUrlConnection.setRequestMethod("POST");
                    mhttpUrlConnection.setDoInput(true);
                    mhttpUrlConnection.setDoOutput(true);
                    OutputStreamWriter mOutputStreamWriter = new OutputStreamWriter(mhttpUrlConnection.getOutputStream());
                    BufferedWriter bufferedWriter = new BufferedWriter(mOutputStreamWriter);
                    String Post_Data = URLEncoder.encode("AdrMail", "UTF-8")+"="+URLEncoder.encode(AdrMail, "UTF-8");
                    bufferedWriter.write(Post_Data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    mOutputStreamWriter.close();
                    InputStreamReader mInputStreamReader = new InputStreamReader(mhttpUrlConnection.getInputStream());
                    BufferedReader mBufferReader = new BufferedReader(mInputStreamReader);
                    String ligne ;
                    while ((ligne = mBufferReader.readLine()) != null){
                        result += ligne;

                    }
                    mBufferReader.close();
                    mInputStreamReader.close();
                    mhttpUrlConnection.disconnect();
                    return result;



                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;


            default:
                Toast.makeText(mContext,"Operation échoué",Toast.LENGTH_LONG).show();

        }
        return null;
    }




    @Override
    protected void onPreExecute() {
        DialogProgress = new ProgressDialog(mContext,R.style.AppCompatAlertDialogStyle);
        ShowDiag();
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        try {
            DialogProgress.dismiss();
            JSONObject JOresultat = new JSONObject(result);
            String resultat = JOresultat.getString("resultat");
            switch (resultat){
                case "Utilisateur":
                    valeurs = JOresultat.getJSONArray("valeurs");
                    JSONObject Info_usr= valeurs.getJSONObject(0);
                    String Id = Info_usr.getString("IDCmpUsr");
                    intent = new Intent(mContext , Menu_Usr.class);
                    intent.putExtra(EXTRA_Operation,resultat);
                    intent.putExtra(ID, Id);
                    mContext.startActivity(intent);
                    break;

                case "Medecin":
                    valeurs = JOresultat.getJSONArray("valeurs");
                    JSONObject Info_Mdc= valeurs.getJSONObject(0);
                    String IDMdc = Info_Mdc.getString("IDMdc");
                    intent = new Intent(mContext , Menu_Mdc.class);
                    intent.putExtra(EXTRA_Operation,resultat);
                    intent.putExtra(ID, IDMdc);
                    mContext.startActivity(intent);
                    break;

                case "Admin" :
                    valeurs = JOresultat.getJSONArray("valeurs");
                    JSONObject Info_Adm= valeurs.getJSONObject(0);
                    String IdAdm = Info_Adm.getString("IDAdm");
                    intent = new Intent(mContext , Menu_Adm.class);
                    intent.putExtra(EXTRA_Operation,resultat);
                    intent.putExtra(ID, IdAdm);
                    mContext.startActivity(intent);


                    break;

                case "Compte Desactive" :
                    Dialog_Cmp_Des = new Dialog(mContext);
                    TextView Fermer_Cmp_Des;
                    TextView window_Cmp_Des_Msg_tv;
                    Button Window_Cmp_Des_Annuler;
                    Button Window_Cmp_Des_valider;

                    Dialog_Cmp_Des.setContentView(R.layout.window_demande_rdv_mdc);

                    Fermer_Cmp_Des = Dialog_Cmp_Des.findViewById(R.id.Fermer_Demande_RdvMdc);
                    window_Cmp_Des_Msg_tv = Dialog_Cmp_Des.findViewById(R.id.window_Demande_RdvMdc_Msg_tv);
                    Window_Cmp_Des_Annuler = Dialog_Cmp_Des.findViewById(R.id.Window_Demande_RdvMdc_Annuler);
                    Window_Cmp_Des_valider = Dialog_Cmp_Des.findViewById(R.id.Window_Demande_RdvMdc_valider);

                    String Msg = "Votre compte est Désactiver, est ce que vous voullez le réactiver ?";
                    window_Cmp_Des_Msg_tv.setText(Msg);

                    Fermer_Cmp_Des.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Dialog_Cmp_Des.dismiss();
                        }
                    });

                    Window_Cmp_Des_Annuler.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Dialog_Cmp_Des.dismiss();
                        }
                    });

                    Window_Cmp_Des_valider.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mArrierePlanCnx = new ArrierePlanCnx(mContext);
                            mArrierePlanCnx.execute("Activer",DesCmp_AdrMail,DesCmp_MDP);
                            /*while (mArrierePlanCnx.getStatus() != ArrierePlanCnx.Status.FINISHED) {

                            }*/

                            Dialog_Cmp_Des.dismiss();
                        }
                    });

                    Dialog_Cmp_Des.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                    Dialog_Cmp_Des.show();

                    break;

                case "Inscription avec succee" :
                    Toast.makeText(mContext,resultat,Toast.LENGTH_SHORT).show();
                    mContext.startActivity(new Intent(mContext, MainActivity.class));
                    break;

                case "Compte Activer" :
                    Toast.makeText(mContext, "Compte Activé", Toast.LENGTH_LONG).show();
                    /*if (mArrierePlanCnx.getStatus() != ArrierePlanCnx.Status.FINISHED) {
                        mArrierePlanCnx.cancel(true);
                    }*/

                    /*while (mArrierePlanCnx.getStatus() != ArrierePlanCnx.Status.FINISHED) {

                    }*/
                    mArrierePlanCnx = new ArrierePlanCnx(mContext);
                    mArrierePlanCnx.execute("Connexion",DesCmp_AdrMail,DesCmp_MDP);
                    break;

                case "Compte non Activer" :
                    Toast.makeText(mContext, "Problème dans l'activation du compte", Toast.LENGTH_LONG).show();
                    break;





                default:
                    Toast.makeText(mContext,"Operation échoué",Toast.LENGTH_SHORT).show();

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
