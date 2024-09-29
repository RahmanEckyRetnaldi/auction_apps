package com.rer.auction.presentation.auction

sealed class AuctionEvent{
    data class NavigateToDetail(val id: String) : AuctionEvent()
}