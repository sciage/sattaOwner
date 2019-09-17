package in.co.sattamaster.ui.AddCoins;


import android.content.SharedPreferences;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import in.co.sattamaster.di.PerActivity;
import in.co.sattamaster.ui.base.MvpPresenter;

@PerActivity
public interface AddCoinsMvpPresenter<V extends AddCoinsMvpView> extends MvpPresenter<V> {

    void getAllUsers(SharedPreferences sharedPreferences, int page);
    void getAllModerator(SharedPreferences sharedPreferences);

    void addUserCoin(String userId, JsonObject coinBalance, SharedPreferences sharedPreferences);
    void addModeratorCoin(String moderator_id, JsonObject coinBalance, SharedPreferences sharedPreferences);
    void addOwnerCoin(JsonObject ownerCoins, SharedPreferences sharedPreferences);

}
