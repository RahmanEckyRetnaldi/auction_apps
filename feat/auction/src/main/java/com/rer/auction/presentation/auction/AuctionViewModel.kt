package com.rer.auction.presentation.auction

import com.rer.core.composable.uiwrapper.ViewModelWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuctionViewModel @Inject constructor() : ViewModelWrapper<AuctionState, AuctionEvent, AuctionIntent>(AuctionState()) {

    override fun onIntent(intent: AuctionIntent) {
        when (intent) {
            is AuctionIntent.OnNavigateToDetail -> {
                //
            }
        }
    }
}