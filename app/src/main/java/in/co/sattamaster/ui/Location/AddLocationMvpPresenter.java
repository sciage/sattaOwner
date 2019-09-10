package in.co.sattamaster.ui.Location;

import android.content.SharedPreferences;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import in.co.sattamaster.di.PerActivity;
import in.co.sattamaster.ui.base.MvpPresenter;

@PerActivity
public interface AddLocationMvpPresenter <V extends AddLocationMvpView> extends MvpPresenter<V> {

    void sendLocation(JsonObject object, SharedPreferences sharedPreferences);
}
