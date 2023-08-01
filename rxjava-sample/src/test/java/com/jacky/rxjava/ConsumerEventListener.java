package com.jacky.rxjava;

import java.util.List;

public interface ConsumerEventListener<T> {

    void onDataChunk(List<T> chunk);

    void processComplete();
}
