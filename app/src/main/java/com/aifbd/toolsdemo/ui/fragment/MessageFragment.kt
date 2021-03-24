package com.aifbd.toolsdemo.ui.fragment

import android.os.Bundle
import com.aifbd.baselib.base.fragment.AifbdBaseFragment
import com.aifbd.toolsdemo.R
import com.aifbd.toolsdemo.ui.viewmodule.MessageViewModel
import kotlinx.android.synthetic.main.frament_message.*

class MessageFragment : AifbdBaseFragment<MessageViewModel>() {

    var title:String = ""
    override fun getLayoutId(): Int {
        return R.layout.frament_message
    }
    companion object {
        private const val DATA = "data"
        fun newInstance(title: String): MessageFragment {
            val bundle = Bundle()
            bundle.putSerializable(DATA, title)
            val fragment = MessageFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
    override fun onIntentData() {
        super.onIntentData()
        title = arguments?.getString(DATA,getString(R.string.app_name)).toString()
    }
    override fun onViewCreated(savedInstanceState: Bundle?) {

    }

    override fun initData() {
        super.initData()
        tv_text.text = "$title"
    }

    override fun createObserver() {
        super.createObserver()
    }

    override fun lazyLoadData() {
        super.lazyLoadData()
    }
}