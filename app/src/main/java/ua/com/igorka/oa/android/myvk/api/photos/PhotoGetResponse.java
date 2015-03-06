package ua.com.igorka.oa.android.myvk.api.photos;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ua.com.igorka.oa.android.myvk.api.IResponse;
import ua.com.igorka.oa.android.myvk.data.Photo;

/**
 * Created by Igor Kuzmenko on 04.03.2015.
 */
public class PhotoGetResponse implements IResponse<Photo> {

    private String mResponse;

    @Override
    public ArrayList<Photo> getItems() {
        ArrayList<Photo> photoList = new ArrayList<>();
        Gson gson = new Gson();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(mResponse);
            JSONArray items = jsonObject.getJSONArray("response");
            Log.i(this.getClass().getSimpleName(), items.toString());
            photoList = gson.fromJson(items.toString(), new TypeToken<ArrayList<Photo>>(){}.getType());
            Log.i("PHOTO_GET_RESPONSE", photoList.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return photoList;
    }

    @Override
    public String getResponse() {
        return mResponse;
    }

    @Override
    public void setResponse(String response) {
        this.mResponse = response;
    }
}
