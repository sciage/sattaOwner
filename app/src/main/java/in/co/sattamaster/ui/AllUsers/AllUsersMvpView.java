package in.co.sattamaster.ui.AllUsers;


import java.util.List;

import in.co.sattamaster.ui.Homepage.ModeratorProfile;
import in.co.sattamaster.ui.base.MvpView;
import in.co.sattamaster.ui.login.UserProfile;

public interface AllUsersMvpView extends MvpView {

    void getAllUsers(List<ModeratorProfile> response);

}
