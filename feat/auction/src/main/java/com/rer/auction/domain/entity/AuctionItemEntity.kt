package com.rer.auction.domain.entity

data class AuctionItemEntity(
    val currentHighestBid: Double = 0.0,
    val imageUrl: String = "",
    val bids: List<BidsItemEntity> = emptyList(),
    val description: String = "",
    val auctionEndTime: String = "",
    val id: String = "",
    val productName: String = "",
    val bidIncrement: Double = 0.0
)

data class BidsItemEntity(
    val bidAmount: Double = 0.0,
    val bidTime: String = "",
    val bidId: String = "",
    val bidderName: String = ""
)
