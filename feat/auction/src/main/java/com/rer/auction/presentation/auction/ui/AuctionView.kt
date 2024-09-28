package com.rer.auction.presentation.auction.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.rer.auction.R
import com.rer.auction.presentation.auction.AuctionState
import com.rer.auction.presentation.auction.ui.component.AuctionHeaderComponent
import com.rer.auction.presentation.auction.ui.component.ChatsScreenComponent
import com.rer.core.composable.scaffold.DesignScaffold
import com.rer.core.ui.theme.defaultPadding

@Composable
fun AuctionView(
    state: AuctionState
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
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = defaultPadding),
                contentAlignment = Alignment.BottomStart
            ) {
                ChatsScreenComponent(
                    auctionState = state,
                    onNewMessageChanged = {

                    },
                    onMessageSend = {

                    }
                )
            }
        },
    )
}
