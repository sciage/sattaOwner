package in.co.sattamaster.owner.ui.PlayMatka;

import in.co.sattamaster.owner.dto.Bid;
import in.co.sattamaster.owner.ui.base.MvpView;

public interface PlayMatkaActivityMvpView extends MvpView {

    void receiveBidSetResult(Bid response);

}
