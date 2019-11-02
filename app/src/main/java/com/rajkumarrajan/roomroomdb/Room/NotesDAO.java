package com.rajkumarrajan.roomroomdb.Room;

import com.rajkumarrajan.roomroomdb.Model.NotesModel;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Maybe;

@Dao
public interface NotesDAO {

    @Query("SELECT * FROM NoteTable")
    Maybe<List<NotesModel>> GetAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void InsertAll(NotesModel notesModel);
}
