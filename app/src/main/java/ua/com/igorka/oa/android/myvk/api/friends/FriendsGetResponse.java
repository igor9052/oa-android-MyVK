package ua.com.igorka.oa.android.myvk.api.friends;

import android.os.Parcel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ua.com.igorka.oa.android.myvk.api.IResponse;
import ua.com.igorka.oa.android.myvk.data.User;

/**
 * Created by Igor Kuzmenko on 27.02.2015.
 * Response for friends.get request
 * @see FriendsGetRequest class
 */
public class FriendsGetResponse implements IResponse<User>, android.os.Parcelable {

    public static final Creator<FriendsGetResponse> CREATOR = new Creator<FriendsGetResponse>() {
        public FriendsGetResponse createFromParcel(Parcel source) {
            return new FriendsGetResponse(source);
        }

        public FriendsGetResponse[] newArray(int size) {
            return new FriendsGetResponse[size];
        }
    };
    private String mResponse = null;

    public FriendsGetResponse() {
    }

    private FriendsGetResponse(Parcel in) {
        this.mResponse = in.readString();
    }

    @Override
    public String getResponse() {
        return mResponse;
    }

    @Override
    public void setResponse(String response) {
        mResponse = response;
    }

    @Override
    public ArrayList<User> getItems() {
        if (mResponse == null) {
            throw new UnsupportedOperationException("You should call setResponse() method first or doRequest is null");
        }
        ArrayList<User> userList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(mResponse);
            JSONObject responseObject = jsonObject.getJSONObject("response");
            JSONArray items = responseObject.getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                JSONObject jsonUser = items.getJSONObject(i);
                User user = new User();
                user.setUserId(jsonUser.getInt("id"));
                user.setFirstName(jsonUser.getString("first_name"));
                user.setLastName(jsonUser.getString("last_name"));
                user.setPhoto100(jsonUser.getString("photo_100"));
                user.setPhoto200Orig(jsonUser.getString("photo_200_orig"));
                userList.add(user);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return userList;
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

