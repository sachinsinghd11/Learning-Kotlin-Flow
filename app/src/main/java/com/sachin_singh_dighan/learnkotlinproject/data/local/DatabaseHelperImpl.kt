package com.sachin_singh_dighan.learnkotlinproject.data.local

import com.sachin_singh_dighan.learnkotlinproject.data.local.entity.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DatabaseHelperImpl(private val appDatabase: AppDatabase): DatabaseHelper {
    override fun getUsers(): Flow<List<User>>  = flow { emit(appDatabase.userDao().getAll()) }

    override fun insertAll(users: List<User>): Flow<Unit>  = flow { emit(appDatabase.userDao().insertAll(users)) }
}