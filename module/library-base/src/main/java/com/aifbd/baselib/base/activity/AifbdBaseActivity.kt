package com.aifbd.baselib.base.activity

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.aifbd.baselib.action.HandlerAction
import com.aifbd.baselib.base.viewmodel.AifbdBaseViewModel
import com.aifbd.baselib.other.getVmClazz
import com.aifbd.baselib.other.hideSoftKeyboard
/**
 * 项目名称：
 * 类名称：AifbdBaseActivity
 * 类描述：BaseActivity基类
 * 作者：Aifbd
 * 创建时间： 2019/08/18-15:33
 * 邮箱：AIFBDI@163.COM
 * 修改简介：
 */
abstract class AifbdBaseActivity<VM :AifbdBaseViewModel> :AppCompatActivity(), HandlerAction {
    lateinit var mViewModel: VM
    /**
     * 是否需要使用DataBinding 供子类BaseVmDbActivity修改，用户请慎动
     */
    private var isUserDb = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLayout()
        onIntentData()//获取数据
        onViewCreated(savedInstanceState)//界面创建完成
        createObserver()
        initData()
    }

    /**
     * 获取getIntent();数据
     */
    open fun onIntentData() {

    }
    /**
     * 获取布局id
     */
    abstract fun getLayoutId(): Int

    /**
     * 初始化view
     */
    abstract fun onViewCreated(savedInstanceState: Bundle?)

    /**
     * 创建观察者
     */
    abstract fun createObserver()
    /**
     * 初始化数据
     */
    open fun initData() {

    }
    /**
     * 初始化布局
     */
    protected open fun initLayout() {
        mViewModel = createViewModel()
        if (!isUserDb) {
            setContentView(getLayoutId())
        }else{
            initDataBind()
        }
        initSoftKeyboard()

    }
    fun setUserDataBinding(isUserDb: Boolean) {
        this.isUserDb = isUserDb
    }
    /**
     * 供子类BaseVmDbActivity 初始化Databinding操作
     */
    open fun initDataBind() {}
    /**
     * 创建viewModel
     */
    private fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClazz(this))
    }
    /**
     * 初始化软键盘
     */
    protected fun initSoftKeyboard() {
        // 点击外部隐藏软键盘，提升用户体验
        getContentView()?.setOnClickListener { v: View? -> hideSoftKeyboard(this) }
    }
    /**
     * 和 setContentView 对应的方法
     */
    fun getContentView(): ViewGroup? {
        return findViewById(Window.ID_ANDROID_CONTENT)
    }

}