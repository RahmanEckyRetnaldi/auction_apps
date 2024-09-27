package com.rer.core.composable.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.rer.core.R

@Composable
fun FailureDialog(
    failureMessage: String,
    onDismissed: () -> Unit = {}
) {
    val isDismissed = remember { mutableStateOf(false) }

    if (!isDismissed.value) {
        Dialog(onDismissRequest = {}) {
            Surface(
                shape = RoundedCornerShape(10.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_exclamation),
                        contentDescription = "",
                        modifier = Modifier.padding(16.dp)
                    )
                    Text(
                        text = failureMessage,
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(16.dp),
                        textAlign = TextAlign.Center
                    )

                    Button(
                        onClick = {
                            onDismissed.invoke()
                            isDismissed.value = true
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)
                            .padding(16.dp),
                    ) {
                        Text(
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White,
                            text = "OK"
                        )
                    }
                }
            }
        }
    }
}
