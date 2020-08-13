package com.fahim.android_client.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.fahim.android_client.data.datasource.SocketDataSource;
import com.fahim.android_client.model.Order;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class OrderRepositoryImpl implements OrderRepository {

    private SocketDataSource socketDataSource;
    private CompositeDisposable disposableBag;

    @Inject
    OrderRepositoryImpl(SocketDataSource socketDataSource){
        this.socketDataSource = socketDataSource;
        disposableBag = new CompositeDisposable();
    }

    @Override
    public LiveData<Order> listenForNewOrder() {
        MutableLiveData<Order> liveData = new MutableLiveData<>();
        disposableBag.add(socketDataSource.init()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(liveData::setValue));
        return liveData;
    }

    @Override
    public void clear() {
        socketDataSource.clear();
        disposableBag.dispose();
    }
}
