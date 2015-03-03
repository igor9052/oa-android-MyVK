package ua.com.igorka.oa.android.myvk.api;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Igor Kuzmenko on 27.02.2015.
 */
public abstract class Request implements IRequest {

    private Map<String, String> mParamsMap = new LinkedHashMap<>();
    private String mBaseUrl;

    protected Request(String baseUrl) {
        mBaseUrl = baseUrl;
    }

    @Override
    public void addParam(String name, String value) {
        mParamsMap.put(name, value);
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
}
