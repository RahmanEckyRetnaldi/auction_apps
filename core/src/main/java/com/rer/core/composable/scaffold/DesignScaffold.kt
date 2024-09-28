package com.rer.core.composable.scaffold

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.rer.core.ui.theme.defaultPadding

data class CustomScaffoldParam(
    val ignoreNavPadding: Boolean = false,
    val fab: @Composable () -> Unit = {},
    val fabPosition: FabPosition = FabPosition.End
)
@Composable
fun DesignScaffold(
    showAppBar: Boolean = false,
    appBar: (@Composable () -> Unit)? = null,
    body: @Composable () -> Unit,
    bottomView: @Composable () -> Unit = {},
    background: @Composable () -> Unit = {},
    padding: Dp = defaultPadding,
    param: CustomScaffoldParam = CustomScaffoldParam(),
) {
    Scaffold(
        modifier = Modifier,
        contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp),
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                background.invoke()
                Column(
                    modifier = if (param.ignoreNavPadding) Modifier
                        .fillMaxSize() else Modifier.fillMaxSize().navigationBarsPadding()
                ) {
                    if (!showAppBar) {
                        Spacer(modifier = Modifier.windowInsetsTopHeight(WindowInsets.safeDrawing))
                    }
                    if (showAppBar) {
                        appBar?.invoke()
                    }
                    Box(
                        modifier = Modifier.padding(
                            start = padding,
                            end = padding
                        )
                    ) { body.invoke() }
                }
            }
        },
        floatingActionButton = param.fab,
        floatingActionButtonPosition = param.fabPosition,
        bottomBar = {
            bottomView.invoke()
        }
    )
}