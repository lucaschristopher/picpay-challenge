package com.picpay.desafio.android.core

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.picpay.desafio.android.data.model.UserResponse
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.presentation.model.UserUiModel

// Extension to create an AlertDialog
fun Context.createDialog(block: AlertDialog.Builder.() -> Unit = {}): AlertDialog {
    val builder = AlertDialog.Builder(this)
    builder.setPositiveButton(android.R.string.ok, null)
    block(builder)
    return builder.create()
}

fun UserResponse.toUser() = User(
    id = this.idUser,
    img = this.imgUser,
    name = this.nameUser,
    username = this.usernameUser
)

fun User.toUiModel() = UserUiModel(
    img = this.img,
    name = this.name,
    username = this.username
)