package com.example.pfe;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

public class RDVAdapter extends ArrayAdapter<RDV_ProfilUsr> {
    Context Ctxt;
    int mRessource;

    public RDVAdapter(Context context, int resource, ArrayList<RDV_ProfilUsr> objects) {
        super(context, resource, objects);
        Ctxt = context;
        mRessource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String Nom_Prenom = getItem(position).getNom_Prenom_Rdv();
        String Specialite = getItem(position).getSpecialite_Rdv();

        LayoutInflater mInflater = LayoutInflater.from(Ctxt);
        convertView = mInflater.inflate(mRessource,parent,false);

        TextView Nom_Prenom_Mdc_TextView = convertView.findViewById(R.id.Nom_Prenom_Mdc_TextView);
        TextView specialite_Mdc_TextView = convertView.findViewById(R.id.specialite_Mdc_TextView);

        Nom_Prenom_Mdc_TextView.setText(Nom_Prenom);
        specialite_Mdc_TextView.setText(Specialite);

        return convertView;


    }
}
