package com.example.drivetypeseed

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

// Renamed and updated for FoodEntry
class FoodEntryRepository(context: Context) {

    private val gson = Gson()
    // The file will be named to reflect its new content
    private val file = File(context.filesDir, "food_entries.json")

    // Loads a list of FoodEntry objects
    fun loadEntries(): List<FoodEntry> {
        if (!file.exists()) {
            return emptyList()
        }
        val json = file.readText()
        val type = object : TypeToken<List<FoodEntry>>() {}.type
        return gson.fromJson(json, type) ?: emptyList()
    }

    // Saves a list of FoodEntry objects
    fun saveEntries(entries: List<FoodEntry>) {
        val json = gson.toJson(entries)
        file.writeText(json)
    }
}
