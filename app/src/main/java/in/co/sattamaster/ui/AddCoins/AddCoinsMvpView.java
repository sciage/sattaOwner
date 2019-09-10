package in.co.sattamaster.ui.AddCoins;


import java.util.List;

import in.co.sattamaster.ui.Homepage.GetAllUsers;
import in.co.sattamaster.ui.Homepage.ModeratorProfile;
import in.co.sattamaster.ui.base.MvpView;
import in.co.sattamaster.ui.login.AllModerators;
import in.co.sattamaster.ui.login.UserProfile;

public interface AddCoinsMvpView extends MvpView {

    void getAllUsers(GetAllUsers response);
    void getListAllGroups(List<AllModerators> response);
    void AddUserCoinResponse(AddUserCoinsResponse response);
    void AddModeratorCoinResponse(AddModeratorCoinsResponse response);

}
