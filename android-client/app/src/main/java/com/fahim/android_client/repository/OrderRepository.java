package com.fahim.android_client.repository;

import androidx.lifecycle.LiveData;

import com.fahim.android_client.model.Order;

public interface OrderRepository {
    LiveData<Order> listenForNewOrder();

    void clear();
}