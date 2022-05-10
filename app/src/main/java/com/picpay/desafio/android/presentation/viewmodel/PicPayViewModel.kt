package com.picpay.desafio.android.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.core.AppState
import com.picpay.desafio.android.core.toUiModel
import com.picpay.desafio.android.domain.GetPicPayUsersUserCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class PicPayViewModel(
    private val getPicPayUsersUserCase: GetPicPayUsersUserCase
) : ViewModel() {

    private val _users = MutableLiveData<AppState>()
    val users: LiveData<AppState> = _users

    fun init() {
        // TODO: val cachedUsers = getUsersInPreferences()
        getPicPayUsers()
    }

    private fun getPicPayUsers() {
        viewModelScope.launch {
            getPicPayUsersUserCase()
                .onStart {
                    _users.postValue(AppState.Loading)
                }
                .catch {
                    _users.postValue(AppState.Error(it))
                }
                .collect { userList ->
                    _users.postValue(
                        AppState.Success(userList.map {
                            it.toUiModel()
                        })
                    )
                }
        }
    }
}
