package in.co.sattamaster.owner.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bid {

    @SerializedName("status") @Expose private Boolean status;
    @SerializedName("data") @Expose private Data data;

    public Boolean getStatus() {
        return status;
    }

    public Data getData() {
        return data;
    }
}
