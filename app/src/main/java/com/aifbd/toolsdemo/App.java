package com.aifbd.toolsdemo;

import com.aifbd.baselib.AifbdBaseApp;

public class App extends AifbdBaseApp {
    @Override
    public void onCreate() {
        super.onCreate();
        setDebug(BuildConfig.DEBUG);
    }
}
