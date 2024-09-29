package com.rer.auction.presentation.auction.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rer.auction.R
import com.rer.auction.presentation.auction.AuctionIntent
import com.rer.auction.presentation.auction.AuctionState
import com.rer.auction.presentation.auction.ui.component.AuctionHeaderComponent
import com.rer.auction.presentation.auction.ui.component.ChatsScreenComponent
import com.rer.core.composable.scaffold.DesignScaffold
import com.rer.core.ui.theme.defaultPadding

@Composable
fun AuctionView(
    state: AuctionState,
    intent: (AuctionIntent) -> Unit
) {
    DesignScaffold(
        showAppBar = true,
        appBar = {
            AuctionHeaderComponent()
        },
        background = {
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = stringResource(R.string.streaming_placeholder),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        },
        body = {
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f),
                contentAlignment = Alignment.CenterEnd
            ) {
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        .clickable(
                            state.auctionList.isNotEmpty()
                        ) {
                            intent.invoke(AuctionIntent.OnShowAuctionListDialog(!state.isShowAuctionDialog))

                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_market_stall),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colorScheme.background
                    )
                }
            }
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = defaultPadding),
                contentAlignment = Alignment.BottomStart
            ) {
                ChatsScreenComponent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.5f),
                    auctionState = state,
                    onNewMessageChanged = {
                        intent.invoke(AuctionIntent.OnNewMessageChanged(it))
                    },
                    onMessageSend = {
                        intent.invoke(AuctionIntent.OnMessageSend)
                    }
                )
            }
        },
    )
}
