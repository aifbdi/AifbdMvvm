package com.aifbd.baselib.base.adapter

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.aifbd.baselib.base.fragment.AifbdBaseFragment
import java.util.*

/**
 * 项目名称：
 * 类名称：AifndBaseFragmentAdapter
 * 类描述：AifndBaseFragmentAdapter基类
 * 作者：Aifbd
 * 创建时间： 2019/08/18-15:33
 * 邮箱：AIFBDI@163.COM
 * 修改简介：
 */
class AifbdBaseFragmentAdapter<F : Fragment>(manager: FragmentManager) :
    FragmentPagerAdapter( manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT ) {
    /** Fragment 集合  */
    private val mFragmentSet: MutableList<F> = ArrayList()

    /** Fragment 标题  */
    private val mFragmentTitle: MutableList<CharSequence?> = ArrayList()

    /**
     * 获取当前的Fragment
     */
    /** 当前显示的Fragment  */
    var showFragment: F? = null
        private set

    /** 当前 ViewPager  */
    private var mViewPager: ViewPager? = null

    /** 设置成懒加载模式  */
    private var mLazyMode = true

    constructor(activity: FragmentActivity) : this(activity.supportFragmentManager) {}
    constructor(fragment: Fragment) : this(fragment.childFragmentManager) {}

    override fun getItem(position: Int): Fragment {
        return mFragmentSet[position] as Fragment
    }

    override fun getCount(): Int {
        return mFragmentSet.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitle[position]
    }

    override fun setPrimaryItem(
        container: ViewGroup,
        position: Int,
        `object`: Any
    ) {
        if (showFragment !== `object`) {
            // 记录当前的Fragment对象
            showFragment = `object` as F
        }
        super.setPrimaryItem(container, position, `object`)
    }

    /**
     * 添加 Fragment
     */
    @JvmOverloads
    fun addFragment(fragment: F, title: CharSequence? = null) {
        mFragmentSet.add(fragment)
        mFragmentTitle.add(title)
        if (mViewPager != null) {
            notifyDataSetChanged()
            if (mLazyMode) {
                mViewPager?.offscreenPageLimit = count
            }
        }
    }


    override fun startUpdate(container: ViewGroup) {
        super.startUpdate(container)
        if (container is ViewPager) {
            // 记录绑定 ViewPager
            mViewPager = container
            refreshLazyMode()
        }
    }

    /**
     * 设置懒加载模式
     */
    fun setLazyMode(lazy: Boolean) {
        mLazyMode = lazy
        refreshLazyMode()
    }

    /**
     * 刷新加载模式
     */
    private fun refreshLazyMode() {
        if (mViewPager == null) {
            return
        }
        if (mLazyMode) {
            // 设置成懒加载模式（也就是不限制 Fragment 展示的数量）
            mViewPager?.offscreenPageLimit = count
        } else {
            mViewPager?.offscreenPageLimit = 1
        }
    }
}