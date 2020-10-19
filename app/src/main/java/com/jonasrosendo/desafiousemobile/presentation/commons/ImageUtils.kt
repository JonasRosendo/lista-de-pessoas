package com.jonasrosendo.desafiousemobile.presentation.commons

import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jonasrosendo.desafiousemobile.R


fun ImageView.load(url: String?) {

    val options = RequestOptions()
        .placeholder(ColorDrawable(ContextCompat.getColor(context, R.color.grey_2E2E2E)))
        .error(R.drawable.doggo)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}