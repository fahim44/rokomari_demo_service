package com.fahim.android_client.di.module;

import com.fahim.android_client.repository.OrderRepository;
import com.fahim.android_client.repository.OrderRepositoryImpl;

import dagger.Binds;
import dagger.Module;

@Module
public interface RepositoryModule {
    @Binds
    OrderRepository bindOrderRepository(OrderRepositoryImpl repository);
}