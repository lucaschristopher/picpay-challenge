package com.picpay.desafio.designsystem.components.skeleton

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.picpay.desafio.designsystem.theme.PicPayChallengeTheme
import com.picpay.desafio.designsystem.theme.af04
import com.picpay.desafio.designsystem.theme.af05
import com.picpay.desafio.designsystem.theme.dp10
import com.picpay.desafio.designsystem.theme.dp16
import com.picpay.desafio.designsystem.theme.dp50

@Composable
fun ShimmerListItem(
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        Box(
            modifier = Modifier
                .size(dp50)
                .clip(CircleShape)
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.width(dp16))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(af05)
                    .height(dp16)
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.height(dp16))
            Box(
                modifier = Modifier
                    .fillMaxWidth(af04)
                    .height(dp10)
                    .shimmerEffect()
            )
        }
    }
}

@Preview
@Composable
private fun Preview() = PicPayChallengeTheme {
    ShimmerListItem()
}

