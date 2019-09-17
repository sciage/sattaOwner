package in.co.sattamaster.retrofit;

import com.google.gson.JsonObject;

import java.util.List;

import in.co.sattamaster.data.network.ApiEndPoint;
import in.co.sattamaster.dto.Bid;
import in.co.sattamaster.ui.AddCoins.AddModeratorCoinsResponse;
import in.co.sattamaster.ui.AddCoins.AddUserCoinsResponse;
import in.co.sattamaster.ui.AllBids.AllBidsPojo;
import in.co.sattamaster.ui.AllBids.HistoryDetailsResponse;
import in.co.sattamaster.ui.Homepage.GetAllUsers;
import in.co.sattamaster.ui.Homepage.LocationPojo;
import in.co.sattamaster.ui.Homepage.UserObject;
import in.co.sattamaster.ui.Location.LocationStatus;
import in.co.sattamaster.ui.RevealNumber.RevealStatus;
import in.co.sattamaster.ui.Withdraw.WithdrawPojo;
import in.co.sattamaster.ui.login.AllModerators;
import in.co.sattamaster.ui.login.LoginResponse;
import in.co.sattamaster.ui.login.RegisterResponse;
import io.reactivex.Single;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NetworkInterface {

    @POST(ApiEndPoint.BIDSET)
    Single<Bid> sendBidset(@Body JsonObject bids);

    @POST(ApiEndPoint.REGISTER_USER)
    Single<RegisterResponse> registerUser(@Body JsonObject bids);


    @POST(ApiEndPoint.NEW_LOCATION)
    Single<LocationStatus> newLocation(@Body JsonObject object);

   // @Headers("Content-Type: application/x-www-form-urlencoded")

   @POST(ApiEndPoint.LOGIN_USER)
    Single<LoginResponse> loginUser(@Body JsonObject object);

    @POST(ApiEndPoint.ADD_USER_COIN)
    Single<AddUserCoinsResponse> addUserCoin(@Path("user_id") String user_id,
                                             @Body JsonObject object);

    @POST(ApiEndPoint.ADD_MODERATOR_COIN)
    Single<AddModeratorCoinsResponse> addModeratorCoin(@Path("moderator_id") String moderator_id,
                                                       @Body JsonObject object);

    @POST(ApiEndPoint.ADD_MODERATOR)
    Single<AddModeratorCoinsResponse> addModerator(@Body JsonObject bids);


    @POST(ApiEndPoint.ADD_OWNER_COIN)
    Single<AddModeratorCoinsResponse> addOwnerCoin(@Body JsonObject bids);

    @POST(ApiEndPoint.REVEAL_NUMBER)
    Single<RevealStatus> sendNumberReveal(@Body JsonObject bids);

    @GET(ApiEndPoint.BIDSET_ID)
    Single<HistoryDetailsResponse> getBidDetails(@Path("id") String id);


    @GET(ApiEndPoint.GET_ALL_MODERATORS)
    Single<List<AllModerators>> getAllModerator();

    @GET(ApiEndPoint.GET_ALL_USERS)
    Single<GetAllUsers> getAllUsers(@Query("page") String page);


    @GET(ApiEndPoint.GET_USER_PROFILE)
    Single<UserObject> getUserProfile();


    @GET(ApiEndPoint.GET_CENTRES)
    Single<List<LocationPojo>> getCentres();

    @GET(ApiEndPoint.WITHDRAW_REQUEST)
    Single<WithdrawPojo> withdrawRequest(@Query("page") String page);

    @GET(ApiEndPoint.GET_ALL_BIDS)
    Single<AllBidsPojo> getAllBids(@Query("page") String page);
}
