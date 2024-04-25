package com.sachin_singh_dighan.learnkotlinproject.data.api

import com.sachin_singh_dighan.learnkotlinproject.data.model.ApiUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ApiHelperImpl(private val apiService: ApiService):    ApiHelper {
    override fun getUsers(): Flow<List<ApiUser>>  = flow { emit(apiService.getUsers()) }

    override fun getMoreUsers(): Flow<List<ApiUser>>  = flow { emit(apiService.getMoreUsers()) }

    override fun getUsersWithError(): Flow<List<ApiUser>> = flow { emit(apiService.getUsersWithError()) }
}