package com.fahim.android_client.view.ui.mainactivity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.fahim.android_client.model.Order;
import com.fahim.android_client.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainViewModel extends ViewModel {

    private List<Order> orders;

    private MediatorLiveData<Boolean> newItemAddedMediLD;

    private OrderRepository orderRepository;

    @Inject
    MainViewModel(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
        orders = new ArrayList<>();
        initiateNewOrderListening(orderRepository);
    }

    private void initiateNewOrderListening(OrderRepository orderRepository) {
        newItemAddedMediLD = new MediatorLiveData<>();
        newItemAddedMediLD.addSource(orderRepository.listenForNewOrder(), order -> {
            if (!orders.contains(order)) {
                orders.add(0, order);
                newItemAddedMediLD.setValue(true);
                newItemAddedMediLD.setValue(false);
            }
        });
    }

    @Override
    protected void onCleared() {
        orderRepository.clear();
        super.onCleared();
    }

    public List<Order> getOrders() {
        return orders;
    }

    public LiveData<Boolean> getNewItemAddedLiveData() {
        return newItemAddedMediLD;
    }
}