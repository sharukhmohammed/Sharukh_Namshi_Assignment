package com.namshi.sharukh.misc

import android.content.Context
import androidx.annotation.*
import com.namshi.sharukh.base.App


/*
Some utility functions which reduces lot of boilerplate from our codebase to access resources
 */
fun string(@StringRes id: Int, context: Context? = null): String {
    return context?.resources?.getString(id) ?: App.instance.resources.getString(id)
}

//fun color(@ColorRes id: Int): Int = ResourcesCompat.getColor(App.instance.resources, id, null)
//
//fun font(@FontRes id: Int): Typeface {
//    var typeface: Typeface = Typeface.DEFAULT
//    try {
//        typeface = ResourcesCompat.getFont(App.instance, id) ?: Typeface.DEFAULT
//    } catch (e: Exception) {
//        Timber.e(e)
//    }
//    return typeface
//}
//
//fun dimen(@DimenRes id: Int): Float = App.instance.resources.getDimension(id)
//fun integer(@IntegerRes id: Int): Int = App.instance.resources.getInteger(id)
//fun drawable(@DrawableRes id: Int): Drawable? = ResourcesCompat.getDrawable(App.instance.resources, id, null)