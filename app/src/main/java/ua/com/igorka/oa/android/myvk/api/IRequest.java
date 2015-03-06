package ua.com.igorka.oa.android.myvk.api;

import android.os.Parcelable;

import java.util.Map;

/**
 * Created by Igor Kuzmenko on 27.02.2015.
 */
public interface IRequest extends Parcelable {
    String getRequest();
    void addParam(String name, String value);
    void addParams(Map<String, String> params);
}
