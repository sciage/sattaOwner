package in.co.sattamaster.ui.AllUsers;



import javax.inject.Inject;

import in.co.sattamaster.data.DataManager;
import in.co.sattamaster.ui.base.BasePresenter;
import in.co.sattamaster.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

public class AllUsersPresenter<V extends AllUsersMvpView> extends BasePresenter<V>
        implements AllUsersMvpPresenter<V> {

    @Inject
    public AllUsersPresenter(DataManager dataManager,
                             SchedulerProvider schedulerProvider,
                             CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
