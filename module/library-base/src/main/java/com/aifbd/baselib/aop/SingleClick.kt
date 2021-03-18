package com.aifbd.baselib.aop

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * 项目名称：
 * 类名称：SingleClick
 * 类描述：防重复点击注解
 * 作者：Aifbd
 * 创建时间： 2019/08/18-15:33
 * 邮箱：AIFBDI@163.COM
 * 修改简介：
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
annotation class SingleClick(
    /**
     * 快速点击的间隔
     */
    val value: Long = 1000
)