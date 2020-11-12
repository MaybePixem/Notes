package com.example.notes.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.notes.R;
import com.example.notes.data.NoteDAO;
import com.example.notes.model.Folder;
import com.example.notes.model.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.orm.SugarContext;

import java.util.ArrayList;
import java.util.List;

public class NotesActivity extends AppCompatActivity {

    private NoteDAO noteDAO;
    private RecyclerView recyclerView;
    private Button editBtn;
    private boolean editFlag = false;
    private String folderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_view);
        TextView folderNameView = findViewById(R.id.folderName);
        folderId = getIntent().getStringExtra("folderId");
        folderNameView.setText(folderId);

        SugarContext.init(this);
        noteDAO = new NoteDAO();


        List<Note> noteList = noteDAO.getAllNotesFromFolder(folderId);
        recyclerView = findViewById(R.id.folderList);

        editBtn = findViewById(R.id.editBtn);
        editBtn.setOnClickListener(v -> handleEditBtnOnClick());

        final FloatingActionButton createNewNoteBtn = findViewById(R.id.createNewNoteBtn);
        createNewNoteBtn.setOnClickListener(v -> handleCreateNewNoteBtnOnClick());

        SearchView searchView = findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                updateNoteList(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                updateNoteList(newText);
                return true;
            }
        });

        displayList(noteList);
    }

    private void updateNoteList(String query) {
        List<Note> noteList = new ArrayList<>();
        for (Note note : noteDAO.getAllNotesFromFolder(folderId)) {
            if (note.getTitle().contains(query)) {
                noteList.add(note);
            }
        }
        displayList(noteList);
    }

    private void displayList(List<Note> noteList) {
        if (editFlag) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            NoteEditListAdapter noteEditListAdapter = new NoteEditListAdapter(noteList);
            recyclerView.setAdapter(noteEditListAdapter);
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            NoteListAdapter noteListAdapter = new NoteListAdapter(noteList);
            recyclerView.setAdapter(noteListAdapter);
        }
    }


    private void handleEditBtnOnClick() {
        if (editFlag) {
            editBtn.setText("Bearbeiten");
            editFlag = false;
        } else {
            editBtn.setText("Fertig");
            editFlag = true;
        }
        displayList(noteDAO.getAllNotesFromFolder(folderId));
    }

    private void handleCreateNewNoteBtnOnClick() {
        Intent intent = new Intent(this, NoteTextView.class);
        intent.putExtra("folderId", folderId);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        navigateBackToFolders();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: // the default resource ID of the actionBar's back button
                navigateBackToFolders();
                break;
        }

        return true;
    }

    private void navigateBackToFolders() {
        Intent intent = new Intent(this, FolderActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}