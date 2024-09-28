package com.rer.auction.presentation.auction.ui

import androidx.compose.runtime.Composable
import com.rer.auction.presentation.auction.AuctionEvent
import com.rer.auction.presentation.auction.AuctionIntent
import com.rer.auction.presentation.auction.AuctionState
import com.rer.auction.presentation.auction.AuctionViewModel
import com.rer.core.composable.uiwrapper.UIStateWrapper

@Composable
fun AuctionScreen() {
    UIStateWrapper<AuctionViewModel, AuctionState, AuctionEvent, AuctionIntent>(
        event = { flow, scope ->

        },
        content = { viewModel, statem ,intent ->

        }

    )
}