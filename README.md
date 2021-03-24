[![Platform][1]][2]  [![GitHub license][3]][4] 

[1]:https://img.shields.io/badge/platform-Android-blue.svg  
[2]:https://github.com/hegaojian/JetpackMvvm
[3]:https://img.shields.io/badge/license-Apache%202-blue.svg
[4]:https://github.com/aifbdi/AifbdMvvm/master/LICENSE

# 🔥🔥🔥:AifbdMvvm
- **🔥🔥AifbdMvvm一个Jetpack结合MVVM的快速开发框架，基于MVVM模式集成谷歌官方推荐的JetPack组件库实现组件化开发：LiveData、ViewModel、Lifecycle组件 使用Kotlin语言，添加大量拓展函数，简化代码 ，帮你简化各种操作，让你快速开发项目框架简约、详细注释，欢迎 star or fork！**
- **使用kotlin语言，添加大量拓展函数，简化代码**
- **组件化看默认master分支demo  基础版本看Basis分支demo**
## 打个小广告
 新建了个项目框架，网络请求可以使用 [RxHttp](https://github.com/hegaojian/ProjectTemplate)，音乐播放器可以使用 [StarrySky](https://github.com/EspoirX/StarrySky)，使用更方便，开发速度更高，有兴趣的可以star一下看看 
 

## 1.如何集成

- **1.1 在root's build.gradle中加入Jitpack仓库**

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

- **1.2 在app's build.gradle中添加依赖**

```
dependencies {
  ...
  implementation '还未发布'
}
```

- **1.3 在app's build.gradle中，android 模块下开启DataBinding(如果你不想用DataBinding,请忽略这一步)**

```
AndroidStudio 4.0 以下版本------>
android {
    ...
    dataBinding {
        enabled = true 
    }
}

AndroidStudio 4.0及以上版本 ------>
android {
    ...
   buildFeatures {
        dataBinding = true
    }
}
 
```

## 2.继承基类
一般我们项目中都会有一套自己定义的符合业务需求的基类 ***BaseActivity/BaseFragment***，所以我们的基类需要**继承本框架的Base类**

- 不想用Databinding的-------可以继承 AifbdBaseActivity/AifbdBaseFragment
- 用Databinding的-----------可以继承AifbdBaseDbActivity/AifbdBaseDbFragment**

**AifbdBaseActivity：**

```
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
```
**AifbdBaseDbActivity：**

```
abstract class AifbdBaseDbActivity<VM : AifbdBaseViewModel, DB : ViewDataBinding> :AifbdBaseActivity<VM>(){
    lateinit var mDatabind: DB

    override fun onCreate(savedInstanceState: Bundle?) {
        setUserDataBinding(true)
        super.onCreate(savedInstanceState)
    }
    /**
     * 创建DataBinding
     */
    override fun initDataBind() {
        mDatabind = DataBindingUtil.setContentView(this, getLayoutId())
        mDatabind.lifecycleOwner = this
    }
}
```
**AifbdBaseFragment：**

```
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
     * 获取getIntent();数据
     */
    open fun onIntentData() {

    }
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
        onIntentData()
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
```

```
abstract class AifbdBaseDbFragment<VM : AifbdBaseViewModel, DB : ViewDataBinding> : AifbdBaseFragment<VM>() {

    //该类绑定的ViewDataBinding
    lateinit var mDatabind: DB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mDatabind = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        mDatabind.lifecycleOwner = this
        return mDatabind.root
    }

}
```
## 3.编写一个登录功能

- **3.1 编写fragment_login.xml界面后转换成 databind 布局（鼠标停在根布局，Alt+Enter 点击提示 Convert to data binding layout即可）**
```
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:bind="http://schemas.android.com/tools">
    <data>
       
    </data>
    <LinearLayout>
       ....
    </LinearLayout>
 </layout>   
```
- **3.2 创建LoginViewModel类继承AifbdBaseViewModel**

```
class LoginViewModel : AifbdBaseViewModel() {
  
}
```

- **3.3 创建LoginFragment 继承基类传入相关泛型,第一个泛型为你创建的LoginViewModel,第二个泛型为ViewDataBind，保存fragment_login.xml后databinding会生成一个FragmentLoginBinding类。（如果没有生成，试着点击Build->Clean Project）**
```
class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding>() {
    
    /**
     *  当前fragment绑定的布局
     */
    override fun getLayoutId(): Int {
        return R.layout.fragment_login
    }
    
     /**
      *  获取传值
      */
     override fun onIntentData() {
            super.onIntentData()
            //title = arguments?.getString(DATA,getString(R.string.app_name)).toString()//案例
        }
     /**
      *  初始化view
      */    
     override fun onViewCreated(savedInstanceState: Bundle?) {
    
      }

     /**
      *  初始化数据
      */
     override fun initData() {
            super.initData()
            tv_text.text = "$title"
      }
        
     override fun createObserver() {
        super.createObserver()
     }
     /**
      *  fragment 懒加载
      */
     override fun lazyLoadData() {
            super.lazyLoadData()
     }
   
}


```
### 注意：使用该请求方式时需要注意，如果该ViewModel并不是跟Activity/Fragment绑定的泛型ViewModel，而是
val mainViewModel:MainViewModel by viewModels()
或者
val mainViewModel：MainViewModel by activityViewModels()


## 5.获取ViewModel
- **5.1我们的activity/fragment会有多个ViewModel，按传统的写法感觉有点累**
```
 val mainViewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory(application)).get(MainViewModel::class.java)
```
- **现在官方Ktx有拓展函数可以轻松调用
```
//在activity中获取当前Activity级别作用域的ViewModel
 private val mainViewModel:MainViewModel by viewModels()
 
//在activity中获取Application级别作用域的ViewModel（注，这个是本框架提供的，Application类继承框架的BaseApp才有用）
 private val mainViewModel by lazy { getAppViewModel<MainViewModel>()}

//在fragment中获取当前Fragment级别作用域的ViewModel
 private val mainViewModel:MainViewModel by viewModels()

//在fragment中获取父类Activity级别作用域的ViewModel
private val mainViewModel：MainViewModel by activityViewModels()

//在fragment中获取Application级别作用域的ViewModel（注，这个是本框架提供的，Application类继承框架的AifbdBaseApp才有用）
private val mainViewModel by lazy { getAppViewModel<MainViewModel>()}
```

## 感谢
- [RxHttp](https://github.com/hegaojian/ProjectTemplate)
- [JetpackMvvm](https://github.com/hegaojian/JetpackMvvm)
- [StarrySky](https://github.com/EspoirX/StarrySky)

## License
```
 Copyright 2019, aifbd      
  
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at 
 
       http://www.apache.org/licenses/LICENSE-2.0 

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```

