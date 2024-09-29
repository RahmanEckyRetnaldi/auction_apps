package com.rer.auction.data.model.request

import com.google.gson.annotations.SerializedName
import com.rer.auction.data.model.response.BidsItemModel

data class BidsRequestModel(
    val id: String = "",
    val bids: BidsBodyRequestModel = BidsBodyRequestModel()
)

data class BidsBodyRequestModel(
    @field:SerializedName("current_highest_bid")
    val currentHighestBid: String? = null,
    @field:SerializedName("bids")
    val bids: List<BidsItemModel>? = null
)