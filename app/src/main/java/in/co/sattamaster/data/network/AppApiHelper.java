/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package in.co.sattamaster.data.network;


import android.content.SharedPreferences;


import com.google.gson.JsonObject;
import java.security.cert.CertificateException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import in.co.sattamaster.dto.Bid;
import in.co.sattamaster.retrofit.NetworkClient;
import in.co.sattamaster.retrofit.NetworkInterface;
import in.co.sattamaster.ui.AddCoins.AddModeratorCoinsResponse;
import in.co.sattamaster.ui.AddCoins.AddUserCoinsResponse;
import in.co.sattamaster.ui.Homepage.GetAllUsers;
import in.co.sattamaster.ui.Homepage.LocationPojo;
import in.co.sattamaster.ui.Homepage.ModeratorProfile;
import in.co.sattamaster.ui.Homepage.UserObject;
import in.co.sattamaster.ui.Location.LocationStatus;
import in.co.sattamaster.ui.RevealNumber.RevealStatus;
import in.co.sattamaster.ui.login.AllModerators;
import in.co.sattamaster.ui.login.LoginResponse;
import in.co.sattamaster.ui.login.RegisterResponse;
import in.co.sattamaster.ui.login.UserProfile;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;

/**
 * Created by janisharali on 28/01/17.
 */

@Singleton
public class AppApiHelper implements ApiHelper {

    private ApiHeader mApiHeader;

    @Inject
    public AppApiHelper(ApiHeader apiHeader) {
        mApiHeader = apiHeader;
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHeader;
    }

    // LOGIN REQUEST




    @Override
    public Single<Bid> sendBidset(JsonObject bids, SharedPreferences sharedPreferences) {
   /*     return Rx2AndroidNetworking.post(ApiEndPoint.BIDSET)
                .setOkHttpClient(getUnsafeOkHttpClient())
                .addJsonObjectBody(bids)
                .build()
                .getObjectSingle(Bid.class); */

        return  NetworkClient.getRetrofit(sharedPreferences).create(NetworkInterface.class)
                .sendBidset(bids)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }


    @Override
    public Single<RegisterResponse> registerUser(JsonObject bids, SharedPreferences sharedPreferences) {
      /*  return Rx2AndroidNetworking.post(ApiEndPoint.REGISTER_USER)
                .setOkHttpClient(getUnsafeOkHttpClient())
                .addJsonObjectBody(bids)
                .build()
                .getObjectSingle(RegisterResponse.class); */

        return  NetworkClient.getRetrofit(sharedPreferences).create(NetworkInterface.class)
                .registerUser(bids)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    @Override
    public Single<LocationStatus> newLocation(JsonObject object, SharedPreferences sharedPreferences) {
      /*  return Rx2AndroidNetworking.post(ApiEndPoint.NEW_LOCATION)
                .setOkHttpClient(getUnsafeOkHttpClient())
                .addJsonObjectBody(object)
                .build()
                .getObjectSingle(LocationStatus.class); */

        return  NetworkClient.getRetrofit(sharedPreferences).create(NetworkInterface.class)
                .newLocation(object)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());


    }

    @Override
    public Single<LoginResponse> loginUser(JsonObject bids, SharedPreferences sharedPreferences) {
     /*   return Rx2AndroidNetworking.post(ApiEndPoint.LOGIN_USER)
                .setOkHttpClient(getUnsafeOkHttpClient())
                .addJsonObjectBody(bids)
                .build()
                .getObjectSingle(LoginResponse.class); */

        return  NetworkClient.getRetrofit(sharedPreferences).create(NetworkInterface.class)
                .loginUser(bids)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<AddUserCoinsResponse> addUserCoin(String userId, JsonObject coinBalance, SharedPreferences sharedPreferences) {
     /*   return Rx2AndroidNetworking.post(ApiEndPoint.ADD_USER_COIN)
                .addPathParameter("user_id", userId)
                .setOkHttpClient(getUnsafeOkHttpClient())
                .addJsonObjectBody(coinBalance)
                .build()
                .getObjectSingle(AddUserCoinsResponse.class); */

        return  NetworkClient.getRetrofit(sharedPreferences).create(NetworkInterface.class)
                .addUserCoin(userId, coinBalance)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    @Override
    public Single<AddModeratorCoinsResponse> addModeratorCoin(String moderator_id, JsonObject coinBalance, SharedPreferences sharedPreferences) {
      /*  return Rx2AndroidNetworking.post(ApiEndPoint.ADD_MODERATOR_COIN)
                .addPathParameter("moderator_id", moderator_id)
                .setOkHttpClient(getUnsafeOkHttpClient())
                .addJsonObjectBody(coinBalance)
                .build()
                .getObjectSingle(AddModeratorCoinsResponse.class); */

        return  NetworkClient.getRetrofit(sharedPreferences).create(NetworkInterface.class)
                .addModeratorCoin(moderator_id, coinBalance)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    @Override
    public Single<AddModeratorCoinsResponse> addModerator(JsonObject moderator, SharedPreferences sharedPreferences) {
       /* return Rx2AndroidNetworking.post(ApiEndPoint.ADD_MODERATOR)
                .setOkHttpClient(getUnsafeOkHttpClient())
                .addJsonObjectBody(moderator)
                .build()
                .getObjectSingle(AddModeratorCoinsResponse.class); */

        return  NetworkClient.getRetrofit(sharedPreferences).create(NetworkInterface.class)
                .addModerator(moderator)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    @Override
    public Single<AddModeratorCoinsResponse> addOwnerCoin(JsonObject ownerCoins, SharedPreferences sharedPreferences) {
      /*  return Rx2AndroidNetworking.post(ApiEndPoint.ADD_OWNER_COIN)
                .setOkHttpClient(getUnsafeOkHttpClient())
                .addJsonObjectBody(ownerCoins)
                .build()
                .getObjectSingle(AddModeratorCoinsResponse.class); */

        return  NetworkClient.getRetrofit(sharedPreferences).create(NetworkInterface.class)
                .addOwnerCoin(ownerCoins)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    @Override
    public Single<List<AllModerators>> getAllModerator(SharedPreferences sharedPreferences) {
      /*  return Rx2AndroidNetworking.get(ApiEndPoint.GET_ALL_MODERATORS)
                .setOkHttpClient(getUnsafeOkHttpClient())
                .build()
                .getObjectListSingle(AllModerators.class); */

        return  NetworkClient.getRetrofit(sharedPreferences).create(NetworkInterface.class)
                .getAllModerator()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    @Override
    public Single<GetAllUsers> getAllUsers(SharedPreferences sharedPreferences) {
    /*    return Rx2AndroidNetworking.get(ApiEndPoint.GET_ALL_USERS)
                .setOkHttpClient(getUnsafeOkHttpClient())
                .build()
                .getObjectSingle(GetAllUsers.class); */
        return  NetworkClient.getRetrofit(sharedPreferences).create(NetworkInterface.class)
                .getAllUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    @Override
    public Single<UserObject> getUserProfile(SharedPreferences sharedPreferences) {
       /*  return Rx2AndroidNetworking.get(ApiEndPoint.GET_USER_PROFILE)
                .addHeaders("Authorization", "Bearer" + " " + token)
                .setOkHttpClient(getUnsafeOkHttpClient())
                .build()
                .getObjectSingle(UserObject.class); */
        return  NetworkClient.getRetrofit(sharedPreferences).create(NetworkInterface.class)
                .getUserProfile()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    @Override
    public Single<List<LocationPojo>> getCentres(SharedPreferences sharedPreferences) {
       /* return Rx2AndroidNetworking.get(ApiEndPoint.GET_CENTRES)
                .setOkHttpClient(getUnsafeOkHttpClient())
                .build()
                .getObjectListSingle(LocationPojo.class);
        */
        return  NetworkClient.getRetrofit(sharedPreferences).create(NetworkInterface.class)
                .getCentres()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<RevealStatus> sendRevealNumber(JsonObject revealNumber, SharedPreferences sharedPreferences) {
       /* return Rx2AndroidNetworking.get(ApiEndPoint.GET_CENTRES)
                .setOkHttpClient(getUnsafeOkHttpClient())
                .build()
                .getObjectListSingle(LocationPojo.class);
        */
        return  NetworkClient.getRetrofit(sharedPreferences).create(NetworkInterface.class)
                .sendNumberReveal(revealNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }




    // block_user_insert.php


















    // post community group
/*
    @Override
    public Single<RegisterResponse> sendAgeOnlineApi(String name, String email, String userId, String deviceId, String socialNetwork) {
        return Rx2AndroidNetworking.post(ApiEndPoint.JOIN_CATEGORIES)
                // .addHeaders(mApiHeader.getPublicApiHeader())
                .addBodyParameter("firstname", "Amit").addPathParameter("pageNumber", "0")
                .addQueryParameter("name", "10")
                .addQueryParameter("email", "10")
                .addQueryParameter("limit", "10")
                .addQueryParameter("limit", "10")
                .addQueryParameter("limit", "10").addBodyParameter(name,email,userId,deviceId,socialNetwork)
                .build()
                .getObjectSingle(RegisterResponse.class);
    }

    // get all groups

    @Override
    public Single<OpenSourceResponse> getCommunityGroup() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_OPEN_SOURCE)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(OpenSourceResponse.class);
    }




    // join all groups

    @Override
    public Single<RegisterResponse> sendAgeOnlineApi(String name, String email, String userId, String deviceId, String socialNetwork) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_LOGIN)
                // .addHeaders(mApiHeader.getPublicApiHeader())
                .addBodyParameter("firstname", "Amit").addPathParameter("pageNumber", "0")
                .addQueryParameter("name", "10")
                .addQueryParameter("email", "10")
                .addQueryParameter("limit", "10")
                .addQueryParameter("limit", "10")
                .addQueryParameter("limit", "10").addBodyParameter(name,email,userId,deviceId,socialNetwork)
                .build()
                .getObjectSingle(RegisterResponse.class);
    }








    @Override
    public Single<RegisterResponse> doGoogleLoginApiCall(LoginRequest.GoogleLoginRequest
                                                              request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_GOOGLE_LOGIN)
                .addHeaders(mApiHeader.getPublicApiHeader())
                .addBodyParameter(request)
                .build()
                .getObjectSingle(RegisterResponse.class);
    }

    @Override
    public Single<RegisterResponse> doFacebookLoginApiCall(LoginRequest.FacebookLoginRequest
                                                                request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_FACEBOOK_LOGIN)
                .addHeaders(mApiHeader.getPublicApiHeader())
                .addBodyParameter(request)
                .build()
                .getObjectSingle(RegisterResponse.class);
    }

    @Override
    public Single<RegisterResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest
                                                              request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_SERVER_LOGIN)
                .addHeaders(mApiHeader.getPublicApiHeader())
                .addBodyParameter(request)
                .build()
                .getObjectSingle(RegisterResponse.class);
    }

    @Override
    public Single<LogoutResponse> doLogoutApiCall() {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_LOGOUT)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(LogoutResponse.class);
    }

    @Override
    public Single<BlogResponse> getBlogApiCall() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_BLOG)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(BlogResponse.class);
    }

    @Override
    public Single<OpenSourceResponse> getOpenSourceApiCall() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_OPEN_SOURCE)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(OpenSourceResponse.class);
    }

    */
}

