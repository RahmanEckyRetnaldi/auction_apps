package com.rer.core.composable.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun BaseDialog(
    onDismiss: () -> Unit,
    dialogProperties: DialogProperties = DialogProperties(),
    overlayColor: Color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f),
    content: @Composable () -> Unit
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(overlayColor)
    ) {
        Dialog(
            onDismissRequest = { onDismiss.invoke() },
            properties = dialogProperties
        ) {
            content.invoke()
        }
    }

}