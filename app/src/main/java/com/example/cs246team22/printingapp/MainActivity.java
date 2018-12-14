package com.example.cs246team22.printingapp;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;


import android.content.Intent;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SpoolViewModel mSpoolViewModel;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    SwipeController swipeController = null;

    public static String TAG = "MainActivity";

    public static final int NEW_SPOOL_ACTIVITY_REQUEST_CODE = 1;
    public static final int PRINT_JOB_ACTIVITY_REQUEST_CODE = 2;
    public static final int WEIGHT_ACTIVITY_REQUEST_CODE = 3;
    public static int SPOOL_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.spoolList);
        final SpoolListAdapter adapter = new SpoolListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Get current ViewModel
        mSpoolViewModel = ViewModelProviders.of(this).get(SpoolViewModel.class);


        //observer for live data, needed to recycler view
        mSpoolViewModel.getAllSpools().observe(this, new Observer<List<Spool>>() {
            @Override
            public void onChanged(@Nullable final List<Spool> spools) {
                // Update the cached copy of the spools in the adapter.
                adapter.setSpools(spools);
            }

        });


        swipeController = new SwipeController(new SwipeControllerActions() {
            @Override
            public void onRightClicked(int position) {
                Spool mySpool = adapter.getSpoolAtPosition(position);
                String cID = Integer.toString(mySpool.getSpoolID());
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
                mSpoolViewModel.deleteSpool(mySpool);
                setID();
            }

            public void onLeftClicked(int position) {
                Spool mySpool = adapter.getSpoolAtPosition(position);
                String cID = Integer.toString(mySpool.getSpoolID());
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
                mSpoolViewModel.deleteSpool(mySpool);
                setID();
            }
        });

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(recyclerView);

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });


        // Add the functionality to swipe items in the
        // recycler view to delete that item
        //This I intend to remove and just use the delete button located in the listadapter


        Log.d("test","app created");

        //initialize firebase and get all the spools
        setID();
        syncCloud();
    }

    @Override
    protected void onDestroy() {
        SpoolRoomDatabase.destroyInstance();
        super.onDestroy();
    }

    public void printJob(View view) {
        Intent intent = new Intent(this, PrintJobActivity.class);
        startActivityForResult(intent, PRINT_JOB_ACTIVITY_REQUEST_CODE );
        Log.d("test","print clicked");
    }

    public void onWeight(View view) {
        Intent intent = new Intent(this, WeightActivity.class);
        startActivityForResult(intent, WEIGHT_ACTIVITY_REQUEST_CODE );
        Log.d("test","weight clicked");
    }


    public void StartAddSpool(View view){

        Intent AddSpoolActivityIntent = new Intent(this, AddSpoolActivity.class);
        startActivityForResult(AddSpoolActivityIntent, NEW_SPOOL_ACTIVITY_REQUEST_CODE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String tName = data.getStringExtra(AddSpoolActivity.REPLY_NAME);
        String tBrand =data.getStringExtra(AddSpoolActivity.REPLY_BRAND);
        String tColor =data.getStringExtra(AddSpoolActivity.REPLY_COLOR);
        int    tWeight = data.getIntExtra(AddSpoolActivity.REPLY_WEIGHT, 0);
        String tMaterial =data.getStringExtra(AddSpoolActivity.REPLY_MATERIAL);

        int numID = data.getIntExtra(WeightActivity.SPOOL_ID, 0);
        int numWeight = data.getIntExtra(WeightActivity.NEW_WEIGHT, 0);
        int numIDp = data.getIntExtra(PrintJobActivity.SPOOL_ID_PRINT, 0);
        int numWeightp = data.getIntExtra(PrintJobActivity.NEW_WEIGHT_PRINT, 0);

        if (requestCode == NEW_SPOOL_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Spool spool = new Spool(SPOOL_ID, tName, tBrand, tColor, tWeight, tMaterial);

            mSpoolViewModel.insert(spool);
            SPOOL_ID++;
            Log.i("firebase", Integer.toString(spool.getSpoolID()));
        }

        else if (requestCode == WEIGHT_ACTIVITY_REQUEST_CODE) {
            LiveData<List<Spool>> spoolData = mSpoolViewModel.getAllSpools();
            List<Spool> spools = spoolData.getValue();

            for (Spool s : spools) {
                if (s.getSpoolID() == numID) {
                    Spool tempSpool = s;
                    tempSpool.setSpoolWeight(numWeight);
                    mSpoolViewModel.update(tempSpool);
                    Log.d("test","spool found and added, old one not deleted cuz idk how to do that");

                    //Update FireStore
                    String docID = Integer.toString(tempSpool.getSpoolID());
                    Log.i("firebase", Integer.toString(tempSpool.getSpoolID()));
                    db.collection("spools").document(docID).set(tempSpool);
                }
            }

            Log.d("test","" + spoolData);


        }
        else if (requestCode == PRINT_JOB_ACTIVITY_REQUEST_CODE) {
            LiveData<List<Spool>> spoolData = mSpoolViewModel.getAllSpools();
            List<Spool> spools = spoolData.getValue();

            Log.d("test","idp:" + numIDp);
            Log.d("test","weightp:" + numWeightp);

            for (Spool s : spools) {
                Log.d("test","there are spools and we're lookin for em");
                Log.d("test","s id: " + s.getSpoolID());
                if (s.getSpoolID() == numIDp) {
                    Spool tempSpool = s;
                    tempSpool.setSpoolWeight(s.getSpoolWeight() - numWeightp);
                    mSpoolViewModel.update(tempSpool);
                    Log.d("test","spool found and weight modified");

                    //Update FireStore
                    String docID = Integer.toString(tempSpool.getSpoolID());
                    Log.i("firebase", Integer.toString(tempSpool.getSpoolID()));
                    db.collection("spools").document(docID).set(tempSpool);
                }
            }

            Log.d("test","print job -> " + spoolData);

        }
        else {
            Toast.makeText(
                    getApplicationContext(),
                    "One or more fields empty, not saved",
                    Toast.LENGTH_LONG).show();
        }

    }

    private void setID(){
        db.collection("spools")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                int tID = Integer.parseInt(document.getId());
                                SPOOL_ID = tID +1;
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });


    }


    private void syncCloud(){


        db.collection("spools")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());

                                 int tID = Integer.parseInt(document.getId());
                                //int tID = Math.toIntExact(i);
                                String tName = document.getString("spoolName");
                                String tBrand = document.getString("spoolBrand");
                                String tColor = document.getString("spoolColor");
                                long w = document.getLong("spoolWeight");
                                //Log.d(TAG, "onComplete: =" + w);
                                int tWeight = (int)w;
                                String tMaterial = document.getString("spoolMaterial");


                                Spool spool = new Spool(tID, tName, tBrand, tColor, tWeight, tMaterial);
                                mSpoolViewModel.insert(spool);


                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

    }
}
