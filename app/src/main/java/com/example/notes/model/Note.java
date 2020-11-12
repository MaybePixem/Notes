package com.example.notes.model;

import com.orm.SugarRecord;

public class Note extends SugarRecord {

    private String title;
    private String text;
    private String imagePath;
    private String folder;

    public Note() {
    }

    public Note(String title, String text, String imagePath, String folder) {
        this.title = title;
        this.text = text;
        this.imagePath = imagePath;
        this.folder = folder;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
