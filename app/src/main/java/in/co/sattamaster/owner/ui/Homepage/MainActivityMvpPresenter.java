package in.co.sattamaster.owner.ui.Homepage;

import in.co.sattamaster.owner.di.PerActivity;
import in.co.sattamaster.owner.ui.base.MvpPresenter;

@PerActivity
public interface MainActivityMvpPresenter <V extends MainActivityMvpView> extends MvpPresenter<V> {
}
