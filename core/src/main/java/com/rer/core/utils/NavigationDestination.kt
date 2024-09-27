package com.rer.core.utils

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
sealed class NavigationDestination: Parcelable{
    data class Splash(val data: String = "") : NavigationDestination()
}