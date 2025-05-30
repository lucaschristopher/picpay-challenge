package com.picpay.desafio.designsystem.components.skeleton

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.picpay.desafio.designsystem.theme.dp16
import com.picpay.desafio.designsystem.theme.dp24

private const val ITEMS = 5

@Composable
fun SkeletonComponent(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = dp16, vertical = dp24)
            .then(modifier),
    ) {
        repeat(ITEMS) {
            ShimmerListItem()
            Spacer(modifier = Modifier.height(dp24))
        }
    }
}