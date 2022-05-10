package com.picpay.desafio.android.presentation.ui

import androidx.recyclerview.widget.DiffUtil
import com.picpay.desafio.android.presentation.model.UserUiModel

class UserListDiffCallback(
    private val oldList: List<UserUiModel>,
    private val newList: List<UserUiModel>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].username.equals(newList[newItemPosition].username)
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true
    }
}