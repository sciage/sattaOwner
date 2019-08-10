package in.co.sattamaster.ui.AddModerator;

import javax.inject.Inject;

import in.co.sattamaster.data.DataManager;
import in.co.sattamaster.ui.base.BasePresenter;
import in.co.sattamaster.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

public class ListOfModeratorPresenter <V extends ListOfModeratorMvpView> extends BasePresenter<V>
        implements ListOfModeratorMvpPresenter<V> {

    @Inject
    public ListOfModeratorPresenter(DataManager dataManager,
                                    SchedulerProvider schedulerProvider,
                                    CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
