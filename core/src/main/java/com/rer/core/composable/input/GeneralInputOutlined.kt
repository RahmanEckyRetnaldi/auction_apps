package com.rer.core.composable.input

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.rer.core.ui.theme.errorLight

data class GeneralInputData(
    val value: String,
    val digitOnly: Boolean = false,
    val title: String? = null,
    val placeholder: String = "",
    val info: String = "",
    val alert: String? = null,
    val isMandatory: Boolean = true,
    val enabled: Boolean = true,
    val outLinedHeight: Dp = 52.dp,
    val onValueChange: (String) -> Unit
)

@Composable
fun GeneralInputOutlined(
    modifier: Modifier,
    data: GeneralInputData,
    color: BaseInputOutlinedColor = BaseInputOutlinedColor(
        textColor = MaterialTheme.colorScheme.onBackground,
        focusedBorderColor = MaterialTheme.colorScheme.outlineVariant,
        unfocusedBorderColor = MaterialTheme.colorScheme.outline,
        cursorColor = MaterialTheme.colorScheme.outlineVariant,
        errorBorderColor = errorLight,
    ),
    textAlign: TextAlign = TextAlign.Start,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium.copy(
        textAlign = textAlign
    ),
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Done
    )
) {
    BaseInputOutlined(
        modifier = modifier,
        data = BaseInputOutlinedData(
            value = data.value,
            title = data.title,
            digitOnly = data.digitOnly,
            placeholder = data.placeholder,
            alert = data.alert,
            onValueChange = {
                data.onValueChange.invoke(it)
            },
            isMandatory = data.isMandatory,
            enabled = data.enabled,
            outlinedHeight = data.outLinedHeight
        ),
        color = color,
        textStyle = textStyle,
        keyboardOptions = keyboardOptions
    )
}