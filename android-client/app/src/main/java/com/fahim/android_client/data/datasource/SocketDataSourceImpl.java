package com.fahim.android_client.data.datasource;

import com.fahim.android_client.BuildConfig;
import com.fahim.android_client.data.template.SocketTemplate;
import com.fahim.android_client.model.Order;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import timber.log.Timber;
import ua.naiksoftware.stomp.dto.LifecycleEvent;

public class SocketDataSourceImpl implements SocketDataSource {

    private PublishSubject<Order> orderPublishSubject;

    private SocketTemplate socketTemplate;

    private CompositeDisposable disposableBag;

    @Inject
    SocketDataSourceImpl(SocketTemplate socketTemplate) {
        this.socketTemplate = socketTemplate;
        orderPublishSubject = PublishSubject.create();
        disposableBag = new CompositeDisposable();
    }

    @Override
    public Flowable<Order> init() {
        socketTemplate.connect();
        handleSocketLifeCycle();
        return orderPublishSubject.toFlowable(BackpressureStrategy.BUFFER);
    }

    private void handleSocketLifeCycle() {
        disposableBag.add(socketTemplate.listenLifecycleEvents()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnError(Timber::e)
                .subscribe(this::subscribeNewOrderOnSocketConnected)
        );
    }

    private void subscribeNewOrderOnSocketConnected(LifecycleEvent lifecycleEvent) {
        if (lifecycleEvent.getType() == LifecycleEvent.Type.OPENED) {
            subscribeToNewOrderTopic();
        }
    }

    private void subscribeToNewOrderTopic() {
        disposableBag.add(socketTemplate.subscribe(BuildConfig.appNewOrderSocketSubscriptionPath, Order.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .doOnError(Timber::e)
                .subscribe(orderPublishSubject::onNext));
    }

    @Override
    public void clear() {
        disposableBag.dispose();
        socketTemplate.disconnect();
    }
}