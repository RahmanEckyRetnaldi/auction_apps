package com.rer.auction.domain.repository

import com.rer.auction.domain.entity.AuctionItemEntity
import com.rer.auction.domain.entity.BidsRequestEntity
import com.rer.auction.domain.entity.ChatEntity
import com.rer.core.network.utils.DomainResult

interface AuctionRepository {
    suspend fun getAuctions(): DomainResult<List<AuctionItemEntity>>
    suspend fun getChats(): DomainResult<List<ChatEntity>>
    suspend fun getAuctionDetail(productId: String): DomainResult<AuctionItemEntity>
    suspend fun updateBids(param: BidsRequestEntity): DomainResult<AuctionItemEntity>
}