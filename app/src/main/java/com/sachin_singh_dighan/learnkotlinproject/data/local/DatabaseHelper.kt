package com.sachin_singh_dighan.learnkotlinproject.data.local

import com.sachin_singh_dighan.learnkotlinproject.data.local.entity.User
import kotlinx.coroutines.flow.Flow

interface DatabaseHelper {

    fun getUsers(): Flow<List<User>>

    fun insertAll(users: List<User>): Flow<Unit>
}