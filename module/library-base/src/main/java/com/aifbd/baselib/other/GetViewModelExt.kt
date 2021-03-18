package com.aifbd.baselib.other

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.aifbd.baselib.AifbdBaseApp
import com.aifbd.baselib.base.viewmodel.AifbdBaseViewModel
import java.lang.reflect.ParameterizedType

/**
 * 获取当前类绑定的泛型ViewModel-clazz
 */
@Suppress("UNCHECKED_CAST")
fun <VM> getVmClazz(obj: Any): VM {
    return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as VM
}

/**
 * 在Activity中得到Application上下文的ViewModel
 */
inline fun <reified VM : AifbdBaseViewModel> AppCompatActivity.getAppViewModel(): VM {
    (this.application as? AifbdBaseApp).let {
        if (it == null) {
            throw NullPointerException("你的Application没有继承框架自带的BaseApp类，暂时无法使用getAppViewModel该方法")
        } else {
            return it.getAppViewModelProvider().get(VM::class.java)
        }
    }
}

/**
 * 在Fragment中得到Application上下文的ViewModel
 * 提示，在fragment中调用该方法时，请在该Fragment onCreate以后调用或者请用by lazy方式懒加载初始化调用，不然会提示requireActivity没有导致错误
 */
inline fun <reified VM : AifbdBaseViewModel> Fragment.getAppViewModel(): VM {
    (this.requireActivity().application as? AifbdBaseApp).let {
        if (it == null) {
            throw NullPointerException("你的Application没有继承框架自带的BaseApp类，暂时无法使用getAppViewModel该方法")
        } else {
            return it.getAppViewModelProvider().get(VM::class.java)
        }
    }
}