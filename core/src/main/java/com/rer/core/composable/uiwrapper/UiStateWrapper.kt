package com.rer.core.composable.uiwrapper

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rer.core.R
import com.rer.core.composable.dialog.FailureDialog
import com.rer.core.composable.dialog.LoaderDialog
import com.rer.core.network.utils.ApiResponseCode
import com.rer.core.network.utils.StatusResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

@Composable
inline fun <reified ViewModel : ViewModelWrapper<State, Event, Intent>, State, Event, Intent> UIStateWrapper(
    viewModel: ViewModel = hiltViewModel(),
    crossinline event: (Flow<Event>, CoroutineScope) -> Unit,
    crossinline content: @Composable (viewModel: ViewModel, state: State, (Intent)->Unit) -> Unit
) {
    //init
    val state: State? by viewModel.state.collectAsStateWithLifecycle()
    val loadingState by viewModel.loadingState.collectAsStateWithLifecycle()
    val responseState by viewModel.responseState.collectAsStateWithLifecycle()

    //event update
    LaunchedEffect(key1 = viewModel) {
        event.invoke(viewModel.event, this)
    }

    //build screen content
    state?.let { st ->
        content(viewModel, st, viewModel::onIntent)
    }

    //show Loading
    if (loadingState) {
        LoaderDialog()
    }

    //error dialog
    if (!loadingState
        && responseState.message.isNotEmpty()
    ) {
        FailureDialog(
            failureMessage = responseState.messageByErrorCode(),
            onDismissed = {

            }
        )
    }
}

@Composable
fun StatusResponse.messageByErrorCode(): String {
    return when (code) {
        ApiResponseCode.internalServerError ->
            stringResource(R.string.failed_connect_to_server)

        else -> message
    }
}