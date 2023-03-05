package com.example.pfe;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

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

public class Menu_Mdc extends AppCompatActivity {

    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mToggle;
    TextView Nom_Usr_Profil, Prenom_Usr_Profil, AdrMail_Usr_Profil, DateDeNaissance_Usr_Profil, Sexe_Usr_Profil, NumTel_Usr_Profil, NoFav_TV_ProfilUsr, NoMsg_TV_ProfilUsr, NoRDV_TV_ProfilUsr,NoMdc_TV_ProfilUsr_SearchAdv,NoDemRDV_TV_ProfilUsr,SpecialiteMdc_Profil,AdrPostale_usr_profil;
    MaterialSearchView Search_View;
    MenuItem Search_item, Search_Adv_Item, Modifier_Profil_Usr, Supprimer_Favoris_Profil_Usr, Supprimer_Messages_Profil_Usr;
    ListView Liste_mdc, Liste_Favoris, Liste_Messages, Liste_RDV, Liste_Dem_RDV;

    public static final String ID_Mdc = "com.exemple.pfe.EXTRA_ID_Mdc";
    public static final String Sexe_Mdc = "com.exemple.pfe.EXTRA_Sexe_Mdc";
    public static final String ID_Usr = "com.exemple.pfe.EXTRA_ID_Usr";
    public static final String Nom_Prenom_Mdc_Rdv = "com.exemple.pfe.EXTRA_Nom_Prenom_Mdc_Rdv";
    public static final String Specialite_Mdc_Rdv = "com.exemple.pfe.EXTRA_Specialite_Mdc_Rdv";
    public static final String Date_Rdv = "com.exemple.pfe.EXTRA_Date_Rdv";
    public static final String Heure_Rdv = "com.exemple.pfe.EXTRA_Heure_Rdv";
    public static final String Contenue_Msg = "com.exemple.pfe.EXTRA_Contenue_Msg";
    public static final String NomPrenomMdc_Msg = "com.exemple.pfe.EXTRA_NomPrenomMdc_Msg";
    //public static final String SpecialiteMdc_Msg = "com.exemple.pfe.EXTRA_SpecialiteMdc_Msg";
    public static final String Date_Msg = "com.exemple.pfe.EXTRA_Date_Msg";
    public static final String Heure_Msg = "com.exemple.pfe.EXTRA_Heure_Msg";
    public static final String Adresse_Courriel_MdfUsr = "com.exemple.pfe.EXTRA_Adresse_Courriel_MdfUsr";
    public static final String Nom_MdfUsr = "com.exemple.pfe.EXTRA_Nom_MdfUsr";
    public static final String Prenom_MdfUsr = "com.exemple.pfe.EXTRA_Prenom_MdfUsr";
    public static final String DateDeNaissannce_MdfUsr = "com.exemple.pfe.EXTRA_DateDeNaissannce_MdfUsr";
    public static final String Sexe_MdfUsr = "com.exemple.pfe.EXTRA_Sexe_MdfUsr";
    public static final String NumTel_MdfUsr = "com.exemple.pfe.EXTRA_NumTel_MdfUsr";
    public static final String MDP_MdfUsr = "com.exemple.pfe.EXTRA_MDP_MdfUsr";
    public static final String IDUsr_MdfUsr = "com.exemple.pfe.EXTRA_IDUsr_MdfUsr";
    public static final String AdressePostale_MdfMdc = "com.exemple.pfe.EXTRA_AdressePostale_MdfMdc";
    public static final String Wilaya_MdfMdc = "com.exemple.pfe.EXTRA_Wilaya_MdfMdc";
    public static final String Specialite_MdfMdc= "com.exemple.pfe.EXTRA_Specialite_MdfMdc";
    public static final String IDRdv_DemandeRDVMdc = "com.exemple.pfe.EXTRA_IDRdv_DemandeRDVMdc";
    public static final String NomUsr_DemandeRDVMdc = "com.exemple.pfe.EXTRA_NomUsr_DemandeRDVMdc";
    public static final String PrenomUsr_DemandeRDVMdc = "com.exemple.pfe.EXTRA_PrenomUsr_DemandeRDVMdc";
    public static final String SexeUsr_DemandeRDVMdc = "com.exemple.pfe.EXTRA_SexeUsr_DemandeRDVMdc";
    public static final String IDUsr_Msg = "com.exemple.pfe.EXTRA_IDUsr_Msg";
    public static final String IDMdc_Msg = "com.exemple.pfe.EXTRA_IDMdc_Msg";

    String ID, Extra_Operation,MDP_ProfilUsr,AdrPostale_ProfilMdc,Wilya_ProfilMdc;
    Intent intent;
    JSONArray valeurs;
    NavigationView mNavigationViewSearch_adv, mMenu_Notif_ProfilUsr;
    RelativeLayout ProgressBarMenuUsr;
    BottomNavigationView BottomNav_usr;
    int size;

    private ArrierePlanSetInfo AsyncTask_ArrierePlan;



    Button Btn_Supprimer,Btn_Supprimer_Fav;

    ArrayList<mdc_search_favoris> Favoris_Liste;
    ArrayList<Messages_MenuMdc> Messages_Liste;

    MessagesAdapter_CheckBoxMdc mMessagesAdapter_CheckBox;
    ArrayList<Messages_MenuMdc_CheckBox> Messages_liste_Check_Box;

    MdcListAdapter_favoris_check_box mFav_CheckBox_adapter;
    ArrayList<mdc_search_favoris_check_box> Favoris_liste_CheckBox;

    ArrayList<mdc_search> Mdc_liste;
    ArrayList<Demande_RDV_Mdc> DemandeRDVMdc_Liste;

    Dialog Dialog_DesactiverCompte_Usr;

    boolean Profil_Mdf = false;


    //Search_Item
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.recherche_frag_toolbar_menu, menu);

        Search_item = menu.findItem(R.id.search_icon);
        Search_Adv_Item = menu.findItem(R.id.search_adv_icon);
        Modifier_Profil_Usr = menu.findItem(R.id.Modifier_Profil_Usr);
        Supprimer_Favoris_Profil_Usr = menu.findItem(R.id.Supprimer_Favoris_Profil_Usr);
        Supprimer_Messages_Profil_Usr = menu.findItem(R.id.Supprimer_Messages_Profil_Usr);
        Search_View.setMenuItem(Search_item);
        Search_item.setVisible(false);
        Search_Adv_Item.setVisible(false);
        Modifier_Profil_Usr.setVisible(true);
        Supprimer_Favoris_Profil_Usr.setVisible(false);
        Supprimer_Messages_Profil_Usr.setVisible(false);


        //Search_Adv
        Search_Adv_Item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (mDrawerLayout.isDrawerOpen(mNavigationViewSearch_adv)) {
                    mDrawerLayout.closeDrawer(mNavigationViewSearch_adv);
                } else {
                    if (mDrawerLayout.isDrawerOpen(mMenu_Notif_ProfilUsr)) {
                        mDrawerLayout.closeDrawer(mMenu_Notif_ProfilUsr);
                        mDrawerLayout.openDrawer(mNavigationViewSearch_adv);
                    } else {
                        if (!mDrawerLayout.isDrawerOpen(mNavigationViewSearch_adv)) {
                            mDrawerLayout.openDrawer(mNavigationViewSearch_adv);
                        } else {
                            mDrawerLayout.closeDrawer(mNavigationViewSearch_adv);
                        }
                    }
                }
                return false;
            }
        });


        //Modifier Profil_Usr
        Modifier_Profil_Usr.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Profil_Mdf = true;
                while (AsyncTask_ArrierePlan.getStatus() != ArrierePlanSetInfo.Status.FINISHED) {

                }
                Intent intent = new Intent(getApplicationContext(),ModifierInformation_ProfilMdc.class);
                intent.putExtra(Adresse_Courriel_MdfUsr, AdrMail_Usr_Profil.getText().toString());
                intent.putExtra(Nom_MdfUsr, Nom_Usr_Profil.getText().toString());
                intent.putExtra(Prenom_MdfUsr, Prenom_Usr_Profil.getText().toString());
                intent.putExtra(DateDeNaissannce_MdfUsr, DateDeNaissance_Usr_Profil.getText().toString());
                intent.putExtra(Sexe_MdfUsr, Sexe_Usr_Profil.getText().toString());
                intent.putExtra(NumTel_MdfUsr, NumTel_Usr_Profil.getText().toString());
                intent.putExtra(MDP_MdfUsr,MDP_ProfilUsr);
                intent.putExtra(IDUsr_MdfUsr,ID);
                intent.putExtra(AdressePostale_MdfMdc, AdrPostale_ProfilMdc);
                intent.putExtra(Wilaya_MdfMdc, Wilya_ProfilMdc);
                intent.putExtra(Specialite_MdfMdc, SpecialiteMdc_Profil.getText().toString());
                getApplicationContext().startActivity(intent);

                return false;
            }
        });


        // supprimer favoeis profil usr

        Supprimer_Favoris_Profil_Usr.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                while (AsyncTask_ArrierePlan.getStatus() != ArrierePlanSetInfo.Status.FINISHED) {

                }
                if (Btn_Supprimer_Fav.getVisibility() == View.GONE){
                    ArrayList<mdc_search_favoris_check_box> Favoris_Liste_CheckBox = new ArrayList<>();
                    for (int i=0;i<Favoris_Liste.size();i++){
                        mdc_search_favoris Mdc_Fav = Favoris_Liste.get(i);
                        String IDFav_CheckBox = Mdc_Fav.getIDFav();
                        String IDMdc_CheckBox = Mdc_Fav.getIDMdc();
                        String Nom_Prenom_Fav_CheckBox = Mdc_Fav.getNom_Prenom();
                        String Specialite_Fav_CheckBox = Mdc_Fav.getSpecialite();
                        String Sexe_Fav_CheckBox = Mdc_Fav.getSexe();
                        Boolean Select = false;
                        mdc_search_favoris_check_box Mdc_Fav_CheckBox = new mdc_search_favoris_check_box(IDFav_CheckBox,IDMdc_CheckBox,Nom_Prenom_Fav_CheckBox,Specialite_Fav_CheckBox,Sexe_Fav_CheckBox,Select);
                        Favoris_Liste_CheckBox.add(Mdc_Fav_CheckBox);

                    }
                    Btn_Supprimer_Fav.setVisibility(View.VISIBLE);
                    mFav_CheckBox_adapter = new MdcListAdapter_favoris_check_box(getApplicationContext(),R.layout.adapter_search_mdc_favoris_check_box,Favoris_Liste_CheckBox);
                    Liste_Favoris.setAdapter(mFav_CheckBox_adapter);
                }else{
                    Liste_Messages.setVisibility(View.VISIBLE);
                    Btn_Supprimer_Fav.setVisibility(View.GONE);
                    MdcListAdapter_favoris mFavorisAdapter = new MdcListAdapter_favoris(getApplicationContext(),R.layout.adapter_search_mdc,Favoris_Liste);
                    Liste_Favoris.setAdapter(mFavorisAdapter);
                }
                return false;
            }
        });


        // supprimer messages profil usr

        Supprimer_Messages_Profil_Usr.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                while (AsyncTask_ArrierePlan.getStatus() != ArrierePlanSetInfo.Status.FINISHED) {

                }
                if (Btn_Supprimer.getVisibility() == View.GONE) {
                    ArrayList<Messages_MenuMdc_CheckBox> Messages_Liste_CheckBox = new ArrayList<>();
                    for (int i = 0; i < Messages_Liste.size(); i++) {
                        Messages_MenuMdc Msg = Messages_Liste.get(i);
                        String IDMsg_CheckBox = Msg.getIDmsg();
                        String ContenueMsg_CheckBox = Msg.getContenueMsg();
                        String NomPrenomMdc_Msg_CheckBox = Msg.getNom_Prenom_Mdc();
                        //String SpecialiteMdc_Msg_CheckBox = Msg.getSpecialitéMdc();
                        String Date_Msg_CheckBox = Msg.getDate();
                        String Heure_Msg_CheckBox = Msg.getHeure();
                        Boolean Select = false;
                        Messages_MenuMdc_CheckBox Msg_CheckBox = new Messages_MenuMdc_CheckBox(IDMsg_CheckBox, ContenueMsg_CheckBox, Date_Msg_CheckBox, Heure_Msg_CheckBox, NomPrenomMdc_Msg_CheckBox, Select);
                        Messages_Liste_CheckBox.add(Msg_CheckBox);
                    }
                    Btn_Supprimer.setVisibility(View.VISIBLE);
                    mMessagesAdapter_CheckBox = new MessagesAdapter_CheckBoxMdc(getApplicationContext(), R.layout.adapter_messages_check_box, Messages_Liste_CheckBox);
                    Liste_Messages.setAdapter(mMessagesAdapter_CheckBox);


                } else {
                    Liste_Messages.setVisibility(View.VISIBLE);
                    Btn_Supprimer.setVisibility(View.GONE);
                    MessagesAdapterMdc mMessagesAdapter = new MessagesAdapterMdc(getApplicationContext(), R.layout.adapter_messages, Messages_Liste);
                    Liste_Messages.setAdapter(mMessagesAdapter);
                }


                return false;
            }
        });


        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__mdc);

        ProgressBarMenuUsr = findViewById(R.id.ProgressBarMenuUsr);


        //toolbar initialisation
        Toolbar toolbar_usr = findViewById(R.id.toolbar_usr);
        setSupportActionBar(toolbar_usr);
        getSupportActionBar().setTitle("Profil");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.gradStop)));


        BottomNav_usr = findViewById(R.id.BottomNav_Usr);
        BottomNav_usr.setOnNavigationItemSelectedListener(BottomNav_usr_listner);
        size = BottomNav_usr.getMenu().size();


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_menu_usr, new fragement_profil_mdc()).commit();
        }

        //boutons supprimer message et favoris
        Btn_Supprimer = findViewById(R.id.Btn_Supprimer);
        Btn_Supprimer_Fav = findViewById(R.id.Btn_Supprimer_Fav);

        //Side Frage
        mDrawerLayout = (DrawerLayout) findViewById(R.id.menu_notif_usr);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar_usr, R.string.Open, R.string.Close);
        mToggle.setDrawerIndicatorEnabled(false);
        mToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDrawerLayout.isDrawerOpen(mNavigationViewSearch_adv)) {
                    mDrawerLayout.closeDrawer(mNavigationViewSearch_adv);
                    mDrawerLayout.openDrawer(mMenu_Notif_ProfilUsr);
                } else {
                    if (mDrawerLayout.isDrawerOpen(mMenu_Notif_ProfilUsr)) {
                        mDrawerLayout.closeDrawer(mMenu_Notif_ProfilUsr);
                    } else {
                        mDrawerLayout.openDrawer(mMenu_Notif_ProfilUsr);
                    }
                }
            }
        });
        mToggle.setHomeAsUpIndicator(R.drawable.ic_list_black_24dp);
        //mDrawerLayout.addDrawerListener(mToggle);
        //mToggle.syncState();
        /*getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/


        //Profil Fragement
        intent = getIntent();
        ID = intent.getStringExtra(ArrierePlanCnx.ID);
        Extra_Operation = intent.getStringExtra(ArrierePlanCnx.EXTRA_Operation);

        AsyncTask_ArrierePlan = new ArrierePlanSetInfo();
        AsyncTask_ArrierePlan.execute(Extra_Operation, ID);


        //Search_fragement
        Search_View = findViewById(R.id.Materiel_Search_Mdc);
        Search_View.setSuggestions(getResources().getStringArray(R.array.Spécialité));
        Search_View.setHint("Recherche ...");
        //button click search
        Search_View.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query != null) {
                    //Log.d("resultat", "onQueryTextSubmit: "+ query);

                    AsyncTask_ArrierePlan = new ArrierePlanSetInfo();
                    AsyncTask_ArrierePlan.execute("Search_Spe", query);

                }
                Search_View.closeSearch();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        //item suggestion selected
        Search_View.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AsyncTask_ArrierePlan = new ArrierePlanSetInfo();
                AsyncTask_ArrierePlan.execute("Search_Spe", parent.getItemAtPosition(position).toString());
                Search_View.closeSearch();

            }
        });


        //Search_ADV
        mNavigationViewSearch_adv = findViewById(R.id.Recherche_avancée);
        mMenu_Notif_ProfilUsr = findViewById(R.id.Menu_Notif_ProfilUsr);

        //meni rdv / deco
        mMenu_Notif_ProfilUsr.setNavigationItemSelectedListener(Navigation_MenuUsr);

        //desactiver compte
        Dialog_DesactiverCompte_Usr = new Dialog(this);


    }

    //Whene we comback to this activity

    @Override
    protected void onResume() {
        super.onResume();
        if (Profil_Mdf){
            getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_menu_usr, new fragement_profil_mdc()).commit();
            AsyncTask_ArrierePlan = new ArrierePlanSetInfo();
            AsyncTask_ArrierePlan.execute(Extra_Operation, ID);
            Profil_Mdf = false;
        }


    }



    //bouton supprimer messages

    public void Supprimer(View view) {
        
        int j=0;
        Messages_liste_Check_Box = mMessagesAdapter_CheckBox.MessageListe;
        //verifier si il ya des msg selected 
        
        for (int i = 0; i < Messages_liste_Check_Box.size(); i++){
            Messages_MenuMdc_CheckBox Test_Message_Checked = Messages_liste_Check_Box.get(i);
            if (Test_Message_Checked.getSelected()){
                j=j+1;
            }
        }

        
        if (j == 0){
            Toast.makeText(this, "Vous n'avais pas selectionné de messages", Toast.LENGTH_SHORT).show();
        }
        else{

            final Dialog Dialog_Supprimer_Msg = new Dialog(this);

            TextView Fermer_Supprimer_Msg;
            Button Supprimer_Msg_Annuler;
            Button Supprimer_Msg_Confirmer;

            Dialog_Supprimer_Msg.setContentView(R.layout.window_supprimer_msg);

            Fermer_Supprimer_Msg = Dialog_Supprimer_Msg.findViewById(R.id.Fermer_Supprimer_Msg);
            Supprimer_Msg_Annuler = Dialog_Supprimer_Msg.findViewById(R.id.Window_Supprimer_Msg_Annuler);
            Supprimer_Msg_Confirmer = Dialog_Supprimer_Msg.findViewById(R.id.Window_Supprimer_Msg_Confirmer);

            Fermer_Supprimer_Msg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog_Supprimer_Msg.dismiss();
                }
            });

            Supprimer_Msg_Annuler.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog_Supprimer_Msg.dismiss();
                }
            });

            Supprimer_Msg_Confirmer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    for (int i = 0; i < Messages_liste_Check_Box.size(); i++) {
                        Messages_MenuMdc_CheckBox Message = Messages_liste_Check_Box.get(i);
                        if (Message.getSelected()) {
                            String IDMsg_Selected = Message.getIDmsg();

                            AsyncTask_ArrierePlan = new ArrierePlanSetInfo();
                            AsyncTask_ArrierePlan.execute("Supprimer_Message", IDMsg_Selected);
                        }
                    }

                    AsyncTask_ArrierePlan = new ArrierePlanSetInfo();
                    AsyncTask_ArrierePlan.execute("Messages", ID);
                    Dialog_Supprimer_Msg.dismiss();
                    Btn_Supprimer.setVisibility(View.GONE);


                }
            });
            Dialog_Supprimer_Msg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            Dialog_Supprimer_Msg.show();
        }



    }

    //bouton supprimer Favoris

    public void Supprimer_Fav(View view){
        int j=0;
        Favoris_liste_CheckBox = mFav_CheckBox_adapter.FavorisListe;
        for (int i=0;i<Favoris_liste_CheckBox.size();i++){
            mdc_search_favoris_check_box Mdc_Fav_CheckBox = Favoris_liste_CheckBox.get(i);
            if (Mdc_Fav_CheckBox.getSelected()){
                j=j+1;
            }
        }

        if (j == 0){
            Toast.makeText(this, "Vous n'avais pas selectionné de medecin favoris", Toast.LENGTH_SHORT).show();
        }else{
            final Dialog Dialog_supprimer_Fav = new Dialog(this);

            TextView Fermer_Supprimer_Fav;
            Button Supprimer_Fav_Annuler;
            Button Supprimer_Fav_Confirmer;

            Dialog_supprimer_Fav.setContentView(R.layout.window_supprimer_fav);

            Fermer_Supprimer_Fav = Dialog_supprimer_Fav.findViewById(R.id.Fermer_Supprimer_Fav);
            Supprimer_Fav_Annuler = Dialog_supprimer_Fav.findViewById(R.id.Window_Supprimer_Fav_Annuler);
            Supprimer_Fav_Confirmer = Dialog_supprimer_Fav.findViewById(R.id.Window_Supprimer_Fav_Confirmer);

            Fermer_Supprimer_Fav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog_supprimer_Fav.dismiss();
                }
            });

            Supprimer_Fav_Annuler.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog_supprimer_Fav.dismiss();
                }
            });

            Supprimer_Fav_Confirmer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < Favoris_liste_CheckBox.size(); i++) {
                        mdc_search_favoris_check_box Mdc_Fav_Supp = Favoris_liste_CheckBox.get(i);
                        if (Mdc_Fav_Supp.getSelected()) {
                            String IDFav_Selected = Mdc_Fav_Supp.getIDFav();

                            AsyncTask_ArrierePlan = new ArrierePlanSetInfo();
                            AsyncTask_ArrierePlan.execute("Supprimer Medecin des favoris", IDFav_Selected);
                        }
                    }

                    AsyncTask_ArrierePlan = new ArrierePlanSetInfo();
                    AsyncTask_ArrierePlan.execute("favoris", ID);
                    Dialog_supprimer_Fav.dismiss();
                    Btn_Supprimer_Fav.setVisibility(View.GONE);
                }
            });


            Dialog_supprimer_Fav.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            Dialog_supprimer_Fav.show();


        }
    }

    //filtrer recherche

    public void Filtrer(View view){

        int j = 0;

        TextView Nom_SearchAdv = findViewById(R.id.Nom_SearchAdv);
        TextView Prenom_SearchAdv = findViewById(R.id.Prenom_SearchAdv);
        Spinner Sexe_SearchAdv = findViewById(R.id.Sexe_SearchAdv);

        ArrayList<mdc_search> Liste_Filtrer = new ArrayList<>();

        if (Nom_SearchAdv.getText().toString().trim().length() == 0 && Prenom_SearchAdv.getText().toString().trim().length() == 0){
            for (int i=0;i<Mdc_liste.size();i++){
                mdc_search Mdc_Filtrer = Mdc_liste.get(i);
                if (Mdc_Filtrer.getSexe().equals(Sexe_SearchAdv.getSelectedItem().toString())){
                    Liste_Filtrer.add(Mdc_Filtrer);
                }
            }
            Liste_mdc = (ListView) findViewById(R.id.Liste_Mdc);
            Liste_mdc.setVisibility(View.VISIBLE);
            MdcListAdapter mMdcListAdapter_Filtrer = new MdcListAdapter(getApplicationContext(),R.layout.adapter_search_mdc,Liste_Filtrer);
            Liste_mdc.setAdapter(mMdcListAdapter_Filtrer);


        }else{
            if (Nom_SearchAdv.getText().toString().trim().length() != 0 && Prenom_SearchAdv.getText().toString().trim().length() == 0){
                for (int i=0;i<Mdc_liste.size();i++){
                    mdc_search Mdc_Filtrer_SexeNom = Mdc_liste.get(i);
                    if (Mdc_Filtrer_SexeNom.getSexe().equals(Sexe_SearchAdv.getSelectedItem().toString()) && Mdc_Filtrer_SexeNom.getNomMdc().toLowerCase().equals(Nom_SearchAdv.getText().toString().toLowerCase())  ){
                        Liste_Filtrer.add(Mdc_Filtrer_SexeNom);
                        j=j+1;
                    }
                }

                if (j==0){
                    Liste_mdc = (ListView) findViewById(R.id.Liste_Mdc);
                    Liste_mdc.setVisibility(View.GONE);
                    NoMdc_TV_ProfilUsr_SearchAdv = findViewById(R.id.NoMdc_TV_ProfilUsr_SearchAdv);
                    NoMdc_TV_ProfilUsr_SearchAdv.setVisibility(View.VISIBLE);

                }else{
                    NoMdc_TV_ProfilUsr_SearchAdv = findViewById(R.id.NoMdc_TV_ProfilUsr_SearchAdv);
                    NoMdc_TV_ProfilUsr_SearchAdv.setVisibility(View.GONE);
                    Liste_mdc = (ListView) findViewById(R.id.Liste_Mdc);
                    Liste_mdc.setVisibility(View.VISIBLE);
                    MdcListAdapter mMdcListAdapter_Filtrer = new MdcListAdapter(getApplicationContext(),R.layout.adapter_search_mdc,Liste_Filtrer);
                    Liste_mdc.setAdapter(mMdcListAdapter_Filtrer);
                }
            }else{
                if (Nom_SearchAdv.getText().toString().trim().length() == 0 && Prenom_SearchAdv.getText().toString().trim().length() != 0){
                    for (int i=0;i<Mdc_liste.size();i++){
                        mdc_search Mdc_Filtrer_SexePrenom = Mdc_liste.get(i);
                        if (Mdc_Filtrer_SexePrenom.getSexe().equals(Sexe_SearchAdv.getSelectedItem().toString()) && Mdc_Filtrer_SexePrenom.getPrenomMdc().toLowerCase().equals(Prenom_SearchAdv.getText().toString().toLowerCase()) ){
                            Liste_Filtrer.add(Mdc_Filtrer_SexePrenom);
                            j=j+1;
                        }
                    }

                    if (j==0){
                        Liste_mdc = (ListView) findViewById(R.id.Liste_Mdc);
                        Liste_mdc.setVisibility(View.GONE);
                        NoMdc_TV_ProfilUsr_SearchAdv = findViewById(R.id.NoMdc_TV_ProfilUsr_SearchAdv);
                        NoMdc_TV_ProfilUsr_SearchAdv.setVisibility(View.VISIBLE);

                    }else{
                        NoMdc_TV_ProfilUsr_SearchAdv = findViewById(R.id.NoMdc_TV_ProfilUsr_SearchAdv);
                        NoMdc_TV_ProfilUsr_SearchAdv.setVisibility(View.GONE);
                        Liste_mdc = (ListView) findViewById(R.id.Liste_Mdc);
                        Liste_mdc.setVisibility(View.VISIBLE);
                        MdcListAdapter mMdcListAdapter_Filtrer = new MdcListAdapter(getApplicationContext(),R.layout.adapter_search_mdc,Liste_Filtrer);
                        Liste_mdc.setAdapter(mMdcListAdapter_Filtrer);
                    }

                }else{
                    if (Nom_SearchAdv.getText().toString().trim().length() != 0 && Prenom_SearchAdv.getText().toString().trim().length() != 0){
                        for (int i=0;i<Mdc_liste.size();i++){
                            mdc_search Mdc_Filtrer_SexeNomPrenom = Mdc_liste.get(i);
                            if (Mdc_Filtrer_SexeNomPrenom.getSexe().equals(Sexe_SearchAdv.getSelectedItem().toString()) && Mdc_Filtrer_SexeNomPrenom.getNomMdc().toLowerCase().equals(Nom_SearchAdv.getText().toString().toLowerCase()) && Mdc_Filtrer_SexeNomPrenom.getPrenomMdc().toLowerCase().equals(Prenom_SearchAdv.getText().toString().toLowerCase())){
                                Liste_Filtrer.add(Mdc_Filtrer_SexeNomPrenom);
                                j=j+1;
                            }
                        }

                        if (j==0){
                            Liste_mdc = (ListView) findViewById(R.id.Liste_Mdc);
                            Liste_mdc.setVisibility(View.GONE);
                            NoMdc_TV_ProfilUsr_SearchAdv = findViewById(R.id.NoMdc_TV_ProfilUsr_SearchAdv);
                            NoMdc_TV_ProfilUsr_SearchAdv.setVisibility(View.VISIBLE);

                        }else{
                            NoMdc_TV_ProfilUsr_SearchAdv = findViewById(R.id.NoMdc_TV_ProfilUsr_SearchAdv);
                            NoMdc_TV_ProfilUsr_SearchAdv.setVisibility(View.GONE);
                            Liste_mdc = (ListView) findViewById(R.id.Liste_Mdc);
                            Liste_mdc.setVisibility(View.VISIBLE);
                            MdcListAdapter mMdcListAdapter_Filtrer = new MdcListAdapter(getApplicationContext(),R.layout.adapter_search_mdc,Liste_Filtrer);
                            Liste_mdc.setAdapter(mMdcListAdapter_Filtrer);
                        }

                    }
                }
            }
        }

        mDrawerLayout.closeDrawer(mNavigationViewSearch_adv);

    }


    //bouton retour
    @Override
    public void onBackPressed() {

    }


    //toolbar hamburger
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    //bottom nav listner user
    private BottomNavigationView.OnNavigationItemSelectedListener BottomNav_usr_listner = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment fragment_selectionne = null;

            switch (menuItem.getItemId()) {
                case R.id.Profil_btn:
                    if (AsyncTask_ArrierePlan.getStatus() != ArrierePlanSetInfo.Status.FINISHED) {
                        AsyncTask_ArrierePlan.cancel(true);
                    }
                    fragment_selectionne = new fragement_profil_mdc();
                    getSupportActionBar().setTitle("Profil");
                    //Intent intent = getIntent();
                    //String ID = intent.getStringExtra(ArrierePlanCnx.ID);
                    //String Operation = intent.getStringExtra(ArrierePlanCnx.EXTRA_Operation);
                    Search_item.setVisible(false);
                    Search_Adv_Item.setVisible(false);
                    Modifier_Profil_Usr.setVisible(true);
                    Search_View.setVisibility(View.GONE);
                    Supprimer_Favoris_Profil_Usr.setVisible(false);
                    Supprimer_Messages_Profil_Usr.setVisible(false);
                    Btn_Supprimer.setVisibility(View.GONE);
                    Btn_Supprimer_Fav.setVisibility(View.GONE);
                    mNavigationViewSearch_adv.setVisibility(View.GONE);

                    mMenu_Notif_ProfilUsr.getMenu().setGroupCheckable(0, false, true);
                    BottomNav_usr.getMenu().setGroupCheckable(0, true, true);


                    //new ArrierePlanSetInfo().execute(Extra_Operation,ID);
                    AsyncTask_ArrierePlan = new ArrierePlanSetInfo();
                    AsyncTask_ArrierePlan.execute(Extra_Operation, ID);

                    /*if (mDrawerLayout.isDrawerOpen(GravityCompat.START)){
                        mDrawerLayout.closeDrawer(Gravity.LEFT);
                    }*/
                    if (mDrawerLayout.isDrawerOpen(mMenu_Notif_ProfilUsr)) {
                        mDrawerLayout.closeDrawer(mMenu_Notif_ProfilUsr);
                    } else {
                        if (mDrawerLayout.isDrawerOpen(mNavigationViewSearch_adv)) {
                            mDrawerLayout.closeDrawer(mNavigationViewSearch_adv);
                        }
                    }
                    break;


                case R.id.Rechercher_btn:
                    if (AsyncTask_ArrierePlan.getStatus() != ArrierePlanSetInfo.Status.FINISHED) {
                        AsyncTask_ArrierePlan.cancel(true);
                    }
                    fragment_selectionne = new fragement_recherche_usr();
                    getSupportActionBar().setTitle("Recherche");
                    Search_item.setVisible(true);
                    Search_Adv_Item.setVisible(true);
                    Modifier_Profil_Usr.setVisible(false);
                    Search_View.setVisibility(View.VISIBLE);
                    Supprimer_Favoris_Profil_Usr.setVisible(false);
                    Supprimer_Messages_Profil_Usr.setVisible(false);
                    Btn_Supprimer.setVisibility(View.GONE);
                    Btn_Supprimer_Fav.setVisibility(View.GONE);
                    mNavigationViewSearch_adv.setVisibility(View.VISIBLE);

                    mMenu_Notif_ProfilUsr.getMenu().setGroupCheckable(0, false, true);
                    BottomNav_usr.getMenu().setGroupCheckable(0, true, true);


                    //new ArrierePlanSetInfo().execute("recherche");
                    AsyncTask_ArrierePlan = new ArrierePlanSetInfo();
                    AsyncTask_ArrierePlan.execute("recherche");


                    if (mDrawerLayout.isDrawerOpen(mMenu_Notif_ProfilUsr)) {
                        mDrawerLayout.closeDrawer(mMenu_Notif_ProfilUsr);
                    } else {
                        if (mDrawerLayout.isDrawerOpen(mNavigationViewSearch_adv)) {
                            mDrawerLayout.closeDrawer(mNavigationViewSearch_adv);
                        }
                    }


                    break;


                case R.id.Favoris_btn:
                    if (AsyncTask_ArrierePlan.getStatus() != ArrierePlanSetInfo.Status.FINISHED) {
                        AsyncTask_ArrierePlan.cancel(true);
                    }
                    fragment_selectionne = new fragement_favoris_usr();
                    getSupportActionBar().setTitle("Favoris");
                    Search_item.setVisible(false);
                    Search_Adv_Item.setVisible(false);
                    Modifier_Profil_Usr.setVisible(false);
                    Search_View.setVisibility(View.GONE);
                    Supprimer_Favoris_Profil_Usr.setVisible(true);
                    Supprimer_Messages_Profil_Usr.setVisible(false);
                    Btn_Supprimer.setVisibility(View.GONE);
                    Btn_Supprimer_Fav.setVisibility(View.GONE);
                    mNavigationViewSearch_adv.setVisibility(View.GONE);

                    mMenu_Notif_ProfilUsr.getMenu().setGroupCheckable(0, false, true);
                    BottomNav_usr.getMenu().setGroupCheckable(0, true, true);


                    //new ArrierePlanSetInfo().execute("favoris",ID);
                    AsyncTask_ArrierePlan = new ArrierePlanSetInfo();
                    AsyncTask_ArrierePlan.execute("favoris", ID);


                    if (mDrawerLayout.isDrawerOpen(mMenu_Notif_ProfilUsr)) {
                        mDrawerLayout.closeDrawer(mMenu_Notif_ProfilUsr);
                    } else {
                        if (mDrawerLayout.isDrawerOpen(mNavigationViewSearch_adv)) {
                            mDrawerLayout.closeDrawer(mNavigationViewSearch_adv);
                        }
                    }
                    break;


                case R.id.Message_btn:
                    if (AsyncTask_ArrierePlan.getStatus() != ArrierePlanSetInfo.Status.FINISHED) {
                        AsyncTask_ArrierePlan.cancel(true);
                    }
                    fragment_selectionne = new fragement_messages_usr();
                    getSupportActionBar().setTitle("Messages");
                    Search_item.setVisible(false);
                    Search_Adv_Item.setVisible(false);
                    Modifier_Profil_Usr.setVisible(false);
                    Search_View.setVisibility(View.GONE);
                    Supprimer_Favoris_Profil_Usr.setVisible(false);
                    Supprimer_Messages_Profil_Usr.setVisible(true);
                    Btn_Supprimer.setVisibility(View.GONE);
                    Btn_Supprimer_Fav.setVisibility(View.GONE);
                    mNavigationViewSearch_adv.setVisibility(View.GONE);

                    mMenu_Notif_ProfilUsr.getMenu().setGroupCheckable(0, false, true);
                    BottomNav_usr.getMenu().setGroupCheckable(0, true, true);


                    AsyncTask_ArrierePlan = new ArrierePlanSetInfo();
                    AsyncTask_ArrierePlan.execute("Messages", ID);


                    if (mDrawerLayout.isDrawerOpen(mMenu_Notif_ProfilUsr)) {
                        mDrawerLayout.closeDrawer(mMenu_Notif_ProfilUsr);
                    } else {
                        if (mDrawerLayout.isDrawerOpen(mNavigationViewSearch_adv)) {
                            mDrawerLayout.closeDrawer(mNavigationViewSearch_adv);
                        }
                    }
                    break;


            }

            if (fragment_selectionne != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_menu_usr, fragment_selectionne).commit();
            }
            return true;
        }
    };


    //Menu_NotifUsr

    private NavigationView.OnNavigationItemSelectedListener Navigation_MenuUsr = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment fragment_selectionne = null;
            switch (menuItem.getItemId()) {
                case R.id.rdv_btn:
                    if (AsyncTask_ArrierePlan.getStatus() != ArrierePlanSetInfo.Status.FINISHED) {
                        AsyncTask_ArrierePlan.cancel(true);
                    }
                    fragment_selectionne = new fragement_rdv_usr();
                    getSupportActionBar().setTitle("Rendez-Vous");
                    Search_item.setVisible(false);
                    Search_Adv_Item.setVisible(false);
                    Modifier_Profil_Usr.setVisible(false);
                    Search_View.setVisibility(View.GONE);
                    Supprimer_Favoris_Profil_Usr.setVisible(false);
                    Supprimer_Messages_Profil_Usr.setVisible(false);
                    Btn_Supprimer.setVisibility(View.GONE);
                    Btn_Supprimer_Fav.setVisibility(View.GONE);
                    mNavigationViewSearch_adv.setVisibility(View.GONE);

                    mMenu_Notif_ProfilUsr.getMenu().setGroupCheckable(0, true, true);
                    BottomNav_usr.getMenu().setGroupCheckable(0, false, true);

                    AsyncTask_ArrierePlan = new ArrierePlanSetInfo();
                    AsyncTask_ArrierePlan.execute("Rendez-Vous", ID);


                    break;

                case R.id.demande_rdv_btn :
                    if (AsyncTask_ArrierePlan.getStatus() != ArrierePlanSetInfo.Status.FINISHED) {
                        AsyncTask_ArrierePlan.cancel(true);
                    }
                    fragment_selectionne = new fragement_demande_rdv_mdc();
                    getSupportActionBar().setTitle("Demande De Rendez-Vous");
                    Search_item.setVisible(false);
                    Search_Adv_Item.setVisible(false);
                    Modifier_Profil_Usr.setVisible(false);
                    Search_View.setVisibility(View.GONE);
                    Supprimer_Favoris_Profil_Usr.setVisible(false);
                    Supprimer_Messages_Profil_Usr.setVisible(false);
                    Btn_Supprimer.setVisibility(View.GONE);
                    Btn_Supprimer_Fav.setVisibility(View.GONE);
                    mNavigationViewSearch_adv.setVisibility(View.GONE);

                    mMenu_Notif_ProfilUsr.getMenu().setGroupCheckable(0, true, true);
                    BottomNav_usr.getMenu().setGroupCheckable(0, false, true);

                    AsyncTask_ArrierePlan = new ArrierePlanSetInfo();
                    AsyncTask_ArrierePlan.execute("Demande de Rendez-Vous", ID);
                    break;

                case R.id.DesactiverCompte_Mdc_btn:
                    if (AsyncTask_ArrierePlan.getStatus() != ArrierePlanSetInfo.Status.FINISHED) {
                        AsyncTask_ArrierePlan.cancel(true);
                    }

                    TextView Fermer_Demande_DesCmp;
                    TextView window_Demande_DesCmp_Msg_tv;
                    Button Window_Demande_DesCmp_Annuler;
                    Button Window_Demande_DesCmp_valider;

                    Dialog_DesactiverCompte_Usr.setContentView(R.layout.window_demande_rdv_mdc);

                    Fermer_Demande_DesCmp = Dialog_DesactiverCompte_Usr.findViewById(R.id.Fermer_Demande_RdvMdc);
                    window_Demande_DesCmp_Msg_tv = Dialog_DesactiverCompte_Usr.findViewById(R.id.window_Demande_RdvMdc_Msg_tv);
                    Window_Demande_DesCmp_Annuler = Dialog_DesactiverCompte_Usr.findViewById(R.id.Window_Demande_RdvMdc_Annuler);
                    Window_Demande_DesCmp_valider = Dialog_DesactiverCompte_Usr.findViewById(R.id.Window_Demande_RdvMdc_valider);

                    String Msg = "Voullez vous vraiment désactiver votre compte ?";
                    window_Demande_DesCmp_Msg_tv.setText(Msg);

                    Fermer_Demande_DesCmp.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Dialog_DesactiverCompte_Usr.dismiss();
                        }
                    });

                    Window_Demande_DesCmp_Annuler.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Dialog_DesactiverCompte_Usr.dismiss();
                        }
                    });

                    Window_Demande_DesCmp_valider.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            AsyncTask_ArrierePlan = new ArrierePlanSetInfo();
                            AsyncTask_ArrierePlan.execute("Desactiver compte", ID);
                            Dialog_DesactiverCompte_Usr.dismiss();

                        }
                    });




                    Dialog_DesactiverCompte_Usr.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                    Dialog_DesactiverCompte_Usr.show();
                    break;

                case R.id.deco_btn:
                    if (AsyncTask_ArrierePlan.getStatus() != ArrierePlanSetInfo.Status.FINISHED) {
                        AsyncTask_ArrierePlan.cancel(true);
                    }
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    break;
            }
            if (fragment_selectionne != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_menu_usr, fragment_selectionne).commit();
            }
            mDrawerLayout.closeDrawer(mMenu_Notif_ProfilUsr);
            return true;
        }
    };


    //Set info Profil recherche msg favoris

    class ArrierePlanSetInfo extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            ProgressBarMenuUsr.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            String Operation = strings[0];
            switch (Operation) {
                case "Medecin":
                    try {
                        String ID = strings[1];
                        String getInfo_Mdc = "http://192.168.1.9:8080/getInformation_ProfilMdc.php";
                        URL getInformation_url = new URL(getInfo_Mdc);
                        HttpURLConnection mhttpURLConnection = (HttpURLConnection) getInformation_url.openConnection();
                        mhttpURLConnection.setRequestMethod("POST");
                        mhttpURLConnection.setDoOutput(true);
                        mhttpURLConnection.setDoInput(true);
                        OutputStreamWriter mOutPutStreamWriter = new OutputStreamWriter(mhttpURLConnection.getOutputStream());
                        BufferedWriter mBufferWriter = new BufferedWriter(mOutPutStreamWriter);
                        String Post_Data = URLEncoder.encode("ID", "UTF-8") + "=" + URLEncoder.encode(ID, "UTF-8");
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


                case "recherche":
                    try {
                        String Url_recherche = "http://192.168.1.9:8080/recherche_Mdc.php";
                        URL recherche_Mdc = new URL(Url_recherche);
                        HttpURLConnection mHttpUrlConnection = (HttpURLConnection) recherche_Mdc.openConnection();
                        mHttpUrlConnection.setDoOutput(true);
                        mHttpUrlConnection.setDoInput(true);
                        InputStreamReader mInputStreamReader = new InputStreamReader(mHttpUrlConnection.getInputStream());
                        BufferedReader mBufferedReader = new BufferedReader(mInputStreamReader);
                        String ligne = "";
                        String result = "";
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

                case "Search_Spe":
                    try {
                        String specialite = strings[1];
                        String Url_searche_spe = "http://192.168.1.9:8080/Filtre_recherche_specialité.php";
                        URL url = new URL(Url_searche_spe);
                        HttpURLConnection mHttpUrlConnection = (HttpURLConnection) url.openConnection();
                        mHttpUrlConnection.setRequestMethod("POST");
                        mHttpUrlConnection.setDoOutput(true);
                        mHttpUrlConnection.setDoInput(true);
                        OutputStreamWriter mOutPutStreamWriter = new OutputStreamWriter(mHttpUrlConnection.getOutputStream());
                        BufferedWriter mBufferedWriter = new BufferedWriter(mOutPutStreamWriter);
                        String Post_Data = URLEncoder.encode("Specialite", "UTF-8") + "=" + URLEncoder.encode(specialite, "UTF-8");
                        mBufferedWriter.write(Post_Data);
                        mBufferedWriter.flush();
                        mBufferedWriter.close();
                        mOutPutStreamWriter.close();
                        InputStreamReader mInPutStreamReader = new InputStreamReader(mHttpUrlConnection.getInputStream());
                        BufferedReader mBufferedReader = new BufferedReader(mInPutStreamReader);
                        String result = "";
                        String ligne = "";
                        while ((ligne = mBufferedReader.readLine()) != null) {
                            result += ligne;
                        }
                        mBufferedReader.close();
                        mInPutStreamReader.close();
                        mHttpUrlConnection.disconnect();

                        return result;


                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;

                case "favoris":
                    try {
                        String ID = strings[1];
                        String Url_favoris_usr = "http://192.168.1.9:8080/getFavoris_ProfilUsr.php";
                        URL favoris_usr_Url = new URL(Url_favoris_usr);
                        HttpURLConnection mHttpUrlConnection = (HttpURLConnection) favoris_usr_Url.openConnection();
                        mHttpUrlConnection.setRequestMethod("POST");
                        mHttpUrlConnection.setDoOutput(true);
                        mHttpUrlConnection.setDoOutput(true);
                        OutputStreamWriter mOutputStreamWriter = new OutputStreamWriter(mHttpUrlConnection.getOutputStream());
                        BufferedWriter mBufferedWriter = new BufferedWriter(mOutputStreamWriter);
                        String Post_Data = URLEncoder.encode("ID", "UTF-8") + "=" + URLEncoder.encode(ID, "UTF-8");
                        mBufferedWriter.write(Post_Data);
                        mBufferedWriter.flush();
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

                case "Messages":

                    try {
                        String ID = strings[1];
                        String Url_Messages_Usr = "http://192.168.1.9:8080/getMessages_ProfilMdc.php";
                        URL Messages_Usr_Url = new URL(Url_Messages_Usr);
                        HttpURLConnection mHttpUrlConnection = (HttpURLConnection) Messages_Usr_Url.openConnection();
                        mHttpUrlConnection.setRequestMethod("POST");
                        mHttpUrlConnection.setDoOutput(true);
                        mHttpUrlConnection.setDoInput(true);
                        OutputStreamWriter mOutputStreamWriter = new OutputStreamWriter(mHttpUrlConnection.getOutputStream());
                        BufferedWriter mBufferedWriter = new BufferedWriter(mOutputStreamWriter);
                        String Post_Date = URLEncoder.encode("ID", "UTF-8") + "=" + URLEncoder.encode(ID, "UTF-8");
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

                case "Rendez-Vous":
                    try {
                        String ID = strings[1];
                        String Url_Rdv_Usr = "http://192.168.1.9:8080/getRDV_ProfilUsr.php";
                        URL Messages_Usr_Url = new URL(Url_Rdv_Usr);
                        HttpURLConnection mHttpUrlConnection = (HttpURLConnection) Messages_Usr_Url.openConnection();
                        mHttpUrlConnection.setRequestMethod("POST");
                        mHttpUrlConnection.setDoOutput(true);
                        mHttpUrlConnection.setDoInput(true);
                        OutputStreamWriter mOutputStreamWriter = new OutputStreamWriter(mHttpUrlConnection.getOutputStream());
                        BufferedWriter mBufferedWriter = new BufferedWriter(mOutputStreamWriter);
                        String Post_Date = URLEncoder.encode("ID", "UTF-8") + "=" + URLEncoder.encode(ID, "UTF-8");
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

                case "Supprimer_Message":
                    try {
                        String IDmsg = strings[1];
                        String Url_Supp_Msg = "http://192.168.1.9:8080/SupprimerMsg_ProfilUsr.php";
                        URL Supprimer_Msg_Url = new URL(Url_Supp_Msg);
                        HttpURLConnection mHttpUrlConnection = (HttpURLConnection) Supprimer_Msg_Url.openConnection();
                        mHttpUrlConnection.setRequestMethod("POST");
                        mHttpUrlConnection.setDoOutput(true);
                        mHttpUrlConnection.setDoInput(true);
                        OutputStreamWriter mOutputStreamWriter = new OutputStreamWriter(mHttpUrlConnection.getOutputStream());
                        BufferedWriter mBufferedWriter = new BufferedWriter(mOutputStreamWriter);
                        String Post_Date = URLEncoder.encode("IDmsg", "UTF-8") + "=" + URLEncoder.encode(IDmsg, "UTF-8");
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

                case "Supprimer Medecin des favoris" :
                    try {
                        String IDFav = strings[1];
                        String Url_Supp_Fav = "http://192.168.1.9:8080/SupprimerFav_ProfilUsr.php";
                        URL Supprimer_Fav_Url = new URL(Url_Supp_Fav);
                        HttpURLConnection mHttpUrlConnection = (HttpURLConnection) Supprimer_Fav_Url.openConnection();
                        mHttpUrlConnection.setRequestMethod("POST");
                        mHttpUrlConnection.setDoOutput(true);
                        mHttpUrlConnection.setDoInput(true);
                        OutputStreamWriter mOutputStreamWriter = new OutputStreamWriter(mHttpUrlConnection.getOutputStream());
                        BufferedWriter mBufferedWriter = new BufferedWriter(mOutputStreamWriter);
                        String Post_Date = URLEncoder.encode("IDFav", "UTF-8") + "=" + URLEncoder.encode(IDFav, "UTF-8");
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

                case "Demande de Rendez-Vous" :
                    try {
                        String IDMdc = strings[1];
                        String Url_Demande_RDV_Mdc = "http://192.168.1.9:8080/Demande_RDV_Mdc.php";
                        URL Demande_RDV_Mdc_Url = new URL(Url_Demande_RDV_Mdc);
                        HttpURLConnection mHttpUrlConnection = (HttpURLConnection) Demande_RDV_Mdc_Url.openConnection();
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

                case "Desactiver compte" :
                    try {
                        String ID = strings[1];
                        String Url_DesCmp_Usr = "http://192.168.1.9:8080/Desactiver_Compte.php";
                        URL DesCmp_Usr_Url = new URL(Url_DesCmp_Usr);
                        HttpURLConnection mHttpUrlConnection = (HttpURLConnection) DesCmp_Usr_Url.openConnection();
                        mHttpUrlConnection.setRequestMethod("POST");
                        mHttpUrlConnection.setDoOutput(true);
                        mHttpUrlConnection.setDoInput(true);
                        OutputStreamWriter mOutputStreamWriter = new OutputStreamWriter(mHttpUrlConnection.getOutputStream());
                        BufferedWriter mBufferedWriter = new BufferedWriter(mOutputStreamWriter);
                        String Post_Date = URLEncoder.encode("ID", "UTF-8") + "=" + URLEncoder.encode(ID, "UTF-8");
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


                default:
                    Toast.makeText(getApplicationContext(), "Opération échoué", Toast.LENGTH_SHORT).show();
            }

            return null;
        }


        @Override
        protected void onPostExecute(String result) {

            try {
                ProgressBarMenuUsr.setVisibility(View.GONE);

                JSONObject JOresultat = new JSONObject(result);
                String resultat = JOresultat.getString("resultat");
                //JSONArray valeurs = JOresultat.getJSONArray("valeurs");
                //Log.d("resultat", "onPostExecute: "+ resultat);
                switch (resultat) {
                    case "Infomation recupere":
                        valeurs = JOresultat.getJSONArray("valeurs");
                        Nom_Usr_Profil = (TextView) findViewById(R.id.Nom_usr_profil);
                        Prenom_Usr_Profil = (TextView) findViewById(R.id.Prenom_usr_profil);
                        AdrMail_Usr_Profil = (TextView) findViewById(R.id.AdrMail_usr_profil);
                        DateDeNaissance_Usr_Profil = (TextView) findViewById(R.id.DateDeNaissance_usr_profil);
                        Sexe_Usr_Profil = (TextView) findViewById(R.id.Sexe_usr_profil);
                        NumTel_Usr_Profil = (TextView) findViewById(R.id.NumTel_usr_profil);
                        SpecialiteMdc_Profil = findViewById(R.id.SpecialiteMdc_Profil);
                        AdrPostale_usr_profil = findViewById(R.id.AdrPostale_usr_profil);

                        JSONObject Info_usr = valeurs.getJSONObject(0);

                        Nom_Usr_Profil.setText(Info_usr.getString("NomUsr"));
                        Prenom_Usr_Profil.setText(Info_usr.getString("PrenomUsr"));
                        SpecialiteMdc_Profil.setText(Info_usr.getString("SpecialiteMdc"));
                        AdrMail_Usr_Profil.setText(Info_usr.getString("AdrMail"));
                        DateDeNaissance_Usr_Profil.setText(Info_usr.getString("DateDeNaissanceUsr"));
                        Sexe_Usr_Profil.setText(Info_usr.getString("SexeUsr"));
                        MDP_ProfilUsr = Info_usr.getString("MDP");
                        String sNumTel_Usr_Profil = "0" + Info_usr.getString("NumTelUsr");
                        NumTel_Usr_Profil.setText(sNumTel_Usr_Profil);
                        AdrPostale_ProfilMdc = Info_usr.getString("AdrMdc");
                        Wilya_ProfilMdc = Info_usr.getString("WilayaMdc");
                        String AdrPostale = AdrPostale_ProfilMdc+", "+Wilya_ProfilMdc;
                        AdrPostale_usr_profil.setText(AdrPostale);
                        break;

                    case "Medecin_ext":
                        valeurs = JOresultat.getJSONArray("valeurs");
                        Mdc_liste = new ArrayList<>();
                        Liste_mdc = (ListView) findViewById(R.id.Liste_Mdc);
                        Liste_mdc.setVisibility(View.VISIBLE);
                        NoMdc_TV_ProfilUsr_SearchAdv = findViewById(R.id.NoMdc_TV_ProfilUsr_SearchAdv);
                        NoMdc_TV_ProfilUsr_SearchAdv.setVisibility(View.GONE);
                        for (int i = 0; i < valeurs.length(); i++) {
                            JSONObject Mdc = valeurs.getJSONObject(i);
                            String IDMDC = Mdc.getString("IDMdc");
                            /*String Nom_Prenom = Mdc.getString("NomUsr") + " " + Mdc.getString("PrenomUsr");*/
                            String NomMdc = Mdc.getString("NomUsr");
                            String PrenomMdc = Mdc.getString("PrenomUsr");
                            String Specialite = Mdc.getString("SpecialiteMdc");
                            String SexeMdc = Mdc.getString("SexeUsr");
                            mdc_search mMdc_Search = new mdc_search(IDMDC, NomMdc, PrenomMdc, Specialite, SexeMdc);
                            Mdc_liste.add(mMdc_Search);
                        }

                        MdcListAdapter mMdcListAdapter = new MdcListAdapter(getApplicationContext(), R.layout.adapter_search_mdc, Mdc_liste);
                        Liste_mdc.setAdapter(mMdcListAdapter);

                        Liste_mdc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                mdc_search MDC_Selected = (mdc_search) parent.getItemAtPosition(position);
                                Intent intent = new Intent(getApplicationContext(), Profil_Mdc_search.class);
                                intent.putExtra(ID_Usr, ID);
                                intent.putExtra(ID_Mdc, MDC_Selected.getIDMdc());
                                intent.putExtra(Sexe_Mdc, MDC_Selected.getSexe());
                                getApplicationContext().startActivity(intent);
                            }
                        });

                        break;

                    case "liste favoris recuperer":
                        valeurs = JOresultat.getJSONArray("valeurs");
                        Favoris_Liste = new ArrayList<>();
                        Liste_Favoris = (ListView) findViewById(R.id.Liste_favoris);
                        Liste_Favoris.setVisibility(View.VISIBLE);
                        NoFav_TV_ProfilUsr = findViewById(R.id.NoFav_TV_ProfilUsr);
                        NoFav_TV_ProfilUsr.setVisibility(View.GONE);
                        Btn_Supprimer_Fav.setVisibility(View.GONE);
                        for (int i = 0; i < valeurs.length(); i++) {
                            JSONObject Mdc_Fav = valeurs.getJSONObject(i);
                            String IDFav = Mdc_Fav.getString("IDFav");
                            String IDMDC_Fav = Mdc_Fav.getString("IDMdc");
                            String Nom_Prenom_Fav = Mdc_Fav.getString("NomUsr") + " " + Mdc_Fav.getString("PrenomUsr");
                            String Specialite_Fav = Mdc_Fav.getString("SpecialiteMdc");
                            String SexeMdc_Fav = Mdc_Fav.getString("SexeUsr");

                            mdc_search_favoris mMdc_Fav_Search = new mdc_search_favoris(IDFav,IDMDC_Fav, Nom_Prenom_Fav, Specialite_Fav, SexeMdc_Fav);

                            Favoris_Liste.add(mMdc_Fav_Search);

                        }


                        MdcListAdapter_favoris mFavListAdapter = new MdcListAdapter_favoris(getApplicationContext(), R.layout.adapter_search_mdc, Favoris_Liste);
                        Liste_Favoris.setAdapter(mFavListAdapter);


                        Liste_Favoris.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                mdc_search_favoris MDC_Selected = (mdc_search_favoris) parent.getItemAtPosition(position);
                                Intent intent = new Intent(getApplicationContext(), Profil_Mdc_search.class);
                                intent.putExtra(ID_Usr, ID);
                                intent.putExtra(ID_Mdc, MDC_Selected.getIDMdc());
                                intent.putExtra(Sexe_Mdc, MDC_Selected.getSexe());
                                getApplicationContext().startActivity(intent);
                            }
                        });
                        break;


                    case "liste favoris non recuperer":
                        Liste_Favoris = (ListView) findViewById(R.id.Liste_favoris);
                        Liste_Favoris.setVisibility(View.GONE);
                        NoFav_TV_ProfilUsr = findViewById(R.id.NoFav_TV_ProfilUsr);
                        NoFav_TV_ProfilUsr.setVisibility(View.VISIBLE);
                        break;


                    case "Messagrie recuperer":
                        valeurs = JOresultat.getJSONArray("valeurs");
                        Messages_Liste = new ArrayList<>();
                        Liste_Messages = findViewById(R.id.Liste_Messages);
                        Liste_Messages.setVisibility(View.VISIBLE);
                        NoMsg_TV_ProfilUsr = findViewById(R.id.NoMsg_TV_ProfilUsr);
                        NoMsg_TV_ProfilUsr.setVisibility(View.GONE);
                        Btn_Supprimer.setVisibility(View.GONE);
                        for (int i = 0; i < valeurs.length(); i++) {
                            JSONObject Message = valeurs.getJSONObject(i);
                            String IDCmpUsr = Message.getString("IDCmpUsr");
                            String IDmsg = Message.getString("IDmsg");
                            String ContenueMsg = Message.getString("ContenueMsg");
                            String DateHeure = Message.getString("DateHeure");
                            String Nom_Prenom_Mdc = Message.getString("NomUsr") + " " + Message.getString("PrenomUsr");
                            int[] array_Date = new int[]{8, 9, 5, 6, 0, 1, 2, 3};
                            String Date = "";
                            for (int j = 0; j < array_Date.length; j++) {
                                Date += DateHeure.charAt(array_Date[j]);
                                if (array_Date[j] == 9 || array_Date[j] == 6) {
                                    Date += "/";
                                }
                            }
                            String Heure = "";
                            for (int z = 11; z <= 15; z++) {
                                Heure += DateHeure.charAt(z);
                            }

                            //Log.d("resultat", "onPostExecute: "+Date);
                            //Log.d("resultat", "onPostExecute: "+Heure);
                            Messages_MenuMdc mMessages_MenuUsr = new Messages_MenuMdc(IDCmpUsr, IDmsg, ContenueMsg, Date, Heure, Nom_Prenom_Mdc);
                            Messages_Liste.add(mMessages_MenuUsr);

                        }
                        MessagesAdapterMdc mMessagesAdapter = new MessagesAdapterMdc(getApplicationContext(), R.layout.adapter_messages, Messages_Liste);
                        Liste_Messages.setAdapter(mMessagesAdapter);

                        Liste_Messages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                /*if (Btn_Supprimer.getVisibility() == View.VISIBLE){
                                    Messages_MenuUsr_CheckBox mMessage_Selected = (Messages_MenuUsr_CheckBox) parent.getItemAtPosition(position);
                                    if (!mMessage_Selected.getSelected()){
                                        mMessage_Selected.setSelected(true);
                                    }else{
                                        mMessage_Selected.setSelected(false);
                                    }

                                }else{*/
                                Messages_MenuMdc mMessage_Selected = (Messages_MenuMdc) parent.getItemAtPosition(position);
                                Intent intent = new Intent(getApplicationContext(), Message_ProfilMdc.class);
                                intent.putExtra(Contenue_Msg, mMessage_Selected.getContenueMsg());
                                intent.putExtra(NomPrenomMdc_Msg, mMessage_Selected.getNom_Prenom_Mdc());
                                intent.putExtra(Date_Msg, mMessage_Selected.getDate());
                                intent.putExtra(Heure_Msg, mMessage_Selected.getHeure());
                                intent.putExtra(IDMdc_Msg, ID);
                                intent.putExtra(IDUsr_Msg, mMessage_Selected.getIDUsr());
                                getApplicationContext().startActivity(intent);


                            }

                            /*}*/
                        });

                        break;


                    case "Messagerie non recuperer":
                        Liste_Messages = findViewById(R.id.Liste_Messages);
                        Liste_Messages.setVisibility(View.GONE);
                        NoMsg_TV_ProfilUsr = findViewById(R.id.NoMsg_TV_ProfilUsr);
                        NoMsg_TV_ProfilUsr.setVisibility(View.VISIBLE);
                        break;

                    case "rendez-vous recuperer":

                        valeurs = JOresultat.getJSONArray("valeurs");
                        final ArrayList<RDV_ProfilUsr> RDV_Liste = new ArrayList<>();
                        Liste_RDV = findViewById(R.id.Liste_RDV);
                        Liste_RDV.setVisibility(View.VISIBLE);
                        NoRDV_TV_ProfilUsr = findViewById(R.id.NoRDV_TV_ProfilUsr);
                        NoRDV_TV_ProfilUsr.setVisibility(View.GONE);

                        for (int i = 0; i < valeurs.length(); i++) {
                            JSONObject RDV = valeurs.getJSONObject(i);
                            String DateHeure = RDV.getString("DateHeure");
                            String Nom_Prenom_MdcRdv = RDV.getString("NomUsr") + " " + RDV.getString("PrenomUsr");
                            String Specialite = RDV.getString("SpecialiteMdc");
                            int[] array_Date = new int[]{8, 9, 5, 6, 0, 1, 2, 3};
                            String Date = "";
                            for (int j = 0; j < array_Date.length; j++) {
                                Date += DateHeure.charAt(array_Date[j]);
                                if (array_Date[j] == 9 || array_Date[j] == 6) {
                                    Date += "/";
                                }
                            }
                            String Heure = "";
                            for (int z = 11; z <= 15; z++) {
                                Heure += DateHeure.charAt(z);
                            }

                            RDV_ProfilUsr mRDV_ProfilUsr = new RDV_ProfilUsr(Nom_Prenom_MdcRdv, Specialite, Heure, Date);
                            RDV_Liste.add(mRDV_ProfilUsr);
                        }

                        RDVAdapter mRDVListAdapter = new RDVAdapter(getApplicationContext(), R.layout.adapter_search_mdc, RDV_Liste);
                        Liste_RDV.setAdapter(mRDVListAdapter);

                        Liste_RDV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                RDV_ProfilUsr RDV_selected = (RDV_ProfilUsr) parent.getItemAtPosition(position);
                                Intent intent = new Intent(getApplicationContext(), RDV_Profil.class);
                                intent.putExtra(Nom_Prenom_Mdc_Rdv, RDV_selected.getNom_Prenom_Rdv());
                                intent.putExtra(Specialite_Mdc_Rdv, RDV_selected.getSpecialite_Rdv());
                                intent.putExtra(Date_Rdv, RDV_selected.getDate_Rdv());
                                intent.putExtra(Heure_Rdv, RDV_selected.getHeure_Rdv());
                                getApplicationContext().startActivity(intent);
                            }
                        });


                        break;

                    case "rendez-vous non recuperer":
                        Liste_RDV = findViewById(R.id.Liste_RDV);
                        Liste_RDV.setVisibility(View.GONE);
                        NoRDV_TV_ProfilUsr = findViewById(R.id.NoRDV_TV_ProfilUsr);
                        NoRDV_TV_ProfilUsr.setVisibility(View.VISIBLE);

                        break;

                    case "Message Supprime" :
                        Toast.makeText(Menu_Mdc.this, "Messages Supprimés", Toast.LENGTH_LONG).show();
                        break;

                    case "Medecin Supprime des Favoris" :
                        Toast.makeText(Menu_Mdc.this, "Medecin Supprimer", Toast.LENGTH_LONG).show();
                        break;

                    case "DemandeRDVMdc_Ext" :
                        valeurs = JOresultat.getJSONArray("valeurs");
                        DemandeRDVMdc_Liste = new ArrayList<>();
                        Liste_Dem_RDV = (ListView) findViewById(R.id.Liste_Dem_RDV);
                        Liste_Dem_RDV.setVisibility(View.VISIBLE);
                        NoDemRDV_TV_ProfilUsr = findViewById(R.id.NoDemRDV_TV_ProfilUsr);
                        NoDemRDV_TV_ProfilUsr.setVisibility(View.GONE);
                        for (int i = 0; i < valeurs.length(); i++) {
                            JSONObject Dem_RDV_Mdc = valeurs.getJSONObject(i);
                            String IDRdv_DemRDVMdc = Dem_RDV_Mdc.getString("IDRdv");
                            String NomUsr_DemRDVMdc = Dem_RDV_Mdc.getString("NomUsr");
                            String PrenomUsr_DemRDVMdc = Dem_RDV_Mdc.getString("PrenomUsr");
                            String SexeMdc_DemRDVMdc = Dem_RDV_Mdc.getString("SexeUsr");

                            Demande_RDV_Mdc DemRDVMdc = new Demande_RDV_Mdc(IDRdv_DemRDVMdc,NomUsr_DemRDVMdc, PrenomUsr_DemRDVMdc, SexeMdc_DemRDVMdc);

                            DemandeRDVMdc_Liste.add(DemRDVMdc);

                        }


                        DemandeRDVMdcAdapter mDemandeRDVMdcAdapter = new DemandeRDVMdcAdapter(getApplicationContext(), R.layout.adapter_demande_rdv_mdc, DemandeRDVMdc_Liste);
                        Liste_Dem_RDV.setAdapter(mDemandeRDVMdcAdapter);


                        Liste_Dem_RDV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Demande_RDV_Mdc Demande_RDV_Mdc_Selected = (Demande_RDV_Mdc) parent.getItemAtPosition(position);
                                Intent intent = new Intent(getApplicationContext(),DemandeRDVMdc.class);
                                intent.putExtra(IDRdv_DemandeRDVMdc,Demande_RDV_Mdc_Selected.getIDRdv());
                                intent.putExtra(NomUsr_DemandeRDVMdc,Demande_RDV_Mdc_Selected.getNomUsr());
                                intent.putExtra(PrenomUsr_DemandeRDVMdc,Demande_RDV_Mdc_Selected.getPrenomUsr());
                                intent.putExtra(SexeUsr_DemandeRDVMdc,Demande_RDV_Mdc_Selected.getSexeUsr());
                                getApplicationContext().startActivity(intent);

                            }
                        });

                        break;


                    case "DemandeRDVMdc_noExt" :
                        Liste_Dem_RDV = (ListView) findViewById(R.id.Liste_Dem_RDV);
                        Liste_Dem_RDV.setVisibility(View.GONE);
                        NoDemRDV_TV_ProfilUsr = findViewById(R.id.NoDemRDV_TV_ProfilUsr);
                        NoDemRDV_TV_ProfilUsr.setVisibility(View.VISIBLE);
                        break;

                    case "Compte desactiver" :
                        Toast.makeText(Menu_Mdc.this, "Compte désactivé", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        break;

                    case "Compte non desactive" :
                        Toast.makeText(Menu_Mdc.this, "Problème dans la désactivation du compte", Toast.LENGTH_LONG).show();

                        break;


                    default:
                        Toast.makeText(getApplicationContext(), "Operation échoué", Toast.LENGTH_LONG).show();

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }
}
