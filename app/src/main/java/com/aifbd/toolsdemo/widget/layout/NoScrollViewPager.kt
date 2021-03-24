package com.aifbd.toolsdemo.widget.layout

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager
import kotlin.math.abs

/**
 * 项目名称：
 * 类名称：NoScrollViewPager.kt
 * 类描述：禁用水平滑动的ViewPager（一般用于 APP 首页的 ViewPager + Fragment）
 * 作者：AlanPaine
 * 创建时间： 2020/3/31-14:01
 * 邮箱：AlanPaine@163.COM
 * 修改简介：
 */
class NoScrollViewPager : ViewPager {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context,attrs)
    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        // 不拦截这个事件
        return false
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent): Boolean {
        // 不处理这个事件
        return false
    }

    override fun executeKeyEvent(event: KeyEvent): Boolean {
        // 不响应按键事件
        return false
    }

    override fun setCurrentItem(item: Int) {
        // 只有相邻页才会有动画
        super.setCurrentItem(item, abs(currentItem - item) == 1)
    }
}