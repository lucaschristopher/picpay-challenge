package com.picpay.desafio.core.presentation.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import com.picpay.desafio.core.presentation.model.State

fun <T> Result<T>.toUiState(): State<T> = fold(
    onSuccess = { State.Success(it) },
    onFailure = { State.Error(it) }
)

fun Context.showToast(@StringRes stringId: Int): Toast =
    Toast.makeText(this, stringId, Toast.LENGTH_LONG)