package in.co.sattamaster.ui.AllBids;

import android.content.SharedPreferences;

import in.co.sattamaster.ui.base.MvpPresenter;

public interface BidsDetailsMvpPresenter<V extends BidsDetailsMvpView> extends MvpPresenter<V> {
    void getBidDetails(String id, SharedPreferences sharedPreferences);
}
