package com.rer.auction.data.model.response

import com.google.gson.annotations.SerializedName


data class AuctionsItemModel(

    @field:SerializedName("current_highest_bid")
    val currentHighestBid: String? = null,

    @field:SerializedName("image_url")
    val imageUrl: String? = null,

    @field:SerializedName("bids")
    val bids: List<BidsItemModel?>? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("auction_end_time")
    val auctionEndTime: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("product_name")
    val productName: String? = null
)

data class BidsItemModel(

    @field:SerializedName("bid_amount")
    val bidAmount: Double? = null,

    @field:SerializedName("bid_time")
    val bidTime: String? = null,

    @field:SerializedName("bid_id")
    val bidId: String? = null,

    @field:SerializedName("bidder_name")
    val bidderName: String? = null
)
