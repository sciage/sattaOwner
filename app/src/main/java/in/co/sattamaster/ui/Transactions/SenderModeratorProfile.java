package in.co.sattamaster.ui.Transactions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SenderModeratorProfile {
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("coin_balance")
    @Expose
    private String coin_balance;
    @SerializedName("created_at")
    @Expose
    private String created_at;

    @SerializedName("active")
    @Expose
    private boolean active;

    @SerializedName("jodi_bid_max_length")
    @Expose
    private String jodi_bid_max_length;

    public String getAddress() {
        return address;
    }

    public String getCoin_balance() {
        return coin_balance;
    }

    public String getCreated_at() {
        return created_at;
    }

    public boolean isActive() {
        return active;
    }

    public String getJodi_bid_max_length() {
        return jodi_bid_max_length;
    }
}


//"address":"gurgaon, haryana",
//                     "coin_balance":9500,
//                     "created_at":"2019-10-02 09:11:15",
//                     "active":true,
//                     "jodi_bid_max_length":7