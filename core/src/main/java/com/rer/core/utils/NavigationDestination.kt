package com.rer.core.utils

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class NavigationDestination: Parcelable{
    data class Auction(val data: String = "") : NavigationDestination()
    data class DetailAuction(val productId: String) : NavigationDestination()
}