package com.example.weatherchecker.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import java.text.DecimalFormat

fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}

fun ViewGroup.getLayoutInflater(): LayoutInflater = LayoutInflater.from(context)

fun ViewGroup.inflate(@LayoutRes layoutId: Int): View = LayoutInflater.from(context).inflate(layoutId, this, false)

@BindingAdapter("imageUrl")
fun ImageView.loadImageFromUrl(url: String?) {
    Picasso.get().load(url).into(this)
}

fun Int.getReadableTimeFromEpoch(): String {
    val localDateTime = Instant.ofEpochSecond(this.toLong()).atZone(ZoneId.systemDefault())
        .toLocalDateTime()
    return localDateTime.format(DateTimeFormatter.ofPattern("HH:mm a"))
}

fun Double.getCentigradeFromKelvin(): String {
    val numberFormat = DecimalFormat("#0.0")
    return numberFormat.format(this - 273.15)
}