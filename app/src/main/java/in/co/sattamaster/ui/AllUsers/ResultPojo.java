package in.co.sattamaster.ui.AllUsers;

public class ResultPojo {
    private String username;
    private String mobileNo;
    private String userBalance;

    public ResultPojo(String username, String mobileNo, String userBalance) {
        this.username = username;
        this.mobileNo = mobileNo;
        this.userBalance = userBalance;
    }

    public String getUsername() {
        return username;
    }


    public String getMobileNo() {
        return mobileNo;
    }


    public String getUserBalance() {
        return userBalance;
    }

}
