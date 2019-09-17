package in.co.sattamaster.ui.AllBids;

import android.content.SharedPreferences;

import javax.inject.Inject;

import in.co.sattamaster.data.DataManager;
import in.co.sattamaster.retrofit.ANError;
import in.co.sattamaster.ui.AllUsers.AllUsersMvpPresenter;
import in.co.sattamaster.ui.AllUsers.AllUsersMvpView;
import in.co.sattamaster.ui.Homepage.GetAllUsers;
import in.co.sattamaster.ui.base.BasePresenter;
import in.co.sattamaster.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class AllBidsPresenter <V extends AllBidsMvpView> extends BasePresenter<V>
        implements AllBidsMvpPresenter<V> {

    @Inject
    public AllBidsPresenter(DataManager dataManager,
                             SchedulerProvider schedulerProvider,
                             CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getAllBids(SharedPreferences sharedPreferences, int currentPage) {
        getCompositeDisposable().add(getDataManager()
                .getAllBids(sharedPreferences, String.valueOf(currentPage))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<AllBidsPojo>() {
                    @Override
                    public void accept(AllBidsPojo response) throws Exception {

                        getMvpView().getAllBids(response);

                        // todo add data and loop to get all friends list
                     /*   getDataManager().updateUserInfo(

                                response.info.getId(),
                                response.info.getUser_token(),
                                DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER
                        );
                        */

                        //getMvpView().hideLoading();
                        // getMvpView().openMainActivity();

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
