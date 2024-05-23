package com.itnation.translateai;

public interface ResponseCallback {

    void onResponse(String response);
    void onError(Throwable throwable);
}
