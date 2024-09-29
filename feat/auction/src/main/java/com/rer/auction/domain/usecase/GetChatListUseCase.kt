package com.rer.auction.domain.usecase

import com.rer.auction.domain.entity.ChatEntity
import com.rer.auction.domain.repository.AuctionRepository
import com.rer.core.network.utils.DomainResult
import javax.inject.Inject

class GetChatListUseCase @Inject constructor(
    private val auctionRepository: AuctionRepository
) {

    suspend operator fun invoke(): DomainResult<List<ChatEntity>> = auctionRepository.getChats()
}