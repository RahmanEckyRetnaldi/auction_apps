package com.rer.auction.presentation.auction

sealed class AuctionIntent{
    data class OnNavigateToDetail(val id: String) : AuctionIntent()
    data class OnNewMessageChanged(val message: String) : AuctionIntent()
    data object OnMessageSend : AuctionIntent()
    data class OnShowAuctionListDialog(val isShow :Boolean) : AuctionIntent()
}