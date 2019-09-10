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


import in.co.sattamaster.BuildConfig;

/**
 * Created by amitshekhar on 01/02/17.
 */

public final class ApiEndPoint {

    public static final String TEST = BuildConfig.BASE_URL + "test02.php";

    public static final String BIDSET = BuildConfig.BASE_URL + "bidsets";

    public static final String REGISTER_USER = BuildConfig.BASE_URL + "admin/1/players";

    public static final String NEW_LOCATION = BuildConfig.BASE_URL + "centres";

    public static final String LOGIN_USER = BuildConfig.BASE_URL + "user/login";

    public static final String GET_ALL_USERS = BuildConfig.BASE_URL + "admin/1/players";

    public static final String GET_ALL_MODERATORS = BuildConfig.BASE_URL + "moderators";

    public static final String GET_USER_PROFILE = BuildConfig.BASE_URL + "user";

    public static final String GET_CENTRES = BuildConfig.BASE_URL + "centres";

    public static final String ADD_USER_COIN = BuildConfig.BASE_URL + "admin/1/players/{user_id}/add-coin-balance";

    public static final String ADD_MODERATOR_COIN = BuildConfig.BASE_URL + "admin/1/moderators/{moderator_id}/add-coin-balance";

    public static final String ADD_OWNER_COIN = BuildConfig.BASE_URL + "admin/1/add-coin-balance";

    public static final String ADD_MODERATOR = BuildConfig.BASE_URL + "admin/1/moderators";

    public static final String ENDPOINT_GOOGLE_LOGIN = BuildConfig.BASE_URL
            + "/588d14f4100000a9072d2943";

    public static final String ENDPOINT_FACEBOOK_LOGIN = BuildConfig.BASE_URL
            + "/588d15d3100000ae072d2944";

    public static final String ENDPOINT_SERVER_LOGIN = BuildConfig.BASE_URL
            + "/588d15f5100000a8072d2945";

    public static final String ENDPOINT_LOGOUT = BuildConfig.BASE_URL
            + "/588d161c100000a9072d2946";

    public static final String ENDPOINT_BLOG = BuildConfig.BASE_URL
            + "/5926ce9d11000096006ccb30";

    public static final String ENDPOINT_OPEN_SOURCE = BuildConfig.BASE_URL
            + "/5926c34212000035026871cd";

    private ApiEndPoint() {
        // This class is not publicly instantiable
    }

}
