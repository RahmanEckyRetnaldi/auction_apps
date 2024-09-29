package com.rer.auction.presentation.auctiondetail

sealed class AuctionDetailIntent {
    data object OnBackPressed: AuctionDetailIntent()
    data class GetAuctionDetail(val id: String) : AuctionDetailIntent()
    data class OnBidAmountChanged(val amount: String) : AuctionDetailIntent()
    data object OnBidNowClick : AuctionDetailIntent()
}