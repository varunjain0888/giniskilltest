package com.gini.skilltest.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.util.Patterns
import android.util.TypedValue
import android.view.View
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.gini.skilltest.R
import com.gini.skilltest.ui.main.view.home.deligates.ImageLoaderDelegate
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.showKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    this.requestFocus()
    imm.showSoftInput(this, 0)
}

fun View.hideKeyboard(): Boolean {
    try {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    } catch (ignored: RuntimeException) {
    }
    return false
}

inline fun View.snack(
    @StringRes messageRes: Int,
    length: Int = Snackbar.LENGTH_LONG,
    f: Snackbar.() -> Unit
) {
    snack(resources.getString(messageRes), length, f)
}

inline fun View.snack(
    message: String?,
    length: Int = Snackbar.LENGTH_LONG,
    f: Snackbar.() -> Unit
) {
    if (message == "success") {
        return
    }
    val snack = message?.let { Snackbar.make(this, it, length) }
    snack?.f()
    snack?.show()
}

fun Snackbar.action(@StringRes actionRes: Int, color: Int? = null, listener: (View) -> Unit) {
    action(view.resources.getString(actionRes), color, listener)
}

fun Snackbar.action(action: String, color: Int? = null, listener: (View) -> Unit) {
    setAction(action, listener)
    color?.let { setActionTextColor(ContextCompat.getColor(context, color)) }
}

fun ImageView.loadImage(url: String?, callback: ImageLoaderDelegate) {
    if (!TextUtils.isEmpty(url)) {
        val requestOptions =
            RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).override(150.dp(), 170.dp())
        Glide.with(context).load(url).listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                callback.onLoadFailed()
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                callback.onLoadSuccess()
                return false
            }
        }).apply(requestOptions).error(this.context.getDrawable(R.drawable.gini_logo)).into(this)
    } else {
        val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
        Glide.with(context).load(R.drawable.loader).apply(requestOptions).into(this)
    }
}

fun ImageView.loadImage(img: Int) {
    val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
    Glide.with(context).load(img).apply(requestOptions).into(this)
}


inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}

fun Fragment.addFragment(fragment: Fragment, frameId: Int) {
    childFragmentManager.inTransaction { add(frameId, fragment) }
}


fun Fragment.replaceFragment(fragment: Fragment, frameId: Int) {
    childFragmentManager.inTransaction { replace(frameId, fragment) }
}

fun TextInputEditText.isEmpty(): Boolean {
    if (TextUtils.isEmpty(this.text?.toString())) {
        requestFocus()
        return true
    }
    return false
}

fun TextInputEditText.isValidEmail(): Boolean {
    if ((!TextUtils.isEmpty(this.text?.toString()) && Patterns.EMAIL_ADDRESS.matcher(this.text?.toString())
            .matches())
    ) {
        return true
    }
    requestFocus()
    return false
}

fun TextInputEditText.isValidPhoneNumber(): Boolean {
    if ((!TextUtils.isEmpty(this.text?.toString()) && Patterns.PHONE.matcher(this.text?.toString())
            .matches())
    ) {
        return true
    }
    requestFocus()
    return false
}

fun TextInputEditText.mismatch(str: String?): Boolean {
    if (this.text.toString() != str) {
        requestFocus()
        return true
    }
    return false
}


fun String.getFormatedTime(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    val formatdate = SimpleDateFormat("HH:mm")
    try {
        val mDate: Date = sdf.parse(this)
        return formatdate.format(mDate)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return ""
}

fun String.getFormatedDate(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    val formatdate = SimpleDateFormat("dd MMM yyyy")
    try {
        val mDate: Date = sdf.parse(this)
        return formatdate.format(mDate)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return ""
}

fun getTotalMin(timeFromServer: String): Int {
    val time = timeFromServer.split("T".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
    val hourMin = time[1].split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
    val hour = Integer.parseInt(hourMin[0])
    val min = Integer.parseInt(hourMin[1])
    return hour * 60 + min //converted time of hour and minutes into total minutes
}

fun minuteToTime(minute: Int): String? {
    var minute = minute
    var hour = minute / 60
    minute %= 60
    var p = "AM"
    if (hour >= 12) {
        hour %= 12
        p = "PM"
    }
    if (hour == 0) {
        hour = 12
    }
    return (if (hour < 10) "0$hour" else hour).toString() + ":" + (if (minute < 10) "0$minute" else minute) + " " + p
}

fun Int.dp(): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()
}

fun Context.dpToPx(dp: Int): Int {
    return (dp * Resources.getSystem().displayMetrics.density).toInt()
}


fun View.setCustomAnimation(
    context: Context?,
    anim: Int,
    duration: Int
) {
    val animation =
        AnimationUtils.loadAnimation(context, anim)
    animation.duration = duration.toLong()
    this.startAnimation(animation)
}

fun getDays(t1: Long?, t2: Long?): Long {
    val diff: Long = t2?.minus(t1 ?: 0) ?: 0
    val days =
        java.util.concurrent.TimeUnit.DAYS.convert(diff, java.util.concurrent.TimeUnit.MILLISECONDS)
    return days
}

fun ViewPager2.onInterceptTouchEvent1(): Boolean {
    var childId: Int = 0
    if (childId > 0) {
        val pager: ViewPager? = findViewById<ViewPager>(childId)
        pager?.requestDisallowInterceptTouchEvent(true)
    }
    return true
}
