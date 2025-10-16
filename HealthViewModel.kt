package com.example.drivetypeseed

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FoodViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = FoodEntryRepository(application)

    private val _entries = MutableStateFlow<List<FoodEntry>>(emptyList())
    val allEntries: StateFlow<List<FoodEntry>> = _entries

    init {
        _entries.value = repository.loadEntries()
    }

    // This function will be called to force a reload from the file
    fun refreshEntries() {
        _entries.value = repository.loadEntries()
    }

    fun insert(foodName: String, calories: String) {
        if (foodName.isNotBlank() && calories.isNotBlank()) {
            val newEntry = FoodEntry(foodName = foodName, calories = calories)
            val updatedList = _entries.value + newEntry
            _entries.value = updatedList
            repository.saveEntries(updatedList)
        }
    }
}

class FoodViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FoodViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FoodViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
