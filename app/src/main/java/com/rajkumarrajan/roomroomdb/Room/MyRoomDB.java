package com.rajkumarrajan.roomroomdb.Room;

import com.rajkumarrajan.roomroomdb.Model.NotesModel;
import com.rajkumarrajan.roomroomdb.Model.UserModel;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {UserModel.class, NotesModel.class}, version = 1)
public abstract class MyRoomDB extends RoomDatabase {

    public abstract UserDAO userDao();

    public abstract NotesDAO notesDAO ();
}
