package com.fahim.android_client.di.module;

import com.fahim.android_client.data.datasource.SocketDataSource;
import com.fahim.android_client.data.datasource.SocketDataSourceImpl;

import dagger.Binds;
import dagger.Module;

@Module
public interface DataSourceModule {
    @Binds
    SocketDataSource bindSocketDataSource(SocketDataSourceImpl dataSource);
}