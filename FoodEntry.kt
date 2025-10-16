package com.example.drivetypeseed

import java.util.Date
import java.util.UUID

// Renamed from HealthEntry to represent a food item
data class FoodEntry(
    val id: String = UUID.randomUUID().toString(),
    val foodName: String,
    val calories: String,
    val timestamp: Date = Date() // Timestamp is kept but not displayed
)
