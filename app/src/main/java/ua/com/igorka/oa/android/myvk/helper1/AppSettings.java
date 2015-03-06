package ua.com.igorka.oa.android.myvk.helper1;

import android.content.Context;
import android.content.SharedPreferences;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * Created by Igor Kuzmenko on 04.03.2015.
 * Some static Application settings are here
 */
public class AppSettings {

    private static SharedPreferences preferences = null;

    public static void imageLoaderInit(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(50 * 1024 * 1024) // 50 Mb
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs() // Remove for release app
                .build();
        ImageLoader.getInstance().init(config);
    }

    public static SharedPreferences getPreferences() {
        return preferences;
    }

    public static void setPreferences(SharedPreferences preferences) {
        AppSettings.preferences = preferences;
    }

    public static class General {
        public static final String SHARED_PREFERENCES_FILE_NAME = "settings";
        public static final int DEFAULT_USER_ID_VALUE = 95178424;
        public static final String CURRENT_USER = "current_user";
    }

    public static class VkIntent {
        public static final String ACTION_RESPONSE = "ua.com.igorka.oa.android.myvk.intent.action.RESPONSE";
        public static final String EXTRA_USER_ID = "ua.com.igorka.oa.android.myvk.intent.extra.USER_ID";
        public static final String EXTRA_REQUEST = "ua.com.igorka.oa.android.myvk.intent.extra.REQUEST";
        public static final String EXTRA_RESPONSE = "ua.com.igorka.oa.android.myvk.intent.extra.RESPONSE";
        public static final String EXTRA_RESPONSE_ITEMS_LIST = "ua.com.igorka.oa.android.myvk.intent.extra.RESPONSE_ITEMS_LIST";

    }


}
