package com.aifbd.message.ui.fragment

import android.os.Bundle
import com.aifbd.baselib.base.fragment.AifbdBaseFragment
import com.aifbd.commonlib.router.RouterFragmentPath
import com.aifbd.message.R
import com.aifbd.message.ui.viewmodule.MessageViewModel
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.android.synthetic.main.frament_message.*

@Route(path = RouterFragmentPath.Message.PAGER_MESSAGE)
class MessageFragment : AifbdBaseFragment<MessageViewModel>() {
    @Autowired
    @JvmField
    var title:String = ""
    override fun getLayoutId(): Int {
        return R.layout.frament_message
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