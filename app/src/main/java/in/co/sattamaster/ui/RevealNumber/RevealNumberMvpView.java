package in.co.sattamaster.ui.RevealNumber;

import java.util.List;

import in.co.sattamaster.ui.Homepage.LocationPojo;
import in.co.sattamaster.ui.base.MvpView;

public interface RevealNumberMvpView extends MvpView {
    void getLocationData(List<LocationPojo> response);
    void getRevealStatus(RevealStatus response);

}
