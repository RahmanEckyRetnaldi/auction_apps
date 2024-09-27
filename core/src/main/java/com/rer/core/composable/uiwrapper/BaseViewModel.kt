package com.rer.core.composable.uiwrapper

import androidx.lifecycle.ViewModel
import com.rer.core.network.utils.StatusResponse
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.updateAndGet

abstract class ViewModelWrapper<STATE:State, EVENT, INTENT>(
    initialState: STATE
): ViewModel(){
    //UI State
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<STATE> = _state.asStateFlow()
    val currentState: STATE get() = state.value

    protected fun updateState(update:(old: STATE) -> STATE): STATE = _state.updateAndGet(update)

    //UI Event
    val eventChannel = Channel<EVENT>()
    val event = eventChannel.receiveAsFlow()

    //loading state
    private val _loadingState = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> get() = _loadingState.asStateFlow()

    fun setLoading(isLoading: Boolean = true) {
        _loadingState.value = isLoading
    }

    //error state
    private val _responseState = MutableStateFlow(StatusResponse())
    val responseState: StateFlow<StatusResponse> get() = _responseState

    fun setStatusResponse(statusResponse: StatusResponse = StatusResponse()) {
        _responseState.value = statusResponse
    }

}