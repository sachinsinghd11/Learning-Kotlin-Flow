package com.sachin_singh_dighan.learnkotlinproject.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sachin_singh_dighan.learnkotlinproject.data.api.ApiHelper
import com.sachin_singh_dighan.learnkotlinproject.data.local.DatabaseHelper
import com.sachin_singh_dighan.learnkotlinproject.ui.retrofit.single.SingleNetworkCallViewModel
import com.sachin_singh_dighan.learnkotlinproject.utils.DispatcherProvider

class ViewModelFactory(
private val apiHelper: ApiHelper,
    private val dbHelper: DatabaseHelper,
    private val dispatcherProvider: DispatcherProvider
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SingleNetworkCallViewModel::class.java)) {
            return SingleNetworkCallViewModel(apiHelper, dbHelper, dispatcherProvider) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}