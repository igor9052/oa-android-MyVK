package ua.com.igorka.oa.android.myvk.api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ua.com.igorka.oa.android.myvk.data.User;

/**
 * Created by Igor Kuzmenko on 27.02.2015.
 *
 */
public class FriendsgetResponse implements IResponse {

    private String mResponse;

    public FriendsgetResponse(String response) {
        mResponse = response;
    }

    @Override
    public List<User> getItems() {
        List<User> userList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(mResponse);
            JSONObject responseObject = jsonObject.getJSONObject("response") ;
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
}

