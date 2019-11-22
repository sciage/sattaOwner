package in.co.sattamaster.ui.RevealNumber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
import in.co.sattamaster.ui.PlayMatka.PlayMatkaActivity;
import in.co.sattamaster.ui.autocomplete.Autocomplete;
import in.co.sattamaster.ui.autocomplete.AutocompleteCallback;
import in.co.sattamaster.ui.autocomplete.AutocompletePresenter;
import in.co.sattamaster.ui.autocomplete.LocationPresenter;
import in.co.sattamaster.ui.autocomplete.UserPresenter;
import in.co.sattamaster.ui.base.BaseActivity;
import in.co.sattamaster.ui.base.MySharedPreferences;

public class RevealNumberActivity extends BaseActivity implements RevealNumberMvpView {

    @BindView(R.id.enter_location_name) EditText enter_location_name;
    @BindView(R.id.enter_reveal_number) EditText enter_reveal_number;
    @BindView(R.id.daily_no_saved) TextView daily_no_saved;
    @BindView(R.id.daily_time_saved) TextView daily_time_saved;
    @BindView(R.id.hourly_no_saved) TextView hourly_no_saved;
    @BindView(R.id.hourly_time_saved) TextView hourly_time_saved;
    @BindView(R.id.sendRevealNumber) Button sendRevealNumber;
    @BindView(R.id.sendHourlyNumber) Button sendHourlyNumber;

    @BindView(R.id.addcoins_progressbar) View progressFrame;

    private Autocomplete userAutocomplete;

    private String centre_id;

    String revealNumber;

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

        sendRevealNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressFrame.setVisibility(View.VISIBLE);

                revealNumber = enter_reveal_number.getText().toString();

                long now_time = System.currentTimeMillis();

                String currentTime = DateFormat.getDateInstance(DateFormat.FULL).format(now_time).concat(", ").concat(DateFormat.getTimeInstance().format(now_time));

                MySharedPreferences.registerDailyNumber(preferences, revealNumber);
                MySharedPreferences.registerDailyTime(preferences, currentTime);


                mPresenter.sendNumberReveal(createDailyBid(), preferences);



            }
        });

        sendHourlyNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressFrame.setVisibility(View.VISIBLE);

                revealNumber = enter_reveal_number.getText().toString();

                long now_time = System.currentTimeMillis();

                String currentTime = DateFormat.getDateInstance(DateFormat.FULL).format(now_time).concat(", ").concat(DateFormat.getTimeInstance().format(now_time));

                MySharedPreferences.registerHourlyNumber(preferences, revealNumber);
                MySharedPreferences.registerHourlyTime(preferences, currentTime);


                mPresenter.sendNumberReveal(createDailyBid(), preferences);

            }
        });


        if (MySharedPreferences.getDailyNumber(preferences)!=null){
            daily_no_saved.setText(String.valueOf("Rewari Daily No : " + MySharedPreferences.getDailyNumber(preferences)));
            daily_time_saved.setText(MySharedPreferences.getDailyTime(preferences));
        }

        if (MySharedPreferences.getHourlyNumber(preferences)!= null){
            hourly_no_saved.setText(String.valueOf("Rewari Hourly No : " + MySharedPreferences.getHourlyNumber(preferences)));
            hourly_time_saved.setText(MySharedPreferences.getHourlyTime(preferences));
        }

        progressFrame.setVisibility(View.VISIBLE);

        mPresenter.getLocation(preferences);

    }

    private JsonObject createDailyBid(){
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        String time = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());

        JsonObject dailyBid = new JsonObject();
        try {
            dailyBid.addProperty("centre_id", centre_id);
            dailyBid.addProperty("number", revealNumber);
            dailyBid.addProperty("created_at", date);
            dailyBid.addProperty("created_at_time", time);

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

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RevealNumberActivity.this);
        alertDialogBuilder.setTitle("Success Reveal No. Added");
        alertDialogBuilder.setMessage("Reveal No added : " + revealNumber);
        alertDialogBuilder.setCancelable(true);
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {


            }
        });
        alertDialogBuilder.show();

       // Toast.makeText(RevealNumberActivity.this, "Reveal Number added", Toast.LENGTH_LONG).show();

        progressFrame.setVisibility(View.GONE);

    }
}
