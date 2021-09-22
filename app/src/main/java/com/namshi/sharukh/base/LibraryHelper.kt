package com.namshi.sharukh.base

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import timber.log.Timber

/**
 * Automatically shows a Hyperlink to the calling Class and Linenumber in the Logs.
 * Allows quick lookup of the caller source just by clicking on the Hyperlink in the Log.
 */
class HyperlinkedDebugTree : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String {
        with(element) {
            return "($fileName:$lineNumber)$methodName()"
        }
    }
}

/**
 * Glide Generated API
* */
@GlideModule
class NamshiGlideModule : AppGlideModule() {
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        //Timber.d("applyOptions glide module $context")
        //val memoryCacheSizeBytes = 1024 * 1024 * 30 // 30mb

        builder.setDefaultRequestOptions(RequestOptions().format(DecodeFormat.PREFER_RGB_565))
            .setDefaultTransitionOptions(Drawable::class.java, DrawableTransitionOptions.withCrossFade())
            .setDefaultTransitionOptions(Bitmap::class.java, BitmapTransitionOptions.withCrossFade())
            .setLogLevel(Log.ERROR)
    }
}