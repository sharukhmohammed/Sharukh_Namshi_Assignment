package com.namshi.sharukh.utils

import android.content.res.Resources
import android.util.TypedValue
import android.view.HapticFeedbackConstants
import android.view.View
import android.widget.ImageView
import com.namshi.sharukh.dataModels.Image
import com.namshi.sharukh.misc.GlideApp
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable


/**
 * Takes an [Image] and loads into [ImageView] using Glide
 * Respects the aspect ratio.
 * */
infix fun ImageView.load(image: Image?) {

    var req = GlideApp.with(this)
        .load(image?.url)
        .fitCenter()

    if (image != null) {
        req = req.override(image.widthPx, image.heightPx)
    }

    req.into(this)
}


fun View.onClick(action: (view: View) -> Unit) {
    setOnClickListener(action)
}


fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

/**
 * conditional visibility of view
 * @param condition condition which must be satisfied for this view to be visible
 */
fun View.showIf(condition: Boolean?) {
    if (condition == true) show()
    else gone()
}

val Number.dpToPx get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), Resources.getSystem().displayMetrics)

fun <E> MutableList<E>.clearAndAddAll(elements: Collection<E>) {
    this.clear()
    this.addAll(elements)
}

operator fun CompositeDisposable?.plusAssign(other: Disposable?): Unit = if (other != null) this?.add(other).let { return } else Unit

fun List<*>.isValidIndex(index: Int) = index in 0 until size
fun List<*>.isNotValidIndex(index: Int) = isValidIndex(index).not()

fun View.hapticFeedback() {
    performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY, HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING)
}