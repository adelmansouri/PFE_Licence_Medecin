package com.example.pfe;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

public class MessagesAdapter_CheckBoxMdc extends ArrayAdapter<Messages_MenuMdc_CheckBox> {

    ArrayList<Messages_MenuMdc_CheckBox> MessageListe;
    Context Ctxt;
    int mRessource;

    public MessagesAdapter_CheckBoxMdc(Context context, int resource, ArrayList<Messages_MenuMdc_CheckBox> objects) {
        super(context, resource, objects);
        MessageListe = new ArrayList<>();
        MessageListe.addAll(objects);
        Ctxt = context;
        this.mRessource = resource;
    }

    private class ViewHolder{
        TextView Date;
        TextView Heure;
        TextView Nom_Prenom_Mdc;
        TextView SpecialiteMdc;
        CheckBox CheckBox_Supp;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /*String Date = getItem(position).getDate();
        String Heure = getItem(position).getHeure();
        String Nom_Prenom_Mdc = getItem(position).getNom_Prenom_Mdc();
        String SpecialiteMdc = getItem(position).getSpecialitéMdc();
        final Boolean Selected = getItem(position).getSelected();*/

        ViewHolder holder = null;
        if (convertView == null){
            LayoutInflater mInflater = LayoutInflater.from(Ctxt);
            convertView = mInflater.inflate(mRessource,parent,false);


            holder = new ViewHolder();
            holder.Nom_Prenom_Mdc = convertView.findViewById(R.id.Nom_Prenom_Mdc_Msg_TextView);
            holder.SpecialiteMdc = convertView.findViewById(R.id.specialite_Mdc_Msg_TextView);
            holder.Heure =  convertView.findViewById(R.id.Heure_Msg_TextView);
            holder.Date = convertView.findViewById(R.id.Date_Msg_TextView);
            holder.CheckBox_Supp = convertView.findViewById(R.id.Supp_CheckBox);

            holder.CheckBox_Supp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int getposition = (Integer) buttonView.getTag();
                    MessageListe.get(getposition).setSelected(buttonView.isChecked());
                }
            });

            convertView.setTag(holder);

        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.CheckBox_Supp.setTag(position);
        holder.Nom_Prenom_Mdc.setText(MessageListe.get(position).getNom_Prenom_Mdc());
        holder.SpecialiteMdc.setText("");
        holder.Heure.setText(MessageListe.get(position).getHeure());
        holder.Date.setText(MessageListe.get(position).getDate());
        holder.CheckBox_Supp.setChecked(MessageListe.get(position).getSelected());











       /* Nom_Prenom_Mdc_Msg_TextView.setText(Nom_Prenom_Mdc);
        Specialite_Mdc_Msg_TextView.setText(SpecialiteMdc);
        Heure_Msg_TextView.setText(Heure);
        Date_Msg_TextView.setText(Date);
        Check_Box_Supp.setChecked(Selected);
        Check_Box_Supp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Check_Box_Supp.isChecked()){

                }else{

                }
            }
        });
*/



        return convertView;

    }


}
