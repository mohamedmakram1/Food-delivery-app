package com.example.WaGbA;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface UserDao {
    @Insert
    void registerUser(UserEntity userEntity);
}
