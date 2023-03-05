package com.example.pfe;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DemandeRDVMdcAdapter extends ArrayAdapter<Demande_RDV_Mdc> {
    Context Ctxt;
    int mRessource;

    public DemandeRDVMdcAdapter(Context context, int resource, ArrayList<Demande_RDV_Mdc> objects) {
        super(context, resource, objects);
        Ctxt = context;
        mRessource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String Nom = getItem(position).getNomUsr();
        String Prenom = getItem(position).getPrenomUsr();
        String Nom_Prenom = Nom+" "+Prenom;

        LayoutInflater mInflater = LayoutInflater.from(Ctxt);
        convertView = mInflater.inflate(mRessource,parent,false);

        TextView Nom_Prenom_Mdc_TextView = convertView.findViewById(R.id.Nom_Prenom_Usr_TextView);


        Nom_Prenom_Mdc_TextView.setText(Nom_Prenom);

        return convertView;


    }
}
