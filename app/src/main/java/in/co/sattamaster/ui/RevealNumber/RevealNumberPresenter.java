package in.co.sattamaster.ui.RevealNumber;

import javax.inject.Inject;

import in.co.sattamaster.data.DataManager;
import in.co.sattamaster.ui.AddCoins.AddCoinsMvpPresenter;
import in.co.sattamaster.ui.AddCoins.AddCoinsMvpView;
import in.co.sattamaster.ui.base.BasePresenter;
import in.co.sattamaster.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

public class RevealNumberPresenter <V extends RevealNumberMvpView> extends BasePresenter<V>
        implements RevealNumberMvpPresenter<V> {

    @Inject
    public RevealNumberPresenter(DataManager dataManager,
                             SchedulerProvider schedulerProvider,
                             CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
