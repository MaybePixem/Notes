package com.example.notes.data;

import com.example.notes.model.Note;
import com.orm.SugarRecord;

import java.util.List;

public class NoteDAO {

    public List<Note> getAllNotes() {
        return SugarRecord.listAll(Note.class);
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

    public int deleteAllNotes() {
        return SugarRecord.deleteAll(Note.class);
    }

    public long updateNote(Note Note) {
        return SugarRecord.update(Note);
    }
}
