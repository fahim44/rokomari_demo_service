package com.fahim.android_client.data.template;

import androidx.annotation.NonNull;

import io.reactivex.Flowable;
import ua.naiksoftware.stomp.dto.LifecycleEvent;

public interface SocketTemplate {
    void connect();

    void disconnect();

    Flowable<LifecycleEvent> listenLifecycleEvents();

    <T> Flowable<T> subscribe(@NonNull String topic, @NonNull Class<T> type);

}