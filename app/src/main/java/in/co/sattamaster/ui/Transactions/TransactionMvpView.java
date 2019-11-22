package in.co.sattamaster.ui.Transactions;

import in.co.sattamaster.ui.AllBids.AllBidsPojo;
import in.co.sattamaster.ui.base.MvpView;

public interface TransactionMvpView extends MvpView {

    void getTransaction(BaseTransaction response);

}
