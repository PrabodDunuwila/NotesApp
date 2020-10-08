package com.example.notesapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Calendar;
import java.util.Objects;

public class Edit extends AppCompatActivity {
    Toolbar toolbar;
    EditText noteTitle, noteDetails;
    long noteId;
    Calendar calendar;
    String todayDate;
    String currentTime;
    NotesDatabase db;
    Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        noteId = i.getLongExtra("ID", 0);
        db = new NotesDatabase(this);
        note = db.getNote(noteId);

        noteTitle = findViewById(R.id.noteTitle);
        noteDetails = findViewById(R.id.noteDetails);
        noteTitle.setText(note.getTitle());
        noteDetails.setText(note.getContent());

        noteTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                getSupportActionBar().setTitle(note.getTitle());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    getSupportActionBar().setTitle(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        calendar = Calendar.getInstance();
        todayDate = calendar.get(Calendar.YEAR) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/"
                + calendar.get(Calendar.DAY_OF_MONTH);
        currentTime = pad(calendar.get(Calendar.HOUR)) + ":" + pad(calendar.get(Calendar.MINUTE))
                + ":" + pad(calendar.get(Calendar.SECOND));
    }

    private String pad(int time) {
        if (time < 10) {
            return "0" + time;
        }
        return String.valueOf(time);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflator = getMenuInflater();
        inflator.inflate(R.menu.save_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        if (item.getItemId() == R.id.save) {
            Note note = new Note(noteId, noteTitle.getText().toString(),
                    noteDetails.getText().toString(), todayDate, currentTime);
            db.editNote(note);
            Toast.makeText(this, "Note updated.", Toast.LENGTH_SHORT).show();
            goToMain();
        } else if (item.getItemId() == R.id.delete) {
            Toast.makeText(this, "Cancel edit.", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void goToMain() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
