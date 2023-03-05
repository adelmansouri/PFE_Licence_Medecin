package com.example.pfe;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
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
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profil_Mdc_search extends AppCompatActivity {


    public static final String Adresse_Mdc_geo = "com.exemple.pfe.EXTRA_Adresse_Mdc_geo";
    Toolbar Toolbar_mdc_search;
    TextView Nom_Search_Mdc,Prenom_Search_Mdc,Specialite_Search_Mdc,AdresseCourriel_Search_Mdc,AdressePostale_Search_Mdc,Sexe_Search_Mdc,NumTel_Search_Mdc,NoCommentaire_SearchMdc,Window_Note_Mdc_tv;
    String Sexe_Mdc,ID_Mdc,ID_Usr;
    RelativeLayout ProgressBarSearchMdc;
    Dialog Dialog_envoi_msg_mdc,Dialog_Demande_rdv,Dialog_Ajouter_Commentaire,Dialog_Note_Mdc;
    CircleImageView Appeler_Mdc;
    private final static int Permission_Requete = 1;
    private ArrierePlanGetInfo_SearchMdc AsyncTask_ArrierePlan;
    TextView Worning_Commentaire_vide;
    ListView Liste_Commentaires;
    RatingBar RatingBar_SearchMdc;
    ScrollView ScrollView_search_mdc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil__mdc_search);


        //progressbar
        ProgressBarSearchMdc = findViewById(R.id.ProgressBarSearchMdc);

        //toolbar

        Toolbar_mdc_search = findViewById(R.id.toolbar_mdc_search);
        setSupportActionBar(Toolbar_mdc_search);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //information of MDC

        Intent intent = getIntent();
        ID_Mdc = intent.getStringExtra(Menu_Usr.ID_Mdc);
        ID_Usr = intent.getStringExtra(Menu_Usr.ID_Usr);
        Sexe_Mdc = intent.getStringExtra(Menu_Usr.Sexe_Mdc);

        //set photo MDC

        ImageView Photo_Mdc_H_F = (ImageView) findViewById(R.id.Photo_Mdc_H_F);

        if (Sexe_Mdc.equals("Homme")){
            Photo_Mdc_H_F.setImageResource(R.drawable.logo_medecin_recherche_homme);
        }
        else{
            Photo_Mdc_H_F.setImageResource(R.drawable.logo_medecin_recherche_femme);
        }

        //get Note

        AsyncTask_ArrierePlan = new ArrierePlanGetInfo_SearchMdc();
        AsyncTask_ArrierePlan.execute("getNote",ID_Mdc,ID_Usr);

        //recuperer les commentaires

        AsyncTask_ArrierePlan = new ArrierePlanGetInfo_SearchMdc();
        AsyncTask_ArrierePlan.execute("Commentaires",ID_Mdc);

        //Get Information Mdc

        AsyncTask_ArrierePlan = new ArrierePlanGetInfo_SearchMdc();
        AsyncTask_ArrierePlan.execute("Information",ID_Mdc);



        //envoi Msg window
        Dialog_envoi_msg_mdc = new Dialog(this);

        //Appeler medecin
        Appeler_Mdc = findViewById(R.id.Appeler_Mdc);
        Appeler_Mdc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Appeler_Mdc();
            }
        });

        //demander rdv
        Dialog_Demande_rdv = new Dialog(this);

        //Ajouter Commentaire

        Dialog_Ajouter_Commentaire = new Dialog(this);

        //Donner Note Mdc

        Dialog_Note_Mdc = new Dialog(this);
        RatingBar_SearchMdc = findViewById(R.id.Rating_bar_search_mdc);
        RatingBar_SearchMdc.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, final float rating, boolean fromUser) {
                Dialog_Note_Mdc.setContentView(R.layout.window_note_mdc);
                TextView Fermer_Note_Mdc = Dialog_Note_Mdc.findViewById(R.id.Fermer_Note_Mdc);
                Button Window_Note_Mdc_Confirmer = Dialog_Note_Mdc.findViewById(R.id.Window_Note_Mdc_Confirmer);
                Button Window_Note_Mdc_Annuler = Dialog_Note_Mdc.findViewById(R.id.Window_Note_Mdc_Annuler);
                Window_Note_Mdc_tv = Dialog_Note_Mdc.findViewById(R.id.window_note_mdc_tv);


                String S = "Voullez vous donnez la note "+rating+" pour ce Médecin ?";
                Window_Note_Mdc_tv.setText(S);


                Fermer_Note_Mdc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog_Note_Mdc.dismiss();
                    }
                });

                Window_Note_Mdc_Annuler.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog_Note_Mdc.dismiss();
                    }
                });

                Window_Note_Mdc_Confirmer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String note = ""+rating;
                        AsyncTask_ArrierePlan = new ArrierePlanGetInfo_SearchMdc();
                        AsyncTask_ArrierePlan.execute("setNote",ID_Mdc,ID_Usr,note);
                        Dialog_Note_Mdc.dismiss();
                    }
                });


                Dialog_Note_Mdc.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                Dialog_Note_Mdc.show();
            }
        });












    }


    //Icone envois MSg

    public void Envoi_Msg(View view){
        TextView Fermer_Envoi_Mdg;
        Button Envoiyer_Msg_btn;

        Dialog_envoi_msg_mdc.setContentView(R.layout.window_envoi_msg_mdc_search);

        Fermer_Envoi_Mdg = (TextView) Dialog_envoi_msg_mdc.findViewById(R.id.Fermer_Envoi_Msg);
        Envoiyer_Msg_btn = (Button) Dialog_envoi_msg_mdc.findViewById(R.id.Btn_Envoiyer_Msg);

        Fermer_Envoi_Mdg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_envoi_msg_mdc.dismiss();
            }
        });



        Envoiyer_Msg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText EditText_Envoi_Mdg = Dialog_envoi_msg_mdc.findViewById(R.id.EditText_Envoi_Msg);
                TextView TV_Envoi_Msg = Dialog_envoi_msg_mdc.findViewById(R.id.TV_Envoi_Msg);
                if (!EditText_Envoi_Mdg.getText().toString().trim().equals("")){
                    AsyncTask_ArrierePlan = new ArrierePlanGetInfo_SearchMdc();
                    AsyncTask_ArrierePlan.execute("Envoi_Msg",ID_Mdc,ID_Usr,EditText_Envoi_Mdg.getText().toString());
                    Dialog_envoi_msg_mdc.dismiss();
                }else{
                    TV_Envoi_Msg.setVisibility(View.VISIBLE);
                }

            }
        });

        Dialog_envoi_msg_mdc.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Dialog_envoi_msg_mdc.show();


    }

    //Appeler un medecin

    public void Appeler_Mdc(){
        String NumTel = NumTel_Search_Mdc.getText().toString();
        if (NumTel.trim().length() > 0){
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},Permission_Requete);

            }else{
                String Tel = "tel:"+NumTel;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(Tel)));
            }
        }else{
            Toast.makeText(this, "Erreur dans le numéro de téléphone", Toast.LENGTH_LONG).show();
        }



    }

    //verification de permission pour appeler
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == Permission_Requete){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Appeler_Mdc();
            }else{
                Toast.makeText(this, "L'application a besoin de votre permission", Toast.LENGTH_LONG).show();
            }
        }
    }





    //met un medecin au favoris

    public void Favoris_Mdc(View view){
        AsyncTask_ArrierePlan = new ArrierePlanGetInfo_SearchMdc();
        AsyncTask_ArrierePlan.execute("Favoris",ID_Mdc,ID_Usr);

    }

    public void GeoLocMdc(View view){
        Intent intent = new Intent(this, Google_Maps.class);
        intent.putExtra(Adresse_Mdc_geo,AdressePostale_Search_Mdc.getText().toString());
        this.startActivity(intent);
    }





    //demander un rendez-vous
    public void Demande_rdv(View view){
        TextView Fermer_Demande_rdv;
        Button Demande_rdv_Annuler;
        Button Demande_rdv_Confirmer;



        Dialog_Demande_rdv.setContentView(R.layout.window_demande_rdv);



        Fermer_Demande_rdv = (TextView) Dialog_Demande_rdv.findViewById(R.id.Fermer_Demande_rdv);
        Demande_rdv_Annuler = Dialog_Demande_rdv.findViewById(R.id.Window_Demande_rdv_annuler);
        Demande_rdv_Confirmer = Dialog_Demande_rdv.findViewById(R.id.Window_Demande_rdv_Confirmer);



        Fermer_Demande_rdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_Demande_rdv.dismiss();
            }
        });




        Demande_rdv_Annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_Demande_rdv.dismiss();
            }
        });



        Demande_rdv_Confirmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask_ArrierePlan = new ArrierePlanGetInfo_SearchMdc();
                AsyncTask_ArrierePlan.execute("Demande rendez vous",ID_Mdc,ID_Usr);
                Dialog_Demande_rdv.dismiss();

            }
        });






        Dialog_Demande_rdv.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Dialog_Demande_rdv.show();

    }


    public void AjouterCommentaire(View view){
        TextView Fermer_Ajouter_Commentaire;
        Button Ajouter_Commentaire_Annuler;
        Button Ajouter_Commentaire_Ajouter;
        EditText Window_Commentaire;




        Dialog_Ajouter_Commentaire.setContentView(R.layout.window_ajouter_commentaire);


        Fermer_Ajouter_Commentaire = Dialog_Ajouter_Commentaire.findViewById(R.id.Fermer_Ajouter_Commentaire);






        Fermer_Ajouter_Commentaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_Ajouter_Commentaire.dismiss();
            }
        });


        Ajouter_Commentaire_Annuler = Dialog_Ajouter_Commentaire.findViewById(R.id.Window_Ajouter_commentaire_Annuler);
        Ajouter_Commentaire_Annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_Ajouter_Commentaire.dismiss();
            }
        });



        Window_Commentaire = Dialog_Ajouter_Commentaire.findViewById(R.id.Window_Commentaire);
        final String Commentaire = Window_Commentaire.getText().toString();

        Ajouter_Commentaire_Ajouter = Dialog_Ajouter_Commentaire.findViewById(R.id.Window_Ajouter_commentaire_Ajouter);
        Ajouter_Commentaire_Ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Commentaire.trim().length() > 0){
                    AsyncTask_ArrierePlan = new ArrierePlanGetInfo_SearchMdc();
                    AsyncTask_ArrierePlan.execute("Ajouter un commentaire",ID_Mdc,ID_Usr,Commentaire);
                    Dialog_Ajouter_Commentaire.dismiss();
                }else{
                    Worning_Commentaire_vide = Dialog_Ajouter_Commentaire.findViewById(R.id.Worning_commentaire_vide);
                    Worning_Commentaire_vide.setVisibility(View.VISIBLE);
                }

            }
        });




        Dialog_Ajouter_Commentaire.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Dialog_Ajouter_Commentaire.show();

    }







    //toolbar return arrow
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }





    class ArrierePlanGetInfo_SearchMdc extends AsyncTask<String, Void, String>{

        @Override
        protected void onPreExecute() {
            ProgressBarSearchMdc.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            String Operation = strings[0];
            switch (Operation){
                case "Information" :
                    try {
                        String ID = strings[1];
                        String getInfo_SearchMdc = "http://192.168.1.9:8080/getInformation_SearchMdc.php";
                        URL url_getInfo_SearchMdc = new URL(getInfo_SearchMdc);
                        HttpURLConnection mhttpUrlConnection = (HttpURLConnection) url_getInfo_SearchMdc.openConnection();
                        mhttpUrlConnection.setRequestMethod("POST");
                        mhttpUrlConnection.setDoInput(true);
                        mhttpUrlConnection.setDoOutput(true);
                        OutputStreamWriter mOutputStreamWriter = new OutputStreamWriter(mhttpUrlConnection.getOutputStream());
                        BufferedWriter mBufferedWriter = new BufferedWriter(mOutputStreamWriter);
                        String Post_Data = URLEncoder.encode("ID","UTF-8")+"="+URLEncoder.encode(ID,"UTF-8");
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

                case "Envoi_Msg" :
                    try {
                        String IDMdc = strings[1];
                        String IDUsr = strings[2];
                        String Msg = strings[3];
                        String Envoi_Msg_Url = "http://192.168.1.9:8080/Envoi_Msg_MdcSearch.php";
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

                case "Favoris" :

                    try {
                        String IDMdc = strings[1];
                        String IDCmpUsr = strings[2];
                        String Ajout_Favoris_Url = "http://192.168.1.9:8080/Ajouter_mdc_favoris.php";
                        URL Url_Ajout_Favoris = new URL(Ajout_Favoris_Url);
                        HttpURLConnection mHttpUrlConnection = (HttpURLConnection) Url_Ajout_Favoris.openConnection();
                        mHttpUrlConnection.setRequestMethod("POST");
                        mHttpUrlConnection.setDoInput(true);
                        mHttpUrlConnection.setDoOutput(true);
                        OutputStreamWriter mOutputStreamWriter = new OutputStreamWriter(mHttpUrlConnection.getOutputStream());
                        BufferedWriter mBufferedWriter = new BufferedWriter(mOutputStreamWriter);
                        String Post_Data = URLEncoder.encode("IDMdc", "UTF-8")+"="+URLEncoder.encode(IDMdc,"UTF-8")+"&&"+
                                URLEncoder.encode("IDCmpUsr","UTF-8")+"="+URLEncoder.encode(IDCmpUsr,"UTF-8");
                        mBufferedWriter.write(Post_Data);
                        mBufferedWriter.flush();
                        mBufferedWriter.close();
                        mOutputStreamWriter.close();
                        InputStreamReader mInputStreamReader = new InputStreamReader(mHttpUrlConnection.getInputStream());
                        BufferedReader mBufferedReader = new BufferedReader(mInputStreamReader);
                        String result="";
                        String ligne="";
                        while ((ligne = mBufferedReader.readLine()) != null){
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


                case "Demande rendez vous" :
                    try {
                        String IDMdc = strings[1];
                        String IDCmpUsr = strings[2];
                        String Ajout_rdv_Url = "http://192.168.1.9:8080/Demande_rdv.php";
                        URL Url_Ajout_rdv = new URL(Ajout_rdv_Url);
                        HttpURLConnection mHttpUrlConnection = (HttpURLConnection) Url_Ajout_rdv.openConnection();
                        mHttpUrlConnection.setRequestMethod("POST");
                        mHttpUrlConnection.setDoInput(true);
                        mHttpUrlConnection.setDoOutput(true);
                        OutputStreamWriter mOutputStreamWriter = new OutputStreamWriter(mHttpUrlConnection.getOutputStream());
                        BufferedWriter mBufferedWriter = new BufferedWriter(mOutputStreamWriter);
                        String Post_Data = URLEncoder.encode("IDMdc", "UTF-8")+"="+URLEncoder.encode(IDMdc,"UTF-8")+"&&"+
                                URLEncoder.encode("IDCmpUsr","UTF-8")+"="+URLEncoder.encode(IDCmpUsr,"UTF-8");
                        mBufferedWriter.write(Post_Data);
                        mBufferedWriter.flush();
                        mBufferedWriter.close();
                        mOutputStreamWriter.close();
                        InputStreamReader mInputStreamReader = new InputStreamReader(mHttpUrlConnection.getInputStream());
                        BufferedReader mBufferedReader = new BufferedReader(mInputStreamReader);
                        String result="";
                        String ligne="";
                        while ((ligne = mBufferedReader.readLine()) != null){
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

                case "Ajouter un commentaire" :
                    try {
                        String IDMdc = strings[1];
                        String IDCmpUsr = strings[2];
                        String Commentaire = strings[3];
                        String Ajouter_Commentaire_Url = "http://192.168.1.9:8080/Ajouter_Commentaire.php";
                        URL Url_Ajout_commentaire = new URL(Ajouter_Commentaire_Url);
                        HttpURLConnection mHttpUrlConnection = (HttpURLConnection) Url_Ajout_commentaire.openConnection();
                        mHttpUrlConnection.setRequestMethod("POST");
                        mHttpUrlConnection.setDoInput(true);
                        mHttpUrlConnection.setDoOutput(true);
                        OutputStreamWriter mOutputStreamWriter = new OutputStreamWriter(mHttpUrlConnection.getOutputStream());
                        BufferedWriter mBufferedWriter = new BufferedWriter(mOutputStreamWriter);
                        String Post_Data = URLEncoder.encode("IDMdc", "UTF-8")+"="+URLEncoder.encode(IDMdc,"UTF-8")+"&&"+
                                URLEncoder.encode("IDCmpUsr","UTF-8")+"="+URLEncoder.encode(IDCmpUsr,"UTF-8")+"&&"+
                                URLEncoder.encode("Commentaire", "UTF-8")+"="+URLEncoder.encode(Commentaire, "UTF-8");
                        mBufferedWriter.write(Post_Data);
                        mBufferedWriter.flush();
                        mBufferedWriter.close();
                        mOutputStreamWriter.close();
                        InputStreamReader mInputStreamReader = new InputStreamReader(mHttpUrlConnection.getInputStream());
                        BufferedReader mBufferedReader = new BufferedReader(mInputStreamReader);
                        String result="";
                        String ligne="";
                        while ((ligne = mBufferedReader.readLine()) != null){
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

                case "Commentaires" :
                    try {
                        String IDMdc = strings[1];
                        String Commentaire_Url = "http://192.168.1.9:8080/getCommentaire_mdcSearch.php";
                        URL Url_Commentaire = new URL(Commentaire_Url);
                        HttpURLConnection mHttpUrlConnection = (HttpURLConnection) Url_Commentaire.openConnection();
                        mHttpUrlConnection.setRequestMethod("POST");
                        mHttpUrlConnection.setDoInput(true);
                        mHttpUrlConnection.setDoOutput(true);
                        OutputStreamWriter mOutputStreamWriter = new OutputStreamWriter(mHttpUrlConnection.getOutputStream());
                        BufferedWriter mBufferedWriter = new BufferedWriter(mOutputStreamWriter);
                        String Post_Data = URLEncoder.encode("IDMdc", "UTF-8")+"="+URLEncoder.encode(IDMdc,"UTF-8");
                        mBufferedWriter.write(Post_Data);
                        mBufferedWriter.flush();
                        mBufferedWriter.close();
                        mOutputStreamWriter.close();
                        InputStreamReader mInputStreamReader = new InputStreamReader(mHttpUrlConnection.getInputStream());
                        BufferedReader mBufferedReader = new BufferedReader(mInputStreamReader);
                        String result="";
                        String ligne="";
                        while ((ligne = mBufferedReader.readLine()) != null){
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

                case "setNote" :
                    try {
                        String IDMdc = strings[1];
                        String IDCmpUsr = strings[2];
                        String Note = strings[3];
                        String setNote_Url = "http://192.168.1.9:8080/setNote_MdcSearch.php";
                        URL Url_setNote = new URL(setNote_Url);
                        HttpURLConnection mHttpUrlConnection = (HttpURLConnection) Url_setNote.openConnection();
                        mHttpUrlConnection.setRequestMethod("POST");
                        mHttpUrlConnection.setDoInput(true);
                        mHttpUrlConnection.setDoOutput(true);
                        OutputStreamWriter mOutputStreamWriter = new OutputStreamWriter(mHttpUrlConnection.getOutputStream());
                        BufferedWriter mBufferedWriter = new BufferedWriter(mOutputStreamWriter);
                        String Post_Data = URLEncoder.encode("IDMdc", "UTF-8")+"="+URLEncoder.encode(IDMdc,"UTF-8")+"&&"+
                                URLEncoder.encode("IDCmpUsr", "UTF-8")+"="+URLEncoder.encode(IDCmpUsr,"UTF-8")+"&&"+
                                URLEncoder.encode("Note", "UTF-8")+"="+URLEncoder.encode(Note,"UTF-8");
                        mBufferedWriter.write(Post_Data);
                        mBufferedWriter.flush();
                        mBufferedWriter.close();
                        mOutputStreamWriter.close();
                        InputStreamReader mInputStreamReader = new InputStreamReader(mHttpUrlConnection.getInputStream());
                        BufferedReader mBufferedReader = new BufferedReader(mInputStreamReader);
                        String result="";
                        String ligne="";
                        while ((ligne = mBufferedReader.readLine()) != null){
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

                case "getNote" :
                    try {
                        String IDMdc = strings[1];
                        String IDCmpUsr = strings[2];
                        String getNote_Url = "http://192.168.1.9:8080/getNote_SearchMdc.php";
                        URL Url_getNote = new URL(getNote_Url);
                        HttpURLConnection mHttpUrlConnection = (HttpURLConnection) Url_getNote.openConnection();
                        mHttpUrlConnection.setRequestMethod("POST");
                        mHttpUrlConnection.setDoInput(true);
                        mHttpUrlConnection.setDoOutput(true);
                        OutputStreamWriter mOutputStreamWriter = new OutputStreamWriter(mHttpUrlConnection.getOutputStream());
                        BufferedWriter mBufferedWriter = new BufferedWriter(mOutputStreamWriter);
                        String Post_Data = URLEncoder.encode("IDMdc", "UTF-8")+"="+URLEncoder.encode(IDMdc,"UTF-8")+"&&"+
                                URLEncoder.encode("IDCmpUsr", "UTF-8")+"="+URLEncoder.encode(IDCmpUsr,"UTF-8");
                        mBufferedWriter.write(Post_Data);
                        mBufferedWriter.flush();
                        mBufferedWriter.close();
                        mOutputStreamWriter.close();
                        InputStreamReader mInputStreamReader = new InputStreamReader(mHttpUrlConnection.getInputStream());
                        BufferedReader mBufferedReader = new BufferedReader(mInputStreamReader);
                        String result="";
                        String ligne="";
                        while ((ligne = mBufferedReader.readLine()) != null){
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
            ProgressBarSearchMdc.setVisibility(View.GONE);
            try {
                JSONObject JOresultat = new JSONObject(result);
                String resultat = JOresultat.getString("resultat");

                switch (resultat){
                    case "Infomation recupere" :
                        JSONArray valeurs = JOresultat.getJSONArray("valeurs");
                        Nom_Search_Mdc = findViewById(R.id.Nom_Search_Mdc);
                        Prenom_Search_Mdc = findViewById(R.id.Prenom_Search_Mdc);
                        Specialite_Search_Mdc = findViewById(R.id.Specialité_search_mdc);
                        AdresseCourriel_Search_Mdc = findViewById(R.id.AdresseCourriel_Search_Mdc);
                        AdressePostale_Search_Mdc = findViewById(R.id.AdressePostale_Search_Mdc);
                        Sexe_Search_Mdc = findViewById(R.id.Sexe_Search_Mdc);
                        NumTel_Search_Mdc = findViewById(R.id.NumTel_Search_Mdc);

                        JSONObject Info_Search_Mdc = valeurs.getJSONObject(0);

                        Nom_Search_Mdc.setText(Info_Search_Mdc.getString("NomUsr"));
                        Prenom_Search_Mdc.setText(Info_Search_Mdc.getString("PrenomUsr"));
                        Specialite_Search_Mdc.setText(Info_Search_Mdc.getString("SpecialiteMdc"));
                        AdresseCourriel_Search_Mdc.setText(Info_Search_Mdc.getString("AdrMail"));
                        String Adresse_Postale = Info_Search_Mdc.getString("AdrMdc")+", "+Info_Search_Mdc.getString("WilayaMdc");
                        AdressePostale_Search_Mdc.setText(Adresse_Postale);
                        Sexe_Search_Mdc.setText(Sexe_Mdc);
                        String NumTel = "0"+ Info_Search_Mdc.getString("NumTelUsr");
                        NumTel_Search_Mdc.setText(NumTel);

                        break;

                    case "Message envoyer" :
                        Toast.makeText(Profil_Mdc_search.this, "Message Envoyé", Toast.LENGTH_LONG).show();
                        break;

                    case "Message non envoyer" :
                        Toast.makeText(Profil_Mdc_search.this, "Problème dans l'envoi de message", Toast.LENGTH_LONG).show();
                        break;

                    case "Medecin Ajouter aux favoris" :
                        Toast.makeText(Profil_Mdc_search.this, "Médecin Ajouté aux favoris", Toast.LENGTH_LONG).show();

                        break;

                    case "Medecin non Ajouter aux favoris" :
                        Toast.makeText(Profil_Mdc_search.this, "Médecin déja dans la liste Favoris", Toast.LENGTH_LONG).show();
                        break;

                    case "rendez-vous Ajouter" :
                        Toast.makeText(Profil_Mdc_search.this, "Demande de rendez vous envoyée", Toast.LENGTH_LONG).show();

                        break;

                    case "rendez-vous non Ajouter" :
                        Toast.makeText(Profil_Mdc_search.this, "Vous Avez Déja un rendez vous", Toast.LENGTH_LONG).show();
                        break;

                    case "Commentaire Ajouter" :
                        Toast.makeText(Profil_Mdc_search.this, "Commentaire Ajouté", Toast.LENGTH_LONG).show();

                        break;

                    case "liste Commentaire recuperer" :
                        valeurs = JOresultat.getJSONArray("valeurs");
                        final ArrayList<Commentaires> Commentaire_Liste = new ArrayList<>();
                        Liste_Commentaires = findViewById(R.id.Liste_Commentaire);
                        Liste_Commentaires.setVisibility(View.VISIBLE);
                        NoCommentaire_SearchMdc = findViewById(R.id.NoCommentaire_SearchMdc);
                        NoCommentaire_SearchMdc.setVisibility(View.GONE);
                        for (int i=0;i<valeurs.length();i++){
                            JSONObject Commentaire = valeurs.getJSONObject(i);
                            String NomPrenom_UsrComm = Commentaire.getString("NomUsr")+" "+Commentaire.getString("PrenomUsr");
                            String Contenue = Commentaire.getString("ContenueCom");
                            Commentaires Comm = new Commentaires(NomPrenom_UsrComm,Contenue);
                            Commentaire_Liste.add(Comm);
                        }


                        AdapterCommentaire mAdapterCommentaire = new AdapterCommentaire(getApplicationContext(), R.layout.adapter_commentaires,Commentaire_Liste);
                        Liste_Commentaires.setAdapter(mAdapterCommentaire);

                        ScrollView_search_mdc = findViewById(R.id.ScrollView_search_mdc);
                        ScrollView_search_mdc.scrollTo(0,0);


                        break;

                    case "liste Commentaire non recuperer" :
                        Liste_Commentaires = findViewById(R.id.Liste_Commentaire);
                        Liste_Commentaires.setVisibility(View.GONE);
                        NoCommentaire_SearchMdc = findViewById(R.id.NoCommentaire_SearchMdc);
                        NoCommentaire_SearchMdc.setVisibility(View.VISIBLE);

                        break;

                    case "Note Modifier" :
                        Toast.makeText(Profil_Mdc_search.this, "La Note a été modifiée", Toast.LENGTH_LONG).show();
                        break;

                    case "Note Ajouter" :
                        Toast.makeText(Profil_Mdc_search.this, "La Note a été ajoutée", Toast.LENGTH_LONG).show();
                        break;

                    case "Note recuperer" :
                        valeurs = JOresultat.getJSONArray("valeurs");
                        JSONObject Note = valeurs.getJSONObject(0);
                        String Note_Mdc = Note.getString("Note");
                        float Note_Mdc_float = Float.parseFloat(Note_Mdc);
                        RatingBar_SearchMdc.setRating(Note_Mdc_float);
                        Dialog_Note_Mdc.dismiss();
                        break;

                }







            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
