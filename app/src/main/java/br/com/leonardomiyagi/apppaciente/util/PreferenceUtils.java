package br.com.leonardomiyagi.apppaciente.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by lmiyagi on 6/28/17.
 */

public class PreferenceUtils {

    private static final String APP_PREFERENCES_NAME = "CONSULTORIO_PACIENTE";

    public static void setPreference(Context context, String key, Object value) throws ClassCastException {
        if (value instanceof String) {
            getPreferences(context).edit().putString(key, (String) value).apply();
        } else if (value instanceof Boolean) {
            getPreferences(context).edit().putBoolean(key, (Boolean) value).apply();
        } else {
            throw new ClassCastException();
        }
    }

    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        return getPreferences(context).getBoolean(key, defaultValue);
    }

    public static String getString(Context context, String key) {
        return getPreferences(context).getString(key, null);
    }

    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(APP_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }
}
