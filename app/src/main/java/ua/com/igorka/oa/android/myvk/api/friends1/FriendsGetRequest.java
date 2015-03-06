package ua.com.igorka.oa.android.myvk.api.friends1;

import android.os.Parcel;

import java.util.LinkedHashMap;
import java.util.Map;

import ua.com.igorka.oa.android.myvk.api.Request;

/**
 * Created by Igor Kuzmenko on 27.02.2015.
 * See http://vk.com/dev/friends.get
 * Request example:
 * https://api.vk.com/method/friends.get?user_id=95178424&v=5.28&count=100&fields=nickname,photo_200_orig,photo_100
 */
public class FriendsGetRequest extends Request {

    public static final String USER_ID = "user_id";
    public static final String API_VERSION = "v";
    public static final String COUNT = "count";
    public static final String FIELDS = "fields";
    //Profile fields to return. Sample values. list comma-separated strings.
    public static final String FIELDS_NICKNAME = "nickname";
    public static final String FIELDS_PHOTO_200 = "photo_200_orig";
    public static final String FIELDS_PHOTO_100 = "photo_100";
    //Names of params of friend.get request http://vk.com/dev/friends.get
    private static final String BASE_URL = "https://api.vk.com/method/friends.get";
    private static final String API_VERSION_VALUE = "5.28";
    private static final String USER_ID_VALUE = "95178424";
    private static final String COUNT_VALUE = "100";
    public static Creator<FriendsGetRequest> CREATOR = new Creator<FriendsGetRequest>() {
        @Override
        public FriendsGetRequest createFromParcel(Parcel source) {
            return new FriendsGetRequest(source);
        }

        @Override
        public FriendsGetRequest[] newArray(int size) {
            return new FriendsGetRequest[size];
        }
    };

    public FriendsGetRequest() {
        super(BASE_URL);
        Map<String, String> params = new LinkedHashMap<>();
        params.put(USER_ID, USER_ID_VALUE);
        params.put(API_VERSION, API_VERSION_VALUE);
        params.put(COUNT, COUNT_VALUE);
        params.put(FIELDS, FIELDS_NICKNAME + "," + FIELDS_PHOTO_100 + "," + FIELDS_PHOTO_200);
        addParams(params);
    }

    public FriendsGetRequest(Parcel in) {
        super(in);
    }
}
