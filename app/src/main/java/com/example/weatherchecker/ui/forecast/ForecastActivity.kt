package com.example.weatherchecker.ui.forecast

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.usecase.GetForecastUseCase
import com.example.weatherchecker.HomeActivity
import com.example.weatherchecker.R
import com.example.weatherchecker.databinding.ActivityForecastBinding
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_forecast.*
import javax.inject.Inject

class ForecastActivity : AppCompatActivity() {

    @Inject
    lateinit var forecastViewModel: ForecastViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        val forecastBinding = DataBindingUtil
            .setContentView<ActivityForecastBinding>(this, R.layout.activity_forecast)
        forecastBinding.lifecycleOwner = this

        val forecastAdapter = ForecastAdapter()
        rvForecast.layoutManager = LinearLayoutManager(this)
        rvForecast.adapter = forecastAdapter

        forecastViewModel.forecastViewStateObservable.observe(this, Observer { viewstate ->
            when (viewstate) {
                is ForecastViewState.Content -> forecastAdapter.setData(viewstate.payload)
                is ForecastViewState.Error -> Toast.makeText(this, viewstate.message, Toast.LENGTH_LONG).show()
            }
        })

        forecastViewModel.getForecast(intent.getStringExtra(HomeActivity.KEY_NAME))

    }
}