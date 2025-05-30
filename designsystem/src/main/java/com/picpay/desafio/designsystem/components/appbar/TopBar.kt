package com.picpay.desafio.designsystem.components.appbar

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.picpay.desafio.designsystem.theme.dp16
import com.picpay.desafio.designsystem.theme.dp8

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarOnlyHeader(title: String) {
    TopAppBar(
        modifier = Modifier.padding(start = dp8, top = dp16),
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
            )
        },
    )
}