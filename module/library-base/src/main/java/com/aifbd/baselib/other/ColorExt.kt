package com.aifbd.baselib.other

import android.app.Activity
import android.content.Context
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

//////////////////////////设置颜色////////////////////////////////////////////
fun Context.getColors(@ColorRes id: Int): Int {
    return ContextCompat.getColor(this, id)
}

fun Fragment.getColors(@ColorRes id: Int): Int {
    var color = 0
    context?.let {
        color = ContextCompat.getColor(it, id)
    }
    return color
}

fun Activity.getColors(@ColorRes id: Int): Int {
    return ContextCompat.getColor(this, id)
}