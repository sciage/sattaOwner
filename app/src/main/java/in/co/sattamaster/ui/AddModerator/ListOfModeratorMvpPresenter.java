package in.co.sattamaster.ui.AddModerator;


import android.content.SharedPreferences;

import in.co.sattamaster.di.PerActivity;
import in.co.sattamaster.ui.base.MvpPresenter;

@PerActivity
public interface ListOfModeratorMvpPresenter <V extends ListOfModeratorMvpView> extends MvpPresenter<V> {
    void getAllModerator(SharedPreferences sharedPreferences);

}
