package in.co.sattamaster.ui.Location;


import android.content.SharedPreferences;

import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

import in.co.sattamaster.data.DataManager;
import in.co.sattamaster.retrofit.ANError;
import in.co.sattamaster.ui.base.BasePresenter;
import in.co.sattamaster.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class AddLocationPresenter <V extends AddLocationMvpView> extends BasePresenter<V>
        implements AddLocationMvpPresenter<V> {

    @Inject
    public AddLocationPresenter(DataManager dataManager,
                                    SchedulerProvider schedulerProvider,
                                    CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void sendLocation(JsonObject object, SharedPreferences sharedPreferences) {
        getCompositeDisposable().add(getDataManager()
                .newLocation(object, sharedPreferences)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<LocationStatus>() {
                    @Override
                    public void accept(@NonNull LocationStatus response)
                            throws Exception {

                        getMvpView().receiveLocation(response);

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
