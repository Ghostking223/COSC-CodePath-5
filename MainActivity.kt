package com.example.drivetypeseed

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.drivetypeseed.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val foodViewModel: FoodViewModel by viewModels {
        FoodViewModelFactory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = FoodEntryAdapter()
        binding.entriesRecyclerView.adapter = adapter
        binding.entriesRecyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                foodViewModel.allEntries.collect { entries ->
                    adapter.submitList(entries)
                }
            }
        }

        binding.addNewFoodButton.setOnClickListener {
            val intent = Intent(this, AddFoodActivity::class.java)
            startActivity(intent)
        }
    }

    // This method is called every time the activity becomes visible to the user.
    override fun onResume() {
        super.onResume()
        // By calling refreshEntries() here, we ensure the list is always up-to-date.
        foodViewModel.refreshEntries()
    }
}
