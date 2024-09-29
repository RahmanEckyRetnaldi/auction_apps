package com.rer.auction.presentation.auction.ui.component

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rer.auction.R
import com.rer.core.composable.label.CustomText
import com.rer.core.composable.spacer.Height
import com.rer.core.composable.spacer.Width
import com.rer.core.ui.theme.defaultPadding

@Composable
fun AuctionHeaderComponent(modifier: Modifier = Modifier) {
    val infiniteTransition =
        rememberInfiniteTransition(label = stringResource(R.string.live_auction_blink_label))

    val blinkAlpha by infiniteTransition.animateFloat(
        initialValue = 1f, targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = stringResource(R.string.live_auction_blink_label),
    )
    Spacer(modifier = Modifier.windowInsetsTopHeight(WindowInsets.safeDrawing))
    Row(
        modifier = modifier.then(
            Modifier
                .fillMaxWidth()
                .wrapContentSize()
                .padding(horizontal = defaultPadding)
        ), verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(0.9f)
        ) {
            CustomText(
                text = stringResource(R.string.live_auction_header),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = MaterialTheme.colorScheme.onPrimary
            )
            10.Height()
            Row(
                modifier = Modifier.wrapContentSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = com.rer.core.R.drawable.profile_placeholder),
                    contentDescription = stringResource(R.string.profile_image_desc),
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
                10.Width()
                CustomText(
                    text = stringResource(R.string.name_placeholder),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

        }
        10.Width()
        Box(
            modifier = Modifier
                .weight(0.1f)
                .alpha(blinkAlpha), contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.error)
            )
        }

    }
}