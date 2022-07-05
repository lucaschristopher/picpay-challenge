package com.picpay.desafio.android.core.util

import android.content.Context
import android.view.View
import androidx.appcompat.app.AlertDialog

fun Context.createDialog(block: AlertDialog.Builder.() -> Unit = {}): AlertDialog {
    val builder = AlertDialog.Builder(this)
    builder.setPositiveButton(android.R.string.ok, null)
    block(builder)
    return builder.create()
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}
