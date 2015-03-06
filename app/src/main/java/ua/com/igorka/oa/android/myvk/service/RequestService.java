package ua.com.igorka.oa.android.myvk.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import ua.com.igorka.oa.android.myvk.api.Connection;
import ua.com.igorka.oa.android.myvk.api.IConnection;
import ua.com.igorka.oa.android.myvk.api.IRequest;
import ua.com.igorka.oa.android.myvk.api.IResponse;
import ua.com.igorka.oa.android.myvk.api.helper.AppSettings;

/**
 * Created by Igor Kuzmenko on 03.03.2015.
 * Service do request and send local broadcast message with result.
 */
public class RequestService extends IntentService {

    private static final String TAG = "REQUEST_SERVICE";

    public RequestService() {
        super("RequestService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG, "Service is started");
        doRequest(intent);
    }

    private void doRequest(Intent intent) {
        IRequest request = intent.getParcelableExtra(AppSettings.VkIntent.EXTRA_REQUEST);
        IResponse response =  intent.getParcelableExtra(AppSettings.VkIntent.EXTRA_RESPONSE);
        IConnection<IRequest, IResponse> connection = new Connection<>(request, response);
        response = connection.sendRequest();
        Intent resultIntent = new Intent();
        resultIntent.setAction(AppSettings.VkIntent.ACTION_RESPONSE + response.getClass().getSimpleName());
        resultIntent.putExtra(AppSettings.VkIntent.EXTRA_RESPONSE, response);
        resultIntent.putParcelableArrayListExtra(AppSettings.VkIntent.EXTRA_RESPONSE_ITEMS_LIST, response.getItems());
        LocalBroadcastManager.getInstance(getApplication()).sendBroadcast(resultIntent);
    }
}
