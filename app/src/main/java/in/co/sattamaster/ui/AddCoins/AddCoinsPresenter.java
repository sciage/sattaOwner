package in.co.sattamaster.ui.AddCoins;


import javax.inject.Inject;

import in.co.sattamaster.data.DataManager;
import in.co.sattamaster.ui.base.BasePresenter;
import in.co.sattamaster.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

public class AddCoinsPresenter<V extends AddCoinsMvpView> extends BasePresenter<V>
        implements AddCoinsMvpPresenter<V> {

    @Inject
    public AddCoinsPresenter(DataManager dataManager,
                             SchedulerProvider schedulerProvider,
                             CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
