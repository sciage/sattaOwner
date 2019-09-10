package in.co.sattamaster.ui.RevealNumber;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RevealStatus {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private RevealData data;

    public Boolean getStatus() {
        return status;
    }

    public RevealData getData() {
        return data;
    }
}
