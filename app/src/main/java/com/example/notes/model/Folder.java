package com.example.notes.model;

import com.orm.SugarRecord;

import java.util.List;

public class Folder extends SugarRecord {

    private String name;
    private List<Note> notes;

    public Folder() {
    }

    public Folder(String name, List<Note> notes) {
        this.name = name;
        this.notes = notes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}
