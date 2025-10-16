package com.example.drivetypeseed

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.drivetypeseed.databinding.ActivityAddFoodBinding

class AddFoodActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddFoodBinding
    private val foodViewModel: FoodViewModel by viewModels {
        // We use the factory we created earlier to pass the application context
        FoodViewModelFactory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recordFoodButton.setOnClickListener {
            val foodName = binding.foodNameEditText.text.toString()
            val calories = binding.caloriesEditText.text.toString()

            // Use the ViewModel to insert the new food entry
            foodViewModel.insert(foodName, calories)

            // Finish the activity and return to the main list screen
            finish()
        }
    }
}
