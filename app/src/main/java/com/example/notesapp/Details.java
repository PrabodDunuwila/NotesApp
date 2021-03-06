package com.example.notesapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Details extends AppCompatActivity {
    TextView mDetails;
    NotesDatabase db;
    Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDetails = findViewById(R.id.detailsOfNote);
        Intent i = getIntent();
        long id = i.getLongExtra("ID", 0);
        db = new NotesDatabase(this);
        note = db.getNote(id);
        getSupportActionBar().setTitle(note.getTitle());
        mDetails.setText(note.getContent());
        mDetails.setMovementMethod(new ScrollingMovementMethod());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteNote(note.getId());
                Toast.makeText(getApplicationContext(), "Note deleted", Toast.LENGTH_SHORT)
                        .show();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflator = getMenuInflater();
        inflator.inflate(R.menu.edit_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        if (item.getItemId() == R.id.editNote) {
            Toast.makeText(this, "Edit note", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, Edit.class);
            i.putExtra("ID", note.getId());
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}
