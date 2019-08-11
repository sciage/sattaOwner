package in.co.sattamaster.ui.AddModerator;

import org.json.JSONObject;

import in.co.sattamaster.di.PerActivity;
import in.co.sattamaster.ui.base.MvpPresenter;

@PerActivity
public interface AddModeratorMvpPresenter<V extends AddModeratorMvpView> extends MvpPresenter<V> {
    void addModerator(JSONObject moderator);
}
