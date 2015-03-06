package ua.com.igorka.oa.android.myvk.api;

import java.io.Serializable;
import java.util.ArrayList;

public interface IResponse<T> extends Serializable {
    ArrayList<T> getItems();

    String getResponse();

    void setResponse(String response);
}
