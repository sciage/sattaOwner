package in.co.sattamaster.ui.AddModerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.co.sattamaster.R;
import in.co.sattamaster.ui.AddCoins.AddModeratorCoinsResponse;
import in.co.sattamaster.ui.base.BaseActivity;

public class AddModeratorActivity extends BaseActivity implements AddModeratorMvpView, View.OnClickListener {

    @BindView(R.id.moderator_name_value) EditText moderator_name_value;
    @BindView(R.id.moderator_address_value) EditText moderator_address_value;
    @BindView(R.id.moderator_mobile_value) EditText moderator_mobile_value;
    @BindView(R.id.moderator_password_value) EditText moderator_password_value;

    @BindView(R.id.show_list_of_moderators) Button show_list_of_moderators;
    @BindView(R.id.add_button) Button add_button;
    @BindView(R.id.addmoderator_progressbar) View progressFrame;


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

        add_button.setOnClickListener(this);
        progressFrame.setVisibility(View.INVISIBLE);

    }

    @Override
    protected void setUp() {

    }

    @Override
    public void onClick(View v) {
        if (v.getId()== R.id.show_list_of_moderators){
            Intent intent02 = new Intent(AddModeratorActivity.this, ListOfModeratorActivity.class);
            startActivity(intent02);
        } else if (v.getId() == R.id.add_button){
            progressFrame.setVisibility(View.VISIBLE);

            mPresenter.addModerator(addModerator());
        }
    }

    private JSONObject addModerator(){
        JSONObject balance = new JSONObject();
        try {
            balance.put("name", moderator_name_value.getText().toString());
            balance.put("phone", moderator_mobile_value.getText().toString());
            balance.put("password", moderator_password_value.getText().toString());
            balance.put("address", moderator_address_value.getText().toString());
            balance.put("coin_balance", "0");

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return balance;
    }

    @Override
    public void addModeratorResponse(AddModeratorCoinsResponse response) {

        if (response.isStatus()){
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();

        }
        progressFrame.setVisibility(View.INVISIBLE);
    }
}
