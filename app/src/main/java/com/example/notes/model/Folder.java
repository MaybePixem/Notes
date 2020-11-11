package com.example.notes.model;

import com.orm.SugarRecord;

import java.util.List;

public class Folder extends SugarRecord {

    private String name;

    public Folder() {
    }

    public Folder(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
