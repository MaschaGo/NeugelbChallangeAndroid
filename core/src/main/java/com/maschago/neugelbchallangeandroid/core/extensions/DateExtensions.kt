package com.maschago.neugelbchallangeandroid.core.extensions

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

// Extension function to convert one date format to an other
fun String.toDayMonthYearDateFormat(
    inputPattern: String = "yyyy-MM-dd",
    outputPattern: String = "dd-MM-yyyy",
): String {
    val inputFormat = SimpleDateFormat(inputPattern, Locale.getDefault())
    val outputFormat = SimpleDateFormat(outputPattern, Locale.getDefault())

    return try {
        val date = inputFormat.parse(this)
        date?.let { outputFormat.format(it) } ?: "Invalid Date"
    } catch (e: Exception) {
        "Invalid Date"
    }
}

// Extension function to extract year from a date string
fun String?.extractYearFromDate(inputPattern: String = "yyyy-MM-dd"): String {
    return try {
        // Define the date format you're working with
        val dateFormat = SimpleDateFormat(inputPattern, Locale.getDefault())
        val parsedDate = this?.let { dateFormat.parse(it) }

        // Extract the year from the parsed date
        val calendar = Calendar.getInstance()
        calendar.time = parsedDate!!
        calendar.get(Calendar.YEAR).toString()
    } catch (e: Exception) {
        "Unknown"
    }
}