package cn.qhung.qa.xposed.impl;

import de.robv.android.xposed.callbacks.XC_LoadPackage;

public abstract class AppHook {
    public abstract void hook(XC_LoadPackage.LoadPackageParam lpparam);
}
