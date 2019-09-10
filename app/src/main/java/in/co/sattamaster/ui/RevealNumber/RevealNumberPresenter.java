package in.co.sattamaster.ui.RevealNumber;

import android.content.SharedPreferences;

import com.google.gson.JsonObject;

import java.util.List;

import javax.inject.Inject;

import in.co.sattamaster.data.DataManager;
import in.co.sattamaster.retrofit.ANError;
import in.co.sattamaster.ui.AddCoins.AddCoinsMvpPresenter;
import in.co.sattamaster.ui.AddCoins.AddCoinsMvpView;
import in.co.sattamaster.ui.Homepage.LocationPojo;
import in.co.sattamaster.ui.base.BasePresenter;
import in.co.sattamaster.utils.rx.SchedulerProvider;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class RevealNumberPresenter <V extends RevealNumberMvpView> extends BasePresenter<V>
        implements RevealNumberMvpPresenter<V> {

    @Inject
    public RevealNumberPresenter(DataManager dataManager,
                             SchedulerProvider schedulerProvider,
                             CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getLocation(SharedPreferences token) {

        getCompositeDisposable().add(getDataManager()
                .getCentres(token)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<List<LocationPojo>>() {
                    @Override
                    public void accept(@NonNull List<LocationPojo> response)
                            throws Exception {

                        getMvpView().getLocationData(response);

                        //     getMvpView().hideLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable)
                            throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().hideLoading();

                        // handle the error here
                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            handleApiError(anError);
                        }
                    }
                }));


    }

    @Override
    public void sendNumberReveal(JsonObject object, SharedPreferences token) {

        getCompositeDisposable().add(getDataManager()
                .sendRevealNumber(object, token)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<RevealStatus>() {
                    @Override
                    public void accept(@NonNull RevealStatus response)
                            throws Exception {

                        getMvpView().getRevealStatus(response);

                        //     getMvpView().hideLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable)
                            throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().hideLoading();

                        // handle the error here
                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            handleApiError(anError);
                        }
                    }
                }));
    }
}
