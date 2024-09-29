package com.rer.auction.presentation.auction.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rer.auction.domain.entity.AuctionItemEntity
import com.rer.core.composable.dialog.BaseDialog
import com.rer.core.composable.image.ImageLoader
import com.rer.core.composable.label.CustomText
import com.rer.core.composable.spacer.Width
import com.rer.core.ui.theme.defaultPadding

@Composable
fun ListAuctionDialog(
    isShow: Boolean,
    onDismiss: (Boolean) -> Unit,
    listAuction: List<AuctionItemEntity>,
    onItemClick: (AuctionItemEntity) -> Unit,

    ) {
    if (isShow) {
        BaseDialog(
            onDismiss = { onDismiss.invoke(false) },
            overlayColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
        ) {
            Surface(
                shape = RoundedCornerShape(10.dp),
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(defaultPadding),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(listAuction) { auction ->
                        AuctionItem(
                            auction = auction,
                            onItemClick = {
                                onItemClick.invoke(it)
                                onDismiss.invoke(false)
                            }
                        )
                    }
                }
            }


        }

    }

}

@Composable
private fun AuctionItem(
    modifier: Modifier = Modifier,
    auction: AuctionItemEntity,
    onItemClick: (AuctionItemEntity) -> Unit
) {
    Row(
        modifier = modifier.then(
            Modifier.clickable {
                onItemClick.invoke(auction)
            }
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ImageLoader(
            url = auction.imageUrl,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(32.dp)
                .clip(RoundedCornerShape(5.dp))
        )
        8.Width()
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            CustomText(
                text = auction.productName, style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            )
            CustomText(
                text = auction.currentHighestBid.toString(),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.White,
                )
            )
        }
    }
}