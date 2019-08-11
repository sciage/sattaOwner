package in.co.sattamaster.ui.Location;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.co.sattamaster.R;
import in.co.sattamaster.ui.DateTime.Pico;
import in.co.sattamaster.ui.DateTime.codec.Type;
import in.co.sattamaster.ui.DateTime.helper.PicoListener;
import in.co.sattamaster.ui.Homepage.MainActivity;
import in.co.sattamaster.ui.base.BaseActivity;
import ui.mainong.pop.Pop;

public class AddLocationActivity extends BaseActivity implements AddLocationMvpView, View.OnClickListener {

    @BindView(R.id.location_daily) EditText location_daily;
    @BindView(R.id.daily_reveal_time) TextView daily_reveal_time;
    @BindView(R.id.daily_reveal_time_last) TextView daily_reveal_time_last;
    @BindView(R.id.reveal_time_daily) Button reveal_time_daily;
    @BindView(R.id.reveal_time_daily_last) Button reveal_time_daily_last;
    @BindView(R.id.add_daily_number) Button add_daily_number;

    @BindView(R.id.location_hour_name) EditText location_hour_name;
    @BindView(R.id.hourly_reveal_time) TextView hourly_reveal_time;
    @BindView(R.id.hourly_reveal_time_last) TextView hourly_reveal_time_last;
    @BindView(R.id.reveal_time_hourly) Button reveal_time_hourly;
    @BindView(R.id.reveal_time_hourly_last) Button reveal_time_hourly_last;
    @BindView(R.id.add_hourly_location) Button add_hourly_location;

    String reveal_time_daily_value;
    String reveal_time_daily_last_value;
    String hourly_reveal_time_value;
    String hourly_reveal_time_last_value;

    @Inject
    AddLocationMvpPresenter<AddLocationMvpView> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(AddLocationActivity.this);

        toolbar.setNavigationIcon(R.drawable.ic_clear_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSupportActionBar().setTitle("Add Location");

        reveal_time_daily.setOnClickListener(this);
        reveal_time_daily_last.setOnClickListener(this);
        add_daily_number.setOnClickListener(this);
        reveal_time_hourly.setOnClickListener(this);
        reveal_time_hourly_last.setOnClickListener(this);
        add_hourly_location.setOnClickListener(this);

    }

    private void reveal_time_daily() {

        Pico pico = new Pico(AddLocationActivity.this, null, Type.TIME);
        pico.setPicoListener(new PicoListener() {
            @Override
            public void result(Calendar calendar) {

                reveal_time_daily_value = Pico.formatTime(calendar);

                daily_reveal_time.setText(Pico.formatTime(calendar));
            }
        });
        pico.show();
    }

    private void reveal_time_daily_last() {

        Pico pico = new Pico(AddLocationActivity.this, null, Type.TIME);
        pico.setPicoListener(new PicoListener() {
            @Override
            public void result(Calendar calendar) {

                reveal_time_daily_last_value = Pico.formatTime(calendar);


                daily_reveal_time_last.setText(Pico.formatTime(calendar));

            }
        });
        pico.show();
    }


    private void reveal_time_hourly() {

        Pico pico = new Pico(AddLocationActivity.this, null, Type.TIME);
        pico.setPicoListener(new PicoListener() {
            @Override
            public void result(Calendar calendar) {

                hourly_reveal_time_value = Pico.formatTime(calendar);


                hourly_reveal_time.setText(Pico.formatTime(calendar));

            }
        });
        pico.show();
    }

    private void reveal_time_hourly_last() {

        Pico pico = new Pico(AddLocationActivity.this, null, Type.TIME);
        pico.setPicoListener(new PicoListener() {
            @Override
            public void result(Calendar calendar) {

                hourly_reveal_time_last_value = Pico.formatTime(calendar);


                hourly_reveal_time_last.setText(Pico.formatTime(calendar));

            }
        });
        pico.show();
    }


  /*  private void showTimePickerDialog(String time) {

        Pico pico = new Pico(this, null, Type.TIME);
        pico.setPicoListener(new PicoListener() {
            @Override
            public void result(Calendar calendar) {
                Pop.info(AddLocationActivity.this, Pico.formatTime(calendar));
                Log.i("TimePicker", Pico.formatDate(calendar));
            }


        });
        pico.show();
    }  */

    @Override
    protected void setUp() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reveal_time_daily:
              //  String reveal_time_daily;

                reveal_time_daily();

                break;
            case R.id.reveal_time_daily_last:

                reveal_time_daily_last();

                break;
            case R.id.add_daily_number:


                break;
            case R.id.reveal_time_hourly:


                reveal_time_hourly();

                break;
            case R.id.reveal_time_hourly_last:


                reveal_time_hourly_last();


                break;
            case R.id.add_hourly_location:


                break;


        }

    }
}
