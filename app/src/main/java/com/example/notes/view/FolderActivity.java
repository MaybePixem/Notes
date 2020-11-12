package com.example.notes.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;

import com.example.notes.R;
import com.example.notes.data.FolderDAO;
import com.example.notes.model.Folder;
import com.example.notes.model.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.orm.SchemaGenerator;
import com.orm.SugarContext;
import com.orm.SugarDb;

import java.util.ArrayList;
import java.util.List;

public class FolderActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FolderDAO folderDAO;
    private Button editBtn;
    private boolean editFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SugarContext.init(this);

        folderDAO = new FolderDAO();
        List<Folder> allFolders = folderDAO.getAllFolders();
        recyclerView = findViewById(R.id.folderList);

        editBtn = findViewById(R.id.editBtn);
        editBtn.setOnClickListener(v -> handleEditBtnOnClick());

        final FloatingActionButton createNewFolderBtn = findViewById(R.id.createNewFolderBtn);
        createNewFolderBtn.setOnClickListener(v -> handleCreateNewFolderBtnOnClick());

        final FloatingActionButton createNewNoteBtn = findViewById(R.id.createNewNoteBtn);
        createNewNoteBtn.setOnClickListener(v -> handleCreateNewNoteBtnOnClick());

        SearchView searchView = findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                updateFolderList(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                updateFolderList(newText);
                return true;
            }
        });

        displayList(allFolders);
    }

    private void updateFolderList(String query) {
        List<Folder> newFolderList = new ArrayList<>();
        for (Folder folder : folderDAO.getAllFolders()) {
            if (folder.getName().contains(query)) {
                newFolderList.add(folder);
            }
        }
        displayList(newFolderList);
    }


    private void displayList(List<Folder> folderList) {
        if (editFlag) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            FolderEditListAdapter folderEditListAdapter = new FolderEditListAdapter(folderList);
            recyclerView.setAdapter(folderEditListAdapter);
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            FolderListAdapter folderListAdapter = new FolderListAdapter(folderList);
            recyclerView.setAdapter(folderListAdapter);
        }
    }

    private void handleCreateNewNoteBtnOnClick() {
    }


    private void handleCreateNewFolderBtnOnClick() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ordner name");
        final EditText input = new EditText(this);

        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton(android.R.string.ok, (dialog, which) -> {
            dialog.dismiss();
            Folder folder = new Folder(input.getText().toString());
            folder.save();
            List<Folder> allFolders = folderDAO.getAllFolders();
            displayList(allFolders);
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    private void handleEditBtnOnClick() {
        if (editFlag) {
            editBtn.setText("Bearbeiten");
            editFlag = false;
        } else {
            editBtn.setText("Fertig");
            editFlag = true;
        }
        displayList(folderDAO.getAllFolders());
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Folder> allFolders = folderDAO.getAllFolders();
        displayList(allFolders);
    }

    public void onTerminate() {
        SugarContext.terminate();
    }
}