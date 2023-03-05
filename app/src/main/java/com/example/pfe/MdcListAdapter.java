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
import java.util.zip.Inflater;

public class MdcListAdapter extends ArrayAdapter<mdc_search> {

    Context Ctxt;
    int mRessource;


    public MdcListAdapter(Context context, int resource, ArrayList<mdc_search> objects) {
        super(context, resource, objects);
        Ctxt = context;
        mRessource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String NomMdc = getItem(position).getNomMdc();
        String PrenomMdc = getItem(position).getPrenomMdc();
        String Specialite = getItem(position).getSpecialite();

        //mdc_search mMdc_Search = new mdc_search(Nom_Prenom,Specialite);
        LayoutInflater mInflater = LayoutInflater.from(Ctxt);
        convertView = mInflater.inflate(mRessource,parent,false);

        TextView Nom_Prenom_Mdc_TextView = convertView.findViewById(R.id.Nom_Prenom_Mdc_TextView);
        TextView specialite_Mdc_TextView = convertView.findViewById(R.id.specialite_Mdc_TextView);

        String Nom_Prenom = NomMdc + " " + PrenomMdc;
        Nom_Prenom_Mdc_TextView.setText(Nom_Prenom);
        specialite_Mdc_TextView.setText(Specialite);

        return convertView;
    }
}
