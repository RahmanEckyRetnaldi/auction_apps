package com.rer.core.utils.extention

fun Int.toDoubleOrDefault(default: Double = 0.0): Double {
    return try {
        this.toDouble()
    } catch (e: Exception) {
        default
    }
}