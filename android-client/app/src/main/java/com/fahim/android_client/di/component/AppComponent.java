package com.fahim.android_client.di.component;

import com.fahim.android_client.app.App;
import com.fahim.android_client.di.module.AppProvideModule;
import com.fahim.android_client.di.module.DataSourceModule;
import com.fahim.android_client.di.module.RepositoryModule;
import com.fahim.android_client.di.module.TemplateModule;
import com.fahim.android_client.di.module.activitymodule.ActivityBindingModule;
import com.lamonjush.libui.di.LibUiDaggerModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class,
        LibUiDaggerModule.class,
        AppProvideModule.class,
        ActivityBindingModule.class,
        RepositoryModule.class,
        DataSourceModule.class,
        TemplateModule.class})
public interface AppComponent extends AndroidInjector<App> {

    @Component.Factory
    interface Factory {
        AppComponent create(@BindsInstance App application);
    }
}