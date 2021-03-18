package com.aifbd.me.ui.fragment

import android.os.Bundle
import com.aifbd.baselib.base.fragment.AifbdBaseFragment
import com.aifbd.commonlib.router.RouterFragmentPath
import com.aifbd.home.ui.viewmodule.MeViewModel
import com.aifbd.me.R
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.android.synthetic.main.frament_me.*

@Route(path = RouterFragmentPath.Me.PAGER_ME)
class MeFragment : AifbdBaseFragment<MeViewModel>() {
    @Autowired
    @JvmField
    var title:String = ""
    override fun getLayoutId(): Int {
        return R.layout.frament_me
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