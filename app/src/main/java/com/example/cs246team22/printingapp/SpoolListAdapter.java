package com.example.cs246team22.printingapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
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
        private final TextView spoolItemView;
        private ImageButton mRemoveButton;

        private SpoolViewHolder(View itemView) {
            super(itemView);
            spoolItemView = itemView.findViewById(R.id.textViewName);
            mRemoveButton = (ImageButton) itemView.findViewById(R.id.ib_remove);

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


            //holder.spoolItemView.setText(current.getSpoolID());
            //
            // THIS IS REALLY BAD CODE PRACTICE but it's okay, in this application
            // there will be not need to translate

            holder.spoolItemView.setText("ID: " + current.getSpoolID() + " | Name: " + current.getSpoolName() + " | Brand: " + current.getSpoolBrand()
                    + " | Color: " + current.getSpoolColor() + " | Weight: " + current.getSpoolWeight() + "grams | Material: " + current.getSpoolMaterial());
            //holder.spoolItemView.setText(current.getSpoolBrand());
            //holder.spoolItemView.setText(current.getSpoolColor());
            //holder.spoolItemView.setText(current.getSpoolWeight());
            //holder.spoolItemView.setText(current.getSpoolMaterial());
        } else {
            // Covers the case of data not being ready yet.
            holder.spoolItemView.setText("No Spool");
        }

        holder.mRemoveButton.setOnClickListener(new View.OnClickListener() {
            SpoolViewModel mSpoolViewModel;
            @Override
            public void onClick(View view) {
                Spool current = mSpools.get(position);
                String cID = Integer.toString(current.getSpoolID());

                // Remove the item on remove/button click
                mSpools.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, mSpools.size());



                //Delete From Cloud too
                db.collection("spools").document(cID)
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "DocumentSnapshot successfully deleted!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error deleting document", e);
                            }
                        });

            }
        });
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

