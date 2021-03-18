package com.aifbd.found.ui.fragment

import android.os.Bundle
import com.aifbd.baselib.base.fragment.AifbdBaseFragment
import com.aifbd.commonlib.router.RouterFragmentPath
import com.aifbd.found.R
import com.aifbd.found.ui.viewmodule.FoundViewModel
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.android.synthetic.main.frament_found.*

@Route(path = RouterFragmentPath.Found.PAGER_FOUND)
class FoundFragment : AifbdBaseFragment<FoundViewModel>() {
    @Autowired
    @JvmField
    var title:String = ""

    override fun getLayoutId(): Int {
        return R.layout.frament_found
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