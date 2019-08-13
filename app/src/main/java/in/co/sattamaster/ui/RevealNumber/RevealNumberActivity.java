package in.co.sattamaster.ui.RevealNumber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import javax.inject.Inject;

import butterknife.ButterKnife;
import in.co.sattamaster.R;
import in.co.sattamaster.ui.AddCoins.AddCoinsActivity;
import in.co.sattamaster.ui.base.BaseActivity;

public class RevealNumberActivity extends BaseActivity implements RevealNumberMvpView {

    @Inject
    RevealNumberMvpPresenter<RevealNumberMvpView> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveal_number);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(RevealNumberActivity.this);
    }

    @Override
    protected void setUp() {

    }
}
