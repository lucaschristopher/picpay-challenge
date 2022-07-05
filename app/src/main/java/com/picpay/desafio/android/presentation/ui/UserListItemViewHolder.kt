package com.picpay.desafio.android.presentation.ui

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.picpay.desafio.android.presentation.model.UserState
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class UserListItemViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    private var bind: ListItemUserBinding = DataBindingUtil.bind(itemView)!!

    fun bind(user: UserState) {
        bind.name.text = user.name
        bind.username.text = user.username
        bind.progressBar.visibility = View.VISIBLE
        Picasso.get()
            .load(user.img)
            .error(R.drawable.ic_round_account_circle)
            .into(bind.picture, object : Callback {
                override fun onSuccess() {
                    bind.progressBar.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    bind.progressBar.visibility = View.GONE
                }
            })
    }
}