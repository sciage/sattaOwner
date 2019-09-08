package in.co.sattamaster.ui.Homepage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import in.co.sattamaster.ui.login.UserProfile;

public class UserObject {
    @SerializedName("token") @Expose private String token;
    @SerializedName("user") @Expose private UserProfile user;

    public String getToken() {
        return token;
    }

    public UserProfile getUser() {
        return user;
    }
}
