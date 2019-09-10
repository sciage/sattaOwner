package in.co.sattamaster.ui.RevealNumber;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RevealData {
    @SerializedName("centre_id")
    @Expose
    private String centreId;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("created_at_time")
    @Expose
    private String createdAtTime;
    @SerializedName("id")
    @Expose
    private String id;

    public String getCentreId() {
        return centreId;
    }

    public String getNumber() {
        return number;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getCreatedAtTime() {
        return createdAtTime;
    }

    public String getId() {
        return id;
    }
}
