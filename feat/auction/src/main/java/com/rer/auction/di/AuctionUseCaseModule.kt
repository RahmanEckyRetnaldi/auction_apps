package com.rer.auction.di

import com.rer.auction.domain.repository.AuctionRepository
import com.rer.auction.domain.usecase.GetAuctionDetailUseCase
import com.rer.auction.domain.usecase.GetAuctionListUseCase
import com.rer.auction.domain.usecase.GetChatListUseCase
import com.rer.auction.domain.usecase.PutBidsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AuctionUseCaseModule {

    @Provides
    @Singleton
    fun provideGetAuctionListUseCase(
        repository: AuctionRepository
    ): GetAuctionListUseCase {
        return GetAuctionListUseCase(
            repository
        )
    }

    @Provides
    @Singleton
    fun provideGetChatListUseCase(
        repository: AuctionRepository
    ): GetChatListUseCase {
        return GetChatListUseCase(
            repository
        )
    }

    @Provides
    @Singleton
    fun provideAuctionDetailUseCase(
        repository: AuctionRepository
    ): GetAuctionDetailUseCase {
        return GetAuctionDetailUseCase(
            repository
        )
    }

    @Provides
    @Singleton
    fun provideUpdateBidsUseCase(
        repository: AuctionRepository
    ): PutBidsUseCase {
        return PutBidsUseCase(
            repository
        )
    }
}