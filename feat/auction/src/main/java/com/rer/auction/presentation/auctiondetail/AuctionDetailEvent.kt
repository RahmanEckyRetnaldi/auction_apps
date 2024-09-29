package com.rer.auction.presentation.auctiondetail

sealed class AuctionDetailEvent {
    data object Back : AuctionDetailEvent()
}