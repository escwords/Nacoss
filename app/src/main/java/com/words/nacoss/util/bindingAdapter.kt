package com.words.nacoss.util

import android.text.method.LinkMovementMethod
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_COMPACT
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.words.nacoss.R

@BindingAdapter("imageFromUrl")
fun getImageFromUrl(view: ImageView, url:String?){
    if(url != null ) {
        Glide.with(view.context)
            .load(url)
            .apply(RequestOptions().placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_img))
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}
/*
@BindingAdapter("wateringText")
fun bindWateringText(textView: TextView, wateringInterval: Int) {
    val resources = textView.context.resources
    val quantityString = resources.getQuantityString(R.plurals.watering_needs_suffix,
        wateringInterval, wateringInterval)
    textView.text = quantityString
}*/

@BindingAdapter("renderHtml")
fun bindRenderHtml(view: TextView, description: String?){
    if(description != null ){
        view.text = HtmlCompat.fromHtml(description,FROM_HTML_MODE_COMPACT)
        view.movementMethod = LinkMovementMethod.getInstance()
    }else{
        view.text = ""
    }
}