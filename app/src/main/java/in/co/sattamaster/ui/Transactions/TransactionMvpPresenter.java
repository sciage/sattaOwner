package in.co.sattamaster.ui.Transactions;

import android.content.SharedPreferences;

import in.co.sattamaster.di.PerActivity;
import in.co.sattamaster.ui.RevealNumber.RevealNumberMvpView;
import in.co.sattamaster.ui.base.MvpPresenter;

@PerActivity
public interface TransactionMvpPresenter <V extends TransactionMvpView> extends MvpPresenter<V> {

    void getTransactions(SharedPreferences sharedPreferences, int currentPage);

}
