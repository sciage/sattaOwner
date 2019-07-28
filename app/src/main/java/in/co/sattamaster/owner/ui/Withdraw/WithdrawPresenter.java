package in.co.sattamaster.owner.ui.Withdraw;

import in.co.sattamaster.owner.data.DataManager;
import in.co.sattamaster.owner.ui.base.BasePresenter;
import in.co.sattamaster.owner.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class WithdrawPresenter <V extends WithdrawMvpView> extends BasePresenter<V>
        implements WithdrawMvpPresenter<V> {

    @Inject
    public WithdrawPresenter(DataManager dataManager,
                                   SchedulerProvider schedulerProvider,
                                   CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
