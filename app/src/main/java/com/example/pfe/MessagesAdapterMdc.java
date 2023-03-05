package com.example.pfe;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MessagesAdapterMdc extends ArrayAdapter<Messages_MenuMdc> {

    Context Ctxt;
    int mRessource;

    public MessagesAdapterMdc(Context context, int resource, ArrayList<Messages_MenuMdc> objects) {
        super(context, resource, objects);
        Ctxt = context;
        this.mRessource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String Date = getItem(position).getDate();
        String Heure = getItem(position).getHeure();
        String Nom_Prenom_Mdc = getItem(position).getNom_Prenom_Mdc();


        if (convertView == null){
            LayoutInflater mInflater = LayoutInflater.from(Ctxt);
            convertView = mInflater.inflate(mRessource,parent,false);
        }


        TextView Nom_Prenom_Mdc_Msg_TextView = convertView.findViewById(R.id.Nom_Prenom_Mdc_Msg_TextView);
        TextView Specialite_Mdc_Msg_TextView = convertView.findViewById(R.id.specialite_Mdc_Msg_TextView);
        TextView Heure_Msg_TextView = convertView.findViewById(R.id.Heure_Msg_TextView);
        TextView Date_Msg_TextView = convertView.findViewById(R.id.Date_Msg_TextView);

        Nom_Prenom_Mdc_Msg_TextView.setText(Nom_Prenom_Mdc);
        Specialite_Mdc_Msg_TextView.setText("");
        Heure_Msg_TextView.setText(Heure);
        Date_Msg_TextView.setText(Date);

        return convertView;

    }
}
