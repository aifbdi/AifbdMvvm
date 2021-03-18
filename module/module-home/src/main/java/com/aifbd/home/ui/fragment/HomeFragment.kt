package com.aifbd.home.ui.fragment

import android.os.Bundle
import com.aifbd.baselib.base.fragment.AifbdBaseFragment
import com.aifbd.commonlib.router.RouterFragmentPath
import com.aifbd.home.R
import com.aifbd.home.ui.viewmodule.HomeViewModel
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.android.synthetic.main.frament_home.*

@Route(path = RouterFragmentPath.Home.PAGER_HOME)
class HomeFragment : AifbdBaseFragment<HomeViewModel>() {
    @Autowired
    @JvmField
    var title:String = ""


    override fun getLayoutId(): Int {
        return R.layout.frament_home
    }

    override fun onViewCreated(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)
        tv_text.text = title
    }

    override fun createObserver() {
        super.createObserver()
    }

    override fun lazyLoadData() {
        super.lazyLoadData()
    }
}