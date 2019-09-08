package in.co.sattamaster.ui.Homepage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import in.co.sattamaster.ui.login.AllModerators;

public class OwnerProfile {
    @SerializedName("coin_balance")
    @Expose
    private String coin_balance;
    @SerializedName("created_at")
    @Expose
    private String created_at;
    @SerializedName("updated_at")
    @Expose
    private String updated_at;

    public String getCoin_balance() {
        return coin_balance;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }
}
