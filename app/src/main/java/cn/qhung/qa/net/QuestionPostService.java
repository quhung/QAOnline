package cn.qhung.qa.net;

import cn.qhung.qa.model.Question;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface QuestionPostService {

    @POST("/question")
    Call<Object> postQuestion(@Body Question body);
}
