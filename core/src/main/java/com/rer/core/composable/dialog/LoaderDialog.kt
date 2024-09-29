package com.rer.core.composable.dialog

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.rer.core.R

@Composable
fun LoaderDialog() {
    BaseDialog(
        onDismiss = {},
    ) {
        Surface(
            modifier = Modifier.size(128.dp),
            shape = RoundedCornerShape(10.dp),
            color = Color.Transparent
        ) {
            LottieAnimation(
                resId = R.raw.loading_animate,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            )
        }
    }
}

@Composable
fun LottieAnimation(
    @RawRes resId: Int,
    modifier: Modifier = Modifier,
    iterations: Int = LottieConstants.IterateForever,
    restartOnPlay: Boolean = true
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(resId))

    LottieAnimation(
        composition = composition,
        modifier = modifier,
        iterations = iterations,
        restartOnPlay = restartOnPlay
    )
}