package com.aifbd.me.debug

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.aifbd.commonlib.router.RouterFragmentPath
import com.aifbd.me.R
import com.aifbd.me.ui.fragment.MeFragment
import com.alibaba.android.arouter.launcher.ARouter

class MainDebugActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity_debug)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ARouter.getInstance().build(RouterFragmentPath.Me.PAGER_ME).withString("title","我的").navigation() as Fragment)
                .commitNow()
        }
    }
}
