package in.co.sattamaster.ui.AllBids;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import in.co.sattamaster.ui.Homepage.LocationPojo;

public class HistoryDetailsResponse {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("centre_id")
    @Expose
    private String centreId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("bid_date")
    @Expose
    private String bidDate;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("coin_balance_cost")
    @Expose
    private String coinBalanceCost;
    @SerializedName("centre_name")
    @Expose
    private String centreName;
    @SerializedName("bids")
    @Expose
    private List<HistoryBidHeader> bids = null;
    @SerializedName("centre")
    @Expose
    private LocationPojo centre;


    public String getId() {
        return id;
    }

    public String getCentreId() {
        return centreId;
    }

    public String getUserId() {
        return userId;
    }

    public String getBidDate() {
        return bidDate;
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

    public String getCentreName() {
        return centreName;
    }

    public List<HistoryBidHeader> getBids() {
        return bids;
    }

    public LocationPojo getCentre() {
        return centre;
    }
}
