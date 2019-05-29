package com.example.weatherchecker.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import com.example.domain.model.Forecast
import com.example.weatherchecker.ui.forecast.ForecastSummary
import com.squareup.picasso.Picasso
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import java.text.DecimalFormat
import kotlin.math.roundToLong

fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}

fun ViewGroup.getLayoutInflater(): LayoutInflater = LayoutInflater.from(context)

fun ViewGroup.inflate(@LayoutRes layoutId: Int): View = LayoutInflater.from(context).inflate(layoutId, this, false)

@BindingAdapter("imageUrl")
fun ImageView.loadImageFromUrl(url: String?) {
    Picasso.get().load(url).into(this)
}

@BindingAdapter("cardinalDirection")
fun ImageView.setIconForCardinalDirection(direction: Double) {
    when (direction) {
        in 0.01..89.99 -> //North East
            this.animate().rotation(-45F).start()
        in 90.01..179.99 -> //south East
            this.animate().rotation(45F).start()
        in 180.01..269.99 -> //South West
            this.animate().rotation(135F).start()
        in 270.01..359.99 -> //North West
            this.animate().rotation(225F).start()
    }
}

fun Int.getReadableTimeFromEpoch(): String {
    val localDateTime = Instant.ofEpochSecond(this.toLong()).atZone(ZoneId.systemDefault())
        .toLocalDateTime()
    return localDateTime.format(DateTimeFormatter.ofPattern("EE hh:mm a"))
}

fun Double.getCentigradeFromKelvin(): String {
    val numberFormat = DecimalFormat("#0.0")
    return numberFormat.format(this - 273.15)
}

fun Forecast.toForecastSummary(): ForecastSummary {
    return with(this) {
        ForecastSummary(
            temperature = temperatureInKelvin.getCentigradeFromKelvin(),
            windDegrees = "%.2f".format(windDegrees).toDouble(),
            windSpeed = "%.2f".format(windSpeed * 3.6),
            condition = condition,
            date = time.getReadableTimeFromEpoch()
        )
    }
}