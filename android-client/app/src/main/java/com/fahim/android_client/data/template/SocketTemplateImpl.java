package com.fahim.android_client.data.template;

import androidx.annotation.NonNull;

import com.fahim.android_client.BuildConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lamonjush.libui.util.ValidationUtil;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Emitter;
import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import timber.log.Timber;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;
import ua.naiksoftware.stomp.dto.LifecycleEvent;
import ua.naiksoftware.stomp.dto.StompMessage;

public class SocketTemplateImpl implements SocketTemplate {

    private ObjectMapper objectMapper;
    private StompClient stompClient;

    @Inject
    SocketTemplateImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void connect() {
        Timber.d("socket connect called.");
        disconnect();
        stompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP,
                BuildConfig.appSocketConnectivityUrl);
        stompClient.connect();
        Timber.d("stomp client connect called.");
    }

    @Override
    public void disconnect() {
        Timber.d("socket disconnect called.");
        if (stompClient != null)
            stompClient.disconnect();
        stompClient = null;
        Timber.d("socket disconnected.");
    }

    @Override
    public Flowable<LifecycleEvent> listenLifecycleEvents() {
        if (Objects.nonNull(stompClient))
            return stompClient.lifecycle();
        Timber.e(new NullPointerException("StompClient null. Failed to get lifecycle."));
        return Flowable.create(Emitter::onComplete, BackpressureStrategy.BUFFER);
    }

    @Override
    public <T> Flowable<T> subscribe(@NonNull String topic, @NonNull Class<T> type) {
        if (Objects.nonNull(stompClient)) {
            return stompClient.topic(topic)
                    .filter(filterStompMessage())
                    .map(mapObject(type))
                    .filter(Objects::nonNull);
        }
        Timber.e(new NullPointerException("StompClient null. Failed to subscribe to " + topic));
        return Flowable.create(Emitter::onComplete, BackpressureStrategy.BUFFER);
    }

    @NotNull
    private Predicate<StompMessage> filterStompMessage() {
        return stompMessage -> Objects.nonNull(stompMessage) && ValidationUtil.isValidString(stompMessage.getPayload());
    }

    @NotNull
    private <T> Function<StompMessage, T> mapObject(@NonNull Class<T> type) {
        return stompMessage -> {
            String json = stompMessage.getPayload();
            try {
                return objectMapper.readValue(json, type);
            } catch (JsonProcessingException e) {
                Timber.e(e);
                return null;
            }
        };
    }
}