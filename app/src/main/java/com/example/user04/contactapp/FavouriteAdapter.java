package com.example.user04.contactapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER04 on 2/22/2018.
 */

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder> {
    private Context mcontext;
    private List<listitem> favourites;
    DatabaseHelper contactdb;

    public FavouriteAdapter(Context mCtx, List<listitem> favourites){
        this.mcontext=mCtx;
        this.favourites=favourites;
    }


    @Override
    public FavouriteAdapter.FavouriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.favlist,parent,false);
        return new FavouriteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FavouriteAdapter.FavouriteViewHolder holder, int position) {
        listitem contact = favourites.get(position);
        holder.txtname.setText(contact.getName());
        holder.txtphone.setText(contact.getEmail());
        holder.txtmail.setText(contact.getNumber());
        holder.txtcompany.setText(contact.getCompany());

    }

    @Override
    public int getItemCount() {
        return favourites.size();
    }

    public class FavouriteViewHolder extends RecyclerView.ViewHolder{
        TextView txtname,txtphone,txtmail,txtcompany;
        public FavouriteViewHolder(View itemView) {
            super(itemView);

            txtname = itemView.findViewById(R.id.name1);
            txtphone = itemView.findViewById(R.id.number1);
            txtmail = itemView.findViewById(R.id.email1);
            txtcompany = itemView.findViewById(R.id.company1);
            contactdb = new DatabaseHelper(mcontext);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(mcontext);
                    builder.setMessage("Remove form favourite?")
                            .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Integer favdlt = contactdb.favdelete(txtname.getText().toString());
                                    if(favdlt>0)
                                        Toast.makeText(mcontext,"Contact is deleted",Toast.LENGTH_LONG).show();
                                    else
                                        Toast.makeText(mcontext,"Contact is not deleted",Toast.LENGTH_LONG).show();
                                }
                            }).setNegativeButton("no",null);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    return true;
                }
            });
        }
    }

    public void setFilter(List<listitem> filter){
        favourites = new ArrayList<>();
        favourites.addAll(filter);
        notifyDataSetChanged();
    }
}
