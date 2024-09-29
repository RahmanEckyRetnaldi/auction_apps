package com.rer.auction.presentation.auction

import com.rer.auction.domain.entity.AuctionItemEntity
import com.rer.auction.domain.entity.ChatEntity
import com.rer.core.composable.uiwrapper.State

data class AuctionState(
    val auctionList: List<AuctionItemEntity> = emptyList(),
    val liveChats: List<ChatEntity> = emptyList(),
    val chatMessage: String = "",
    val isShowAuctionDialog: Boolean = false,
) : State
