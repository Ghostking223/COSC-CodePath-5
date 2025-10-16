package com.example.drivetypeseed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class FoodEntryAdapter : ListAdapter<FoodEntry, FoodEntryAdapter.FoodEntryViewHolder>(FoodEntryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodEntryViewHolder {
        // Corrected to use the PROPER layout file. This will fix the error.
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_food_entry, parent, false)
        return FoodEntryViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodEntryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FoodEntryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val foodNameTextView: TextView = itemView.findViewById(R.id.foodNameTextView)
        private val calorieCountTextView: TextView = itemView.findViewById(R.id.calorieCountTextView)

        fun bind(entry: FoodEntry) {
            foodNameTextView.text = entry.foodName
            calorieCountTextView.text = entry.calories
        }
    }
}

class FoodEntryDiffCallback : DiffUtil.ItemCallback<FoodEntry>() {
    override fun areItemsTheSame(oldItem: FoodEntry, newItem: FoodEntry):
        Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: FoodEntry, newItem: FoodEntry):
        Boolean {
        return oldItem == newItem
    }
}
