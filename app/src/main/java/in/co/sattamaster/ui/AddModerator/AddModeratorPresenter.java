package in.co.sattamaster.ui.AddModerator;

import android.content.SharedPreferences;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import javax.inject.Inject;

import in.co.sattamaster.data.DataManager;
import in.co.sattamaster.retrofit.ANError;
import in.co.sattamaster.ui.AddCoins.AddModeratorCoinsResponse;
import in.co.sattamaster.ui.base.BasePresenter;
import in.co.sattamaster.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class AddModeratorPresenter<V extends AddModeratorMvpView> extends BasePresenter<V>
        implements AddModeratorMvpPresenter<V> {

    @Inject
    public AddModeratorPresenter(DataManager dataManager,
                                 SchedulerProvider schedulerProvider,
                                 CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void addModerator(JsonObject moderator, SharedPreferences sharedPreferences) {
        getCompositeDisposable().add(getDataManager()
                .addModerator(moderator, sharedPreferences)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<AddModeratorCoinsResponse>() {
                    @Override
                    public void accept(AddModeratorCoinsResponse response) throws Exception {

                        getMvpView().addModeratorResponse(response);

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
