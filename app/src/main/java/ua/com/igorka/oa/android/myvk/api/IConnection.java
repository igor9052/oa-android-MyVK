package ua.com.igorka.oa.android.myvk.api;

public interface IConnection<REQUEST extends IRequest, RESPONSE extends IResponse> {
    REQUEST request();
    RESPONSE sendRequest();
}
