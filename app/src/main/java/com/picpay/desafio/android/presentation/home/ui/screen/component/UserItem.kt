package com.picpay.desafio.android.presentation.home.ui.screen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.picpay.desafio.android.R
import com.picpay.desafio.android.domain.model.UserModel
import com.picpay.desafio.designsystem.theme.GreenContainer
import com.picpay.desafio.designsystem.theme.PicPayChallengeTheme
import com.picpay.desafio.designsystem.theme.af07
import com.picpay.desafio.designsystem.theme.dp12
import com.picpay.desafio.designsystem.theme.dp16
import com.picpay.desafio.designsystem.theme.dp4
import com.picpay.desafio.designsystem.theme.dp50

@Composable
fun UserItem(
    modifier: Modifier = Modifier,
    user: UserModel,
) {
    Row(
        modifier = modifier.padding(vertical = dp12),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier
                .size(dp50)
                .clip(CircleShape),
            model = user.image,
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(R.string.user_avatar_content_description),
        ) {
            when (painter.state) {
                is AsyncImagePainter.State.Loading -> {
                    CircularProgressIndicator(color = GreenContainer)
                }

                is AsyncImagePainter.State.Error -> {
                    Icon(
                        modifier = Modifier.size(dp50),
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = stringResource(R.string.user_avatar_content_description),
                    )
                }

                else -> SubcomposeAsyncImageContent()
            }
        }
        Spacer(modifier = Modifier.width(dp16))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(af07),
                text = stringResource(R.string.avatar_name, user.username.lowercase()),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(dp4))
            Text(
                modifier = Modifier.fillMaxWidth(af07),
                text = user.name,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview
@Composable
private fun Preview() = PicPayChallengeTheme {
    UserItem(
        user = UserModel(
            id = "1",
            name = "Sandrine Spinka",
            username = "Tod86",
            image = "https://randomuser.me/api/portraits/men/1.jpg"
        )
    )
}