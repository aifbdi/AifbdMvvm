package com.aifbd.toolsdemo.ui.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.aifbd.baselib.base.activity.AifbdBaseDbActivity
import com.aifbd.baselib.base.adapter.AifbdBaseFragmentAdapter
import com.aifbd.toolsdemo.R
import com.aifbd.toolsdemo.databinding.ActivityDbMainBinding
import com.aifbd.toolsdemo.ui.fragment.FoundFragment
import com.aifbd.toolsdemo.ui.fragment.HomeFragment
import com.aifbd.toolsdemo.ui.fragment.MeFragment
import com.aifbd.toolsdemo.ui.fragment.MessageFragment
import com.aifbd.toolsdemo.ui.viewmodule.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * DataBinding的用法
 */
class MainDbActivity : AifbdBaseDbActivity<MainViewModel, ActivityDbMainBinding>(),
    BottomNavigationView.OnNavigationItemSelectedListener {

    private val adapter = AifbdBaseFragmentAdapter<Fragment>(this)
    override fun getLayoutId(): Int {
        return R.layout.activity_db_main
    }

    override fun onViewCreated(savedInstanceState: Bundle?) {
        adapter.addFragment(HomeFragment.newInstance("首页"))
        adapter.addFragment(FoundFragment.newInstance("发现"))
        adapter.addFragment(MessageFragment.newInstance("消息"))
        adapter.addFragment(MeFragment.newInstance("我的"))
        mDatabind.vpHomePager.adapter = adapter
        mDatabind.bvMainNavigation.setOnNavigationItemSelectedListener(this)
    }

    override fun createObserver() {

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_home -> {
                mDatabind.vpHomePager.currentItem = 0
                return true
            }
            R.id.home_found -> {
                mDatabind.vpHomePager.currentItem = 1
                return true
            }
            R.id.home_message -> {
                mDatabind.vpHomePager.currentItem = 2
                return true
            }
            R.id.home_me -> {
                mDatabind.vpHomePager.currentItem = 3
                return true
            }
            else -> return false
        }
    }

}