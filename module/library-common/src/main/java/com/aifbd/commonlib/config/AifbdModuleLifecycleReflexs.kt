package com.fcfrt.commonlib.config

/**
 * 项目名称：
 * 类名称：FcfrtModuleLifecycleReflexs.kt
 * 类描述：类描述: 组件生命周期初始化类的类目管理者,在这里注册需要初始化的组件,通过反射动态调用各个组件的初始化方法
 * 作者：AlanPaine
 * 创建时间： 2020/3/23-14:52
 * 邮箱：AlanPaine@163.COM
 * 修改简介：
 */
object AifbdModuleLifecycleReflexs {
    /** 基础库  */
    private const val BaseInit = "com.aifbd.commonlib.application.AifbdCommonModuleInit"

    /** main组件库  */
    private const val MainInit = "com.aifbd.main.application.MainModuleInit"

    var initModuleNames = arrayOf(
        BaseInit,
        MainInit
    )
}