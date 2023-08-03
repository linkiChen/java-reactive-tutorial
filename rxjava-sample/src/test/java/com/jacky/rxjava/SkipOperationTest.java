package com.jacky.rxjava;

import org.junit.Test;
import reactor.core.publisher.Flux;

public class SkipOperationTest {

    @Test
    public void skipTest() {
        Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .skip(4) // 跳过前4个
                .subscribe(System.out::println);
    }

    @Test
    public void skipLastTest() {
        Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .skipLast(4) // 跳过后4个
                .subscribe(System.out::println);
    }

    @Test
    public void skipUntilTest() {
        Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .skipUntil(e -> e > 5) // 直到 true前, 都跳过
                .subscribe(System.out::println);
    }

    @Test
    public void skipWhileTest() {
        Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .skipWhile(e -> e < 5) //
                .subscribe(System.out::println);
    }
}
