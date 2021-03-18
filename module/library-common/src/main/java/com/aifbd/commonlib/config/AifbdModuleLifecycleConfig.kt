package com.aifbd.commonlib.config

import com.aifbd.baselib.AifbdBaseApp
import com.aifbd.commonlib.application.AifbdIModuleInit
import com.fcfrt.commonlib.config.AifbdModuleLifecycleReflexs

/**
 * 项目名称：
 * 类名称：FcfrtModuleLifecycleConfig.kt
 * 类描述：作为组件生命周期初始化的配置类,通过反射机制,动态调用每个组件初始化逻辑
 * 作者：AlanPaine
 * 创建时间： 2020/3/24-9:58
 * 邮箱：AlanPaine@163.COM
 * 修改简介：
 */
class AifbdModuleLifecycleConfig {
    private object SingleHolder {
        internal val instances = AifbdModuleLifecycleConfig()
    }

    /** 优先初始化  */
    fun initModuleAhead(application: AifbdBaseApp?) {
        for (moduleName in AifbdModuleLifecycleReflexs.initModuleNames) {
            try {
                val clazz = Class.forName(moduleName)
                val init = clazz.newInstance() as AifbdIModuleInit
                init.onInitAhead(application)
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            } catch (e: InstantiationException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }
        }
    }

    /** 后初始化  */
    fun initModuleLow(application: AifbdBaseApp?) {
        for (moduleName in AifbdModuleLifecycleReflexs.initModuleNames) {
            try {
                val clazz = Class.forName(moduleName)
                val init = clazz.newInstance() as AifbdIModuleInit
                // 调用初始化方法
                init.onInitLow(application)
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            } catch (e: InstantiationException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }
        }
    }

    companion object {
        val instance: AifbdModuleLifecycleConfig
            get() = SingleHolder.instances
    }
}