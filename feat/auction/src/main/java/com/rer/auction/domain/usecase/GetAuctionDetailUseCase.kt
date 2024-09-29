package com.rer.auction.domain.usecase

import com.rer.auction.domain.entity.AuctionItemEntity
import com.rer.auction.domain.repository.AuctionRepository
import com.rer.core.network.utils.DomainResult
import javax.inject.Inject

class GetAuctionDetailUseCase @Inject constructor(
    private val repository: AuctionRepository
) {
    suspend operator fun invoke(productId: String):
            DomainResult<AuctionItemEntity> = repository.getAuctionDetail(productId = productId)
}