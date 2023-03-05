package com.example.pfe;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;


public class RDV_Profil extends AppCompatActivity {

    TextView Nom_Prenom_MdcRdv,SpecialiteMdc_Rdv,Date_Rdv_Mdc,Heure_Rdv_Mdc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rdv__profil);

        Toolbar toolbar_RDV_valide = findViewById(R.id.toolbar_RDV_Validé);
        setSupportActionBar(toolbar_RDV_valide);
        getSupportActionBar().setTitle("Rendez-Vous");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.gradStart)));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Nom_Prenom_MdcRdv = findViewById(R.id.Nom_Prenom_MdcRdv);
        SpecialiteMdc_Rdv = findViewById(R.id.SpecialiteMdc_Rdv);
        Date_Rdv_Mdc = findViewById(R.id.Date_Rdv);
        Heure_Rdv_Mdc = findViewById(R.id.Heure_Rdv);

        //get Information
        Intent intent = getIntent();
        String Nom_Prenom_Mdc_Rdv = intent.getStringExtra(Menu_Usr.Nom_Prenom_Mdc_Rdv);
        String Specialite_Mdc_Rdv = intent.getStringExtra(Menu_Usr.Specialite_Mdc_Rdv);
        String Date_Rdv = intent.getStringExtra(Menu_Usr.Date_Rdv);
        String Heure_Rdv = intent.getStringExtra(Menu_Usr.Heure_Rdv);

        Nom_Prenom_MdcRdv.setText(Nom_Prenom_Mdc_Rdv);
        SpecialiteMdc_Rdv.setText(Specialite_Mdc_Rdv);
        Date_Rdv_Mdc.setText(Date_Rdv);
        Heure_Rdv_Mdc.setText(Heure_Rdv);



    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
