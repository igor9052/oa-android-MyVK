package ua.com.igorka.oa.android.myvk.api.photos;

import android.os.Parcel;

import java.util.LinkedHashMap;
import java.util.Map;

import ua.com.igorka.oa.android.myvk.api.Request;
import ua.com.igorka.oa.android.myvk.api.helper.AppSettings;

/**
 * Created by Igor Kuzmenko on 04.03.2015.
 * See http://vk.com/dev/photos.get
 * Request example:
 * https://api.vk.com/method/photos.get?owner_id=7250961&album_id=profile
 */
public class PhotosGetRequest extends Request{

    //Parameters of photo.get request
    public static final String OWNER_ID = "owner_id";
    public static final String ALBUM_ID = "album_id";
    public static final String ALBUM_ID_PROFILE_VALUE = "profile";
    public static final String ALBUM_ID_WALL_VALUE = "wall";
    public static final String ALBUM_ID_SAVED_VALUE = "saved";
    private static final String BASE_URL = "https://api.vk.com/method/photos.get";
    public static Creator<PhotosGetRequest> CREATOR = new Creator<PhotosGetRequest>() {
        @Override
        public PhotosGetRequest createFromParcel(Parcel source) {
            return new PhotosGetRequest(source);
        }

        @Override
        public PhotosGetRequest[] newArray(int size) {
            return new PhotosGetRequest[size];
        }
    };

    public PhotosGetRequest() {
        super(BASE_URL);
        Map<String, String> params = new LinkedHashMap<>();
        params.put(OWNER_ID, String.valueOf(AppSettings.General.DEFAULT_USER_ID_VALUE));
        params.put(ALBUM_ID, ALBUM_ID_PROFILE_VALUE);
        addParams(params);
    }
    public PhotosGetRequest(Parcel in) {
        super(in);
    }

    public void setWallAlbum() {
        this.addParam(ALBUM_ID, ALBUM_ID_WALL_VALUE);
    }

    public void setProfileAlbum() {
        this.addParam(ALBUM_ID, ALBUM_ID_PROFILE_VALUE);
    }

    public void setSavedAlbum() {
        this.addParam(ALBUM_ID, ALBUM_ID_SAVED_VALUE);
    }

    public void setOwnerId(int id) {
        this.addParam(OWNER_ID, String.valueOf(id));
    }
}
