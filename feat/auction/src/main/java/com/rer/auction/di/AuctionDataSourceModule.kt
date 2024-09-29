package com.rer.auction.di

import com.rer.auction.data.remote.source.AuctionRemoteSource
import com.rer.auction.data.remote.source.AuctionRemoteSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AuctionDataSourceModule {

    @Binds
    abstract fun bindOnAuctionRemoteSource(
        auctionRemoteSourceImpl: AuctionRemoteSourceImpl
    ): AuctionRemoteSource
}