package com.rajkumarrajan.roomroomdb.Room;

import com.rajkumarrajan.roomroomdb.Model.UserModel;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Flowable;
import io.reactivex.Maybe;

@Dao
public interface UserDAO {

    @Query("SELECT * FROM UserTable")
    Maybe<List<UserModel>> GetAll();

    @Query("SELECT * FROM UserTable where first_name LIKE  :firstName AND last_name LIKE :lastName")
    UserModel findByName(String firstName, String lastName);

    @Query("SELECT COUNT(*) from UserTable")
    int countUsers();

    @Query("SELECT COUNT(*) FROM UserTable where mobile_number = :number")
    Flowable<Integer> CheckNumber(long number);

    @Query("SELECT * FROM UserTable where mobile_number = :number")
    Flowable<UserModel> GetDetailsByNumber(long number);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void InsertAll(UserModel users);

    @Delete
    void delete(UserModel user);

    /*@Query("SELECT * FROM users WHERE uid IN (:userIds)")
    Flowable<List<User>> loadAllByIds(int[] userIds);*/
}
