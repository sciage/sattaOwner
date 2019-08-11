package in.co.sattamaster.ui.Location;

import javax.inject.Inject;

import in.co.sattamaster.data.DataManager;
import in.co.sattamaster.ui.base.BasePresenter;
import in.co.sattamaster.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

public class AddLocationPresenter <V extends AddLocationMvpView> extends BasePresenter<V>
        implements AddLocationMvpPresenter<V> {

    @Inject
    public AddLocationPresenter(DataManager dataManager,
                                    SchedulerProvider schedulerProvider,
                                    CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
