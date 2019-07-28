package in.co.sattamaster.owner.ui.PlayMatka;

import in.co.sattamaster.owner.dto.Bid;
import in.co.sattamaster.owner.ui.base.MvpView;

public interface AllUsersMvpView extends MvpView {

    void receiveBidSetResult(Bid response);

}
