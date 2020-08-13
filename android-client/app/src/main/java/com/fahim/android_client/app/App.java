package com.fahim.android_client.app;

import android.app.Application;

import com.fahim.android_client.BuildConfig;
import com.fahim.android_client.di.component.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import timber.log.Timber;

public class App extends Application implements HasAndroidInjector {

    @Inject
    volatile DispatchingAndroidInjector<Object> androidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        injectIfNecessary();
    }

    protected AndroidInjector applicationInjector() {
        return DaggerAppComponent.factory().create(this);
    }

    private void injectIfNecessary() {
        if (androidInjector == null) {
            synchronized (this) {
                if (androidInjector == null) {
                    applicationInjector().inject(this);
                }
            }
        }
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        injectIfNecessary();
        return androidInjector;
    }
}