package com.jacky.rxjava;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

/**
 * 通过扩展BaseSubscriber来实现自定义的Subscriber
 * BaseSubscriber里的hook开头的方法都是空实现, 子类也是可以重写的
 * 而on开头的方法都定义为final方法,所以子类不能重写; 实际上hook开头的方法都是在对应的
 * on开头方法中被调用的,比如: onNext -> hookOnNext, onError -> hookOnError
 */
public class ConsumerSubscriber2<T> extends BaseSubscriber<T> {
    /**
     * 订阅的时候调用
     *
     * @param subscription
     */
    @Override
    protected void hookOnSubscribe(Subscription subscription) {
        System.out.println("Consumer subscribe");
        // 表示再请求一个元素, 如果没有request(1),
        // 则只会订阅到publisher,不会触发后续操作
        subscription.request(10);
        request(1);
    }

    @Override
    protected void hookOnNext(T value) {
        System.out.println("Consumer next:" + value);
        request(100);
//        System.out.println("------- hookOnNext done -------");
    }
}
