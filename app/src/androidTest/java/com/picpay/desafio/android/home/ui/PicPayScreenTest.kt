package com.picpay.desafio.android.home.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.picpay.desafio.android.domain.model.UserModel
import com.picpay.desafio.android.domain.usecase.GetUsersUseCase
import com.picpay.desafio.android.presentation.home.ui.screen.PicPayScreen
import com.picpay.desafio.android.presentation.home.viewmodel.PicPayViewModel
import com.picpay.desafio.core.data.network.helper.NetworkHelper
import com.picpay.desafio.core.presentation.model.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PicPayScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var viewModel: PicPayViewModel
    private val fakeNetworkHelper = NetworkHelper { true }
    private val fakeUseCaseError = FakeGetUserUseCaseError()
    private val fakeUseCaseSuccessEmptyResult = FakeGetUserUseCaseSuccess(emptyList())
    private val fakeUseCaseSuccessWithResult = FakeGetUserUseCaseSuccess(
        listOf(
            UserModel(
                id = "1",
                name = "Sandrine Spinka",
                username = "Tod86",
                image = "https://randomuser.me/api/portraits/men/1.jpg",
            ),
            UserModel(
                id = "2",
                name = "Carli Carroll",
                username = "Constantin_Sawayn",
                image = "https://randomuser.me/api/portraits/men/2.jpg"
            )
        )
    )

    @Test
    fun whenUsersAreLoadedDisplayUsersList() {

        viewModel = PicPayViewModel(fakeUseCaseSuccessWithResult, fakeNetworkHelper)

        composeTestRule.setContent {
            PicPayScreen(viewModel = viewModel)
        }

        // Wait for the recomposition and check if the data was displayed
        composeTestRule.onNodeWithText("Sandrine Spinka").assertIsDisplayed()
    }

    @Test
    fun whenErrorOccursDisplayErrorContent() {
        viewModel = PicPayViewModel(fakeUseCaseError, fakeNetworkHelper)

        composeTestRule.setContent {
            PicPayScreen(viewModel = viewModel)
        }

        // Wait for the recomposition and check if the data was displayed
        composeTestRule.onNodeWithText("Algo deu errado. Tente novamente.").assertIsDisplayed()
    }

    @Test
    fun whenUsersAreLoadedDisplayEmptyContent() {
        viewModel = PicPayViewModel(fakeUseCaseSuccessEmptyResult, fakeNetworkHelper)

        composeTestRule.setContent {
            PicPayScreen(viewModel = viewModel)
        }

        // Wait for the recomposition and check if the data was displayed
        composeTestRule.onNodeWithText("Não há nada aqui no momento.\nTente recarregar.")
            .assertIsDisplayed()
    }
}

private class FakeGetUserUseCaseSuccess(
    private val response: List<UserModel>
) : GetUsersUseCase {
    override fun invoke(isConnected: Boolean): Flow<State<List<UserModel>>> {
        return flowOf(
            State.Success(response)
        )
    }
}


private class FakeGetUserUseCaseError : GetUsersUseCase {
    override fun invoke(isConnected: Boolean): Flow<State<List<UserModel>>> {
        return flowOf(State.Error(Exception("Erro simulado!")))
    }
}