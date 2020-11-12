package com.example.notes.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.notes.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.SQLOutput;

public class NoteTextView extends AppCompatActivity {

    private String folderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_text_view);
        folderId = getIntent().getStringExtra("folderId");

        TextView textField = findViewById(R.id.editTextTextMultiLine2);
        FloatingActionButton saveTextButton = findViewById(R.id.saveTextButton);

        textField.setOnFocusChangeListener((view, hasFocus) -> {
            if (hasFocus) {
                saveTextButton.setClickable(true);
                saveTextButton.setVisibility(View.VISIBLE);
            } else {

                saveTextButton.setClickable(false);
                saveTextButton.setVisibility(View.INVISIBLE);
            }
        });
    }


    @Override
    public void onBackPressed() {
        navigateBackToNotes();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: // the default resource ID of the actionBar's back button
                navigateBackToNotes();
                break;
        }

        return true;
    }

    private void navigateBackToNotes() {
        Intent intent = new Intent(this, NotesActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("folderId", folderId);
        startActivity(intent);
        finish();
    }
}