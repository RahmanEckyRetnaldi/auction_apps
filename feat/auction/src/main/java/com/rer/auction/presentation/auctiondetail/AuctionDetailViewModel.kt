package com.rer.auction.presentation.auctiondetail

import androidx.lifecycle.viewModelScope
import com.rer.auction.domain.entity.BidsRequestEntity
import com.rer.auction.domain.usecase.GetAuctionDetailUseCase
import com.rer.auction.domain.usecase.PutBidsUseCase
import com.rer.core.composable.uiwrapper.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuctionDetailViewModel @Inject constructor(
    private val auctionDetailUseCase: GetAuctionDetailUseCase,
    private val updateBids: PutBidsUseCase
) : BaseViewModel<AuctionDetailState, AuctionDetailEvent, AuctionDetailIntent>(
    AuctionDetailState()
) {
    override fun onIntent(intent: AuctionDetailIntent) {
        when (intent) {
            is AuctionDetailIntent.OnBackPressed -> {
                sendEvent(AuctionDetailEvent.Back)
            }

            is AuctionDetailIntent.GetAuctionDetail -> {
                getAuctionDetail(intent.id)
            }

            is AuctionDetailIntent.OnBidAmountChanged -> {
                updateState {
                    it.copy(
                        bidAmount = intent.amount
                    )
                }
            }

            is AuctionDetailIntent.OnBidNowClick -> {
                doBidNow()
            }
        }
    }

    private fun getAuctionDetail(productId: String) = viewModelScope.launch {
        setLoading(true)
        val response = auctionDetailUseCase.invoke(productId)
        response.fold(
            failure = { error ->
                setLoading(false)
                if (error != null) {
                    setStatusResponse(error)
                }
            },
            success = { result ->
                setLoading(false)
                updateState {
                    it.copy(auction = result)
                }
            }
        )
    }

    private fun doBidNow() = viewModelScope.launch {
        setLoading(true)
        val response = updateBids.invoke(
            highestBid = currentState.auction.currentHighestBid,
            param = BidsRequestEntity(
                id = currentState.auction.id,
                myBid = currentState.bidAmount,
                bids = currentState.auction.bids
            )
        )
        response.fold(
            failure = { error ->
                setLoading(false)
                if (error != null) {
                    setStatusResponse(error)
                }
            },
            success = { result ->
                setLoading(false)
                updateState {
                    it.copy(
                        auction = result,
                        bidAmount = ""
                    )
                }
            }
        )
    }

}