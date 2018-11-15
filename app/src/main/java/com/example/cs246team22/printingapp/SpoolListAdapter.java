package com.example.cs246team22.printingapp;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class SpoolListAdapter extends RecyclerView.Adapter<SpoolListAdapter.SpoolViewHolder> {

    class SpoolViewHolder extends RecyclerView.ViewHolder {
        private final TextView spoolItemView;

        private SpoolViewHolder(View itemView) {
            super(itemView);
            spoolItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Spool> mSpools; // Cached copy of words

    SpoolListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public SpoolViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new SpoolViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SpoolViewHolder holder, int position) {
        if (mSpools != null) {
            Spool current = mSpools.get(position);
            holder.spoolItemView.setText(current.getID());
        } else {
            // Covers the case of data not being ready yet.
            holder.spoolItemView.setText("No Spool");
        }
    }

    void setSpools(List<Spool> spools){
        mSpools = spools;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mSpools != null)
            return mSpools.size();
        else return 0;
    }
}