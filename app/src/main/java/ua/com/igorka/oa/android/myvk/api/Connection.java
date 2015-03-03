package ua.com.igorka.oa.android.myvk.api;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Igor Kuzmenko on 27.02.2015.
 */
public class Connection implements IConnection {

    private IRequest mRequest;

    public Connection(IRequest request) {
        mRequest = request;
    }

    @Override
    public IRequest request() {
        return mRequest;
    }

    @Override
    public String response() {
        Log.i("REQUEST/" + mRequest.getClass().getSimpleName(), mRequest.getRequest());
        HttpURLConnection connection = null;
        StringBuilder response = new StringBuilder();
        try {
            URL url = new URL(mRequest.getRequest());
            connection = (HttpURLConnection) url.openConnection();
            InputStream in = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null)
                connection.disconnect();
        }
        return response.toString();
    }


}
