package com.jacky.rxjava;

import org.junit.Test;
import reactor.core.publisher.Flux;

public class DistinctOperatorTest {

    @Test
    public void distinctTest() {
        Flux.just(1, 2, 3, 4, 5, 6, 7, 3, 4, 5)
                .distinct() // 去掉重复的元素
                .subscribe(System.out::println);
    }

    @Test
    public void distinctUntilChangeTest() {
        Flux.just(1, 2, 2, 3, 4, 4, 5, 5, 6, 7, 4, 5)
                .distinctUntilChanged() // 去掉连续重复的元素
                .subscribe(System.out::println);
    }

    @Test
    public void distinctTest1() {
        Flux.just(1, 2, 3, 4, 5, 6, 7, 3, 4, 5)
                .distinct(e -> e <= 4 && e % 2 == 0)
                .subscribe(System.out::println);
    }
}
