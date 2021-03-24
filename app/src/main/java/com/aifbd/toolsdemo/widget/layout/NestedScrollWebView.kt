package com.aifbd.toolsdemo.widget.layout

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.webkit.WebView
import androidx.core.view.NestedScrollingChild
import androidx.core.view.NestedScrollingChildHelper
import androidx.core.view.ViewCompat

/**
 * 项目名称：
 * 类名称：NestedScrollWebView.kt
 * 类描述：支持嵌套滚动的 WebView
 * 作者：AlanPaine
 * 创建时间： 2020/3/31-14:01
 * 邮箱：AlanPaine@163.COM
 * 修改简介：
 */
class NestedScrollWebView : WebView, NestedScrollingChild {
    private var mChildHelper: NestedScrollingChildHelper = NestedScrollingChildHelper(this)
    private var mLastMotionY = 0
    private val mScrollOffset = IntArray(2)
    private val mScrollConsumed = IntArray(2)
    private var mNestedOffsetY = 0
    private var mChange = false

    constructor(context: Context) : super(context) 
    constructor(context: Context, attrs: AttributeSet) : super(context,attrs) 

    constructor(context: Context,attrs: AttributeSet,defStyleAttr: Int) : super(context, attrs, defStyleAttr) 
    

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        var result = false
        val trackedEvent = MotionEvent.obtain(event)
        val action = event.actionMasked
        if (action == MotionEvent.ACTION_DOWN) {
            mNestedOffsetY = 0
        }
        val y = event.y.toInt()
        event.offsetLocation(0f, mNestedOffsetY.toFloat())
        when (action) {
            MotionEvent.ACTION_DOWN -> {
                mChange = false
                mLastMotionY = y
                startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL)
                result = super.onTouchEvent(event)
            }
            MotionEvent.ACTION_MOVE -> {
                var deltaY = mLastMotionY - y
                if (dispatchNestedPreScroll(0, deltaY, mScrollConsumed, mScrollOffset)) {
                    deltaY -= mScrollConsumed[1]
                    trackedEvent.offsetLocation(0f, mScrollOffset[1].toFloat())
                    mNestedOffsetY += mScrollOffset[1]
                }
                mLastMotionY = y - mScrollOffset[1]
                val oldY = scrollY
                val newScrollY = Math.max(0, oldY + deltaY)
                val dyConsumed = newScrollY - oldY
                val dyUnconsumed = deltaY - dyConsumed
                if (dispatchNestedScroll(0, dyConsumed, 0, dyUnconsumed, mScrollOffset)) {
                    mLastMotionY -= mScrollOffset[1]
                    trackedEvent.offsetLocation(0f, mScrollOffset[1].toFloat())
                    mNestedOffsetY += mScrollOffset[1]
                }
                if (mScrollConsumed[1] == 0 && mScrollOffset[1] == 0) {
                    if (mChange) {
                        mChange = false
                        trackedEvent.action = MotionEvent.ACTION_DOWN
                        super.onTouchEvent(trackedEvent)
                    } else {
                        result = super.onTouchEvent(trackedEvent)
                    }
                    trackedEvent.recycle()
                } else {
                    if (!mChange) {
                        mChange = true
                        super.onTouchEvent(
                            MotionEvent.obtain(
                                0,
                                0,
                                MotionEvent.ACTION_CANCEL,
                                0f,
                                0f,
                                0
                            )
                        )
                    }
                }
            }
            MotionEvent.ACTION_POINTER_DOWN, MotionEvent.ACTION_POINTER_UP, MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                stopNestedScroll()
                result = super.onTouchEvent(event)
            }
            else -> {
            }
        }
        return result
    }

    /**
     * [NestedScrollingChild]
     */
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

    init {
        isNestedScrollingEnabled = true
    }
}