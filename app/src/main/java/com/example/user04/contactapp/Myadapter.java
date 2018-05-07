package com.example.user04.contactapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER04 on 2/19/2018.
 */

public class Myadapter extends RecyclerView.Adapter<Myadapter.ViewHolder> {

    private List<listitem> listitems;
    private Context context;

    DatabaseHelper contactdb;

    public Myadapter(List<listitem> listitems, Context context) {
        this.listitems = listitems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_category, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        listitem listItem = listitems.get(position);

        holder.name.setText(listItem.getName());
        holder.number.setText(listItem.getNumber());
        holder.email.setText(listItem.getEmail());
        holder.company.setText(listItem.getCompany());


//        holder.imageView.setImageResource(listItem.getCompany());


    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView number ;
        public TextView email;
        public TextView company;
        public ImageView img;


        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);

            number = (TextView) itemView.findViewById(R.id.number);

            email= (TextView) itemView.findViewById(R.id.email);
            company= (TextView) itemView.findViewById(R.id.company);
            img=(ImageView) itemView.findViewById(R.id.favimg);
            contactdb = new DatabaseHelper(context);

            itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {




                    listitem listItem = listitems.get(getLayoutPosition());
                    Intent myIntent = new Intent(context, EditContact.class);
                    myIntent.putExtra("id", listItem.getName()); //Optional parameters
                    myIntent.putExtra("name", listItem.getName()); //Optional parameters
                    myIntent.putExtra("number", listItem.getNumber()); //Optional parameters
                    myIntent.putExtra("email", listItem.getEmail()); //Optional parameters
                    myIntent.putExtra("company", listItem.getCompany());
                    context.startActivity(myIntent);

                }
            });

            img.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

                    img.setImageResource(R.drawable.ic_favorite_black_24dp);
                    boolean isfav = contactdb.addfavourite(name.getText().toString(),number.getText().toString(),email.getText().toString(),company.getText().toString());
                    if(isfav==true)
                        Toast.makeText(context,"Add in favourite",Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(context,"Failed to add in favourite",Toast.LENGTH_SHORT).show();
                }
            });




        }

    }

    public void setFilter(List<listitem> filter){
        listitems = new ArrayList<>();
        listitems.addAll(filter);
        notifyDataSetChanged();
    }




}

