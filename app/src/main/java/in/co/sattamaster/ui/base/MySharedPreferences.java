package in.co.sattamaster.ui.base;

import android.content.SharedPreferences;


/**
 * Created by harish on 1/2/2017.
 */

public class MySharedPreferences {

    private static final String AMAZON_USER_ID = "AMAZON_USER_ID";
    private static final String DAILY_REVEAL_NUMBER = "DAILY_REVEAL_NUMBERSS";
    private static final String DAILY_REVEAL_TIME = "DAILY_REVEAL_TIMESS";

    private static final String Hourly_REVEAL_NUMBER = "Hourly_REVEAL_NUMBERSS";
    private static final String Hourly_REVEAL_TIME = "Hourly_REVEAL_TIMESS";
    private static final String USER_TOKEN = "USER_TOKEN";

    public static void wipe(SharedPreferences sharedPreferences) {
        MySharedPreferences.storeValueInSharedPreferences(sharedPreferences, AMAZON_USER_ID, null);
    }

    protected static void storeValueInSharedPreferences(SharedPreferences sharedPreferences, String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    protected static void storeValueInSharedPreferences2(SharedPreferences sharedPreferences, String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    protected static String getValueFromSharedPreferences(SharedPreferences sharedPreferences, String key) {
        return sharedPreferences.getString(key, null);
    }

    public static void registerUserId(SharedPreferences sharedPreferences, String userId) {
        MySharedPreferences.storeValueInSharedPreferences2(sharedPreferences, AMAZON_USER_ID, userId);
    }

    public static void registerDailyNumber(SharedPreferences sharedPreferences, String userId) {
        MySharedPreferences.storeValueInSharedPreferences2(sharedPreferences, DAILY_REVEAL_NUMBER, userId);
    }

    public static void registerDailyTime(SharedPreferences sharedPreferences, String userId) {
        MySharedPreferences.storeValueInSharedPreferences2(sharedPreferences, DAILY_REVEAL_TIME, userId);
    }

    public static void registerHourlyNumber(SharedPreferences sharedPreferences, String userId) {
        MySharedPreferences.storeValueInSharedPreferences2(sharedPreferences, Hourly_REVEAL_NUMBER, userId);
    }

    public static void registerHourlyTime(SharedPreferences sharedPreferences, String userId) {
        MySharedPreferences.storeValueInSharedPreferences2(sharedPreferences, Hourly_REVEAL_TIME, userId);
    }

    public static String getHourlyNumber(SharedPreferences sharedPreferences) {
        return MySharedPreferences.getValueFromSharedPreferences(sharedPreferences, Hourly_REVEAL_NUMBER);
    }

    public static String getHourlyTime(SharedPreferences sharedPreferences) {
        return MySharedPreferences.getValueFromSharedPreferences(sharedPreferences, Hourly_REVEAL_TIME);
    }



    public static String getDailyNumber(SharedPreferences sharedPreferences) {
        return MySharedPreferences.getValueFromSharedPreferences(sharedPreferences, DAILY_REVEAL_NUMBER);
    }

    public static String getDailyTime(SharedPreferences sharedPreferences) {
        return MySharedPreferences.getValueFromSharedPreferences(sharedPreferences, DAILY_REVEAL_TIME);
    }

    public static String getUserId(SharedPreferences sharedPreferences) {
        return MySharedPreferences.getValueFromSharedPreferences(sharedPreferences, AMAZON_USER_ID);
    }


    public static void registerToken(SharedPreferences sharedPreferences, String userId) {
        MySharedPreferences.storeValueInSharedPreferences2(sharedPreferences, USER_TOKEN, userId);
    }

    public static String getToken(SharedPreferences sharedPreferences) {
        return MySharedPreferences.getValueFromSharedPreferences(sharedPreferences, USER_TOKEN);
    }

}
