package com.example.notes.data;

import com.example.notes.model.Note;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

public class NoteDAO {

    public List<Note> getAllNotesFromFolder(String folderId) {
        try {
            return SugarRecord.findWithQuery(Note.class, "SELECT * FROM Note where folder = '" + folderId+"'");
        }catch (Exception e){
            return new ArrayList<>();
        }
    }

    public Note getNoteById(int id) {
        return SugarRecord.findById(Note.class, id);
    }

    public boolean deleteNote(Note Note) {
        if (Note != null) {
            return SugarRecord.delete(Note);
        }
        return false;
    }

    public long updateNote(Note Note) {
        return SugarRecord.update(Note);
    }
}
