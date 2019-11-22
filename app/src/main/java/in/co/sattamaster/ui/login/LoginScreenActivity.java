package in.co.sattamaster.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import butterknife.BindView;
import in.co.sattamaster.R;
import in.co.sattamaster.ui.Homepage.MainActivity;
import in.co.sattamaster.ui.base.BaseActivity;
import in.co.sattamaster.ui.base.MySharedPreferences;
import javax.inject.Inject;

import butterknife.ButterKnife;

public class LoginScreenActivity extends BaseActivity implements LoginScreenMvpView {
    @BindView(R.id.loginbutton) Button loginbutton;
    @BindView(R.id.registerbutton) Button registerbutton;
    @BindView(R.id.phone_number) EditText phoneNumber;
    @BindView(R.id.password) EditText password;
    @BindView(R.id.login_progressbar) View login_progressbar;

    @Inject
    LoginScreenMvpPresenter<LoginScreenMvpView> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(LoginScreenActivity.this);

        login_progressbar.setVisibility(View.GONE);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (password.getText().toString().isEmpty() || phoneNumber.getText().toString().isEmpty()){
                    Toast.makeText(LoginScreenActivity.this, "Please enter value", Toast.LENGTH_LONG).show();
                } else {
                  //  isLoggedIn = true;

                    login_progressbar.setVisibility(View.VISIBLE);
                    mPresenter.sendBidSet(createLogin(), preferences);


                }
            }
        });


        generatetoken();
        subscribeToTopic("admin");


        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginScreenActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void generatetoken() {

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {

                        while(!task.isSuccessful()){
                            generatetoken();

                        }
                        String token = task.getResult().getToken();

                        // Get new Instance ID token
                        // String token = task.getResult().generatetoken();

                        // Log and toast
                        //  String msg = getString(R.string.msg_token_fmt, token);
                        // Log.d(TAG, msg);
                        // Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void subscribeToTopic(String topic){

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                FirebaseMessaging.getInstance().subscribeToTopic(topic)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                String msg = String.valueOf("Subscribed to topic " + topic);
                                if (!task.isSuccessful()) {
                                    msg = String.valueOf("Failed to Subscribed to topic " + topic);
                                }
                                //   Toast.makeText(PostsDetailsActivity.this, msg, Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        }, 10000);


    }

    @Override
    protected void setUp() {

    }

    private JsonObject createLogin(){
        JsonObject loginObject = new JsonObject();
        try {
            loginObject.addProperty("phone", phoneNumber.getText().toString());
            loginObject.addProperty("password", password.getText().toString());

        } catch (JsonIOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return loginObject;
    }

    @Override
    public void getLoginResponse(LoginResponse response) {

        String token = response.getToken();

        Intent intent = new Intent(LoginScreenActivity.this, MainActivity.class);
        MySharedPreferences.registerUserId(preferences, response.getUser().getId().toString());
        MySharedPreferences.registerToken(preferences, response.getToken());
        intent.putExtra("isLoggedIn", true);
        startActivity(intent);

        login_progressbar.setVisibility(View.GONE);

    }
}
