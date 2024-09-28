package com.rer.auction.presentation.auction

import com.rer.auction.domain.entity.AuctionItemEntity
import com.rer.auction.domain.entity.ChatEntity
import com.rer.core.composable.uiwrapper.State

data class AuctionState(
    val listAuction: List<AuctionItemEntity> = emptyList(),
    val liveChats: List<ChatEntity> = emptyList(),
    val chatMessage: String = "",
): State
