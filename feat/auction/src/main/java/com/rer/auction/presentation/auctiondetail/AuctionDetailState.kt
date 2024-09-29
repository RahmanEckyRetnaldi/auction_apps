package com.rer.auction.presentation.auctiondetail

import com.rer.auction.domain.entity.AuctionItemEntity
import com.rer.core.composable.uiwrapper.State

data class AuctionDetailState(
    val auction: AuctionItemEntity = AuctionItemEntity(),
    val bidAmount: String = "",
) : State
