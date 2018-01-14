package cn.qhung.qa;

import android.app.Application;


public class QaApplication extends Application {
    private static QaApplication mInstance;

    public static QaApplication inst() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
}
