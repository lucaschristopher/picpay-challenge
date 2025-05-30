package com.picpay.desafio.android.presentation.home.mvi

import com.picpay.desafio.android.domain.model.UserModel

data class PicPayViewState(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val hasError: Boolean = false,
    val exception: Exception? = null,
    val users: List<UserModel> = emptyList(),
)