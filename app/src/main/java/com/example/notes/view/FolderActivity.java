package com.example.notes.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.notes.R;
import com.example.notes.data.FolderDAO;
import com.example.notes.model.Folder;
import com.example.notes.model.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.orm.SugarContext;

import java.util.List;

public class FolderActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SugarContext.init(this);

        FolderDAO folderDAO = new FolderDAO();
        List<Folder> allFolders = folderDAO.getAllFolders();
        recyclerView = findViewById(R.id.folderList);

        final Button editBtn = (Button) findViewById(R.id.editBtn);
        editBtn.setOnClickListener(v -> handleEditBtnOnClick());

        final FloatingActionButton createNewFolderBtn = (FloatingActionButton) findViewById(R.id.createNewFolderBtn);
        editBtn.setOnClickListener(v -> handleCreateNewFolderBtnOnClick());

        final FloatingActionButton createNewNoteBtn = (FloatingActionButton) findViewById(R.id.createNewNoteBtn);
        editBtn.setOnClickListener(v -> handleCreateNewNoteBtnOnClick());
    }

    private void displayList(List<Folder> allFolders) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FolderListAdapter folderListAdapter = new FolderListAdapter(allFolders);
        recyclerView.setAdapter(folderListAdapter);
    }

    private void handleCreateNewNoteBtnOnClick() {
    }

    private void handleCreateNewFolderBtnOnClick() {
    }

    private void handleEditBtnOnClick() {
    }

}