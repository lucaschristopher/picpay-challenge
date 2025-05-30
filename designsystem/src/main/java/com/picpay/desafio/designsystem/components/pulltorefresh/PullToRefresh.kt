package com.picpay.desafio.designsystem.components.pulltorefresh

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.picpay.desafio.designsystem.theme.Gray
import com.picpay.desafio.designsystem.theme.LightWhite
import com.picpay.desafio.designsystem.theme.PicPayChallengeTheme
import com.picpay.desafio.designsystem.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PullToRefreshComponent(
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
    state: PullToRefreshState = rememberPullToRefreshState(),
    contentAlignment: Alignment = Alignment.TopStart,
    indicator: @Composable BoxScope.() -> Unit = {
        Indicator(
            modifier = Modifier
                .align(Alignment.TopCenter),
            containerColor = LightWhite,
            color = Primary,
            isRefreshing = isRefreshing,
            state = state,
        )
    },
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier.pullToRefresh(state = state, isRefreshing = isRefreshing, onRefresh = onRefresh),
        contentAlignment = contentAlignment
    ) {
        content()
        indicator()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun PullToRefreshComponentPreview() = PicPayChallengeTheme {
    Box(modifier = Modifier.fillMaxSize()) {
        PullToRefreshComponent(
            isRefreshing = true,
            onRefresh = {},
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Gray)
            ) {}
        }
    }
}