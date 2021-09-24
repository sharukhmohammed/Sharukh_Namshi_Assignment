package com.namshi.sharukh.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Build
import android.os.SystemClock
import android.text.Editable
import android.text.TextWatcher
import android.util.TypedValue
import android.view.HapticFeedbackConstants
import android.view.View
import android.view.View.MeasureSpec
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.namshi.sharukh.base.App
import com.namshi.sharukh.base.GlideApp
import com.namshi.sharukh.models.Image
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable


infix fun ImageView.load(image: Image?) {

    //GlideApp.with(this).clear(this)

    var req = GlideApp.with(this)
        .load(image?.url)
        .fitCenter()

    if (image != null) {
        req = req.override(image.width.toFloat().dpToPx, image.height.toFloat().dpToPx)
    }

    req.into(this)
    //show()
}


fun View.onClick(debounceTime: Long = 300L, action: (view: View) -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0

        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
            else action(v)
            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}

fun List<View>.onClick(debounceTime: Long = 300L, action: (view: View) -> Unit) {
    val listener = object : View.OnClickListener {
        private var lastClickTime: Long = 0

        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
            else action(v)
            lastClickTime = SystemClock.elapsedRealtime()
        }
    }
    forEach { it.setOnClickListener(listener) }
}


fun View.hide() {
    this.visibility = View.INVISIBLE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

/**
 * conditional visibility of view
 * @param condition condition which must be satisfied for this view to be visible
 * @param hide if true, view will be hidden in case condition fails, else view will be set to gone
 */
fun View.showIf(condition: Boolean?, hide: Boolean = false) {
    if (condition == true) show()
    else {
        if (hide) hide()
        else gone()
    }
}

/**
 * conditional enability of view
 * @param condition condition which must be satisfied for this view to be visible
 */
fun View.enableIf(condition: Boolean?) {
    isEnabled = condition == true
}


/**
 * Checks the text if it's not empty or null, then shows the text view and sets the text
 * else it'll be gone
 * */
fun TextView.showIf(text: String?) {
    if (text.isNullOrEmpty())
        gone()
    else {
        show()
        this.text = text
    }
}

fun View.gone() {
    this.visibility = View.GONE
}


/**
 *  Change status bar color transparent
 */

@SuppressLint("ObsoleteSdkInt")
fun Window.transparentStatusBarColor() {
    if (Build.VERSION.SDK_INT >= 21) {
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        setWindowFlag(this)
        statusBarColor = Color.TRANSPARENT
    }
}

private fun setWindowFlag(window: Window) {
    val winParams = window.attributes
    winParams.flags = winParams.flags and WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS.inv()
    window.attributes = winParams
}


fun View.fadedHide(time: Long = 1000L) {
    this.animate().alpha(0f).setDuration(time).start()
}

fun View.fadedShow(time: Long = 1000L) {
    this.animate().alpha(1f).setDuration(time).start()
}

fun View.dramaticHide() {
    this.animate()?.scaleX(2f)?.scaleY(2f)?.alpha(0f)?.duration = 200
}

fun View.dramaticShow() {
    this.show()
    this.animate()?.scaleX(.8f)?.scaleY(.8f)?.alpha(1f)?.duration = 200
}


fun View.toBitmap(): Bitmap? {
    val view = this
    return if (view.height <= 0 || view.width <= 0) {
        view.measure(
            MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
            MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
        )
        view.layout(0, 0, view.measuredWidth, view.measuredHeight)
        val bitmap = Bitmap.createBitmap(view.measuredWidth, view.measuredHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        bitmap
    } else {
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        bitmap
    }
}


/**
 * @return Flowable<String> as output for text changes.
 * @see Flowable
 * Remember to dispose this properly or it'll cause mem leaks
 * @author Sharukh Mohammed
 * */
fun EditText.textChanges(): Flowable<String> {
    return Flowable.create({ emitter ->
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                emitter.onNext(p0.toString())
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }, BackpressureStrategy.LATEST)
}


val Float.dpToPx
    get() = (this * (App.instance.resources.displayMetrics.densityDpi / 160f)).toInt()

val Float.pxToDp
    get() = (this * (160f / App.instance.resources.displayMetrics.densityDpi)).toInt()

val Float.pxToSp
    get() = this / App.instance.resources.displayMetrics.scaledDensity

val Float.spToPx
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this, App.instance.resources.displayMetrics).toInt()


fun Int.toDp(context: Context): Int = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), context.resources.displayMetrics
).toInt()


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