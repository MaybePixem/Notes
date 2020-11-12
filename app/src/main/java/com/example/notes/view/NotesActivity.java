package com.example.notes.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.notes.R;
import com.example.notes.data.NoteDAO;
import com.example.notes.model.Folder;
import com.example.notes.model.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.orm.SugarContext;

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

        displayList(noteList);
    }

    private void displayList(List<Note> noteList) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        NoteListAdapter noteListAdapter = new NoteListAdapter(noteList);
        recyclerView.setAdapter(noteListAdapter);
    }

    private void displayEditList(List<Note> noteList) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        NoteEditListAdapter noteEditListAdapter = new NoteEditListAdapter(noteList);
        recyclerView.setAdapter(noteEditListAdapter);
    }

    private void handleEditBtnOnClick() {
    }

    private void handleCreateNewNoteBtnOnClick() {
        Intent intent = new Intent(this, NoteTextView.class);
        intent.putExtra("noteId", 1);
        intent.putExtra("folderId", folderId);
        startActivity(intent);
    }
}