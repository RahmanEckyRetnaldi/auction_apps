package com.rer.auction.domain.usecase

import com.rer.auction.domain.entity.AuctionItemEntity
import com.rer.auction.domain.repository.AuctionRepository
import com.rer.core.network.utils.DomainResult
import javax.inject.Inject

class GetAuctionListUseCase @Inject constructor(
    private val repository: AuctionRepository
) {

    suspend operator fun invoke(): DomainResult<List<AuctionItemEntity>> = repository.getAuctions()

}