package ua.com.igorka.oa.android.myvk.api;

public interface IConnection<T1 extends IRequest> {
    T1 request();
    String response();
}
