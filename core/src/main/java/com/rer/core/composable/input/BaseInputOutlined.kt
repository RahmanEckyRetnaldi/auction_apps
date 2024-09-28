package com.rer.core.composable.input

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import com.rer.core.composable.label.CustomText
import com.rer.core.composable.spacer.Width
import com.rer.core.ui.theme.Neutral100
import com.rer.core.ui.theme.Neutral300
import com.rer.core.ui.theme.Neutral500
import com.rer.core.ui.theme.errorDark
import com.rer.core.ui.theme.errorLight
import com.rer.core.utils.specialCharPattern

data class BaseInputOutlinedData(
    val value: String,
    val title: String? = null,
    val placeholder: String = "",
    val isMandatory: Boolean = false,
    val info: String = "",
    val alert: String? = null,
    val outlinedHeight: Dp = 52.dp,
    val enabled: Boolean = true,
    val readOnly: Boolean = false,
    val digitOnly: Boolean = false,
    val isDeniedSpecialChar: Boolean = false,
    val disabledTextColor: Color? = null,
    val keyboardActions: KeyboardActions = KeyboardActions.Default,
    val onValueChange: (String) -> Unit,
    val onClick: () -> Unit = {},
    val prefix: @Composable (() -> Unit)? = null,
    val suffix: @Composable (() -> Unit)? = null,
    val leadingIcon: @Composable (() -> Unit)? = null,
    val trailingIcon: @Composable (() -> Unit)? = null,
)

data class BaseInputOutlinedColor(
    val textColor: Color,
    val focusedBorderColor: Color,
    val unfocusedBorderColor: Color,
    val cursorColor: Color,
    val errorBorderColor: Color,
)

@Composable
fun BaseInputOutlined(
    modifier: Modifier,
    data: BaseInputOutlinedData,
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
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    val hasFocus = remember { mutableStateOf(false) }

    Box(
        modifier = modifier,
    ) {

        Column(
            modifier = Modifier
                .align(Alignment.TopStart),
        ) {
            data.title?.let {
                Row(
                    modifier = modifier
                ) {
                    CustomText(
                        modifier = Modifier,
                        text = Pair(data.title, if (data.isMandatory) " *" else ""),
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.SemiBold
                        ),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }

            OutlinedTextField(
                value = data.value,
                onValueChange = {
                    it.checkDigitOnly(
                        isDigitOnly = data.digitOnly,
                        action = {
                            it.specialCharacterCheck(
                                isDeniedSpecialChar = data.isDeniedSpecialChar,
                                action = {
                                    data.onValueChange.invoke(it)
                                }
                            )

                        },
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = if (data.title == null) 0.dp else 10.dp)
                    .height(data.outlinedHeight)
                    .onFocusChanged {
                        hasFocus.value = it.hasFocus
                    }
                    .clickable(enabled = data.enabled) {
                        data.onClick.invoke()
                    },
                enabled = data.enabled,
                readOnly = data.readOnly,
                isError = !data.alert.isNullOrEmpty(),
                textStyle = textStyle,
                placeholder = {
                    if (data.placeholder.isNotEmpty()) {
                        TextPlaceholder(
                            placeholder = data.placeholder,
                            textAlign = textAlign
                        )
                    }
                },
                suffix = data.suffix,
                prefix = data.prefix,
                leadingIcon = data.leadingIcon,
                trailingIcon = data.trailingIcon,
                visualTransformation = visualTransformation,
                keyboardOptions = keyboardOptions,
                shape = RoundedCornerShape(8.dp),
                colors = data.disabledTextColor.setDisableTextColor(
                    color = color
                ),
                keyboardActions = data.keyboardActions,
            )
            AlertText(data)
        }

    }
}

@Composable
fun AlertText(data: BaseInputOutlinedData) {
    if (data.info.isNotEmpty())
        CustomText(
            text = data.info,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSecondary
        )

    if (!data.alert.isNullOrEmpty())
        CustomText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp),
            text = data.alert,
            style = MaterialTheme.typography.bodyMedium,
            color = errorLight
        )
}

@Composable
private fun Color?.setDisableTextColor(color: BaseInputOutlinedColor): TextFieldColors {
    return if (this != null) {
        OutlinedTextFieldDefaults.colors(
            focusedTextColor = color.textColor,
            unfocusedTextColor = color.textColor,
            focusedBorderColor = color.focusedBorderColor,
            unfocusedBorderColor = color.unfocusedBorderColor,
            cursorColor = color.cursorColor,
            errorBorderColor = color.errorBorderColor,
            disabledTextColor = Neutral500,
            disabledContainerColor = Neutral100,
            disabledBorderColor = Neutral300
        )
    } else {
        OutlinedTextFieldDefaults.colors(
            focusedTextColor = color.textColor,
            unfocusedTextColor = color.textColor,
            focusedBorderColor = color.focusedBorderColor,
            unfocusedBorderColor = color.unfocusedBorderColor,
            cursorColor = color.cursorColor,
            errorBorderColor = color.errorBorderColor,
            disabledContainerColor = Neutral100,
            disabledBorderColor = Neutral300,
            disabledTextColor = Neutral500,
        )
    }
}

private fun String.checkDigitOnly(
    isDigitOnly: Boolean,
    action: () -> Unit
) {
    if (isDigitOnly) {
        if (this.isDigitsOnly()) {
            action.invoke()
        }
    } else {
        action.invoke()
    }
}
private fun String.specialCharacterCheck(
    isDeniedSpecialChar: Boolean,
    action: () -> Unit
) {
    if (isDeniedSpecialChar) {
        if (!specialCharPattern.containsMatchIn(this)) {
            action.invoke()
        }
    } else {
        action.invoke()
    }
}


@Composable
private fun TextPlaceholder(
    placeholder: String,
    textAlign: TextAlign,
) {
    CustomText(
        text = placeholder,
        modifier = Modifier
            .fillMaxWidth(),
        style = MaterialTheme.typography.titleSmall.copy(
            lineHeight = 20.sp,
            fontWeight = FontWeight.Medium,
        ),
        color = Neutral500,
        textAlign = textAlign
    )
}

@Composable
fun IsMandatory(isMandatory: Boolean) {
    if (isMandatory) {
        2.Width()
        Text(
            modifier = Modifier,
            text = "*",
            style = MaterialTheme.typography.labelLarge.copy(
                fontWeight = FontWeight.SemiBold
            ),
            color = errorDark
        )
    }
}