package in.co.sattamaster.ui.Location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocationStatus {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private LocationData data;

    public Boolean getStatus() {
        return status;
    }

    public LocationData getData() {
        return data;
    }
}
