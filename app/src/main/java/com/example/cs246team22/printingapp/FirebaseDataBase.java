package com.example.cs246team22.printingapp;

import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseDataBase {

    FirebaseDataBase(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
    }
}
