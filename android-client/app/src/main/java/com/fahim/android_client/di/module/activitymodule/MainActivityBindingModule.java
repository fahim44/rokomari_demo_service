package com.fahim.android_client.di.module.activitymodule;

import androidx.lifecycle.ViewModel;

import com.fahim.android_client.view.ui.mainactivity.MainViewModel;
import com.lamonjush.libui.di.anotation.ViewModelKey;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public interface MainActivityBindingModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    ViewModel mainViewModel(MainViewModel viewModel);
}