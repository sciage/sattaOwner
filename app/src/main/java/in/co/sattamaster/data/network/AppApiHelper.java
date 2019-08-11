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


import com.rx2androidnetworking.Rx2AndroidNetworking;

import org.json.JSONObject;

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
import in.co.sattamaster.ui.Homepage.LocationPojo;
import in.co.sattamaster.ui.Homepage.UserObject;
import in.co.sattamaster.ui.Location.LocationStatus;
import in.co.sattamaster.ui.base.MySharedPreferences;
import in.co.sattamaster.ui.login.AllModerators;
import in.co.sattamaster.ui.login.LoginResponse;
import in.co.sattamaster.ui.login.RegisterResponse;
import in.co.sattamaster.ui.login.UserProfile;
import io.reactivex.Single;
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
    public Single<Bid> sendBidset(JSONObject bids) {
        return Rx2AndroidNetworking.post(ApiEndPoint.BIDSET)
                //  .addHeaders(mApiHeader.getProtectedApiHeader())
                .setOkHttpClient(getUnsafeOkHttpClient())
             //   .addBodyParameter("user_id", user_id)
              //  .addBodyParameter("centre_id", centre_id)
                .addJSONObjectBody(bids)
                .build()
                .getObjectSingle(Bid.class);
    }


    @Override
    public Single<RegisterResponse> registerUser(JSONObject bids) {
        return Rx2AndroidNetworking.post(ApiEndPoint.REGISTER_USER)
                //  .addHeaders(mApiHeader.getProtectedApiHeader())
                .setOkHttpClient(getUnsafeOkHttpClient())
                //   .addBodyParameter("user_id", user_id)
                //  .addBodyParameter("centre_id", centre_id)
                .addJSONObjectBody(bids)
                .build()
                .getObjectSingle(RegisterResponse.class);
    }

    @Override
    public Single<LocationStatus> newLocation(JSONObject object) {
        return Rx2AndroidNetworking.post(ApiEndPoint.NEW_LOCATION)
                //  .addHeaders(mApiHeader.getProtectedApiHeader())
                .setOkHttpClient(getUnsafeOkHttpClient())
                //   .addBodyParameter("user_id", user_id)
                //  .addBodyParameter("centre_id", centre_id)
                .addJSONObjectBody(object)
                .build()
                .getObjectSingle(LocationStatus.class);
    }

    @Override
    public Single<LoginResponse> loginUser(JSONObject bids) {
        return Rx2AndroidNetworking.post(ApiEndPoint.LOGIN_USER)
                //  .addHeaders(mApiHeader.getProtectedApiHeader())
                .setOkHttpClient(getUnsafeOkHttpClient())
                //   .addBodyParameter("user_id", user_id)
                //  .addBodyParameter("centre_id", centre_id)
                .addJSONObjectBody(bids)
                .build()
                .getObjectSingle(LoginResponse.class);
    }

    @Override
    public Single<List<AllModerators>> getAllModerator() {
        return Rx2AndroidNetworking.get(ApiEndPoint.GET_ALL_MODERATORS)
                //  .addHeaders(mApiHeader.getProtectedApiHeader())
                .setOkHttpClient(getUnsafeOkHttpClient())
                //   .addBodyParameter("user_id", user_id)
                //  .addBodyParameter("centre_id", centre_id)
                .build()
                .getObjectListSingle(AllModerators.class);
    }

    @Override
    public Single<List<UserProfile>> getAllUsers() {
        return Rx2AndroidNetworking.get(ApiEndPoint.GET_ALL_USERS)
                //  .addHeaders(mApiHeader.getProtectedApiHeader())
                .setOkHttpClient(getUnsafeOkHttpClient())
                //   .addBodyParameter("user_id", user_id)
                //  .addBodyParameter("centre_id", centre_id)
                .build()
                .getObjectListSingle(UserProfile.class);
    }

    @Override
    public Single<UserObject> getUserProfile(String token) {
        return Rx2AndroidNetworking.get(ApiEndPoint.GET_USER_PROFILE)
            //    .addHeaders("Authorization", "Bearer" + " " + token)
                .addHeaders("Authorization", "Bearer" + " " + token)
                .setOkHttpClient(getUnsafeOkHttpClient())
                //   .addBodyParameter("user_id", user_id)
                //  .addBodyParameter("centre_id", centre_id)
                .build()
                .getObjectSingle(UserObject.class);
    }

    @Override
    public Single<List<LocationPojo>> getCentres(String token) {
        return Rx2AndroidNetworking.get(ApiEndPoint.GET_CENTRES)
            //    .addHeaders("Authorization", "Bearer" + " " + token)
                .setOkHttpClient(getUnsafeOkHttpClient())
                //   .addBodyParameter("user_id", user_id)
                //  .addBodyParameter("centre_id", centre_id)
                .build()
                .getObjectListSingle(LocationPojo.class);
    }


    private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager)trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

