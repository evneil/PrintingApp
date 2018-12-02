package com.example.cs246team22.printingapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class SpoolListAdapter extends RecyclerView.Adapter<SpoolListAdapter.SpoolViewHolder> {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    class SpoolViewHolder extends RecyclerView.ViewHolder {
        private final TextView spoolItemView;

        private SpoolViewHolder(View itemView) {
            super(itemView);
            spoolItemView = itemView.findViewById(R.id.textViewName);
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
    public void onBindViewHolder(@NonNull SpoolViewHolder holder, int position) {
        if (mSpools != null) {
            final Spool current = mSpools.get(position);



            /*


            db.collection("spools")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d("Firebase", document.getId() + " => " + document.getData());
                                    int tID = Integer.parseInt(document.getId());
                                    if (tID == Integer.parseInt(document.getId())){
                                        current.setSpoolID(Integer.parseInt(document.getId()) + 1);
                                    }

                                }
                            } else {
                                Log.d("Firebase", "Error getting documents: ", task.getException());
                            }
                        }
                    });
            */

            //This adds whatever is in the RecView to the Cloud
            String docID = Integer.toString(current.getSpoolID());
            db.collection("spools").document(docID).set(current);


            //holder.spoolItemView.setText(current.getSpoolID());
            //
            // THIS IS REALLY BAD CODE PRACTICE but it's okay, in this application
            // there will be not need to translate

            holder.spoolItemView.setText(current.getSpoolID() + " " + current.getSpoolName() + " " + current.getSpoolBrand()
                    + " " + current.getSpoolColor() + " " + current.getSpoolWeight() + " " + current.getSpoolMaterial());
            //holder.spoolItemView.setText(current.getSpoolBrand());
            //holder.spoolItemView.setText(current.getSpoolColor());
            //holder.spoolItemView.setText(current.getSpoolWeight());
            //holder.spoolItemView.setText(current.getSpoolMaterial());
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
    // mSpools has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mSpools != null)
            return mSpools.size();
        else return 0;
    }
}

