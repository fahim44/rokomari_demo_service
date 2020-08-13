package com.fahim.android_client.di.module;

import com.fahim.android_client.data.template.SocketTemplate;
import com.fahim.android_client.data.template.SocketTemplateImpl;

import dagger.Binds;
import dagger.Module;

@Module
public interface TemplateModule {
    @Binds
    SocketTemplate bindSocketTemplate(SocketTemplateImpl template);
}