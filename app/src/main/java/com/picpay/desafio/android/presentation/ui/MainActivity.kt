package com.picpay.desafio.android.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.core.AppState
import com.picpay.desafio.android.core.createDialog
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.presentation.model.UserUiModel
import com.picpay.desafio.android.presentation.viewmodel.PicPayViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val userListAdapter by lazy { UserListAdapter() }
    private val viewModel by viewModel<PicPayViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        setupListeners()
        initObserver()
        setupList()
        viewModel.init()
    }

    private fun initObserver() {
        viewModel.users.observe(this) {
            when (it) {
                AppState.Loading -> showLoading()
                is AppState.Error -> {
                    createDialog {
                        setMessage(it.error.message)
                        setCancelable(false)
                        setPositiveButton(android.R.string.ok) { _, _ -> viewModel.init() }
                    }.show()
                    hideLoading()
                }
                is AppState.Success -> {
                    userListAdapter.users = it.list
                    setupLayout(it.list)
                    hideLoading()
                }
            }
        }
    }

    private fun setupList() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = userListAdapter
        }
    }

    private fun setupListeners() {
//        TODO: redo refresh
//        binding.btnRefresh.setOnClickListener {
//            viewModel.init()
//        }
    }

    private fun setupLayout(list: List<UserUiModel>) {
        if (list.isNullOrEmpty()) {
            hideList()
//            binding.tvUsersNotFound.visibility = View.VISIBLE
//            binding.btnRefresh.visibility = View.VISIBLE
        } else {
            showList()
//            binding.tvUsersNotFound.visibility = View.GONE
//            binding.btnRefresh.visibility = View.GONE
        }
    }

    private fun showList() {
        binding.recyclerView.visibility = View.VISIBLE
    }

    private fun hideList() {
        binding.recyclerView.visibility = View.GONE
    }

    private fun showLoading() {
        binding.userListProgressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.userListProgressBar.visibility = View.GONE
    }
}
