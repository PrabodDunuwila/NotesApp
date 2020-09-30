package com.example.notesapp;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AddNote extends AppCompatActivity {

    Toolbar toolbar;
    EditText noteTitle, noteDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        noteTitle = findViewById(R.id.noteTitle);
        noteDetails = findViewById(R.id.noteDetails);
    }
}