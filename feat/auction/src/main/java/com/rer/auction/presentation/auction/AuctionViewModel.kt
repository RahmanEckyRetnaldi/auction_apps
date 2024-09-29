package com.rer.auction.presentation.auction

import androidx.lifecycle.viewModelScope
import com.rer.auction.domain.usecase.GetAuctionListUseCase
import com.rer.auction.domain.usecase.GetChatListUseCase
import com.rer.core.composable.uiwrapper.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuctionViewModel @Inject constructor(
    private val getAuctionUseCase: GetAuctionListUseCase,
    private val getChatListUseCase: GetChatListUseCase
) : BaseViewModel<AuctionState, AuctionEvent, AuctionIntent>(AuctionState()) {

    init {
        getAuctionList()
        getChats()
    }

    override fun onIntent(intent: AuctionIntent) {
        when (intent) {
            is AuctionIntent.OnNavigateToDetail -> {
                sendEvent(AuctionEvent.NavigateToDetail(intent.id))
            }

            is AuctionIntent.OnMessageSend -> {
                doSendMessage()
            }

            is AuctionIntent.OnNewMessageChanged -> {
                updateState {
                    it.copy(chatMessage = intent.message)
                }
            }

            is AuctionIntent.OnShowAuctionListDialog -> {
                updateState {
                    it.copy(
                        isShowAuctionDialog = intent.isShow
                    )
                }
            }
        }
    }

    private fun doSendMessage() = viewModelScope.launch {
        delay(1000)
        updateState {
            it.copy(chatMessage = "")
        }
    }

    private fun getAuctionList() = viewModelScope.launch {
        setLoading(true)
        val response = getAuctionUseCase.invoke()
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
                    it.copy(auctionList = result)
                }
            }
        )
    }

    private fun getChats() = viewModelScope.launch {
        setLoading(true)
        val response = getChatListUseCase.invoke()
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
                    it.copy(liveChats = result)
                }
            }
        )
    }

}