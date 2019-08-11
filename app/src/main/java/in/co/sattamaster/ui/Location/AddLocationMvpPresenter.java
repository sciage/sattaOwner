package in.co.sattamaster.ui.Location;

import org.json.JSONObject;

import in.co.sattamaster.di.PerActivity;
import in.co.sattamaster.ui.base.MvpPresenter;

@PerActivity
public interface AddLocationMvpPresenter <V extends AddLocationMvpView> extends MvpPresenter<V> {

    void sendLocation(JSONObject object);
}
