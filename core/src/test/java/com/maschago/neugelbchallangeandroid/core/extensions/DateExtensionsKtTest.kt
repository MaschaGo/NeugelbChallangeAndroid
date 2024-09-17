package com.maschago.neugelbchallangeandroid.core.extensions

import org.junit.Assert.assertEquals
import org.junit.Test

class DateExtensionsKtTest {

    @Test
    fun `toDayMonthYearDateFormat should correctly convert date from yyyy-MM-dd to dd-MM-yyyy`() {
        // Given
        val dateString = "2023-09-10"

        // When
        val formattedDate = dateString.toDayMonthYearDateFormat()

        // Then
        assertEquals("10-09-2023", formattedDate)
    }

    @Test
    fun `toDayMonthYearDateFormat should return Invalid Date for malformed input`() {
        // Given
        val dateString = "invalid-date"

        // When
        val formattedDate = dateString.toDayMonthYearDateFormat()

        // Then
        assertEquals("Invalid Date", formattedDate)
    }

    @Test
    fun `toDayMonthYearDateFormat should return Invalid Date for null date`() {
        // Given
        val dateString = ""

        // When
        val formattedDate = dateString.toDayMonthYearDateFormat()

        // Then
        assertEquals("Invalid Date", formattedDate)
    }

    @Test
    fun `extractYearFromDate should correctly extract year from yyyy-MM-dd date`() {
        // Given
        val dateString = "2023-09-10"

        // When
        val year = dateString.extractYearFromDate()

        // Then
        assertEquals("2023", year)
    }

    @Test
    fun `extractYearFromDate should return Unknown for malformed date`() {
        // Given
        val dateString = "invalid-date"

        // When
        val year = dateString.extractYearFromDate()

        // Then
        assertEquals("Unknown", year)
    }

    @Test
    fun `extractYearFromDate should return Unknown for empty string`() {
        // Given
        val dateString = ""

        // When
        val year = dateString.extractYearFromDate()

        // Then
        assertEquals("Unknown", year)
    }

    @Test
    fun `extractYearFromDate should correctly extract year for custom input pattern`() {
        // Given
        val dateString = "10/09/2023"
        val inputPattern = "dd/MM/yyyy"

        // When
        val year = dateString.extractYearFromDate(inputPattern)

        // Then
        assertEquals("2023", year)
    }

}
