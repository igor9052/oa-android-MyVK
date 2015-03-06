package ua.com.igorka.oa.android.myvk.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.nostra13.universalimageloader.core.ImageLoader;

import ua.com.igorka.oa.android.myvk.R;
import ua.com.igorka.oa.android.myvk.helper1.AppSettings;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        //TODO: fix bug if too big number is typed.
        findViewById(R.id.button_set_user_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.edit_text_user_id);
                if (editText.getText().toString() == null || "".equals(editText.getText().toString())) {
                    return;
                }
                AppSettings.getPreferences().edit().putInt(AppSettings.General.CURRENT_USER,
                        Integer.parseInt(editText.getText().toString())).commit();
            }
        });

        findViewById(R.id.button_friend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FriendActivity.class));
            }
        });

        findViewById(R.id.button_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PhotoActivity.class));
            }
        });

    }

    private void init() {
        if (!ImageLoader.getInstance().isInited()) {
            AppSettings.imageLoaderInit(getApplication());
        }
        SharedPreferences.Editor editor = getSharedPreferences(AppSettings.General.SHARED_PREFERENCES_FILE_NAME, MODE_PRIVATE).edit();
        editor.putInt(AppSettings.VkIntent.EXTRA_USER_ID, AppSettings.General.DEFAULT_USER_ID_VALUE);
        editor.putInt(AppSettings.General.CURRENT_USER, AppSettings.General.DEFAULT_USER_ID_VALUE);
        editor.apply();
        AppSettings.setPreferences(getSharedPreferences(AppSettings.General.SHARED_PREFERENCES_FILE_NAME, MODE_PRIVATE));
    }
}
