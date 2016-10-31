package com.acer.acer.android_version;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ACER on 10/24/2016.
 */

public class Adapter_android extends RecyclerView.Adapter<Adapter_android.ViewHolder> {
    private Context context;
    List<Model> oop;


    public Adapter_android(List<Model> oop, Context context){
        super();
        //Getting all the superheroes
        this.oop = oop;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Model model_android =  oop.get(position);
        holder.txtver.setText(model_android.getVersi());
        holder.txtname.setText(model_android.getNama());
        holder.txtapi.setText(model_android.getApi());
        holder.model = model_android;
    }

    @Override
    public int getItemCount() {
        return oop.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtver,txtname;
        public TextView txtapi;
        Model model;
        public ViewHolder(View itemView) {
            super(itemView);
            txtver = (TextView) itemView.findViewById(R.id.txtver);
            txtname = (TextView) itemView.findViewById(R.id.txtname);
            txtapi= (TextView) itemView.findViewById(R.id.txtapi);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
