package ua.com.igorka.oa.android.myvk.api;

import java.util.List;

/**
 * Created by Igor Kuzmenko on 05.03.2015.
 */
public interface GSonable<T> {
    List<T> parse();
}
