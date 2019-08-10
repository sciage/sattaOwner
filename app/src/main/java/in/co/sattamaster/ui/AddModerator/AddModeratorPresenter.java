package in.co.sattamaster.ui.AddModerator;

import javax.inject.Inject;

import in.co.sattamaster.data.DataManager;
import in.co.sattamaster.ui.base.BasePresenter;
import in.co.sattamaster.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

public class AddModeratorPresenter<V extends AddModeratorMvpView> extends BasePresenter<V>
        implements AddModeratorMvpPresenter<V> {

    @Inject
    public AddModeratorPresenter(DataManager dataManager,
                                 SchedulerProvider schedulerProvider,
                                 CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
