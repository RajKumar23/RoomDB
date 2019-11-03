package com.rajkumarrajan.roomroomdb.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "NoteTable")
public class NotesModel {

    @PrimaryKey(autoGenerate = true)
    private int NoteID;

    @ColumnInfo(name = "ImageResource")
    private int ImageResource;

    @ColumnInfo(name = "Title")
    private String Title;

    @ColumnInfo(name = "Description")
    private String Description;

    @ColumnInfo(name = "ImageLink")
    private String ImageLink;

    @ColumnInfo(name = "Type")
    private String Type;

    public NotesModel() {
    }

    public NotesModel(int imageResource, String title, String description, String imageLink, String type) {
        ImageResource = imageResource;
        Title = title;
        Description = description;
        ImageLink = imageLink;
        Type = type;
    }

    public int getNoteID() {
        return NoteID;
    }

    public void setNoteID(int noteID) {
        NoteID = noteID;
    }

    public int getImageResource() {
        return ImageResource;
    }

    public void setImageResource(int imageResource) {
        ImageResource = imageResource;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImageLink() {
        return ImageLink;
    }

    public void setImageLink(String imageLink) {
        ImageLink = imageLink;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
