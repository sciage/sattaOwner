package in.co.sattamaster.ui.AddCoins;

import androidx.core.content.res.ResourcesCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.co.sattamaster.R;
import in.co.sattamaster.ui.autocomplete.Autocomplete;
import in.co.sattamaster.ui.autocomplete.AutocompleteCallback;
import in.co.sattamaster.ui.autocomplete.AutocompletePresenter;
import in.co.sattamaster.ui.autocomplete.ModeratorPresenter;
import in.co.sattamaster.ui.autocomplete.UserPresenter;
import in.co.sattamaster.ui.base.BaseActivity;
import in.co.sattamaster.ui.login.AllModerators;
import in.co.sattamaster.ui.login.UserProfile;
import timber.log.Timber;

public class AddCoinsActivity extends BaseActivity implements AddCoinsMvpView, View.OnClickListener {

    @Inject
    AddCoinsMvpPresenter<AddCoinsMvpView> mPresenter;

    @BindView(R.id.user_coins) EditText user_coins;
    @BindView(R.id.moderator_mobile) EditText moderator_mobile;
    @BindView(R.id.user_mobile) EditText user_mobile;

    @BindView(R.id.enter_moderator_coins) EditText enter_moderator_coins;
    @BindView(R.id.enter_owner_coins) EditText enter_owner_coins;

    @BindView(R.id.send_user_coin) Button send_user_coin;
    @BindView(R.id.send_moderator_coin) Button send_moderator_coin;
    @BindView(R.id.send_owner_coin) Button send_owner_coin;

    private Autocomplete userAutocomplete;
    private String userId;
    private boolean selectedUser = false;
    private boolean selectedModerator = false;

    @BindView(R.id.addcoins_progressbar) View progressFrame;
    private String moderator_id;


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

        try {
            mPresenter.getAllUsers();
            mPresenter.getAllModerator();

            progressFrame.setVisibility(View.VISIBLE);

        } catch (Exception ex){
        }
    }

    @Override
    protected void setUp() {

    }

    private void setupUserAutocomplete(List<UserProfile> response) {
        // EditText edit = (EditText) findViewById(R.id.single);
        float elevation = 6f;
        Drawable backgroundDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.shadow, null);

        AutocompletePresenter<UserProfile> presenter = new UserPresenter(this, response);
        AutocompleteCallback<UserProfile> callback = new AutocompleteCallback<UserProfile>() {
            @Override
            public boolean onPopupItemClicked(Editable editable, UserProfile item) {
                editable.clear();
                editable.append(item.getName());
                setUser(item.getId());
                selectedModerator = true;
                return true;
            }

            public void onPopupVisibilityChanged(boolean shown) {
            }
        };

        userAutocomplete = Autocomplete.<UserProfile>on(user_mobile)
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

                break;
            case R.id.send_moderator_coin:
                break;

            case R.id.send_owner_coin:
                break;
        }

    }

    @Override
    public void getAllUsers(List<UserProfile> response) {
        setupUserAutocomplete(response);

    }

    @Override
    public void getListAllGroups(List<AllModerators> response) {
        setupModeratorAutocomplete(response);

        progressFrame.setVisibility(View.INVISIBLE);

    }
}
