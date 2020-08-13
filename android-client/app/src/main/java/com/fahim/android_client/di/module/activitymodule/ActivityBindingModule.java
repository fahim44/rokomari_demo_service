package com.fahim.android_client.di.module.activitymodule;

import com.fahim.android_client.view.ui.mainactivity.MainActivity;
import com.lamonjush.libui.di.anotation.ActivityScoped;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = {MainActivityBindingModule.class})
    MainActivity contributeMainActivityInjector();
}