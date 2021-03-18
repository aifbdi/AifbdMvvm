package com.aifbd.found.debug

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.aifbd.commonlib.router.RouterFragmentPath
import com.aifbd.found.R
import com.aifbd.found.ui.fragment.FoundFragment
import com.alibaba.android.arouter.launcher.ARouter

class MainDebugActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity_debug)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ARouter.getInstance().build(RouterFragmentPath.Found.PAGER_FOUND).withString("title","发现").navigation() as Fragment )
                .commitNow()
        }
    }
}
