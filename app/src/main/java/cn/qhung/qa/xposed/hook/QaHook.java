package cn.qhung.qa.xposed.hook;

import cn.qhung.qa.xposed.impl.AppHook;
import cn.qhung.qa.xposed.impl.ZhishiImpl;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;


public class QaHook implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        AppHook appHook;
        switch (lpparam.packageName) {
            case "com.inke.trivia":
                appHook = new ZhishiImpl();
                break;
            default:
                appHook = null;
        }
        if (appHook != null) {
            appHook.hook(lpparam);
        }
    }
}
