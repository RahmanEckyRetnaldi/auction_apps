package com.rer.auction.presentation.auctiondetail.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rer.auction.domain.entity.BidsItemEntity
import com.rer.core.composable.label.CustomText
import com.rer.core.composable.spacer.Width

@Composable
fun BidItemComponent(
    modifier: Modifier = Modifier,
    bid: BidsItemEntity,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = com.rer.core.R.drawable.profile_placeholder),
            contentDescription = null,
            modifier = Modifier
                .size(52.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        8.Width()
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            CustomText(
                text = bid.bidderName,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            )
            CustomText(
                text = bid.bidAmountMYFormat(),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.White,
                )
            )
        }
    }
}