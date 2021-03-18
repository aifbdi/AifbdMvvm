package com.aifbd.baselib.base.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.aifbd.baselib.base.viewmodel.AifbdBaseViewModel
/**
 * 项目名称：
 * 类名称：AifbdBaseDbActivity
 * 类描述：DataBindingActivity基类,，自动把ViewModel注入Activity和Databind注入进来了需要使用Databind的清继承它
 * 作者：Aifbd
 * 创建时间： 2019/08/18-15:33
 * 邮箱：AIFBDI@163.COM
 * 修改简介：
 */
abstract class AifbdBaseDbActivity<VM : AifbdBaseViewModel, DB : ViewDataBinding> :AifbdBaseActivity<VM>(){
    lateinit var mDatabind: DB

    override fun onCreate(savedInstanceState: Bundle?) {
        setUserDataBinding(true)
        super.onCreate(savedInstanceState)
    }
    /**
     * 创建DataBinding
     */
    override fun initDataBind() {
        mDatabind = DataBindingUtil.setContentView(this, getLayoutId())
        mDatabind.lifecycleOwner = this
    }
}