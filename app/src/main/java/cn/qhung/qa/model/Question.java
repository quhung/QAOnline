package cn.qhung.qa.model;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


public class Question {
    @SerializedName("title")
    public String title;
    @SerializedName("answer")
    public List<String> answer;

    public JSONObject toJson() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", title);
        JSONArray jsonArray = new JSONArray();
        for (String ans : answer) {
            jsonArray.put(ans);
        }
        jsonObject.put("answer", jsonArray);
        return jsonObject;
    }
}
