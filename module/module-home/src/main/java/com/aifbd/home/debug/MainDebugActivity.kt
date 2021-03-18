package com.aifbd.home.debug

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.aifbd.commonlib.router.RouterFragmentPath
import com.aifbd.home.R
import com.aifbd.home.ui.fragment.HomeFragment
import com.alibaba.android.arouter.launcher.ARouter

class MainDebugActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity_debug)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ARouter.getInstance().build(RouterFragmentPath.Home.PAGER_HOME).withString("title","首页").navigation() as Fragment)
                .commitNow()
        }
    }
}
