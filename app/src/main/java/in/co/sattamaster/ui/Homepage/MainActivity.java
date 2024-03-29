package in.co.sattamaster.ui.Homepage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import butterknife.BindView;
import in.co.sattamaster.R;
import in.co.sattamaster.data.DataManager;
import in.co.sattamaster.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import in.co.sattamaster.ui.base.MySharedPreferences;
import in.co.sattamaster.ui.login.LoginScreenActivity;
import in.co.sattamaster.ui.login.UserProfile;

public class MainActivity extends BaseActivity implements MainActivityMvpView{
    @Inject
    DataManager mDataManager;

    protected boolean isLoggedIn;

    TextView balance_amount_value;
    TextView user_name;
    TextView moderator;
    View progressFrame;

    GridAdapter gridAdapter;


    @Inject
    MainActivityMvpPresenter<MainActivityMvpView> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_launcher);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(MainActivity.this);

        progressFrame = (View) findViewById(R.id.main_progressbar);
        moderator = (TextView) findViewById(R.id.moderator);
        user_name = (TextView) findViewById(R.id.user_name);
        balance_amount_value = (TextView) findViewById(R.id.balance_amount_value);

        Intent intent = getIntent();

        isLoggedIn = intent.getBooleanExtra("isLoggedIn", false);

        if (!isLoggedIn){
            Intent intent02 = new Intent(MainActivity.this, LoginScreenActivity.class);
            startActivity(intent02);
        } else {
            try {
                progressFrame.setVisibility(View.VISIBLE);

                mPresenter.getUserProfile(preferences);
                // getGroupsJoined();
            } catch (Exception    e) {
                e.printStackTrace();
            }
        }




        GridView view = (GridView) findViewById(R.id.gridmain);


        getSupportActionBar().setTitle("Satta Home Page");

        gridAdapter = new GridAdapter(getBaseContext());

        view.setAdapter(gridAdapter);
        view.setFocusable(false);

    }

    @Override
    protected void setUp() {

    }

    @Override
    protected void onDestroy() {
        isLoggedIn = false;

        super.onDestroy();
    }

    @Override
    public void getUserProfile(UserObject response) {
        if (response.getUser().getId()!=null){
            balance_amount_value.setText(response.getUser().getProfile().getCoin_balance());
            user_name.setText(response.getUser().getName());
            moderator.setText(String.valueOf(response.getUser().getName() + " ( " + response.getUser().getPhone() + " )"));

            gridAdapter.addAll(response);


        }

        progressFrame.setVisibility(View.GONE);


    }
}
