package com.rer.auction.data.mapper

import com.rer.auction.data.model.request.BidsBodyRequestModel
import com.rer.auction.data.model.request.BidsRequestModel
import com.rer.auction.data.model.response.AuctionsItemModel
import com.rer.auction.data.model.response.BidsItemModel
import com.rer.auction.domain.entity.AuctionItemEntity
import com.rer.auction.domain.entity.BidsItemEntity
import com.rer.auction.domain.entity.BidsRequestEntity
import com.rer.core.utils.extention.toDoubleOrZero

object AuctionMapper {
    object Entity{
        fun List<AuctionsItemModel>?.toEntity() : List<AuctionItemEntity> {
            return this?.map { it.toEntity() } ?: emptyList()
        }

        fun AuctionsItemModel.toEntity() : AuctionItemEntity {
            return AuctionItemEntity(
                id = this.id.orEmpty(),
                productName = this.productName.orEmpty(),
                description = this.description.orEmpty(),
                auctionEndTime = this.auctionEndTime.orEmpty(),
                currentHighestBid = this.currentHighestBid.orEmpty().toDoubleOrZero(),
                imageUrl = this.imageUrl.orEmpty(),
                bids = this.bids?.map { it.toEntity() } ?: emptyList()
            )
        }

        private fun BidsItemModel?.toEntity() : BidsItemEntity {
            return  BidsItemEntity(
                bidId = this?.bidId.orEmpty(),
                bidderName = this?.bidderName.orEmpty(),
                bidAmount = this?.bidAmount ?: 0.0,
                bidTime = this?.bidTime.orEmpty()
            )
        }
    }

    object Model{
        fun BidsRequestEntity.toModel() : BidsRequestModel {
            return BidsRequestModel(
                id = this.id,
                bids = BidsBodyRequestModel(
                    currentHighestBid = this.myBid,
                    bids = this.bids.map {
                        BidsItemModel(
                            bidAmount = it.bidAmount,
                            bidderName = it.bidderName,
                            bidTime = it.bidTime,
                            bidId = it.bidId
                        )
                    }
                )
            )
        }
    }
}