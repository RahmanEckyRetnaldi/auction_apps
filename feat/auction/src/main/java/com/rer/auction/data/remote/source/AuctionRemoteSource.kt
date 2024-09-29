package com.rer.auction.data.remote.source

import com.rer.auction.data.model.request.BidsRequestModel
import com.rer.auction.data.model.response.AuctionsItemModel
import com.rer.auction.data.model.response.ChatResponseItemModel
import com.rer.core.network.utils.DomainResult

interface AuctionRemoteSource {
    suspend fun getAuctions(): DomainResult<List<AuctionsItemModel>>
    suspend fun getChats(): DomainResult<List<ChatResponseItemModel>>
    suspend fun getAuctionDetail(productId: String): DomainResult<AuctionsItemModel>
    suspend fun updateBids(param: BidsRequestModel): DomainResult<AuctionsItemModel>
}