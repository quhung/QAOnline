package cn.qhung.qa.xposed.impl;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.qhung.qa.model.Question;
import cn.qhung.qa.net.NetUtils;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class ZhishiImpl extends AppHook {
    private static final String TAG = "ZhishiImpl";
    private String mTitle;

    @Override
    public void hook(XC_LoadPackage.LoadPackageParam lpparam) {
        XposedHelpers.findAndHookMethod("com.inke.trivia.hq.goldfinger.c", lpparam.classLoader,
                "a", JSONObject.class, new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        Object[] args = param.args;
                        if (args == null || args.length == 0)
                            return;
                        JSONObject jsonObjects = (JSONObject) args[0];
                        Log.d(TAG, "msg : " + jsonObjects);
                        JSONArray array = jsonObjects.optJSONArray("ms");
                        if (array == null || array.length() <= 0) {
                            return;
                        }
                        for (int i = 0; i < array.length(); i++) {
                            jsonObjects = array.getJSONObject(i);
                            String tp = jsonObjects.optString("tp");
                            if ("trivia_game.question".equals(tp)) {
                                onQuestion(jsonObjects.optJSONObject("question"));
                            } else {
                                Log.v(TAG, "miss tp :" + jsonObjects);
                            }
                        }
                    }
                });
    }


    private void onQuestion(JSONObject jsonObject) throws JSONException {
        Log.d(TAG, "on question: " + jsonObject);
        String title = jsonObject.optString("desc");
        if (TextUtils.equals(title, mTitle)) {
            return;
        }
        mTitle = title;
        JSONArray options = jsonObject.optJSONArray("options");
        Question question = new Question();
        question.title = title.trim();
        List<String> answer = new ArrayList<>();
        for (int i = 0; i < options.length(); i++) {
            JSONObject obj = options.optJSONObject(i);
            answer.add(obj.optString("content"));
        }
        question.answer = answer;
        NetUtils.postQuestion(question);
    }
}
