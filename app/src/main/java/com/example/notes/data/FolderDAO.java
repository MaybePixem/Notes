package com.example.notes.data;

import com.example.notes.model.Folder;
import com.orm.SugarRecord;

import java.util.List;

public class FolderDAO {

    public List<Folder> getAllFolders() {
        return SugarRecord.listAll(Folder.class);
    }

    public boolean deleteFolder(Folder folder) {
        if (folder != null) {
            return SugarRecord.delete(folder);
        }
        return false;
    }

}
