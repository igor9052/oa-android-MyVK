package ua.com.igorka.oa.android.myvk.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import ua.com.igorka.oa.android.myvk.R;
import ua.com.igorka.oa.android.myvk.api.photos.PhotoGetResponse;
import ua.com.igorka.oa.android.myvk.api.photos1.PhotosGetRequest;
import ua.com.igorka.oa.android.myvk.data.Photo;
import ua.com.igorka.oa.android.myvk.helper1.AppSettings;
import ua.com.igorka.oa.android.myvk.service.RequestService;

public class PhotoActivity extends ActionBarActivity {

    private LocalBroadcastManager broadcastManager;
    private List<Photo> photoArrayList = new ArrayList<>();
    private BroadcastReceiver photoResponseReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.hasExtra(AppSettings.VkIntent.EXTRA_RESPONSE)) {
                photoArrayList = intent.getParcelableArrayListExtra(AppSettings.VkIntent.EXTRA_RESPONSE_ITEMS_LIST);
                Log.i(PhotoActivity.this.getClass().getSimpleName(), photoArrayList.toString());
                photoAdapter.clear();
                photoAdapter.addAll(photoArrayList);
            }
        }
    };
    private PhotoAdapter photoAdapter;
    private PhotosGetRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        broadcastManager = LocalBroadcastManager.getInstance(getApplication());

        photoAdapter = new PhotoAdapter(this, photoArrayList);
        GridView gridView = (GridView) findViewById(R.id.photo_gridview);
        gridView.setAdapter(photoAdapter);
        startService(getRequestIntent());
    }

    private Intent getRequestIntent() {
        if (request == null) {
            request = new PhotosGetRequest();
        }
        request.setOwnerId(AppSettings.getPreferences().getInt(AppSettings.General.CURRENT_USER, 1));
        Intent intent = new Intent(this, RequestService.class);
        intent.putExtra(AppSettings.VkIntent.EXTRA_REQUEST, request);
        intent.putExtra(AppSettings.VkIntent.EXTRA_RESPONSE, new PhotoGetResponse());
        return intent;
    }

    @Override
    protected void onResume() {
        super.onResume();
        broadcastManager.registerReceiver(photoResponseReceiver,
                new IntentFilter(AppSettings.VkIntent.ACTION_RESPONSE
                        + PhotoGetResponse.class.getSimpleName()));

    }

    @Override
    protected void onPause() {
        super.onPause();
        broadcastManager.unregisterReceiver(photoResponseReceiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_photo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_wall_photos) {
            setTitle(getString(R.string.title_wall_photos));
            request.setWallAlbum();
            startService(getRequestIntent());
            return true;
        }
        if (id == R.id.action_profile_photos) {
            setTitle(getString(R.string.title_profile_photos));
            request.setProfileAlbum();
            startService(getRequestIntent());
            return true;
        }
        if (id == R.id.action_saved_photos) {
            setTitle(getString(R.string.title_saved_photos));
            request.setSavedAlbum();
            startService(getRequestIntent());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static final class PhotoAdapter extends ArrayAdapter<Photo> {

        public PhotoAdapter(Context context, List<Photo> objects) {
            super(context, 0, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(getContext(), R.layout.grid_item_photo, null);
            }
            ImageView photoBig = (ImageView) convertView.findViewById(R.id.imageview_photo_src_big);
            TextView text = (TextView) convertView.findViewById(R.id.text_photo_text);

            Photo photo = getItem(position);

            ImageLoader.getInstance().displayImage(photo.getSrcBig(),photoBig);
            text.setText(photo.getText());
            return convertView;
        }
    }
}
