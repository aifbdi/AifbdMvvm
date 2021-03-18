package com.aifbd.baselib.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.aifbd.baselib.base.viewmodel.AifbdBaseViewModel

/**
 * 项目名称：
 * 类名称：AifbdBaseFragment
 * 类描述：DataBindingFragment基类,，自动把ViewModel注入Fragment和Databind注入进来了需要使用Databind的清继承它
 * 作者：Aifbd
 * 创建时间： 2019/08/18-15:33
 * 邮箱：AIFBDI@163.COM
 * 修改简介：
 */
abstract class AifbdBaseDbFragment<VM : AifbdBaseViewModel, DB : ViewDataBinding> : AifbdBaseFragment<VM>() {

    //该类绑定的ViewDataBinding
    lateinit var mDatabind: DB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mDatabind = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        mDatabind.lifecycleOwner = this
        return mDatabind.root
    }

}