package com.eddiej.searchbeers.util

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.eddiej.searchbeers.R

@BindingAdapter("app:imageUrl")
fun loadImage(imageView: AppCompatImageView, url: String) {
    Glide.with(imageView)
        .load(url)
        .error(R.drawable.ic_baseline_image_not_supported_24)
        .fallback(R.drawable.ic_baseline_image_not_supported_24)
        .into(imageView)
}