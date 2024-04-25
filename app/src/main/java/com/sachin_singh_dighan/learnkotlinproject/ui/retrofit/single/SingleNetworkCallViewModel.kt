package com.sachin_singh_dighan.learnkotlinproject.ui.retrofit.single

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sachin_singh_dighan.learnkotlinproject.data.api.ApiHelper
import com.sachin_singh_dighan.learnkotlinproject.data.local.DatabaseHelper
import com.sachin_singh_dighan.learnkotlinproject.data.model.ApiUser
import com.sachin_singh_dighan.learnkotlinproject.ui.base.UiState
import com.sachin_singh_dighan.learnkotlinproject.utils.DispatcherProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class SingleNetworkCallViewModel(
    private val apiHelper: ApiHelper,
    private val dbHelper: DatabaseHelper,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<ApiUser>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<ApiUser>>> = _uiState

    init {
        fetchUsers()
    }
    private fun fetchUsers() {
        viewModelScope.launch(dispatcherProvider.main) {
            _uiState.value = UiState.Loading
            apiHelper.getUsers()
                .flowOn(dispatcherProvider.io)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }
                .collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }

}