package com.rer.core.utils.extention

import java.text.NumberFormat
import java.util.Locale

fun Double.formatCurrencyToMYR(): String {
    return try {
        val amount = this
        val malaysiaLocale = Locale("ms", "MY")
        val currencyFormatter = NumberFormat.getCurrencyInstance(malaysiaLocale)

        val formattedAmount = currencyFormatter.format(amount)
        if (formattedAmount.startsWith("RM")) {
            formattedAmount.replace("RM", "RM ")
        } else {
            formattedAmount
        }
    } catch (e: Exception) {
        "RM 0.00"
    }
}