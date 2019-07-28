package in.co.sattamaster.owner.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RevealDate {
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("revealed_numbers")
    @Expose
    private RevealedNumbers revealedNumbers;

    public String getDate() {
        return date;
    }

    public RevealedNumbers getRevealedNumbers() {
        return revealedNumbers;
    }
}
