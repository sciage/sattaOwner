package in.co.sattamaster.ui.AddCoins;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import in.co.sattamaster.ui.login.UserProfile;

public class AddUserCoinsResponse {

    @SerializedName("status") @Expose private boolean status;
    @SerializedName("data") @Expose private UserProfile user;

    public UserProfile getUser() {
        return user;
    }

    public boolean isStatus() {
        return status;
    }
}
