package com.rer.core.utils

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.time.temporal.ChronoUnit
import java.util.Locale

const val countingDownFormat = "%02d:%02d:%02d:%02d"
fun getCurrentDateTimeFormatted(): String {
    val currentDateTime = ZonedDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
    return currentDateTime.format(formatter)
}

fun parseTargetDate(targetDateStr: String?): ZonedDateTime? {
    return try {
        if (targetDateStr.isNullOrBlank()) {
            null
        } else {
            ZonedDateTime.parse(
                targetDateStr,
                DateTimeFormatter.ISO_DATE_TIME
            )
        }
    } catch (e: DateTimeParseException) {
        null
    }
}

//calculate remaining time in seconds
fun calculateRemainingTimeInSeconds(targetDate: ZonedDateTime): Long {
    val now = ZonedDateTime.now()
    return ChronoUnit.SECONDS.between(now, targetDate)
}

//remaining time in DD:HH:MM:SS
fun formatRemainingTime(seconds: Long): String {
    val days = seconds / (24 * 3600)
    val hours = (seconds % (24 * 3600)) / 3600
    val minutes = (seconds % 3600) / 60
    val remainingSeconds = seconds % 60

    return String.format(
        locale = Locale.US,
        format = countingDownFormat,
        days, hours, minutes, remainingSeconds
    )
}