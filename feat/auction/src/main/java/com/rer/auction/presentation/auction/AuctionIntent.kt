package com.rer.auction.presentation.auction

sealed class AuctionIntent{
    data object OnNavigateToDetail : AuctionIntent()
}