package in.co.sattamaster.owner.ui.Result;

import in.co.sattamaster.owner.data.DataManager;
import in.co.sattamaster.owner.ui.base.BasePresenter;
import in.co.sattamaster.owner.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class ResultActivityPresenter <V extends ResultActivityMvpView> extends BasePresenter<V>
        implements ResultActivityMvpPresenter<V> {

    @Inject
    public ResultActivityPresenter(DataManager dataManager,
                                   SchedulerProvider schedulerProvider,
                                   CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
