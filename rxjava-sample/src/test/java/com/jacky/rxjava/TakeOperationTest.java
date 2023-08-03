package com.jacky.rxjava;

import org.junit.Test;
import reactor.core.publisher.Flux;

public class TakeOperationTest {

    @Test
    public void takeTest() {
        Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .take(4) // 取前4个
                .subscribe(System.out::println);
    }

    @Test
    public void takeLastTest() {
        Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .takeLast(4) // 取后4个
                .subscribe(System.out::println);
    }

    @Test
    public void takeUntilTest() {
        Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .takeUntil(e -> e > 5) // 直到(true) 一直做
                .subscribe(System.out::println);
    }

    @Test
    public void takeWhileTest() {
        Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .takeWhile(e -> e < 5) // 当为(true)前 一直做
                .subscribe(System.out::println);

    }
}
