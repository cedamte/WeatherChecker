package com.example.weatherchecker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.model.HomeScreenState
import com.example.domain.model.ScreenState
import com.example.weatherchecker.databinding.ActivityHomeBinding
import com.example.weatherchecker.ui.home.AddCityBottomSheet
import com.example.weatherchecker.ui.home.CitiesAdapter
import com.example.weatherchecker.ui.home.HomeViewModel
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

const val RC_HOME_SEARCH = 101
const val ADD_CITY_BOTTOM_SHEET_TAG = "add_city"

class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        if (Places.isInitialized().not()) Places.initialize(application, BuildConfig.API_KEY_GOOGLE)

        val homeBinding = DataBindingUtil.setContentView<ActivityHomeBinding>(this, R.layout.activity_home)
        homeBinding.lifecycleOwner = this

        val citiesAdapter = CitiesAdapter(homeViewModel::onCitySelected)
        val layoutManager = LinearLayoutManager(this)
        rvCities.layoutManager = layoutManager
        rvCities.addItemDecoration(DividerItemDecoration(this, layoutManager.orientation))
        rvCities.adapter = citiesAdapter

        homeViewModel.homeScreenStateObservable.observe(this, Observer { screenState ->
            when(screenState) {
                is HomeScreenState.Content -> citiesAdapter.setData(screenState.payload)
                is HomeScreenState.LaunchCitySelection -> launchCitySelection()
                is HomeScreenState.ShowFavoriteOption -> showFavoriteOption()
                is ScreenState.Error -> Toast.makeText(this, screenState.errorMessage, Toast.LENGTH_LONG).show()
            }
        })

        fab_search.setOnClickListener { homeViewModel.onSearchSelected() }

        homeViewModel.getSavedCities()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_HOME_SEARCH) {
            data?.let {
                if (resultCode == RESULT_OK) { homeViewModel.onSearchResult(Autocomplete.getPlaceFromIntent(it)) }
                else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                    homeViewModel.onSearchError(Autocomplete.getStatusFromIntent(it))
                }
            }
        }
    }

    private fun launchCitySelection() {
        val fields = listOf(Place.Field.NAME, Place.Field.ADDRESS_COMPONENTS)
        val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fields)
            .setTypeFilter(TypeFilter.CITIES)
            .build(this)
        startActivityForResult(intent, RC_HOME_SEARCH)
    }

    private fun showFavoriteOption() {
        val addCityBottomSheet = AddCityBottomSheet.newInstance(homeViewModel::onFavoriteOptionSelected)
        addCityBottomSheet.show(supportFragmentManager, ADD_CITY_BOTTOM_SHEET_TAG)
    }
}
