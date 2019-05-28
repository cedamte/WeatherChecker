package com.example.weatherchecker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (Places.isInitialized().not()) Places.initialize(application, "AIzaSyB7g52X1Xu0cWPWFkCKa29ktRhOYqqzpHw")
        val placesClient = Places.createClient(this)

        val autocompleteSupportFragment = supportFragmentManager.findFragmentById(R.id.autocomplete_fragment) as
                AutocompleteSupportFragment
        autocompleteSupportFragment.setPlaceFields(listOf(Place.Field.NAME, Place.Field.ADDRESS_COMPONENTS))

        autocompleteSupportFragment.setTypeFilter(TypeFilter.CITIES)
        autocompleteSupportFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener{
            override fun onPlaceSelected(place: Place) {
                place.addressComponents
            }

            override fun onError(p0: Status) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })

    }
}
