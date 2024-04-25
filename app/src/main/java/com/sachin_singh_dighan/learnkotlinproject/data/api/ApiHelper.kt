package com.sachin_singh_dighan.learnkotlinproject.data.api

import com.sachin_singh_dighan.learnkotlinproject.data.model.ApiUser
import kotlinx.coroutines.flow.Flow


interface ApiHelper {

    fun getUsers(): Flow<List<ApiUser>>

    fun getMoreUsers(): Flow<List<ApiUser>>

    fun getUsersWithError(): Flow<List<ApiUser>>

}