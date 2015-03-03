package ua.com.igorka.oa.android.myvk.api;

/**
 * Created by Igor Kuzmenko on 27.02.2015.
 * Request example:
 * https://api.vk.com/method/friends.get?user_id=95178424&v=5.28&count=100&fields=nickname,photo_200_orig,photo_100
 */
public class FriendsgetRequest extends Request {

    //Names of params of friend.get request http://vk.com/dev/friends.get
    private final static String BASE_URL = "https://api.vk.com/method/friends.get";
    public static final String USER_ID = "user_id";
    public static final String API_VERSION = "v";
    public static final String COUNT = "count";
    public static final String FIELDS = "fields";

    //Profile fields to return. Sample values. list comma-separated strings.
    public static final String FIELDS_NICKNAME = "nickname";
    public static final String FIELDS_PHOTO_200 = "photo_200_orig";
    public static final String FIELDS_PHOTO_100 = "photo_100";

    private static final String mApiVersion = "5.28";
    private static final String mUserId = "95178424";
    private static final String mCount = "100";



    public FriendsgetRequest() {
        super(BASE_URL);
        addParams();
    }

    private void addParams() {
        addParam(USER_ID, mUserId);
        addParam(API_VERSION,mApiVersion);
        addParam(COUNT, mCount);
        addParam(FIELDS, FIELDS_NICKNAME + "," + FIELDS_PHOTO_100 + "," + FIELDS_PHOTO_200);
    }

}
