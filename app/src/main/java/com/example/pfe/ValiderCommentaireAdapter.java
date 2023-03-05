package com.example.pfe;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ValiderCommentaireAdapter extends ArrayAdapter<ValiderCommentaire_ProfilAdm> {

    Context Ctxt;
    int mRessource;

    public ValiderCommentaireAdapter(Context context, int resource, ArrayList<ValiderCommentaire_ProfilAdm> objects) {
        super(context, resource, objects);
        Ctxt = context;
        mRessource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String NomUsr = getItem(position).getNomUsr();
        String PrenomUsr = getItem(position).getPrenomUsr();
        String NomPrenom = NomUsr+" "+PrenomUsr;
        String ContenueCom = getItem(position).getContenueCom();


        LayoutInflater mInflater = LayoutInflater.from(Ctxt);
        convertView = mInflater.inflate(mRessource, parent,false);

        TextView Adapter_ValiderCommentaire_NomPrenom = convertView.findViewById(R.id.Adapter_ValiderCommentaire_NomPrenom);
        TextView Adapter_ValiderCommentaire_Contenue = convertView.findViewById(R.id.Adapter_ValiderCommentaire_ContenueCom);

        Adapter_ValiderCommentaire_NomPrenom.setText(NomPrenom);
        Adapter_ValiderCommentaire_Contenue.setText(ContenueCom);

        return convertView;

    }
}
