package com.rer.core.network.utils

data class StatusResponse(
    var isSuccess: Boolean = true,
    var code: String = "",
    var message: String = "",
    var response: String = "",
)
