package com.rer.core.utils.extention

fun String.toDoubleOrZero(): Double {
    return try {
        this.toDouble()
    } catch (e: Exception) {
        0.0
    }
}

fun String.orDefault(default: String = ""): String {
    return this.ifEmpty { default }
}