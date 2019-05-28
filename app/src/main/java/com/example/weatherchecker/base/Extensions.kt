package com.example.weatherchecker.base

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}

fun ViewGroup.getLayoutInflater() = LayoutInflater.from(context)

@BindingAdapter("imageUrl")
fun ImageView.loadImageFromUrl(url: String?) {
    Picasso.get().load(url).into(this)
}