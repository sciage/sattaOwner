package in.co.sattamaster.ui.Transactions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SenderProfile {

    @SerializedName("moderator_id")
    @Expose
    private String moderator_id;
    @SerializedName("coin_balance")
    @Expose
    private String coin_balance;
    @SerializedName("created_at")
    @Expose
    private String created_at;
    @SerializedName("moderator")
    @Expose
    private Sendermoderator profile;


    public String getModerator_id() {
        return moderator_id;
    }

    public String getCoin_balance() {
        return coin_balance;
    }

    public String getCreated_at() {
        return created_at;
    }

    public Sendermoderator getModerator() {
        return profile;
    }
}




//"moderator_id":2,
//               "coin_balance":907,
//               "created_at":"2019-10-02 19:37:05",
//               "moderator"