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

public class AdapterCommentaire extends ArrayAdapter<Commentaires> {

    Context Ctxt;
    int mRessource;

    public AdapterCommentaire(Context context, int resource, ArrayList<Commentaires> objects) {
        super(context, resource, objects);
        Ctxt = context;
        mRessource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String NomPrenom = getItem(position).getNomPrenom();
        String Contenue = getItem(position).getContenue();


        LayoutInflater mInflater = LayoutInflater.from(Ctxt);
        convertView = mInflater.inflate(mRessource, parent,false);

        TextView Adapter_Commentaire_NomPrenom = convertView.findViewById(R.id.Adapter_Commentaire_NomPrenom);
        TextView Adapter_Commentaire_Contenue = convertView.findViewById(R.id.Adapter_Commentaire_ContenueCom);

        Adapter_Commentaire_NomPrenom.setText(NomPrenom);
        Adapter_Commentaire_Contenue.setText(Contenue);

        return convertView;

    }
}
