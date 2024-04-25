package com.sachin_singh_dighan.learnkotlinproject.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.sachin_singh_dighan.learnkotlinproject.data.local.entity.User

@Dao
interface UserDao {

    @Query("SELECT * FROM User")
    fun getAll(): List<User>

    @Insert
    fun insertAll(users: List<User>)

    @Delete
    fun delete(user: User)

}