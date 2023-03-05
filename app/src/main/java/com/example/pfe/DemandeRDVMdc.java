package com.example.pfe;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
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
import java.text.DateFormat;
import java.util.Calendar;

public class DemandeRDVMdc extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    TextView Demande_RdvMdc_MNomPrenom,CliquerIci_HeureDemRdvMdc,Date_DemRdvMdc,CliquerIci_DateDemRdvMdc,Heure_DemRdvMdc;
    Intent intent;
    String IDRdv_DemandeRDVMdc,NomUsr_DemandeRDVMdc,PrenomUsr_DemandeRDVMdc,SexeUsr_DemandeRDVMdc;
    Dialog Dialog_valider_rdv,Dialog_refuser_rdv;
    String DateSelected = "",HeureSelected = "";

    ArrierePlanInfo_DemRdv mArrierePlanInfo_DemRdv;
    String y,m,d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demande_rdvmdc);

        Toolbar toolbar_Demande_RdvMdc = findViewById(R.id.toolbar_Demande_RdvMdc);
        setSupportActionBar(toolbar_Demande_RdvMdc);
        getSupportActionBar().setTitle("Demande de rendez-vous");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.gradStop)));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //get Information
        intent = getIntent();
        IDRdv_DemandeRDVMdc = intent.getStringExtra(Menu_Mdc.IDRdv_DemandeRDVMdc);
        NomUsr_DemandeRDVMdc = intent.getStringExtra(Menu_Mdc.NomUsr_DemandeRDVMdc);
        PrenomUsr_DemandeRDVMdc = intent.getStringExtra(Menu_Mdc.PrenomUsr_DemandeRDVMdc);
        SexeUsr_DemandeRDVMdc = intent.getStringExtra(Menu_Mdc.SexeUsr_DemandeRDVMdc);

        //initialisation

        Demande_RdvMdc_MNomPrenom = findViewById(R.id.Demande_RdvMdc_MNomPrenom);
        CliquerIci_HeureDemRdvMdc = findViewById(R.id.CliquerIci_HeureDemRdvMdc);
        Date_DemRdvMdc = findViewById(R.id.Date_DemRdvMdc);
        CliquerIci_DateDemRdvMdc = findViewById(R.id.CliquerIci_DateDemRdvMdc);
        Heure_DemRdvMdc = findViewById(R.id.Heure_DemRdvMdc);
        String MNomPrenom = "";

        if (SexeUsr_DemandeRDVMdc.equals("Homme")){
            MNomPrenom = "Monsieur : "+NomUsr_DemandeRDVMdc+" "+PrenomUsr_DemandeRDVMdc;
        }else{
            MNomPrenom = "Madame : "+NomUsr_DemandeRDVMdc+" "+PrenomUsr_DemandeRDVMdc;
        }


        Demande_RdvMdc_MNomPrenom.setText(MNomPrenom);
        String CliqueIci_Date = "<u> CliquerIci pour ajouter la date de rendez-vous </u>";
        String CliqueIci_Heure = "<u> CliquerIci pour ajouter l'heure de rendez-vous </u>";
        CliquerIci_DateDemRdvMdc.setText(Html.fromHtml(CliqueIci_Date));
        CliquerIci_HeureDemRdvMdc.setText(Html.fromHtml(CliqueIci_Heure));


        //valider dem rdv
        Dialog_valider_rdv = new Dialog(this);


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    public void Date_DemRDVMdc(View view){
        DialogFragment datePicker = new DatePickerFragement();
        datePicker.show(getSupportFragmentManager(), "Date Picker");

    }

    public void Heure_DemRDVMdc(View view){
        DialogFragment TimePicker = new TimePickerFragement();
        TimePicker.show(getSupportFragmentManager(),"Time picker");

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        y= Integer.toString(year);
        m= Integer.toString(month);
        m=m+1;
        d= Integer.toString(dayOfMonth);
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        DateSelected = DateFormat.getDateInstance().format(c.getTime());

        Date_DemRdvMdc.setText(DateSelected);

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        HeureSelected = hourOfDay+":"+minute;
        Heure_DemRdvMdc.setText(HeureSelected);
    }

    public void Valider_rendez_vous(View view){
        TextView Fermer_Demande_RdvMdc;
        TextView window_Demande_RdvMdc_Msg_tv;
        Button Window_Demande_RdvMdc_Annuler;
        Button Window_Demande_RdvMdc_valider;


        Dialog_valider_rdv.setContentView(R.layout.window_demande_rdv_mdc);

        Fermer_Demande_RdvMdc = Dialog_valider_rdv.findViewById(R.id.Fermer_Demande_RdvMdc);
        window_Demande_RdvMdc_Msg_tv = Dialog_valider_rdv.findViewById(R.id.window_Demande_RdvMdc_Msg_tv);
        Window_Demande_RdvMdc_Annuler = Dialog_valider_rdv.findViewById(R.id.Window_Demande_RdvMdc_Annuler);
        Window_Demande_RdvMdc_valider = Dialog_valider_rdv.findViewById(R.id.Window_Demande_RdvMdc_valider);

        String Msg = "Voullez vous vraiment valider le rendez-vous le "+DateSelected+" à "+HeureSelected+" ?";
        window_Demande_RdvMdc_Msg_tv.setText(Msg);

        Fermer_Demande_RdvMdc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_valider_rdv.dismiss();
            }
        });

        Window_Demande_RdvMdc_Annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_valider_rdv.dismiss();
            }
        });


        Window_Demande_RdvMdc_valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mArrierePlanInfo_DemRdv = new ArrierePlanInfo_DemRdv();
                mArrierePlanInfo_DemRdv.execute("Valider Demande RDVMdc", IDRdv_DemandeRDVMdc, HeureSelected, y, m, d);
                Dialog_valider_rdv.dismiss();

            }
        });




        Dialog_valider_rdv.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Dialog_valider_rdv.show();

    }

    public void Refuser_rendez_vous(View view){
        TextView Fermer_Demande_RdvMdc;
        TextView window_Demande_RdvMdc_Msg_tv;
        Button Window_Demande_RdvMdc_Annuler;
        Button Window_Demande_RdvMdc_valider;


        Dialog_refuser_rdv.setContentView(R.layout.window_demande_rdv_mdc);

        Fermer_Demande_RdvMdc = Dialog_refuser_rdv.findViewById(R.id.Fermer_Demande_RdvMdc);
        window_Demande_RdvMdc_Msg_tv = Dialog_refuser_rdv.findViewById(R.id.window_Demande_RdvMdc_Msg_tv);
        Window_Demande_RdvMdc_Annuler = Dialog_refuser_rdv.findViewById(R.id.Window_Demande_RdvMdc_Annuler);
        Window_Demande_RdvMdc_valider = Dialog_refuser_rdv.findViewById(R.id.Window_Demande_RdvMdc_valider);

        String Msg = "Voullez vous vraiment refuser le rendez-vous ?";
        window_Demande_RdvMdc_Msg_tv.setText(Msg);

        Fermer_Demande_RdvMdc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_refuser_rdv.dismiss();
            }
        });

        Window_Demande_RdvMdc_Annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_refuser_rdv.dismiss();
            }
        });


        Window_Demande_RdvMdc_valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mArrierePlanInfo_DemRdv = new ArrierePlanInfo_DemRdv();
                mArrierePlanInfo_DemRdv.execute("refuser Demande RDVMdc", IDRdv_DemandeRDVMdc);
                Dialog_valider_rdv.dismiss();
            }
        });




        Dialog_valider_rdv.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Dialog_valider_rdv.show();

    }



    class ArrierePlanInfo_DemRdv extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            String Operation = strings[0];
            switch (Operation) {
                case "Valider Demande RDVMdc":
                    try {
                        String IDRdv = strings[1];
                        String HeureSelected = strings[2];
                        String year = strings[3];
                        String month = strings[4];
                        String day = strings[5];
                        String validerDemRdvMdc_Url = "http://192.168.1.9:8080/valider_DemRdvMdc.php";
                        URL url_ValiderDemRdvMdc = new URL(validerDemRdvMdc_Url);
                        HttpURLConnection mhttpUrlConnection = (HttpURLConnection) url_ValiderDemRdvMdc.openConnection();
                        mhttpUrlConnection.setRequestMethod("POST");
                        mhttpUrlConnection.setDoInput(true);
                        mhttpUrlConnection.setDoOutput(true);
                        OutputStreamWriter mOutputStreamWriter = new OutputStreamWriter(mhttpUrlConnection.getOutputStream());
                        BufferedWriter mBufferedWriter = new BufferedWriter(mOutputStreamWriter);
                        String Post_Data = URLEncoder.encode("IDRdv", "UTF-8") + "=" + URLEncoder.encode(IDRdv, "UTF-8") + "&&" +
                                URLEncoder.encode("HeureSelected", "UTF-8") + "=" + URLEncoder.encode(HeureSelected, "UTF-8") + "&&" +
                                URLEncoder.encode("year", "UTF-8") + "=" + URLEncoder.encode(year, "UTF-8") + "&&" +
                                URLEncoder.encode("month", "UTF-8") + "=" + URLEncoder.encode(month, "UTF-8") + "&&" +
                                URLEncoder.encode("day", "UTF-8") + "=" + URLEncoder.encode(day, "UTF-8");
                        mBufferedWriter.write(Post_Data);
                        mBufferedWriter.close();
                        mOutputStreamWriter.close();
                        InputStreamReader mInputStreamReader = new InputStreamReader(mhttpUrlConnection.getInputStream());
                        BufferedReader mBufferedReader = new BufferedReader(mInputStreamReader);
                        String result = "";
                        String ligne = "";
                        while ((ligne = mBufferedReader.readLine()) != null) {
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

                case "refuser Demande RDVMdc":
                    try {
                        String IDRdv = strings[1];
                        String RefuserDemRdvMdc_Url = "http://192.168.1.9:8080/Refuser_DemRdvMdc.php";
                        URL url_RefuserDemRdvMdc = new URL(RefuserDemRdvMdc_Url);
                        HttpURLConnection mhttpUrlConnection = (HttpURLConnection) url_RefuserDemRdvMdc.openConnection();
                        mhttpUrlConnection.setRequestMethod("POST");
                        mhttpUrlConnection.setDoInput(true);
                        mhttpUrlConnection.setDoOutput(true);
                        OutputStreamWriter mOutputStreamWriter = new OutputStreamWriter(mhttpUrlConnection.getOutputStream());
                        BufferedWriter mBufferedWriter = new BufferedWriter(mOutputStreamWriter);
                        String Post_Data = URLEncoder.encode("IDRdv", "UTF-8") + "=" + URLEncoder.encode(IDRdv, "UTF-8");
                        mBufferedWriter.write(Post_Data);
                        mBufferedWriter.close();
                        mOutputStreamWriter.close();
                        InputStreamReader mInputStreamReader = new InputStreamReader(mhttpUrlConnection.getInputStream());
                        BufferedReader mBufferedReader = new BufferedReader(mInputStreamReader);
                        String result = "";
                        String ligne = "";
                        while ((ligne = mBufferedReader.readLine()) != null) {
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
            try {
                JSONObject JOresultat = new JSONObject(result);
                String resultat = JOresultat.getString("resultat");

                switch (resultat) {
                    case "demande de rdv valide":
                        Toast.makeText(DemandeRDVMdc.this, "Rendez-vous validé", Toast.LENGTH_LONG).show();
                        onBackPressed();
                        break;

                    case "demande de rdv non valide" :
                        Toast.makeText(DemandeRDVMdc.this, "Probleme dans la validation de rendez-vous.", Toast.LENGTH_LONG).show();
                        break;

                    case "demande de rdv refuse":
                        Toast.makeText(DemandeRDVMdc.this, "Rendez-vous refusé", Toast.LENGTH_LONG).show();
                        onBackPressed();
                        break;

                    case "demande de rdv non refuse" :
                        Toast.makeText(DemandeRDVMdc.this, "Probleme dans le refus de rendez-vous", Toast.LENGTH_LONG).show();
                        break;


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
