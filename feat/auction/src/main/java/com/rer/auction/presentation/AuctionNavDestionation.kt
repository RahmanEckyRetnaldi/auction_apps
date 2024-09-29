package com.rer.auction.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.rer.auction.presentation.auction.ui.AuctionScreen
import com.rer.auction.presentation.auctiondetail.ui.AuctionDetailScreen
import com.rer.core.composable.uiwrapper.ContentWrapper
import com.rer.core.utils.NavigationDestination
import dev.olshevski.navigation.reimagined.NavController

@Composable
fun NavigationDestination.isAuctionNavDestination(
    navController: NavController<NavigationDestination>
): Boolean {
    when (val destination = this) {
        is NavigationDestination.Auction -> ContentWrapper(
            statusBarColor = null
        ) {
            AuctionScreen(
                navController = navController
            )
        }

        is NavigationDestination.DetailAuction -> ContentWrapper(
            statusBarColor = MaterialTheme.colorScheme.secondary,
            useStatusBarDarkIcon = true
        ) {
            AuctionDetailScreen(
                navController = navController,
                productId = destination.productId,
            )
        }

        else -> return false
    }
    return true
}