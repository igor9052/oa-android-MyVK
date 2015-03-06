package ua.com.igorka.oa.android.myvk.api;

import android.os.Parcel;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Igor Kuzmenko on 27.02.2015.
 *
 */
public abstract class Request implements IRequest {

    private String mBaseUrl;
    private Map<String, String> mParamsMap = new LinkedHashMap<>();

    protected Request(String baseUrl) {
        mBaseUrl = baseUrl;
    }

    protected Request(Parcel in) {
        mBaseUrl = in.readString();
        in.readMap(mParamsMap,LinkedHashMap.class.getClassLoader());
    }

    @Override
    public void addParam(String name, String value) {
        mParamsMap.put(name, value);
    }

    @Override
    public void addParams(Map<String, String> params) {
        for (String key: params.keySet()) {
            addParam(key, params.get(key));
        }
    }

    @Override
    public String getRequest() {
        StringBuilder result = new StringBuilder(mBaseUrl);
        result.append("?");
        for (String item : mParamsMap.keySet()) {
            result.append(item).append("=").append(mParamsMap.get(item)).append("&");
        }
        result.deleteCharAt(result.length()-1);
        return result.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mBaseUrl);
        dest.writeMap(mParamsMap);
    }


}
