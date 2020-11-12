package com.example.notes.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.notes.R;

public class NoteTextView extends AppCompatActivity {

    private String folderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_text_view);
        folderId = getIntent().getStringExtra("folderId");

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