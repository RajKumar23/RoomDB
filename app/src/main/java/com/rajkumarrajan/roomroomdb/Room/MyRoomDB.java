package com.rajkumarrajan.roomroomdb.Room;

import android.content.Context;

import com.rajkumarrajan.roomroomdb.Model.UserModel;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {UserModel.class}, version = 1)
public abstract class MyRoomDB extends RoomDatabase {

    public abstract UserDao userDao();
}
