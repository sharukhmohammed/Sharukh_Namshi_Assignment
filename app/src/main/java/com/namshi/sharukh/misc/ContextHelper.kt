package com.namshi.sharukh.misc

import android.content.Context
import androidx.annotation.StringRes
import com.namshi.sharukh.base.App


/**
 * String utility functions to access string res
 * */
fun string(@StringRes id: Int, context: Context? = null): String {
    return context?.resources?.getString(id) ?: App.instance.resources.getString(id)
}