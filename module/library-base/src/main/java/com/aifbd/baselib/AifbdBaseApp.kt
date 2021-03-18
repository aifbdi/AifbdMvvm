package com.aifbd.baselib

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
/**
 * 项目名称：
 * 类名称：AifbdBaseApp
 * 类描述：AifbdBaseApp基类
 * 作者：Aifbd
 * 创建时间： 2019/08/18-15:33
 * 邮箱：AIFBDI@163.COM
 * 修改简介：
 */
open class AifbdBaseApp : Application() , ViewModelStoreOwner {
    private lateinit var mAppViewModelStore: ViewModelStore

    private var mFactory: ViewModelProvider.Factory? = null

    override fun getViewModelStore(): ViewModelStore {
        return mAppViewModelStore
    }

    override fun onCreate() {
        super.onCreate()
        mAppViewModelStore = ViewModelStore()
    }

    /**
     * 获取一个全局的ViewModel
     */
    fun getAppViewModelProvider(): ViewModelProvider {
        return ViewModelProvider(this, this.getAppFactory())
    }

    private fun getAppFactory(): ViewModelProvider.Factory {
        if (mFactory == null) {
            mFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(this)
        }
        return mFactory as ViewModelProvider.Factory
    }
    fun setDebug(isDebug: Boolean) {
        sDebug = isDebug
    }
    fun isDebug(): Boolean {
        return sDebug
    }
    companion object {
        private var sDebug = false
    }
}