package com.example.cs246team22.printingapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class SpoolListAdapter extends RecyclerView.Adapter<SpoolListAdapter.SpoolViewHolder> {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public static String TAG = "SpoolListAdapter";

     static class SpoolViewHolder extends RecyclerView.ViewHolder {

         private final TextView spoolIDView;
         private final TextView spoolNameView;
         private final TextView spoolBrandView;
         private final TextView spoolColorView;
         private final TextView spoolWeightView;
         private final TextView spoolMaterialView;
         private ImageButton mRemoveButton;

        private SpoolViewHolder(View itemView) {
            super(itemView);
            spoolIDView = itemView.findViewById(R.id.textViewID);
            spoolNameView = itemView.findViewById(R.id.textViewName);
            spoolBrandView = itemView.findViewById(R.id.textViewBrand);
            spoolColorView = itemView.findViewById(R.id.textViewColor);
            spoolWeightView = itemView.findViewById(R.id.textViewWeight);
            spoolMaterialView = itemView.findViewById(R.id.textViewMaterial);

        }
    }

    private final LayoutInflater mInflater;
    private List<Spool> mSpools; // Cached copy of spools

    SpoolListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @NonNull
    @Override
    public SpoolViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new SpoolViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SpoolViewHolder holder, final int position) {
        if (mSpools != null) {
            Spool current = mSpools.get(position);

            //This adds whatever is in the RecView to the Cloud
            String docID = Integer.toString(current.getSpoolID());
            db.collection("spools").document(docID).set(current);

            holder.spoolIDView.setText("ID: " + current.getSpoolID());
            holder.spoolNameView.setText("Name: " + current.getSpoolName());
            holder.spoolBrandView.setText("Brand: " + current.getSpoolBrand());
            holder.spoolColorView.setText("Color: " + current.getSpoolColor());
            holder.spoolWeightView.setText("Weight: " + current.getSpoolWeight() + " g");
            holder.spoolMaterialView.setText("Material: " + current.getSpoolMaterial());
            if(position %2 == 1)
            {
                holder.itemView.setBackgroundColor(Color.DKGRAY);
            }
    else
            {
                holder.itemView.setBackgroundColor(Color.GRAY);
                //holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));

            }
        } else {
            // Covers the case of data not being ready yet.
            holder.spoolIDView.setText("No Spool");
        }

    }



    void setSpools(List<Spool> spools){
        mSpools = spools;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mSpools has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mSpools != null)
            return mSpools.size();
        else return 0;
    }

    public Spool getSpoolAtPosition (int position) {
        return mSpools.get(position);
    }
}

