package com.aifbd.commonlib.router

/**
 * 项目名称：
 * 类名称：RouterFragmentPath.kt
 * 类描述：组件化路由 用于组件化开发中,ARouter Fragment路径统一注册, 注册的路径请写好注释、标注业务功能
 * 作者：AlanPaine
 * 创建时间： 2020/3/31-14:01
 * 邮箱：AlanPaine@163.COM
 * 修改简介：
 */
class RouterFragmentPath {
    /** 首页组件  */
    object Home {
        private const val HOME = "/home"

        /** 首页  */
        const val PAGER_HOME = "$HOME/Home"
    }
    /** 发现组件  */
    object Found {
        private const val FOUND = "/found"

        /** 首页  */
        const val PAGER_FOUND = "$FOUND/Found"
    }
    /** 消息组件  */
    object Message {
        private const val MESSAGE = "/message"

        /** 首页  */
        const val PAGER_MESSAGE = "$MESSAGE/Message"
    }

    /** 我的组件  */
    object Me {
        private const val ME = "/me"

        /** 我的  */
        const val PAGER_ME = "$ME/Me"
    }
}