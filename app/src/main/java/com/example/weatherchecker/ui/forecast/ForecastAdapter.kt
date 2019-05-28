package com.example.weatherchecker.ui.forecast

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherchecker.base.getLayoutInflater
import com.example.weatherchecker.databinding.ItemForecastBinding

class ForecastAdapter : RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    private val forecastList = mutableListOf<ForecastSummary>()

    fun setData(data: List<ForecastSummary>) {
        forecastList.clear()
        forecastList.addAll(data)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ForecastViewHolder(ItemForecastBinding.inflate(parent.getLayoutInflater(), parent, false))

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(forecastList[position])
    }

    override fun getItemCount() = forecastList.size

    class ForecastViewHolder(private val itemForecastBinding: ItemForecastBinding)
        : RecyclerView.ViewHolder(itemForecastBinding.root) {
        fun bind(forecastSummary: ForecastSummary) {
            itemForecastBinding.forecastSummary = forecastSummary
            itemForecastBinding.executePendingBindings()
        }
    }
}