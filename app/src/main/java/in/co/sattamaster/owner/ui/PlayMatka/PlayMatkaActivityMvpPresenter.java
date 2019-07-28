package in.co.sattamaster.owner.ui.PlayMatka;

import org.json.JSONObject;

import in.co.sattamaster.owner.di.PerActivity;
import in.co.sattamaster.owner.ui.base.MvpPresenter;

@PerActivity
public interface PlayMatkaActivityMvpPresenter <V extends PlayMatkaActivityMvpView> extends MvpPresenter<V> {

    void sendBidSet(String user_id, String centre_id, JSONObject bidset);
}
