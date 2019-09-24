package com.emeryc.repos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.emeryc.repos.transfer.Repos;

import java.util.List;

public class Adapteur extends RecyclerView.Adapter<Adapteur.MyViewHolder>{
    public Context context;
    public List<Repos> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tvName;
        public MyViewHolder(LinearLayout v) {
            super(v);
            tvName = v.findViewById(R.id.textItem);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public Adapteur(List<Repos> pDataset, Context ctx) {
        mDataset = pDataset;
        this.context = ctx;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public Adapteur.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {
        // create a new view
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.layout, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Repos objetActuel = mDataset.get(position);
        holder.tvName.setText(String.valueOf(objetActuel.name));
    }



    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}