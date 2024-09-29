package com.rer.core.composable.uiwrapper

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rer.core.network.utils.StatusResponse
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch

abstract class BaseViewModel<STATE : State, EVENT, INTENT>(
    initialState: STATE
) : ViewModel() {
    //UI State
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<STATE> = _state.asStateFlow()
    val currentState: STATE get() = state.value

    abstract fun onIntent(intent: INTENT)

    protected fun updateState(update: (old: STATE) -> STATE): STATE = _state.updateAndGet(update)

    //UI Event
    private val _eventChannel = Channel<EVENT>()
    val event = _eventChannel.receiveAsFlow()

    fun sendEvent(event: EVENT) = viewModelScope.launch {
        _eventChannel.send(event)
    }

    //loading state
    private val _loadingState = MutableStateFlow(0)  // Counter
    val loadingState: StateFlow<Boolean> = _loadingState
        .asStateFlow()
        .map { it > 0 }
        .stateIn(viewModelScope, SharingStarted.Eagerly, false)

    fun setLoading(isLoading: Boolean = true) {
        if (isLoading) {
            _loadingState.value += 1
        } else {
            _loadingState.value = maxOf(_loadingState.value - 1, 0)
        }
    }

    //error state
    private val _responseState = MutableStateFlow(StatusResponse())
    val responseState: StateFlow<StatusResponse> = _responseState
        .asStateFlow()
        .stateIn(
        viewModelScope, SharingStarted.Eagerly,
        StatusResponse()
    )

    fun setStatusResponse(statusResponse: StatusResponse = StatusResponse()) {
        _responseState.value = statusResponse
    }


}