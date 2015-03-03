package ua.com.igorka.oa.android.myvk.activity;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.ArrayList;
import java.util.List;

import ua.com.igorka.oa.android.myvk.R;
import ua.com.igorka.oa.android.myvk.api.Connection;
import ua.com.igorka.oa.android.myvk.api.FriendsgetRequest;
import ua.com.igorka.oa.android.myvk.api.FriendsgetResponse;
import ua.com.igorka.oa.android.myvk.api.IConnection;
import ua.com.igorka.oa.android.myvk.api.IResponse;
import ua.com.igorka.oa.android.myvk.data.User;


public class FriendActivity extends ActionBarActivity {

    private FriendAdapter friendAdapter;
    private static List<User> sUserList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);
        ListView friendList;
        friendAdapter = new FriendAdapter(this, new ArrayList<User>());
        friendList = (ListView) findViewById(R.id.friend_listview);
        friendList.setAdapter(friendAdapter);

        if (sUserList == null){
            AsyncTask<Void, Void, List<User>> asyncTask = new AsyncTask<Void, Void, List<User>>() {
                @Override
                protected List<User> doInBackground(Void... params) {
                    return getFriends();
                }

                @Override
                protected void onPostExecute(List<User> users) {
                    FriendActivity.sUserList = users;
                    friendAdapter.addAll(sUserList);
                }
            }.execute();
        }
        else {
            friendAdapter.addAll(sUserList);
        }

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

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getContext())
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(50 * 1024 * 1024) // 50 Mb
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs() // Remove for release app
                .build();


        public FriendAdapter(Context context, List<User> objects) {
            super(context, 0, objects);
            ImageLoader.getInstance().init(config);
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

    private List<User> getFriends() {
        IConnection<FriendsgetRequest> connection = new Connection(new FriendsgetRequest());
        IResponse userList = new FriendsgetResponse(connection.response());
        return userList.getItems();
    }
}
