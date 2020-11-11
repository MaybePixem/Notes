package com.example.notes.data;

import com.example.notes.model.Folder;

import java.util.List;

public class FolderDAO {
    public List<Folder> getAllFolders() {
        return Folder.findWithQuery(Folder.class, "Select * from Folder");
    }
}
