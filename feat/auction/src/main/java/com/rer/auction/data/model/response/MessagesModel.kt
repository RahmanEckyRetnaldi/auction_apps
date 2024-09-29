package com.rer.auction.data.model.response

import com.google.gson.annotations.SerializedName

data class ChatResponseItemModel(
    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("user")
    val user: String? = null,

    @field:SerializedName("timestamp")
    val timestamp: String? = null
)
