package com.rer.auction.domain.usecase

import com.rer.auction.domain.entity.AuctionItemEntity
import com.rer.auction.domain.entity.BidsItemEntity
import com.rer.auction.domain.entity.BidsRequestEntity
import com.rer.auction.domain.repository.AuctionRepository
import com.rer.core.network.utils.DomainResult
import com.rer.core.network.utils.StatusResponse
import com.rer.core.utils.extention.toDoubleOrZero
import com.rer.core.utils.getCurrentDateTimeFormatted
import javax.inject.Inject

class PutBidsUseCase @Inject constructor(
    private val repository: AuctionRepository
) {

    suspend operator fun invoke(
        highestBid: Double,
        param: BidsRequestEntity
    ): DomainResult<AuctionItemEntity> {
        val myBid = BidsItemEntity(
            bidAmount = param.myBid.toDoubleOrZero(),
            bidId = "105",
            bidderName = "Andre",
            bidTime = getCurrentDateTimeFormatted()
        )
        val tempBids = param.bids.toMutableList()
        val exists = tempBids.any { it.bidId == myBid.bidId }
        return if (myBid.bidAmount > highestBid) {
            if (exists) {
                tempBids.indexOfFirst { it.bidId == myBid.bidId }
                    .takeIf { it != -1 }?.let { index ->
                        tempBids[index] = myBid
                    } ?: run {
                    println("Bid with id ${myBid.bidId} not found.")
                }
                repository.updateBids(
                    param.copy(
                        bids = tempBids
                    )
                )
            } else {
                tempBids.add(myBid)
                val newParam = param.copy(
                    bids = tempBids
                )
                repository.updateBids(newParam)
            }
        } else {
            DomainResult.Error(
                StatusResponse(
                    isSuccess = false,
                    code = "992",
                    message = "Bid amount must be greater than highest bid."
                )
            )
        }


    }
}