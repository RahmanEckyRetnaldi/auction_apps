package com.rer.auction.domain.entity

data class BidsRequestEntity(
    val id: String = "",
    val myBid: String = "",
    val bids: List<BidsItemEntity> = emptyList()
)
