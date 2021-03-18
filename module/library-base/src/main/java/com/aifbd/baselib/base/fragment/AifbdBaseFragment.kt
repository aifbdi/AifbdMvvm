package com.aifbd.baselib.base.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import com.aifbd.baselib.action.HandlerAction
import com.aifbd.baselib.base.viewmodel.AifbdBaseViewModel
import com.aifbd.baselib.other.getVmClazz
/**
 * 项目名称：
 * 类名称：AifbdBaseFragment
 * 类描述：BaseFragment基类
 * 作者：Aifbd
 * 创建时间： 2019/08/18-15:33
 * 邮箱：AIFBDI@163.COM
 * 修改简介：
 */
abstract class AifbdBaseFragment <VM : AifbdBaseViewModel> : Fragment(), HandlerAction {
    //是否第一次加载
    private var isFirst: Boolean = true

    lateinit var mViewModel: VM

    lateinit var mActivity: AppCompatActivity

    /**
     * 获取布局id
     */
    abstract fun getLayoutId(): Int

    /**
     * 初始化view
     */
    abstract fun onViewCreated(savedInstanceState: Bundle?)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as AppCompatActivity
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isFirst = true
        mViewModel = createViewModel()
        onViewCreated(savedInstanceState)
        createObserver()
        initData()
    }

    /**
     * 创建viewModel
     */
    private fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClazz(this))
    }

    /**
     * 创建观察者
     */
    open fun createObserver(){}
    /**
     * 懒加载
     */
    open fun lazyLoadData(){}
    /**
     * 初始化数据
     */
    open fun initData() {

    }

    override fun onResume() {
        super.onResume()
        onVisible()
    }
    /**
     * 延迟加载 防止 切换动画还没执行完毕时数据就已经加载好了，这时页面会有渲染卡顿  bug
     * 这里传入你想要延迟的时间，延迟时间可以设置比转场动画时间长一点 单位： 毫秒
     * 不传默认 300毫秒
     * @return Long
     */
    open fun lazyLoadTime(): Long {
        return 300
    }
    /**
     * 是否需要懒加载
     */
    private fun onVisible() {
        if (lifecycle.currentState == Lifecycle.State.STARTED && isFirst) {
            // 延迟加载 防止 切换动画还没执行完毕时数据就已经加载好了，这时页面会有渲染卡顿
            handler.postDelayed( {
                lazyLoadData()
                isFirst = false
            },lazyLoadTime())
        }
    }
}