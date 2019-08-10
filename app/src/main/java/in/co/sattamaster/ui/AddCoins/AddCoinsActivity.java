package in.co.sattamaster.ui.AddCoins;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.co.sattamaster.R;
import in.co.sattamaster.ui.base.BaseActivity;

public class AddCoinsActivity extends BaseActivity implements AddCoinsMvpView, View.OnClickListener {

    @Inject
    AddCoinsMvpPresenter<AddCoinsMvpView> mPresenter;

    @BindView(R.id.user_coins) EditText user_coins;
    @BindView(R.id.enter_moderator_coins) EditText enter_moderator_coins;
    @BindView(R.id.enter_owner_coins) EditText enter_owner_coins;

    @BindView(R.id.send_user_coin) Button send_user_coin;
    @BindView(R.id.send_moderator_coin) Button send_moderator_coin;
    @BindView(R.id.send_owner_coin) Button send_owner_coin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_coins);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(AddCoinsActivity.this);


        toolbar.setNavigationIcon(R.drawable.ic_clear_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSupportActionBar().setTitle("Add Coins");

        send_user_coin.setOnClickListener(this);
        send_moderator_coin.setOnClickListener(this);
        send_owner_coin.setOnClickListener(this);
    }

    @Override
    protected void setUp() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send_user_coin:

                break;
            case R.id.send_moderator_coin:
                break;

            case R.id.send_owner_coin:
                break;
        }

    }
}
