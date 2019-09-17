package in.co.sattamaster.ui.AllBids;

import in.co.sattamaster.ui.base.MvpView;

public interface BidsDetailsMvpView extends MvpView {
    void getBidDetails(HistoryDetailsResponse response);
}
