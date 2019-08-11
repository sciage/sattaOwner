package in.co.sattamaster.ui.Location;

import in.co.sattamaster.ui.base.MvpView;

public interface AddLocationMvpView extends MvpView {

    void receiveLocation(LocationStatus response);
}
