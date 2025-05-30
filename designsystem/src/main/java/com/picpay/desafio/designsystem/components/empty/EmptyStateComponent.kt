package com.picpay.desafio.designsystem.components.empty

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.picpay.desafio.designsystem.R
import com.picpay.desafio.designsystem.theme.OrangeContainer
import com.picpay.desafio.designsystem.theme.dp10
import com.picpay.desafio.designsystem.theme.dp14
import com.picpay.desafio.designsystem.theme.dp16
import com.picpay.desafio.designsystem.theme.dp24
import com.picpay.desafio.designsystem.theme.dp30
import com.picpay.desafio.designsystem.theme.dp40

@Composable
fun EmptyStateComponent(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dp24)
            .verticalScroll(rememberScrollState()),
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .then(modifier),
            colors = CardDefaults.cardColors().copy(containerColor = OrangeContainer),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dp16, vertical = dp30)
                    .then(modifier),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier
                        .size(dp40),
                    imageVector = Icons.Default.Info,
                    tint = OrangeContainer,
                    contentDescription = stringResource(R.string.empty_state_icon_content_description),
                )

                Text(
                    modifier = Modifier.padding(top = dp14),
                    text = stringResource(R.string.empty_state_title),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                )

                Spacer(modifier.height(dp10))

                Text(
                    text = stringResource(R.string.empty_state_description),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}