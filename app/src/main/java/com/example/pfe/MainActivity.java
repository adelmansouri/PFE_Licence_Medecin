package com.example.pfe;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;


import java.math.BigInteger;
import java.time.Instant;

public class MainActivity extends AppCompatActivity {

   EditText EditText_AdrMail,EditText_MDP;
   //RelativeLayout ProgressBarCnx;
   /*Button mInscription_btn;*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ProgressBarCnx = findViewById(R.id.ProgressBarCnx);
        EditText_AdrMail = (EditText) findViewById(R.id.EditText_AdrMail);
        EditText_MDP = (EditText) findViewById(R.id.EditText_MDP);
        /*mInscription_btn = (Button) findViewById(R.id.inscription_btn);*/

    }




    public void Connecter(View view){
        //ProgressBarCnx.setVisibility(View.VISIBLE);
        String AdrMail = EditText_AdrMail.getText().toString();
        String MDP = EditText_MDP.getText().toString();
        String Operation = "Connexion";

        byte[] MDP_byte = MDP.getBytes();
        BigInteger MDP_byte_data = null;

        try {
            MDP_byte_data = new BigInteger(1,MD5.encryptMD5(MDP_byte));
        } catch (Exception e) {
            e.printStackTrace();
        }

        MDP = MDP_byte_data.toString();


        ArrierePlanCnx mArrierePlanCnx = new ArrierePlanCnx(this);
        mArrierePlanCnx.execute(Operation,AdrMail,MDP);
        //ProgressBarCnx.setVisibility(View.GONE);

    }

    public void Inscription(View view){
        startActivity(new Intent(this,activity_inscription.class));

    }

    public void Mdp_Oublier(View view){
        startActivity(new Intent(this,MotDePasse_Oublier.class));
    }

    @Override
    public void onBackPressed() {

    }
}
