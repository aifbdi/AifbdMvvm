package com.aifbd.commonlib.application

import com.aifbd.baselib.AifbdBaseApp
import com.alibaba.android.arouter.launcher.ARouter
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

class AifbdCommonModuleInit:AifbdIModuleInit {
    override fun onInitAhead(application: AifbdBaseApp?): Boolean {
        // 初始化日志
        Logger.addLogAdapter(object : AndroidLogAdapter() {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return application?.isDebug()!!
            }
        })
        if (application?.isDebug()!!) {
            ARouter.openLog() // 开启日志
            ARouter.openDebug() // 使用InstantRun的时候，需要打开该开关，上线之后关闭，否则有安全风险
        }
        ARouter.init(application)
        return false
    }

    override fun onInitLow(application: AifbdBaseApp?): Boolean {
        return false
    }
}