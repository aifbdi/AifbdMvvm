package com.aifbd.main

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.aifbd.baselib.base.activity.AifbdBaseDbActivity
import com.aifbd.baselib.base.fragment.AifbdBaseFragment
import com.aifbd.baselib.base.adapter.AifbdBaseFragmentAdapter
import com.aifbd.commonlib.router.RouterFragmentPath
import com.aifbd.main.databinding.ActivityMainBinding
import com.alibaba.android.arouter.launcher.ARouter
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AifbdBaseDbActivity<ViewM, ActivityMainBinding>(),
    BottomNavigationView.OnNavigationItemSelectedListener {

    private val adapter =AifbdBaseFragmentAdapter<Fragment>(this)

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onViewCreated(savedInstanceState: Bundle?) {
        mDatabind.click=TabClick()
        adapter.addFragment(ARouter.getInstance().build(RouterFragmentPath.Home.PAGER_HOME).withString("title","首页").navigation() as Fragment)
        adapter.addFragment(ARouter.getInstance().build(RouterFragmentPath.Found.PAGER_FOUND).withString("title","发现").navigation()as Fragment)
        adapter.addFragment(ARouter.getInstance().build(RouterFragmentPath.Message.PAGER_MESSAGE).withString("title","消息").navigation()as Fragment)
        adapter.addFragment(ARouter.getInstance().build(RouterFragmentPath.Me.PAGER_ME).withString("title","我的").navigation()as Fragment)
        mDatabind.vpHomePager.adapter = adapter
        // 设置导航栏条目点击事件
        mDatabind.bvMainNavigation.setOnNavigationItemSelectedListener(this)
    }

    override fun createObserver() {

    }

    inner class TabClick {
        fun onMsg(){
           // mDatabind.tvText.text = "${index++}次"
        }
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