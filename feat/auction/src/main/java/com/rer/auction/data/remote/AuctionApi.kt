package com.rer.auction.data.remote

import com.rer.auction.data.model.request.BidsBodyRequestModel
import com.rer.auction.data.model.response.AuctionsItemModel
import com.rer.auction.data.model.response.ChatResponseItemModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface AuctionApi {

    @GET(AuctionRoutes.AUCTION)
    fun getAuctions(): Call<List<AuctionsItemModel>>

    @GET("${AuctionRoutes.AUCTION}/{id}")
    fun getAuctionDetail(@Path("id") auctionId: String): Call<AuctionsItemModel>

    @PUT("${AuctionRoutes.AUCTION}/{id}")
    fun updateBids(
        @Path("id") auctionId: String,
        @Body bidRequest: BidsBodyRequestModel
    ): Call<AuctionsItemModel>

    @GET(AuctionRoutes.MESSAGES)
    fun getMessages(): Call<List<ChatResponseItemModel>>
}