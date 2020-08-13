package com.fahim.android_client.di.module;

import android.app.Application;
import android.content.Context;

import com.fahim.android_client.app.App;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppProvideModule {

    @Singleton
    @Provides
    Application provideApplication(App app) {
        return app;
    }

    @Singleton
    @Provides
    Context provideContext(App app) {
        return app.getApplicationContext();
    }

    @Singleton
    @Provides
    ObjectMapper provideObjectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }
}