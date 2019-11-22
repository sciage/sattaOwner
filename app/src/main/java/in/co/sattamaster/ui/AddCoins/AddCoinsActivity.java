package in.co.sattamaster.ui.AddCoins;

import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import org.json.JSONException;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.co.sattamaster.R;
import in.co.sattamaster.ui.Homepage.GetAllUsers;
import in.co.sattamaster.ui.Homepage.MainActivity;
import in.co.sattamaster.ui.Homepage.ModeratorProfile;
import in.co.sattamaster.ui.PlayMatka.PlayMatkaActivity;
import in.co.sattamaster.ui.autocomplete.Autocomplete;
import in.co.sattamaster.ui.autocomplete.AutocompleteCallback;
import in.co.sattamaster.ui.autocomplete.AutocompletePresenter;
import in.co.sattamaster.ui.autocomplete.ModeratorPresenter;
import in.co.sattamaster.ui.autocomplete.UserPresenter;
import in.co.sattamaster.ui.base.BaseActivity;
import in.co.sattamaster.ui.base.Constants;
import in.co.sattamaster.ui.base.MySharedPreferences;
import in.co.sattamaster.ui.login.AllModerators;
import in.co.sattamaster.ui.login.UserProfile;
import timber.log.Timber;

public class AddCoinsActivity extends BaseActivity implements AddCoinsMvpView, View.OnClickListener {

    @Inject
    AddCoinsMvpPresenter<AddCoinsMvpView> mPresenter;

    @BindView(R.id.user_coins) EditText user_coins;
    @BindView(R.id.moderator_mobile) EditText moderator_mobile;
    @BindView(R.id.user_mobile) EditText user_mobile;
    @BindView(R.id.balance_amount_value)
    TextView balance_amount_value;

    @BindView(R.id.enter_moderator_coins) EditText enter_moderator_coins;
    @BindView(R.id.enter_owner_coins) EditText enter_owner_coins;

    @BindView(R.id.send_user_coin) Button send_user_coin;
    @BindView(R.id.send_moderator_coin) Button send_moderator_coin;
    @BindView(R.id.send_owner_coin) Button send_owner_coin;

    private Autocomplete userAutocomplete;
    private String userId;
    private String coinBalance;
    private boolean selectedUser = false;
    private boolean selectedModerator = false;

    @BindView(R.id.addcoins_progressbar) View progressFrame;
    private String moderator_id;


    private static final int PAGE_START = 1;
    private int currentPage = PAGE_START;


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

        Intent intent = getIntent();
        coinBalance = intent.getStringExtra(Constants.WALLET_BALANCE);

        balance_amount_value.setText(coinBalance);

        try {
            mPresenter.getAllUsers(preferences, currentPage);
            mPresenter.getAllModerator(preferences);

            progressFrame.setVisibility(View.VISIBLE);

        } catch (Exception ex){
        }
    }

    @Override
    protected void setUp() {

    }

    private void setupUserAutocomplete(List<ModeratorProfile> response) {
        // EditText edit = (EditText) findViewById(R.id.single);
        float elevation = 6f;
        Drawable backgroundDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.shadow, null);

        AutocompletePresenter<ModeratorProfile> presenter = new UserPresenter(this, response);
        AutocompleteCallback<ModeratorProfile> callback = new AutocompleteCallback<ModeratorProfile>() {
            @Override
            public boolean onPopupItemClicked(Editable editable, ModeratorProfile item) {
                editable.clear();
                editable.append(item.getName());
                setUser(item.getId());
                selectedModerator = true;
                return true;
            }

            public void onPopupVisibilityChanged(boolean shown) {
            }
        };

        userAutocomplete = Autocomplete.<ModeratorProfile>on(user_mobile)
                .with(elevation)
                .with(backgroundDrawable)
                .with(presenter)
                .with(callback)
                .build();
    }

    private void setupModeratorAutocomplete(List<AllModerators> response) {
        // EditText edit = (EditText) findViewById(R.id.single);
        float elevation = 6f;
        Drawable backgroundDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.shadow, null);

        AutocompletePresenter<AllModerators> presenter = new ModeratorPresenter(this, response);
        AutocompleteCallback<AllModerators> callback = new AutocompleteCallback<AllModerators>() {
            @Override
            public boolean onPopupItemClicked(Editable editable, AllModerators item) {
                editable.clear();
                editable.append(item.getName());
                setModerator_id(item.getId());
                selectedUser = true;
                return true;
            }

            public void onPopupVisibilityChanged(boolean shown) {
            }
        };

        userAutocomplete = Autocomplete.<AllModerators>on(moderator_mobile)
                .with(elevation)
                .with(backgroundDrawable)
                .with(presenter)
                .with(callback)
                .build();
    }

    private void setModerator_id(String moderator_id) {
        this.moderator_id = moderator_id;
    }

    private void setUser(String userId) {
        this.userId = userId;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send_user_coin:

                progressFrame.setVisibility(View.VISIBLE);
                mPresenter.addUserCoin(userId, balanceJson(user_coins.getText().toString()), preferences);

                break;
            case R.id.send_moderator_coin:

                progressFrame.setVisibility(View.VISIBLE);
                mPresenter.addModeratorCoin(moderator_id, balanceJson(enter_moderator_coins.getText().toString()), preferences);

                break;

            case R.id.send_owner_coin:

                progressFrame.setVisibility(View.VISIBLE);
                mPresenter.addOwnerCoin(balanceJson(enter_owner_coins.getText().toString()), preferences);

                break;
        }

    }

    @Override
    public void getAllUsers(List<ModeratorProfile> response) {
        setupUserAutocomplete(response);

    }

    @Override
    public void getListAllGroups(List<AllModerators> response) {
        setupModeratorAutocomplete(response);

        progressFrame.setVisibility(View.INVISIBLE);

    }

    private JsonObject balanceJson(String balanceAmount){
        JsonObject balance = new JsonObject();
        try {
            balance.addProperty("amount", balanceAmount);

        } catch (JsonIOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return balance;
    }

    @Override
    public void AddUserCoinResponse(AddUserCoinsResponse response) {

        if (response.isStatus()){
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();

        }
        progressFrame.setVisibility(View.INVISIBLE);

        Intent intent = new Intent(AddCoinsActivity.this, MainActivity.class);
        intent.putExtra("isLoggedIn", true);

        startActivity(intent);



    }

    @Override
    public void AddModeratorCoinResponse(AddModeratorCoinsResponse response) {

        if (response.isStatus()){
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();

        }
        progressFrame.setVisibility(View.INVISIBLE);

        Intent intent = new Intent(AddCoinsActivity.this, MainActivity.class);
        intent.putExtra("isLoggedIn", true);

        startActivity(intent);


    }

}
