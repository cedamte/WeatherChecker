package com.example.weatherchecker.ui.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.City
import com.example.weatherchecker.base.getLayoutInflater
import com.example.weatherchecker.databinding.ItemCityBinding

class CitiesAdapter(private val onCitySelectedListener: (City) -> Unit)
    : RecyclerView.Adapter<CitiesAdapter.CityViewHolder>() {

    private val cities = mutableListOf<City>()

    fun setData(data: List<City>) {
        cities.clear()
        cities.addAll(data)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CityViewHolder(ItemCityBinding.inflate(parent.getLayoutInflater(), parent, false))

    override fun getItemCount() = cities.size

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(cities[position], onCitySelectedListener)
    }

    class CityViewHolder(private val itemCityBinding: ItemCityBinding) : RecyclerView.ViewHolder(itemCityBinding.root) {
        fun bind(city: City, listener: (City) -> Unit) {
            itemCityBinding.city = city
            itemCityBinding.root.setOnClickListener { listener(city) }
            itemCityBinding.executePendingBindings()
        }
    }
}