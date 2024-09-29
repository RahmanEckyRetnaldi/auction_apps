package com.rer.core.composable.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.rer.core.composable.label.CustomText
import com.rer.core.composable.spacer.Width
import com.rer.core.ui.theme.onSurfaceVariantLight
import com.rer.core.ui.theme.primaryLight

data class BaseButtonParams(
    val text: String,
    val textColor: Color? = null,
    val enabled: Boolean = false,
    val colorEnable: Pair<Color, Color> = Pair(primaryLight, White),
    val colorDisable: Pair<Color, Color> = Pair(onSurfaceVariantLight, White),
    val borderShape: Pair<BorderStroke?, Shape> = Pair(
        null,
        RoundedCornerShape(8.dp)
    ),
    val contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    val leadingIcon: @Composable (() -> Unit)? = null,
    val height: Dp = 40.dp
)

@Composable
fun BaseButton(
    baseButtonParams: BaseButtonParams,
    modifier: Modifier = Modifier,
    action: () -> Unit
) {
    Button(
        onClick = { action.invoke() },
        modifier = modifier.then(
            Modifier
                .height(height = baseButtonParams.height)
        ),
        shape = baseButtonParams.borderShape.second,
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = 0.dp,
            pressedElevation = 5.dp,
            disabledElevation = 0.dp
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = baseButtonParams.colorEnable.first,
            disabledContainerColor = baseButtonParams.colorDisable.first.copy(alpha = 0.5f)
        ),
        border = baseButtonParams.borderShape.first,
        enabled = baseButtonParams.enabled,
        contentPadding = baseButtonParams.contentPadding
    ) {
        if (baseButtonParams.leadingIcon != null) {
            baseButtonParams.leadingIcon.invoke()
            8.Width()
        }
        CustomText(
            text = baseButtonParams.text,
            style = MaterialTheme.typography.bodyMedium,
            color = baseButtonParams.textColor
                ?: if (baseButtonParams.enabled) {
                    baseButtonParams.colorEnable.second
                } else {
                    baseButtonParams.colorDisable.second.copy(alpha = 0.5f)
                }
        )
    }
}