package in.co.sattamaster.owner.ui.Homepage;

import in.co.sattamaster.owner.di.PerActivity;
import in.co.sattamaster.owner.ui.base.MvpPresenter;

@PerActivity
public interface LoginScreenMvpPresenter  <V extends LoginScreenMvpView> extends MvpPresenter<V> {

    void doLogin (String phone, String password);
}
