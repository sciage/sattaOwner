package in.co.sattamaster.owner.ui.PlayMatka;

import com.androidnetworking.error.ANError;

import org.json.JSONObject;

import in.co.sattamaster.owner.data.DataManager;
import in.co.sattamaster.owner.dto.Bid;
import in.co.sattamaster.owner.ui.base.BasePresenter;
import in.co.sattamaster.owner.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class PlayMatkaActivityPresenter <V extends PlayMatkaActivityMvpView> extends BasePresenter<V>
        implements PlayMatkaActivityMvpPresenter<V> {

    @Inject
    public PlayMatkaActivityPresenter(DataManager dataManager,
                                      SchedulerProvider schedulerProvider,
                                      CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void sendBidSet(String user_id, String centre_id, JSONObject bidset) {

        getCompositeDisposable().add(getDataManager()
                .sendBidset(user_id, centre_id, bidset)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<Bid>() {
                    @Override
                    public void accept(@NonNull Bid response)
                            throws Exception {

                        getMvpView().receiveBidSetResult(response);

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
