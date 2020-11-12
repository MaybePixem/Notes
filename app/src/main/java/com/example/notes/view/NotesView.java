package com.example.notes.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.notes.R;

public class NotesView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_view);
        TextView folderNameView = findViewById(R.id.folderName);
        folderNameView.setText(getIntent().getStringExtra("folderId"));
    }
}