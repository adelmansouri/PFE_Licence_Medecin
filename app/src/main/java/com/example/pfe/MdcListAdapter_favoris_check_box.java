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

public class MdcListAdapter_favoris_check_box extends ArrayAdapter<mdc_search_favoris_check_box> {

    ArrayList<mdc_search_favoris_check_box> FavorisListe;
    Context Ctxt;
    int mRessource;


    public MdcListAdapter_favoris_check_box(Context context, int resource, ArrayList<mdc_search_favoris_check_box> objects) {
        super(context, resource, objects);
        FavorisListe = new ArrayList<>();
        FavorisListe.addAll(objects);
        Ctxt = context;
        mRessource = resource;
    }

    private class ViewHolder{
        TextView Nom_Prenom_Mdc;
        TextView SpecialiteMdc;
        CheckBox CheckBox_Supp_Fav;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        /*String Nom_Prenom = getItem(position).getNom_Prenom();
        String Specialite = getItem(position).getSpecialite();*/

        ViewHolder holder = null;
        if (convertView == null){
            LayoutInflater mInflater = LayoutInflater.from(Ctxt);
            convertView = mInflater.inflate(mRessource,parent,false);


            holder = new ViewHolder();
            holder.Nom_Prenom_Mdc = convertView.findViewById(R.id.Nom_Prenom_Fav_TextView);
            holder.SpecialiteMdc = convertView.findViewById(R.id.specialite_Fav_TextView);
            holder.CheckBox_Supp_Fav = convertView.findViewById(R.id.Supp_CheckBox_Fav);

            holder.CheckBox_Supp_Fav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int getposition = (Integer) buttonView.getTag();
                    FavorisListe.get(getposition).setSelected(buttonView.isChecked());
                }
            });

            convertView.setTag(holder);

        }else{
            holder = (ViewHolder) convertView.getTag();
        }


        holder.CheckBox_Supp_Fav.setTag(position);
        holder.Nom_Prenom_Mdc.setText(FavorisListe.get(position).getNom_Prenom());
        holder.SpecialiteMdc.setText(FavorisListe.get(position).getSpecialite());
        holder.CheckBox_Supp_Fav.setChecked(FavorisListe.get(position).getSelected());

        return convertView;
    }
}
