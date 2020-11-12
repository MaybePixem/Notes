package com.example.notes.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.notes.R;
import com.example.notes.data.NoteDAO;
import com.example.notes.model.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NoteTextView extends AppCompatActivity {

    private String folderId;
    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_text_view);
        folderId = getIntent().getStringExtra("folderId");
        NoteDAO noteDAO = new NoteDAO();
        if (getIntent().getStringExtra("noteId") != null) {
            note = noteDAO.getNoteById(Long.parseLong(getIntent().getStringExtra("noteId")));
        } else {
            note = new Note();
            note.setFolder(folderId);
        }

        TextView textField = findViewById(R.id.noteTxt);
        TextView titleText = findViewById(R.id.titleTxt);
        titleText.setText(note.getTitle());
        textField.setText(note.getText());
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