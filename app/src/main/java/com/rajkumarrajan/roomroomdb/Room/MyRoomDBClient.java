package com.rajkumarrajan.roomroomdb.Room;

import android.content.Context;

import androidx.room.Room;

public class MyRoomDBClient {

    private Context mCtx;
    private static MyRoomDBClient mInstance;

    //our app database object
    private MyRoomDB myRoomDB;

    private MyRoomDBClient(Context mCtx) {
        this.mCtx = mCtx;

        //creating the app database with Room database builder
        //MyToDos is the name of the database
        myRoomDB = Room.databaseBuilder(mCtx, MyRoomDB.class, "UserDB")
//                .allowMainThreadQueries()
                .build();
    }

    public static synchronized MyRoomDBClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new MyRoomDBClient(mCtx);
        }
        return mInstance;
    }

    public MyRoomDB getAppDatabase() {
        return myRoomDB;
    }

    public static void destroyInstance() {
        mInstance = null;
    }
}
