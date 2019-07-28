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

package in.co.sattamaster.owner.di.module;

import android.app.Application;
import android.content.Context;

import in.co.sattamaster.BuildConfig;
import in.co.sattamaster.owner.data.AppDataManager;
import in.co.sattamaster.owner.data.DataManager;
import in.co.sattamaster.owner.data.network.ApiHeader;
import in.co.sattamaster.owner.data.network.ApiHelper;
import in.co.sattamaster.owner.data.network.AppApiHelper;
import in.co.sattamaster.owner.data.prefs.AppPreferencesHelper;
import in.co.sattamaster.owner.data.prefs.PreferencesHelper;
import in.co.sattamaster.owner.di.ApiInfo;
import in.co.sattamaster.owner.di.ApplicationContext;
import in.co.sattamaster.owner.di.PreferenceInfo;
import in.co.sattamaster.owner.utils.AppConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by janisharali on 27/01/17.
 */

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApiInfo
    String provideApiKey() {
        return BuildConfig.API_KEY;
    }


    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }


    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @Singleton
    ApiHeader.ProtectedApiHeader provideProtectedApiHeader(@ApiInfo String apiKey,
                                                           PreferencesHelper preferencesHelper) {
        return new ApiHeader.ProtectedApiHeader(
                apiKey,
                preferencesHelper.getUserId(),
                preferencesHelper.getFirebaseToken());
    }


}
