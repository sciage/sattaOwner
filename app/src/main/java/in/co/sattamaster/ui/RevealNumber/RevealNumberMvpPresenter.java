package in.co.sattamaster.ui.RevealNumber;

import android.content.SharedPreferences;

import com.google.gson.JsonObject;

import in.co.sattamaster.di.PerActivity;
import in.co.sattamaster.ui.AddCoins.AddCoinsMvpView;
import in.co.sattamaster.ui.base.MvpPresenter;

@PerActivity
public interface RevealNumberMvpPresenter <V extends RevealNumberMvpView> extends MvpPresenter<V> {
    void getLocation(SharedPreferences token);
    void sendNumberReveal(JsonObject object, SharedPreferences token);

}
