package in.co.sattamaster.ui.AllBids;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BidsData {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("bidset_id")
    @Expose
    private String bidsetId;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("did_win")
    @Expose
    private String didWin;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("coin_balance_cost")
    @Expose
    private String coinBalanceCost;

    public String getId() {
        return id;
    }

    public String getBidsetId() {
        return bidsetId;
    }

    public String getType() {
        return type;
    }

    public String getDidWin() {
        return didWin;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getCoinBalanceCost() {
        return coinBalanceCost;
    }
}
