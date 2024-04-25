package com.sachin_singh_dighan.learnkotlinproject.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sachin_singh_dighan.learnkotlinproject.data.local.dao.UserDao
import com.sachin_singh_dighan.learnkotlinproject.data.local.entity.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

abstract fun userDao(): UserDao
}