package com.picpay.desafio.android.presentation.home.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.picpay.desafio.android.R
import com.picpay.desafio.android.domain.model.UserModel
import com.picpay.desafio.android.presentation.home.mvi.PicPayAction
import com.picpay.desafio.android.presentation.home.mvi.PicPayViewState
import com.picpay.desafio.android.presentation.home.ui.screen.component.UserItem
import com.picpay.desafio.android.presentation.home.viewmodel.PicPayViewModel
import com.picpay.desafio.designsystem.components.appbar.TopAppBarOnlyHeader
import com.picpay.desafio.designsystem.components.pulltorefresh.PullToRefreshComponent
import com.picpay.desafio.designsystem.components.skeleton.SkeletonComponent
import com.picpay.desafio.designsystem.theme.PicPayChallengeTheme
import com.picpay.desafio.designsystem.theme.dp20
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PicPayScreen(
    viewModel: PicPayViewModel = koinViewModel(),
) {
    LaunchedEffect(Unit) {
        viewModel.dispatch(PicPayAction.GetUsers)
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    PullToRefreshComponent(
        isRefreshing = uiState.isRefreshing,
        onRefresh = { viewModel.dispatch(PicPayAction.OnRefresh) }
    ) {
        Scaffold(
            topBar = {
                TopAppBarOnlyHeader(
                    title = stringResource(R.string.contacts)
                )
            }
        ) { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Content(uiState = uiState)
            }
        }
    }
}

@Composable
private fun Content(
    uiState: PicPayViewState
) {
    when {
        uiState.isLoading -> {
            SkeletonComponent()
        }

        uiState.exception != null -> {
            Text(text = "Erro: ${uiState.exception.message}")
        }

        else -> {
            UserList(users = uiState.users)
        }
    }

}

@Composable
fun UserList(users: List<UserModel>) {
    LazyColumn(modifier = Modifier.padding(dp20)) {
        items(users) { user ->
            UserItem(user = user)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ContentPreview() = PicPayChallengeTheme {
    Content(
        uiState = PicPayViewState(
            isLoading = false,
            users = listOf(
                UserModel(
                    id = "1",
                    name = "Sandrine Spinka",
                    username = "Tod86",
                    image = "https://randomuser.me/api/portraits/men/1.jpg"
                ),
                UserModel(
                    id = "2",
                    name = "Carli Carroll",
                    username = "Constantin_Sawayn",
                    image = "https://randomuser.me/api/portraits/men/2.jpg"
                ),
                UserModel(
                    id = "3",
                    name = "Annabelle Reilly",
                    username = "Lawrence_Nader62",
                    image = "https://randomuser.me/api/portraits/men/3.jpg"
                ),
            )
        )
    )
}