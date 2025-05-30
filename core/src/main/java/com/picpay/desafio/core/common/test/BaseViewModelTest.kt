package com.picpay.desafio.core.common.test

import com.picpay.desafio.core.presentation.viewmodel.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn

fun <T> BaseViewModel<*, *, T>.observeEffect(): SharedFlow<Any?> {
    return this.screen.shareIn(
        CoroutineScope(Dispatchers.Unconfined),
        started = SharingStarted.Eagerly,
        replay = 1
    )
}

inline fun <reified T> T.setValue(fieldName: String, value: Any?) {
    val field = T::class.java.getDeclaredField(fieldName)
    field.isAccessible = true
    field.set(this, value)
}