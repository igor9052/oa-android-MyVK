package ua.com.igorka.oa.android.myvk.api.photos;

import android.os.Parcel;
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
 * Response for friends.get request
 * @see ua.com.igorka.oa.android.myvk.api.photos.PhotosGetRequest class
 */
public class PhotoGetResponse implements IResponse<Photo>, android.os.Parcelable {

    public static final Creator<PhotoGetResponse> CREATOR = new Creator<PhotoGetResponse>() {
        public PhotoGetResponse createFromParcel(Parcel source) {
            return new PhotoGetResponse(source);
        }

        public PhotoGetResponse[] newArray(int size) {
            return new PhotoGetResponse[size];
        }
    };
    private String mResponse;

    public PhotoGetResponse() {
    }

    private PhotoGetResponse(Parcel in) {
        this.mResponse = in.readString();
    }

    @Override
    public ArrayList<Photo> getItems() {
        ArrayList<Photo> photoList = new ArrayList<>();
        Gson gson = new Gson();
        try {
            JSONObject jsonObject = new JSONObject(mResponse);
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mResponse);
    }
}
