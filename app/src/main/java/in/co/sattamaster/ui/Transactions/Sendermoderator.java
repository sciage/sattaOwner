package in.co.sattamaster.ui.Transactions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import in.co.sattamaster.ui.Homepage.ModeratorProfile;

public class Sendermoderator {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("profile")
    @Expose
    private SenderModeratorProfile profile;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public SenderModeratorProfile getProfile() {
        return profile;
    }

    //"id":2,
    //                  "name":"Harish",
    //                  "phone":"8800128259",
    //                  "profile"
}
