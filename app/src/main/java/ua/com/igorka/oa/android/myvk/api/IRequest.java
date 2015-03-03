package ua.com.igorka.oa.android.myvk.api;

/**
 * Created by Igor Kuzmenko on 27.02.2015.
 */
public interface IRequest {
    String getRequest();
    void addParam(String name, String value);
}
