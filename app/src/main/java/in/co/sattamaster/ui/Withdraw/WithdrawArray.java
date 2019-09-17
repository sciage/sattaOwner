package in.co.sattamaster.ui.Withdraw;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WithdrawArray {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("player_id")
    @Expose
    private String playerId;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("is_completed")
    @Expose
    private String isCompleted;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;


    public String getId() {
        return id;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getAmount() {
        return amount;
    }

    public String getIsCompleted() {
        return isCompleted;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
