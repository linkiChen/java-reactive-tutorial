package com.jacky.rxjava;

import org.junit.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class FluxTest1 {

    @Test
    public void fluxJustTest() {
        // 只产生一个元素
        Flux<Integer> just1 = Flux.just(1);
        just1.subscribe(System.out::println);
        System.out.println("-----------------");

        // 产生多个元素
        Flux<String> just = Flux.just("a", "b", "c", "d");
        just.subscribe(System.out::println);

    }

    @Test
    public void fluxEmptyTest() {
        // empty 产生0上元素
        Flux<String> empty = Flux.empty();
        empty.subscribe(res -> System.out.println("result:" + res));
    }

    @Test
    public void fluxFromIterableTest() {
        List<String> iterable = Arrays.asList("foo", "bar", "foobar");
        Flux<String> seq = Flux.fromIterable(iterable);
        seq.subscribe(System.out::println);
    }

    @Test
    public void fluxArrayTest() {
        // 从给定的数组中产生一个Flux
        // 如果是一个空数组, 那实际产生的是一个FluxEmpty
        // 如果只有一个元素, 则相当于是调用Flux.just产生一个元素的Flux
        Flux<Integer> fluxArr = Flux.fromArray(new Integer[]{1, 2, 3, 4});
        fluxArr.subscribe(System.out::println);
    }

    @Test
    public void fluxRangeTest() {
        Flux.range(4, 5)
                .subscribe(System.out::println);
    }


    @Test
    public void fluxSubscribe() {
        Flux<Integer> range = Flux.range(4, 5).delayElements(Duration.ofSeconds(1));
        range.subscribe(
                System.out::println, // consumer是处理每一次循环的事件,这里是打印输出循环的元素
                err -> System.out.println("error: " + err), // errorConsumer 是当发生错误事件时才会触发
                () -> System.out.println("Done"), // completeConsumer是当所有的元素都处理完后才触发
                subscription -> subscription.request(6) // request是指获取元素的最大个数, range最多产生5个元素,
                // 如果最多获取个数小于产生的个数, 那么就不会触发completeConsumer; 如果获取的个数大于等于产生的个数都会触发
                // completeConsumer, 获取的个数大于产生的也不会触发异常
        );
    }

    @Test
    public void consumerSubscriberTest() {
        ConsumerSubscriber<Integer> cs = new ConsumerSubscriber<>();
        Flux<Integer> range = Flux.range(4, 5);

        Flux<Integer> range2 = Flux.range(10, 3);
        // BaseSubscriber是单次使用的,所以这里只会触发range2的订阅
        range2.subscribe(cs);
        range.subscribe(cs);
    }

    // todo 还不知道怎么测试limitRate
    @Test
    public void backpressureTest() {
        ConsumerSubscriber2<Integer> cs = new ConsumerSubscriber2();
        Flux<Integer> range = Flux.range(1, 10000);
        range.limitRate(10)
                .subscribe(cs);
    }


    @Test
    public void useGenerateTest() {
        // generate是同步生成元素
        // stateSupplier是指定初始化的状态
        // generator是消费SynchronousSink并生成新的状态
        Flux<String> generate = Flux.generate(
                () -> 0,
                (state, sink) -> {
                    sink.next("3 * " + state + " = " + 3 * state);
                    if (state == 10) sink.complete();
                    return state + 1; // 生成新的状态
                });
        generate.subscribe(System.out::println);
    }

//    @Test
//    public void createTest() {
//        Flux.create(sink->{
//        })
//    }

    @Test
    public void handleTest() {
        Flux<String> handle = Flux.just(-1, 30, 12, 6, 90)
                .handle((i, sink) -> {
                    String letter = alphabet(i);
                    if (letter != null) {
                        sink.next(letter);
                    }
                });
        handle.subscribe(System.out::println);
    }

    public String alphabet(int letterNumber) {
        if (letterNumber < 0 || letterNumber > 30) {
            return null;
        }
        int letterIndexAscii = 'A' + letterNumber - 1;
        return "" + (char) letterIndexAscii;
    }
}
