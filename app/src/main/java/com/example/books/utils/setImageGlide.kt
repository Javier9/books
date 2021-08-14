package com.example.books.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

fun ImageView.setImageGlide(path: String?, placeholder: Drawable?, isFitCenter: Boolean? = true) {
    try {
        val options = RequestOptions()
            .placeholder(placeholder)
            .priority(Priority.NORMAL)
            .format(DecodeFormat.PREFER_RGB_565)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .dontAnimate()
            .apply {
                if(isFitCenter == null || isFitCenter) fitCenter() else centerCrop()
            }

        Glide.with(this)
            .asBitmap()
            .load(path)
            .apply(options)
            .into(this)
    } catch (e: Exception) {
    }
}