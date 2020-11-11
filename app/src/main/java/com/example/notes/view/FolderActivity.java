package com.example.notes.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;

import com.example.notes.R;
import com.example.notes.data.FolderDAO;
import com.example.notes.model.Folder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.orm.SugarContext;

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

        editBtn = (Button) findViewById(R.id.editBtn);
        editBtn.setOnClickListener(v -> handleEditBtnOnClick());

        final FloatingActionButton createNewFolderBtn = (FloatingActionButton) findViewById(R.id.createNewFolderBtn);
        createNewFolderBtn.setOnClickListener(v -> handleCreateNewFolderBtnOnClick());

        final FloatingActionButton createNewNoteBtn = (FloatingActionButton) findViewById(R.id.createNewNoteBtn);
        createNewNoteBtn.setOnClickListener(v -> handleCreateNewNoteBtnOnClick());
        displayList(allFolders);
    }

    private void displayList(List<Folder> folderList) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FolderListAdapter folderListAdapter = new FolderListAdapter(folderList);
        recyclerView.setAdapter(folderListAdapter);
    }

    private void displayEditList(List<Folder> folderList) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FolderEditListAdapter folderEditListAdapter = new FolderEditListAdapter(folderList);
        recyclerView.setAdapter(folderEditListAdapter);
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
            if (editFlag) {
                displayEditList(allFolders);
            } else {
                displayList(allFolders);
            }
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    private void handleEditBtnOnClick() {
        if (editFlag) {
            displayList(folderDAO.getAllFolders());
            editBtn.setText("Bearbeiten");
            editFlag = false;
        } else {
            displayEditList(folderDAO.getAllFolders());
            editBtn.setText("Fertig");
            editFlag = true;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Folder> allFolders = folderDAO.getAllFolders();
        displayList(allFolders);
    }

}