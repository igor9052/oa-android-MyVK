package ua.com.igorka.oa.android.myvk.api;

import android.os.Parcelable;

import java.util.ArrayList;

public interface IResponse<T> extends Parcelable {
    ArrayList<T> getItems();

    String getResponse();

    void setResponse(String response);
}
