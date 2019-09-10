package in.co.sattamaster.ui.AddCoins;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OwnerCoinBalance {
    @SerializedName("created_at")
    @Expose
    private String created_at;
    @SerializedName("coin_balance")
    @Expose
    private String coinBalance;
    @SerializedName("updated_at")
    @Expose
    private String updated_at;

    public String getCreated_at() {
        return created_at;
    }

    public String getCoinBalance() {
        return coinBalance;
    }

    public String getUpdated_at() {
        return updated_at;
    }
}
