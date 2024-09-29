package com.rer.auction.domain.entity

import androidx.compose.runtime.Immutable
import com.rer.core.utils.extention.formatCurrencyToMYR

@Immutable
data class AuctionItemEntity(
    val currentHighestBid: Double = 0.0,
    val imageUrl: String = "",
    val bids: List<BidsItemEntity> = emptyList(),
    val description: String = "",
    val auctionEndTime: String = "",
    val id: String = "",
    val productName: String = "",
    val bidIncrement: Double = 0.0
){
    fun highestBidMYFormat() = currentHighestBid.formatCurrencyToMYR()
}
@Immutable
data class BidsItemEntity(
    val bidAmount: Double = 0.0,
    val bidTime: String = "",
    val bidId: String = "",
    val bidderName: String = ""
){
    fun bidAmountMYFormat() = bidAmount.formatCurrencyToMYR()
}
