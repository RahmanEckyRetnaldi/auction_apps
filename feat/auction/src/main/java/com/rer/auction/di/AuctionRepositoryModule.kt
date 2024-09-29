package com.rer.auction.di

import com.rer.auction.data.repository.AuctionRepositoryImpl
import com.rer.auction.domain.repository.AuctionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class AuctionRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindOneAuctionRepository(
        auctionRepositoryImpl : AuctionRepositoryImpl
    ): AuctionRepository
}