package in.co.sattamaster.ui.AllUsers;


import android.content.SharedPreferences;

import in.co.sattamaster.di.PerActivity;
import in.co.sattamaster.ui.base.MvpPresenter;

@PerActivity
public interface AllUsersMvpPresenter<V extends AllUsersMvpView> extends MvpPresenter<V> {

    void getAllUsers(SharedPreferences sharedPreferences, int currentPage);

}
