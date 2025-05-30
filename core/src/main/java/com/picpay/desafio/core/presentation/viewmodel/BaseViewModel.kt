package com.picpay.desafio.core.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.core.presentation.mvi.GenericExceptionResult
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<ACTION, RESULT, STATE> : ViewModel() {
    private val _screen = MutableSharedFlow<RESULT>()
    val screen: SharedFlow<RESULT> = _screen

    private val _genericScreen = MutableSharedFlow<GenericExceptionResult>()
    val genericExceptionsFlow: SharedFlow<GenericExceptionResult> = _genericScreen

    private val _uiState: MutableStateFlow<STATE> by lazy { MutableStateFlow(initialState) }
    val uiState: StateFlow<STATE> = _uiState

    abstract val initialState: STATE

    val currentState: STATE get() = uiState.value

    abstract fun dispatch(actionEvent: ACTION)

    protected fun emitScreenResult(screenResult: RESULT) = viewModelScope.launch {
        _screen.emit(screenResult)
    }

    protected fun emitGenericExceptionResult(genericExceptionResult: GenericExceptionResult) =
        viewModelScope.launch {
            _genericScreen.emit(genericExceptionResult)
        }

    protected fun updateUiState(uiState: STATE) {
        _uiState.value = uiState
    }

    protected fun updateUiState(reduce: STATE.() -> STATE) {
        _uiState.value = uiState.value.reduce()
    }
}
