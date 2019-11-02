package com.rajkumarrajan.roomroomdb.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "UserDB")
public class UserModel {

    @PrimaryKey(autoGenerate = true)
    private int UserID;

    @ColumnInfo(name = "mobile_number")
    private long MobileNumber;

    @ColumnInfo(name = "first_name")
    private String UserFirsName;

    @ColumnInfo(name = "last_name")
    private String UserLastName;

    public UserModel() {
    }

    public UserModel(String userFirsName, String userLastName, long mobileNumber) {
        UserFirsName = userFirsName;
        UserLastName = userLastName;
        MobileNumber = mobileNumber;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public long getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public String getUserFirsName() {
        return UserFirsName;
    }

    public void setUserFirsName(String userFirsName) {
        UserFirsName = userFirsName;
    }

    public String getUserLastName() {
        return UserLastName;
    }

    public void setUserLastName(String userLastName) {
        UserLastName = userLastName;
    }
}
