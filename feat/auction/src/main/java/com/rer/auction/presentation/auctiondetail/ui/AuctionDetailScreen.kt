package com.rer.auction.presentation.auctiondetail.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.rer.auction.presentation.auctiondetail.AuctionDetailEvent
import com.rer.auction.presentation.auctiondetail.AuctionDetailIntent
import com.rer.auction.presentation.auctiondetail.AuctionDetailState
import com.rer.auction.presentation.auctiondetail.AuctionDetailViewModel
import com.rer.core.composable.uiwrapper.UIStateWrapper
import com.rer.core.utils.NavigationDestination
import dev.olshevski.navigation.reimagined.NavController
import dev.olshevski.navigation.reimagined.pop
import kotlinx.coroutines.launch

@Composable
fun AuctionDetailScreen(
    navController: NavController<NavigationDestination>,
    productId: String
) {

    UIStateWrapper<AuctionDetailViewModel, AuctionDetailState, AuctionDetailEvent, AuctionDetailIntent>(
        event = { flow, scope ->
            scope.launch {
                flow.collect { event ->
                    when (event) {
                        is AuctionDetailEvent.Back -> {
                            navController.pop()
                        }
                    }

                }
            }
        },
        content = { _, state, intent ->

            LaunchedEffect(key1 = Unit) {
                intent.invoke(AuctionDetailIntent.GetAuctionDetail(productId))
            }

            AuctionDetailView(
                state = state,
                intent = intent
            )

        },
    )

}