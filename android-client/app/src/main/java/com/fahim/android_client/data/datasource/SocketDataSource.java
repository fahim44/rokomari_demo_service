package com.fahim.android_client.data.datasource;

import com.fahim.android_client.model.Order;

import io.reactivex.Flowable;

public interface SocketDataSource {
    Flowable<Order> init();

    void clear();
}