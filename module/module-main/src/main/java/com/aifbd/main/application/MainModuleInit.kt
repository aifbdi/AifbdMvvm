package com.aifbd.main.application

import com.aifbd.baselib.AifbdBaseApp
import com.aifbd.commonlib.application.AifbdIModuleInit
import com.orhanobut.logger.Logger


/**
 * 应用模块: main
 * 类描述: main组件的业务初始化
 */
class MainModuleInit : AifbdIModuleInit {
    override fun onInitAhead(application: AifbdBaseApp?): Boolean {
        Logger.i("main组件初始化完成 -- onInitAhead")
        return false
    }

    override fun onInitLow(application: AifbdBaseApp?): Boolean {
        return false
    }
}