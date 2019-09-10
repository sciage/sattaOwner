package in.co.sattamaster.ui.AddModerator;

import android.content.SharedPreferences;

import java.util.List;

import javax.inject.Inject;

import in.co.sattamaster.data.DataManager;
import in.co.sattamaster.retrofit.ANError;
import in.co.sattamaster.ui.base.BasePresenter;
import in.co.sattamaster.ui.login.AllModerators;
import in.co.sattamaster.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class ListOfModeratorPresenter <V extends ListOfModeratorMvpView> extends BasePresenter<V>
        implements ListOfModeratorMvpPresenter<V> {

    @Inject
    public ListOfModeratorPresenter(DataManager dataManager,
                                    SchedulerProvider schedulerProvider,
                                    CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getAllModerator(SharedPreferences sharedPreferences) {
        //   getMvpView().showLoading();

        getCompositeDisposable().add(getDataManager()
                .getAllModerator(sharedPreferences)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<List<AllModerators>>() {
                    @Override
                    public void accept(List<AllModerators> response) throws Exception {

                        getMvpView().getListAllGroups(response);

                     /*   getDataManager().updateUserInfo(

                                response.info.getId(),
                                response.info.getUser_token(),
                                DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER
                        );
                        */



                        //   getMvpView().openMainActivity();

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().hideLoading();

                        // handle the login error here
                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            handleApiError(anError);
                        }
                    }
                }));
    }
}
