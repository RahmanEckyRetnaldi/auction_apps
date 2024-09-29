package com.rer.auction.data.remote.source

import com.rer.auction.data.model.request.BidsRequestModel
import com.rer.auction.data.model.response.AuctionsItemModel
import com.rer.auction.data.model.response.ChatResponseItemModel
import com.rer.auction.data.remote.AuctionApi
import com.rer.core.di.IoDispatcher
import com.rer.core.network.utils.DomainResult
import com.rer.core.network.utils.RetrofitHolder
import com.rer.core.network.utils.StatusResponse
import com.rer.core.network.utils.withTimeOut
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject


class AuctionRemoteSourceImpl @Inject constructor(
    private val api: RetrofitHolder,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : AuctionRemoteSource {
    override suspend fun getAuctions(): DomainResult<List<AuctionsItemModel>> =
        withContext(dispatcher) {
            api.internalRetrofit.create(AuctionApi::class.java).getAuctions().withTimeOut().fold(
                failure = {
                    DomainResult.Error(it)
                },
                success = {
                    if (it != null) {
                        DomainResult.Success(it)
                    } else {
                        DomainResult.Error(
                            StatusResponse(
                                isSuccess = false,
                                code = "995",
                                message = "Result Null"
                            ),
                        )
                    }

                }
            )
        }

    override suspend fun getChats(): DomainResult<List<ChatResponseItemModel>> =
        withContext(dispatcher) {
            api.internalRetrofit.create(AuctionApi::class.java).getMessages().withTimeOut().fold(
                failure = {
                    DomainResult.Error(it)
                },
                success = {
                    if (it != null) {
                        DomainResult.Success(it)
                    } else {
                        DomainResult.Error(
                            StatusResponse(
                                isSuccess = false,
                                code = "995",
                                message = "Result Null"
                            ),
                        )
                    }

                }
            )
        }

    override suspend fun getAuctionDetail(productId: String): DomainResult<AuctionsItemModel> =
        withContext(dispatcher) {
            api.internalRetrofit.create(AuctionApi::class.java).getAuctionDetail(productId)
                .withTimeOut().fold(
                    failure = {
                        DomainResult.Error(it)
                    },
                    success = {
                        if (it != null) {
                            DomainResult.Success(it)
                        } else {
                            DomainResult.Error(
                                StatusResponse(
                                    isSuccess = false,
                                    code = "995",
                                    message = "Result Null"
                                ),
                            )
                        }

                    }
                )
        }

    override suspend fun updateBids(param: BidsRequestModel): DomainResult<AuctionsItemModel> =
        withContext(dispatcher) {
            api.internalRetrofit.create(AuctionApi::class.java).updateBids(
                auctionId = param.id,
                bidRequest = param.bids
            )
                .withTimeOut().fold(
                    failure = {
                        DomainResult.Error(it)
                    },
                    success = {
                        if (it != null) {
                            DomainResult.Success(it)
                        } else {
                            DomainResult.Error(
                                StatusResponse(
                                    isSuccess = false,
                                    code = "995",
                                    message = "Result Null"
                                ),
                            )
                        }

                    }
                )
        }

}