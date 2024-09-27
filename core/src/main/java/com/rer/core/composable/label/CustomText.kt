package com.rer.core.composable.label

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import com.rer.core.ui.theme.errorLight

@Composable
fun CustomText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current,
    color: Color = MaterialTheme.colorScheme.onBackground,
    textAlign: TextAlign? = null,
    textDecoration: TextDecoration? = null,
    maxLines: Int = Int.MAX_VALUE,
    fontWeight: FontWeight? = null
) {
    Text(
        text = text,
        modifier = modifier,
        style = style,
        color = color,
        textAlign = textAlign,
        textDecoration = textDecoration,
        maxLines = maxLines,
        overflow = TextOverflow.Clip.takeIf { maxLines == Int.MAX_VALUE } ?: TextOverflow.Ellipsis,
        fontWeight = fontWeight
    )
}

@Composable
fun CustomText(
    text: Pair<String, String>,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current,
    color: Color = MaterialTheme.colorScheme.onBackground,
    textAlign: TextAlign? = null,
    textDecoration: TextDecoration? = null,
    maxLines: Int = Int.MAX_VALUE
) {
    Text(
        modifier = modifier,
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(color = color, textDecoration = textDecoration)) {
                append(text.first)
            }
            withStyle(style = SpanStyle(color = errorLight)) {
                append(text.second)
            }
        },
        style = style,
        textAlign = textAlign,
        maxLines = maxLines,
    )
}

@Composable
fun CustomText(
    text: AnnotatedString,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current,
    color: Color = MaterialTheme.colorScheme.onBackground,
    textAlign: TextAlign? = null,
    textDecoration: TextDecoration? = null,
    maxLines: Int = Int.MAX_VALUE
) {
    Text(
        text = text,
        modifier = modifier,
        style = style,
        color = color,
        textAlign = textAlign,
        textDecoration = textDecoration,
        maxLines = maxLines
    )
}