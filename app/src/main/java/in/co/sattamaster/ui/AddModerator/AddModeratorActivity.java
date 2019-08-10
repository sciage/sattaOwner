package in.co.sattamaster.ui.AddModerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.co.sattamaster.R;
import in.co.sattamaster.ui.base.BaseActivity;

public class AddModeratorActivity extends BaseActivity implements AddModeratorMvpView, View.OnClickListener {

    @BindView(R.id.moderator_name_value) EditText moderator_name_value;
    @BindView(R.id.moderator_address_value) EditText moderator_address_value;
    @BindView(R.id.moderator_mobile_value) EditText moderator_mobile_value;
    @BindView(R.id.moderator_password_value)
    EditText moderator_password_value;

    @BindView(R.id.show_list_of_moderators) Button show_list_of_moderators;
    @BindView(R.id.search_bid_button)
    Button search_bid_button;

    @Inject
    AddModeratorMvpPresenter<AddModeratorMvpView> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_moderator);

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
        getSupportActionBar().setTitle("Add Moderator");

        show_list_of_moderators.setOnClickListener(this);

    }

    @Override
    protected void setUp() {

    }

    @Override
    public void onClick(View v) {
        if (v.getId()== R.id.show_list_of_moderators){
            Intent intent02 = new Intent(AddModeratorActivity.this, ListOfModeratorActivity.class);
            startActivity(intent02);
        }
    }
}
