package com.rer.auction.presentation.auction.ui

import androidx.compose.runtime.Composable
import com.rer.auction.presentation.auction.AuctionEvent
import com.rer.auction.presentation.auction.AuctionIntent
import com.rer.auction.presentation.auction.AuctionState
import com.rer.auction.presentation.auction.AuctionViewModel
import com.rer.auction.presentation.auction.ui.component.ListAuctionDialog
import com.rer.core.composable.uiwrapper.UIStateWrapper
import com.rer.core.utils.NavigationDestination
import dev.olshevski.navigation.reimagined.NavController
import dev.olshevski.navigation.reimagined.navigate
import kotlinx.coroutines.launch

@Composable
fun AuctionScreen(
    navController: NavController<NavigationDestination>
) {
    UIStateWrapper<AuctionViewModel, AuctionState, AuctionEvent, AuctionIntent>(
        event = { flow, scope ->
            scope.launch {
                flow.collect { event ->
                    when (event) {
                        is AuctionEvent.NavigateToDetail -> {
                            navController.navigate(NavigationDestination.DetailAuction(event.id))
                        }
                    }

                }
            }

        },
        content = { _, state, intent ->
            AuctionView(
                state = state,
                intent = intent,
            )

            ListAuctionDialog(
                isShow = state.isShowAuctionDialog, listAuction = state.auctionList,
                onDismiss = {
                    intent.invoke(AuctionIntent.OnShowAuctionListDialog(false))
                }
            ) {
                intent(AuctionIntent.OnNavigateToDetail(it.id))
            }

        }

    )
}