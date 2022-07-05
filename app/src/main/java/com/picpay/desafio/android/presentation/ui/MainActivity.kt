package com.picpay.desafio.android.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.presentation.state.State
import com.picpay.desafio.android.core.util.Constants
import com.picpay.desafio.android.core.util.createDialog
import com.picpay.desafio.android.core.util.gone
import com.picpay.desafio.android.core.util.visible
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.presentation.model.UserState
import com.picpay.desafio.android.presentation.viewmodel.PicPayViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    // region Properties
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by viewModel<PicPayViewModel>()
    private val userListAdapter by lazy { UserListAdapter() }
    // endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        setupListeners()
        setupList()
        initObserver()
        viewModel.init()
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.users.collect { state ->
                when (state) {
                    is State.Loading -> showLoading()
                    is State.Error -> handleError(state.message)
                    is State.Success -> handleSuccess(state.data)
                    else -> {}
                }
            }
        }
    }

    private fun handleSuccess(data: List<UserState>) {
        userListAdapter.users = data
        setupLayout(data)
        hideLoading()
    }

    private fun handleError(message: String) {
        createDialog {
            setMessage(message)
            setCancelable(false)
            setPositiveButton(android.R.string.ok) { _, _ -> viewModel.init() }
        }.show()
        hideLoading()
    }

    private fun setupList() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = userListAdapter
        }
    }

    private fun setupListeners() {
        binding.btnRefresh.setOnClickListener {
            binding.btnRefresh.gone()
            hideList()
            viewModel.init()
        }
    }

    private fun setupLayout(list: List<UserState>) {
        if (list.isEmpty()) {
            hideList()
            handleError(Constants.ERROR_NOT_USERS_MESSAGE)
            binding.btnRefresh.gone()
        } else {
            showList()
            binding.btnRefresh.visible()
        }
    }

    // region View Functions
    private fun showList() {
        binding.recyclerView.visible()
    }

    private fun hideList() {
        binding.recyclerView.gone()
    }

    private fun showLoading() {
        binding.userListProgressBar.visible()
    }

    private fun hideLoading() {
        binding.userListProgressBar.gone()
    }
    //endregion
}
