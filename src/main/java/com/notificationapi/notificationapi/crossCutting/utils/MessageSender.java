package com.notificationapi.notificationapi.crossCutting.utils;

public interface MessageSender<T>{
    void execute(T message, String idMessage);
}
