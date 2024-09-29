package com.rer.auction.data.repository

import com.rer.auction.data.mapper.AuctionMapper.Entity.toEntity
import com.rer.auction.data.mapper.AuctionMapper.Model.toModel
import com.rer.auction.data.mapper.MessagesMapper.Entity.toEntity
import com.rer.auction.data.remote.source.AuctionRemoteSource
import com.rer.auction.domain.entity.AuctionItemEntity
import com.rer.auction.domain.entity.BidsRequestEntity
import com.rer.auction.domain.entity.ChatEntity
import com.rer.auction.domain.repository.AuctionRepository
import com.rer.core.network.utils.DomainResult
import javax.inject.Inject

class AuctionRepositoryImpl @Inject constructor(
    private val remoteSource: AuctionRemoteSource
) : AuctionRepository {
    override suspend fun getAuctions(): DomainResult<List<AuctionItemEntity>> {
        return remoteSource.getAuctions().fold(
            success = {
                DomainResult.Success(it.toEntity())
            },
            failure = {
                DomainResult.Error(it)
            }
        )
    }

    override suspend fun getChats(): DomainResult<List<ChatEntity>> {
        return remoteSource.getChats().fold(
            success = {
                DomainResult.Success(it.toEntity())
            },
            failure = {
                DomainResult.Error(it)
            }
        )
    }

    override suspend fun getAuctionDetail(productId: String): DomainResult<AuctionItemEntity> {
        return remoteSource.getAuctionDetail(productId).fold(
            success = {
                DomainResult.Success(it.toEntity())
            },
            failure = {
                DomainResult.Error(it)
            }
        )
    }

    override suspend fun updateBids(param: BidsRequestEntity): DomainResult<AuctionItemEntity> {
        return remoteSource.updateBids(param.toModel()).fold(
            success = {
                DomainResult.Success(it.toEntity())
            },
            failure = {
                DomainResult.Error(it)
            }
        )
    }
}