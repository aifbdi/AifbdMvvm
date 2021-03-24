package com.aifbd.toolsdemo.widget.layout

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.core.view.*
import androidx.viewpager.widget.ViewPager

/**
 * 项目名称：
 * 类名称：NestedViewPager.kt
 * 类描述：支持嵌套滚动的 ViewPager
 * 作者：AlanPaine
 * 创建时间： 2020/3/31-14:01
 * 邮箱：AlanPaine@163.COM
 * 修改简介：
 */
class NestedViewPager : ViewPager, NestedScrollingParent, NestedScrollingChild {
    private var mParentHelper: NestedScrollingParentHelper = NestedScrollingParentHelper(this)
    private var mChildHelper: NestedScrollingChildHelper = NestedScrollingChildHelper(this)

    constructor(context: Context) : super(context)
    constructor(context: Context,attrs: AttributeSet) : super(context, attrs)

    override fun setNestedScrollingEnabled(enabled: Boolean) {
        mChildHelper.isNestedScrollingEnabled = enabled
    }

    override fun isNestedScrollingEnabled(): Boolean {
        return mChildHelper.isNestedScrollingEnabled
    }

    override fun startNestedScroll(axes: Int): Boolean {
        return mChildHelper.startNestedScroll(axes)
    }

    override fun stopNestedScroll() {
        mChildHelper.stopNestedScroll()
    }

    override fun hasNestedScrollingParent(): Boolean {
        return mChildHelper.hasNestedScrollingParent()
    }

    override fun dispatchNestedScroll(
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        offsetInWindow: IntArray?
    ): Boolean {
        return mChildHelper.dispatchNestedScroll(
            dxConsumed,
            dyConsumed,
            dxUnconsumed,
            dyUnconsumed,
            offsetInWindow
        )
    }

    override fun dispatchNestedPreScroll(
        dx: Int,
        dy: Int,
        consumed: IntArray?,
        offsetInWindow: IntArray?
    ): Boolean {
        return mChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow)
    }

    override fun dispatchNestedFling(
        velocityX: Float,
        velocityY: Float,
        consumed: Boolean
    ): Boolean {
        return mChildHelper.dispatchNestedFling(velocityX, velocityY, consumed)
    }

    override fun dispatchNestedPreFling(
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        return mChildHelper.dispatchNestedPreFling(velocityX, velocityY)
    }

    override fun onStartNestedScroll(
        child: View,
        target: View,
        nestedScrollAxes: Int
    ): Boolean {
        return nestedScrollAxes and ViewCompat.SCROLL_AXIS_VERTICAL != 0
    }

    override fun onNestedScrollAccepted(
        child: View,
        target: View,
        nestedScrollAxes: Int
    ) {
        mParentHelper.onNestedScrollAccepted(child, target, nestedScrollAxes)
        startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL)
    }

    override fun onStopNestedScroll(target: View) {
        mParentHelper.onStopNestedScroll(target)
        stopNestedScroll()
    }

    override fun onNestedScroll(
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int
    ) {
        dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, null)
    }

    override fun onNestedPreScroll(
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray
    ) {
        dispatchNestedPreScroll(dx, dy, consumed, null)
    }

    override fun onNestedFling(
        target: View,
        velocityX: Float,
        velocityY: Float,
        consumed: Boolean
    ): Boolean {
        return dispatchNestedFling(velocityX, velocityY, consumed)
    }

    override fun onNestedPreFling(
        target: View,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        return dispatchNestedPreFling(velocityX, velocityY)
    }

    override fun getNestedScrollAxes(): Int {
        return mParentHelper.nestedScrollAxes
    }

    init {
        isNestedScrollingEnabled = true
    }
}