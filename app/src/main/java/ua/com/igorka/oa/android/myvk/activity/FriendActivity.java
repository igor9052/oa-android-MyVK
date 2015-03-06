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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import ua.com.igorka.oa.android.myvk.R;
import ua.com.igorka.oa.android.myvk.api.IRequest;
import ua.com.igorka.oa.android.myvk.api.friends.FriendsGetRequest;
import ua.com.igorka.oa.android.myvk.api.friends.FriendsGetResponse;
import ua.com.igorka.oa.android.myvk.data.User;
import ua.com.igorka.oa.android.myvk.helper.AppSettings;
import ua.com.igorka.oa.android.myvk.service.RequestService;


public class FriendActivity extends ActionBarActivity {

    private BroadcastReceiver friendsReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.hasExtra(AppSettings.VkIntent.EXTRA_RESPONSE)) {
                List<User> sUserList = intent.getParcelableArrayListExtra(AppSettings.VkIntent.EXTRA_RESPONSE_ITEMS_LIST);
                Log.i("Broadcast", "Broadcast is working");
                friendAdapter.clear();
                friendAdapter.addAll(sUserList);
            }
        }
    };
    private FriendAdapter friendAdapter;
    private LocalBroadcastManager broadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);
        broadcastManager = LocalBroadcastManager.getInstance(getApplication());

        final ListView friendList;
        friendAdapter = new FriendAdapter(this, new ArrayList<User>());
        friendList = (ListView) findViewById(R.id.friend_listview);
        friendList.setAdapter(friendAdapter);

        friendList.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User user = (User) friendList.getItemAtPosition(position);
                FriendsGetRequest request = new FriendsGetRequest();
                AppSettings.getPreferences().edit().putInt(AppSettings.General.CURRENT_USER, user.getUserId()).commit();
                request.addParam(FriendsGetRequest.USER_ID, String.valueOf(user.getUserId()));
                startService(initFriendsGetRequestIntent(request));
            }
        });
        startService(initFriendsGetRequestIntent(null));
    }

    private Intent initFriendsGetRequestIntent(IRequest request) {
        int userId = AppSettings.getPreferences().getInt(AppSettings.General.CURRENT_USER, 0);
        FriendsGetResponse response = new FriendsGetResponse();
        if (request == null) {
            request = new FriendsGetRequest();
            request.addParam(FriendsGetRequest.USER_ID, String.valueOf(userId));
        }
        Intent intent = new Intent(this, RequestService.class);
        intent.putExtra(AppSettings.VkIntent.EXTRA_REQUEST, request);
        intent.putExtra(AppSettings.VkIntent.EXTRA_RESPONSE, response);
        return intent;
    }

    @Override
    protected void onResume() {
        super.onResume();
        broadcastManager.registerReceiver(friendsReceiver,
                new IntentFilter(AppSettings.VkIntent.ACTION_RESPONSE + FriendsGetResponse.class.getSimpleName()));
    }

    @Override
    protected void onPause() {
        super.onPause();
        broadcastManager.unregisterReceiver(friendsReceiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_friend, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static final class FriendAdapter extends ArrayAdapter<User> {

        public FriendAdapter(Context context, List<User> objects) {
            super(context, 0, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(getContext(), R.layout.list_item_friend, null);
            }

            ImageView image100 = (ImageView) convertView.findViewById(R.id.imageview_image100);
            TextView idView = (TextView) convertView.findViewById(R.id.text_user_id);
            TextView firstNameView = (TextView) convertView.findViewById(R.id.text_user_first_name);
            TextView lastNameView = (TextView) convertView.findViewById(R.id.text_user_last_name);

            User user = getItem(position);

            ImageLoader.getInstance().displayImage(user.getPhoto100(), image100);
            idView.setText(Integer.toString(user.getUserId()));
            firstNameView.setText(user.getFirstName());
            lastNameView.setText(user.getLastName());

            return convertView;
        }
    }
}
