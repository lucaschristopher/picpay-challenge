package com.picpay.desafio.android.presentation.home.mvi

internal interface PicPayAction {
    object GetUsers : PicPayAction
    object OnRefresh : PicPayAction
}