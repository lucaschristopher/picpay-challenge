package com.picpay.desafio.core.presentation.mvi

sealed class GenericExceptionResult {

    data class OnGenericError(val onRetryButtonClick: (() -> Unit)? = null) :
        GenericExceptionResult()

    data class OnNoNetworkError(val onRetryButtonClick: (() -> Unit)? = null) :
        GenericExceptionResult()
}