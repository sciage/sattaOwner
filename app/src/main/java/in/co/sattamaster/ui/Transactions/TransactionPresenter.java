package in.co.sattamaster.ui.Transactions;

import android.content.SharedPreferences;

import javax.inject.Inject;

import in.co.sattamaster.data.DataManager;
import in.co.sattamaster.retrofit.ANError;
import in.co.sattamaster.ui.AllBids.HistoryDetailsResponse;
import in.co.sattamaster.ui.RevealNumber.RevealNumberMvpPresenter;
import in.co.sattamaster.ui.RevealNumber.RevealNumberMvpView;
import in.co.sattamaster.ui.base.BasePresenter;
import in.co.sattamaster.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class TransactionPresenter <V extends TransactionMvpView> extends BasePresenter<V>
        implements TransactionMvpPresenter<V> {

    @Inject
    public TransactionPresenter(DataManager dataManager,
                                 SchedulerProvider schedulerProvider,
                                 CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getTransactions(SharedPreferences sharedPreferences, int currentPage) {
        getCompositeDisposable().add(getDataManager()
                .getTransanctions(sharedPreferences, String.valueOf(currentPage))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<BaseTransaction>() {
                    @Override
                    public void accept(BaseTransaction response) throws Exception {

                        getMvpView().getTransaction(response);

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
