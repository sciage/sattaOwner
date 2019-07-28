package in.co.sattamaster.owner.ui.Homepage;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import in.co.sattamaster.owner.R;
import in.co.sattamaster.owner.data.DataManager;
import in.co.sattamaster.owner.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainActivityMvpView{
    @Inject
    DataManager mDataManager;

    @Inject
    MainActivityMvpPresenter<MainActivityMvpView> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_launcher);


        Intent intent = getIntent();

        isLoggedIn = intent.getBooleanExtra("isLoggedIn", false);

        if (!isLoggedIn){
            Intent intent02 = new Intent(MainActivity.this, LoginScreenActivity.class);
            startActivity(intent02);
        }

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(MainActivity.this);


        GridView view = (GridView) findViewById(R.id.gridmain);


        getSupportActionBar().setTitle("Satta Home Page");


        view.setAdapter(new GridAdapter(getBaseContext()));
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
}
