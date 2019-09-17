package in.co.sattamaster.ui.AllBids;

import android.content.SharedPreferences;

import in.co.sattamaster.di.PerActivity;
import in.co.sattamaster.ui.AllUsers.AllUsersMvpView;
import in.co.sattamaster.ui.base.MvpPresenter;

@PerActivity
public interface AllBidsMvpPresenter <V extends AllBidsMvpView> extends MvpPresenter<V> {

    void getAllBids(SharedPreferences sharedPreferences, int currentPage);

}
