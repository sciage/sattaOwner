package in.co.sattamaster.owner.ui.History;

import in.co.sattamaster.owner.data.DataManager;
import in.co.sattamaster.owner.ui.base.BasePresenter;
import in.co.sattamaster.owner.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class HistoryActivityPresenter <V extends HistoryActivityMvpView> extends BasePresenter<V>
        implements HistoryActivityMvpPresenter<V> {

    @Inject
    public HistoryActivityPresenter(DataManager dataManager,
                                 SchedulerProvider schedulerProvider,
                                 CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}

