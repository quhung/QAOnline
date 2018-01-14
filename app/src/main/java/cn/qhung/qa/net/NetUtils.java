package cn.qhung.qa.net;

import android.util.Log;

import org.json.JSONException;

import cn.qhung.qa.Constants;
import cn.qhung.qa.model.Question;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetUtils {
    private static final QuestionPostService sService;
    private static final String TAG = "NetUtils";

    static {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        sService = retrofit.create(QuestionPostService.class);
    }

    public static void postQuestion(Question question) throws JSONException {
        Call<Object> call = sService.postQuestion(question);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Log.d(TAG, "on response: " + response);
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.d(TAG, "onFailure");
                t.printStackTrace();

            }
        });
    }
}
