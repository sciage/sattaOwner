package in.co.sattamaster.ui.Transactions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class TransactionData {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("sender_user")
    @Expose
    private TransactionSender senderUser;
    @SerializedName("receiver_user")
    @Expose
    private TransactionReceiver receiverUser;

    public String getId() {
        return id;
    }

    public String getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public TransactionSender getSenderUser() {
        return senderUser;
    }

    public TransactionReceiver getReceiverUser() {
        return receiverUser;
    }
}
