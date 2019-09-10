package in.co.sattamaster.ui.RevealNumber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

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

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.co.sattamaster.R;
import in.co.sattamaster.ui.AddCoins.AddCoinsActivity;
import in.co.sattamaster.ui.DateTime.Pico;
import in.co.sattamaster.ui.DateTime.codec.Type;
import in.co.sattamaster.ui.DateTime.helper.PicoListener;
import in.co.sattamaster.ui.Homepage.GetAllUsers;
import in.co.sattamaster.ui.Homepage.LocationPojo;
import in.co.sattamaster.ui.Homepage.ModeratorProfile;
import in.co.sattamaster.ui.Location.AddLocationActivity;
import in.co.sattamaster.ui.autocomplete.Autocomplete;
import in.co.sattamaster.ui.autocomplete.AutocompleteCallback;
import in.co.sattamaster.ui.autocomplete.AutocompletePresenter;
import in.co.sattamaster.ui.autocomplete.LocationPresenter;
import in.co.sattamaster.ui.autocomplete.UserPresenter;
import in.co.sattamaster.ui.base.BaseActivity;

public class RevealNumberActivity extends BaseActivity implements RevealNumberMvpView {

    @BindView(R.id.enter_location_name) EditText enter_location_name;
    @BindView(R.id.enter_reveal_number) EditText enter_reveal_number;
    @BindView(R.id.current_reveal_time) TextView current_reveal_time;
    @BindView(R.id.last_reveal_time) TextView last_reveal_time;
    @BindView(R.id.reveal_date) TextView reveal_date;
    @BindView(R.id.sendRevealNumber) Button sendRevealNumber;
    @BindView(R.id.reveal_time) Button reveal_time;

    @BindView(R.id.addcoins_progressbar) View progressFrame;

    private Autocomplete userAutocomplete;
    private String centre_id;

    String created_at_value;
    String created_at_time;

    @Inject
    RevealNumberMvpPresenter<RevealNumberMvpView> mPresenter;
    private boolean selectedLocation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveal_number);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(RevealNumberActivity.this);


        toolbar.setNavigationIcon(R.drawable.ic_clear_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSupportActionBar().setTitle("Reveal Number");

        reveal_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reveal_time_hourly();

            }
        });

        sendRevealNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressFrame.setVisibility(View.VISIBLE);

                mPresenter.sendNumberReveal(createDailyBid(), preferences);
            }
        });

        progressFrame.setVisibility(View.VISIBLE);

        mPresenter.getLocation(preferences);

    }

    private void reveal_time_hourly() {

        Pico pico = new Pico(RevealNumberActivity.this, null, Type.CALENDAR);
        pico.setPicoListener(new PicoListener() {
            @Override
            public void result(Calendar calendar) {

                created_at_value = Pico.formatDate(calendar);
                created_at_time = Pico.formatTime(calendar);

                reveal_date.setText(Pico.humanDate(calendar));
               // reveal_date.setText(Pico.formatDate(calendar));

            }
        });
        pico.show();
    }

    private JsonObject createDailyBid(){
        JsonObject dailyBid = new JsonObject();
        try {
            dailyBid.addProperty("centre_id", centre_id);
            dailyBid.addProperty("number", enter_reveal_number.getText().toString());
            dailyBid.addProperty("created_at", created_at_value);
            dailyBid.addProperty("created_at_time", created_at_time);

        } catch (JsonIOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return dailyBid;
    }

    private void setupUserAutocomplete(List<LocationPojo> response) {
        // EditText edit = (EditText) findViewById(R.id.single);
        float elevation = 6f;
        Drawable backgroundDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.shadow, null);

        AutocompletePresenter<LocationPojo> presenter = new LocationPresenter(this, response);
        AutocompleteCallback<LocationPojo> callback = new AutocompleteCallback<LocationPojo>() {
            @Override
            public boolean onPopupItemClicked(Editable editable, LocationPojo item) {
                editable.clear();
                editable.append(item.getName());
                setLocation(item.getId());
                current_reveal_time.setText(String.valueOf("Current Time: " + item.getNumberRevealTime()));
                last_reveal_time.setText(String.valueOf("Last Time: " + item.getLastBidTime()));
                selectedLocation = true;
                return true;
            }

            public void onPopupVisibilityChanged(boolean shown) {
            }
        };

        userAutocomplete = Autocomplete.<LocationPojo>on(enter_location_name)
                .with(elevation)
                .with(backgroundDrawable)
                .with(presenter)
                .with(callback)
                .build();
    }

    private void setLocation(String id) {
        this.centre_id= id;
    }

    @Override
    protected void setUp() {

    }

    @Override
    public void getLocationData(List<LocationPojo> response) {
        setupUserAutocomplete(response);

        progressFrame.setVisibility(View.GONE);

    }

    @Override
    public void getRevealStatus(RevealStatus response) {
        Toast.makeText(RevealNumberActivity.this, "Reveal Number added", Toast.LENGTH_LONG).show();

        progressFrame.setVisibility(View.GONE);

    }
}
