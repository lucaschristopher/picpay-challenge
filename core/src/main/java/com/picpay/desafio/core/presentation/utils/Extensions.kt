package com.picpay.desafio.core.presentation.utils

import com.picpay.desafio.core.presentation.model.State

fun <T> Result<T>.toUiState(): State<T> = fold(
    onSuccess = { State.Success(it) },
    onFailure = { State.Error(it) }
)