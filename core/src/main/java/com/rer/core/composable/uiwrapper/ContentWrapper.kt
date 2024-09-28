package com.rer.core.composable.uiwrapper

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.rer.core.utils.findActivity

@Composable
fun ContentWrapper(
    statusBarColor: Color? = Color.Transparent,
    ignoreStatusBar: Boolean = true,
    useStatusBarDarkIcon: Boolean = false,
    useAndroidNavDarkIcon: Boolean = false,
    androidNavColor: Color = Color.Transparent,
    content: @Composable () -> Unit
) {
    // set full screen
    SetFullScreen(isDecorFitsSystemWindows = !ignoreStatusBar)
    // when null set transparent
    SetStatusBarColor(
        color = statusBarColor ?: Color.Transparent,
        useDarkIcon = useStatusBarDarkIcon,
        useAndroidNavDarkIcon = useAndroidNavDarkIcon,
        androidNavColor = androidNavColor
    )
    // load content
    content.invoke()
}

@Composable
fun SetFullScreen(isDecorFitsSystemWindows: Boolean) {
    val view = LocalView.current
    val window = view.context.findActivity()?.window
    if (window != null) {
        WindowCompat.setDecorFitsSystemWindows(window, isDecorFitsSystemWindows)
    }
}

@Composable
fun SetStatusBarColor(
    color: Color = Color.Transparent,
    useDarkIcon: Boolean = false,
    useAndroidNavDarkIcon: Boolean = false,
    androidNavColor: Color = Color.Transparent
) {
    rememberSystemUiController().apply {
        setStatusBarColor(
            color = color,
            darkIcons = useDarkIcon
        )
        setNavigationBarColor(color = androidNavColor, darkIcons = useAndroidNavDarkIcon)
    }
}