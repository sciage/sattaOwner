package in.co.sattamaster.owner.ui.Homepage;

import in.co.sattamaster.owner.data.DataManager;
import in.co.sattamaster.owner.ui.base.BasePresenter;
import in.co.sattamaster.owner.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class MainActivityPresenter <V extends MainActivityMvpView> extends BasePresenter<V>
        implements MainActivityMvpPresenter<V> {

    @Inject
    public MainActivityPresenter(DataManager dataManager,
                                      SchedulerProvider schedulerProvider,
                                      CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}


