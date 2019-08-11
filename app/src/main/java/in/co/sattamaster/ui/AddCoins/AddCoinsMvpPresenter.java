package in.co.sattamaster.ui.AddCoins;


import org.json.JSONObject;

import in.co.sattamaster.di.PerActivity;
import in.co.sattamaster.ui.base.MvpPresenter;

@PerActivity
public interface AddCoinsMvpPresenter<V extends AddCoinsMvpView> extends MvpPresenter<V> {

    void getAllUsers();
    void getAllModerator();

    void addUserCoin(String userId, JSONObject coinBalance);
    void addModeratorCoin(String moderator_id, JSONObject coinBalance);
    void addOwnerCoin(JSONObject ownerCoins);

}
