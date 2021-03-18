package com.aifbd.message.debug;

import com.aifbd.baselib.AifbdBaseApp;
import com.aifbd.commonlib.config.AifbdModuleLifecycleConfig;
import com.aifbd.message.BuildConfig;

public class App extends AifbdBaseApp {
    @Override
    public void onCreate() {
        super.onCreate();
        setDebug(BuildConfig.DEBUG);
        // 初始化需要初始化的组件
        AifbdModuleLifecycleConfig.Companion.getInstance().initModuleAhead(this);
    }
}
