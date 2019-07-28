package in.co.sattamaster.owner.ui.Homepage;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import butterknife.BindView;
import in.co.sattamaster.owner.R;
import in.co.sattamaster.owner.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class AddModeratorActivity extends BaseActivity implements AddModeratorMvpView {
    Button place_bid;
    AddModeratorAdapter addModeratorAdapter;

    @BindView(R.id.moderator_name_value) EditText moderator_name_value;
    @BindView(R.id.moderator_address_value) EditText moderator_address_value;
    @BindView(R.id.moderator_mobile_value) EditText moderator_mobile_value;

    @Inject
    AddModeratorMvpPresenter<AddModeratorMvpView> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      /*  if (!isLoggedIn){
            Intent intent = new Intent(AddModeratorActivity.this, LoginScreenActivity.class);
            startActivity(intent);
        }
       */

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(AddModeratorActivity.this);

        toolbar.setNavigationIcon(R.drawable.ic_clear_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSupportActionBar().setTitle("Satta");

    }

    @Override
    protected void setUp() {

    }
}
