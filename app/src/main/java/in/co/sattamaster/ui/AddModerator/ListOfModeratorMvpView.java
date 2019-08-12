package in.co.sattamaster.ui.AddModerator;


import java.util.List;

import in.co.sattamaster.ui.base.MvpView;
import in.co.sattamaster.ui.login.AllModerators;

public interface ListOfModeratorMvpView  extends MvpView {
    void getListAllGroups(List<AllModerators> response);

}
