package com.rer.core.composable.appbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rer.core.R
import com.rer.core.ui.theme.onBackgroundLight

data class BaseAppBarParams(
    val background: Color = Color.Transparent,
    val contentColor: Color = onBackgroundLight,
    val useSafeArea: Boolean = true,
    val contentPadding: PaddingValues = PaddingValues(start = 16.dp, end = 16.dp)
)

@Composable
fun BaseAppBar(
    modifier: Modifier = Modifier,
    params: BaseAppBarParams = BaseAppBarParams(),
    title: (@Composable () -> Unit)? = null,
    backBtnIconResId: Int = R.drawable.ic_arrow_back_ios_24,
    onBackPressed: (() -> Unit)? = null,
) {
    Column(
        modifier = modifier.then(
            Modifier
                .background(params.background)
        )
    ) {
        if (params.useSafeArea) {
            Spacer(modifier = Modifier.windowInsetsTopHeight(WindowInsets.safeDrawing))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .padding(params.contentPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.weight(0.1f),
                contentAlignment = Alignment.Center
            ) {
                if (onBackPressed != null) {
                    IconButton(modifier = Modifier.align(Alignment.Center),
                        onClick = { onBackPressed.invoke() }) {
                        Icon(
                            painter = painterResource(id = backBtnIconResId),
                            contentDescription = stringResource(R.string.back_button_description),
                            tint = params.contentColor
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .weight(0.8f),
                contentAlignment = Alignment.Center
            ) {
                title?.invoke()
            }
        }
    }
}