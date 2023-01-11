package com.example.pizzalab.utils.common

import android.graphics.Typeface
import android.net.Uri
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.pizzalab.R
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.ExperimentalCoroutinesApi

@BindingAdapter("visibleGone")
fun View.setVisibility(show: Boolean) {
    visibility = if (show) View.VISIBLE else View.GONE
}

@BindingAdapter("inputError")
fun TextInputLayout.setError(@StringRes error: Int) {
    if (error != INVALID_RES)
        setError(resources.getString(error))
    else
        setError(null)
}

@BindingAdapter("glideRes", "defaultGlideRes")
fun View.setGlideRes(
    glideRes: Int?,
    defaultGlideRes: Int
) {
    val image = glideRes?.let { AppCompatResources.getDrawable(context, it) }
    Glide.with(this)
        .load(image)
        .error(defaultGlideRes)
        .placeholder(defaultGlideRes).centerCrop()
        .into(this as ImageView)
}

@BindingAdapter("uriRes")
fun ShapeableImageView.setUriRes(uri: Uri?) {
    if (uri != null && uri.toString() != EMPTY)
        setImageURI(uri)
    else
        setImageResource(R.drawable.ic_default_avatar)
}

@BindingAdapter("safeText")
fun TextView.setSafeText(value: Int) {
    text = value.toString()
}

@BindingAdapter("textFormatted")
fun TextView.setTextFormatted(textRes: TextRes) {
    with(textRes) {
        setText(
            if (textResource != INVALID_RES && text.isNullOrBlank().not()) {
                resources.getString(textResource, text)
            } else {
                EMPTY
            }
        )
    }
}

@BindingAdapter("boldText")
fun TextView.setTextBold(shouldBoldText: Boolean) {
    if (shouldBoldText) {
        setTypeface(null, Typeface.BOLD)
        setTextColor(resources.getColor(R.color.black))
    }
}

@ExperimentalCoroutinesApi
@BindingAdapter("textChanges")
fun EditText.setTextChange(callback: TextChangesCallback) {
    callback.textChanges(textChanges())
}

@BindingAdapter("textPrice")
fun TextView.setTextPrice(price: String?) {
    if (price != null)
        text = resources.getString(R.string.pizza_price, price)
}