package com.example.notes.data;

import com.example.notes.model.Folder;
import com.orm.SugarRecord;

import java.util.List;

public class FolderDAO {

    public List<Folder> getAllFolders() {
        return SugarRecord.listAll(Folder.class);
    }

    public Folder getFolderById(int id) {
            return SugarRecord.findById(Folder.class, id);
    }

    public boolean deleteFolder(Folder folder) {
        if (folder != null) {
            return SugarRecord.delete(folder);
        }
        return false;
    }

    public long updateFolder(Folder folder) {
        return SugarRecord.update(folder);
    }
}
