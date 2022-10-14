package com.voyager.common.images

import android.graphics.Bitmap
import android.widget.ImageView
import java.net.URL

val cache = hashMapOf<String, Bitmap>()

interface Cache {
    fun get(url: String?): Bitmap
    fun put(url: String?, image: Bitmap?)
    fun contains(url: String?)
}

fun ImageView.load(url: String?) {
    val cache = context?.applicationContext.allCaches()
    val dimens = getImageViewDimensions()
    val image = if (url in cache) {
        loadImageFromCache(url)
    } else {
        loadImageFromUrl(url)
    }
    this.tag = url
    this.setImageBitmap(image)
}

fun Image

fun loadImageFromCache(url: String): Bitmap {
    return cache.get(url)
}

fun loadImageFromUrl(url: String): Bitmap {
    val connection = URL(url).openConnection()
    connection.
}

