package com.example.notes.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.notes.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NotesView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_view);
        TextView folderNameView = findViewById(R.id.folderName);
        folderNameView.setText(getIntent().getStringExtra("folderId"));

        final FloatingActionButton createNewNoteBtn = findViewById(R.id.createNewNoteBtn);
        createNewNoteBtn.setOnClickListener(v -> handleCreateNewNoteBtnOnClick());
    }

    private void handleCreateNewNoteBtnOnClick() {
        Intent intent = new Intent(this, NoteTextView.class);
        intent.putExtra("noteId", 1);
        startActivity(intent);
    }
}