package com.jacky.rxjava;

import org.junit.Test;
import reactor.core.publisher.Flux;

public class CountAndJudgeOperatorTest {

    @Test
    public void countTest() {
        Flux.just(3, 5, 1, 6, 9)
                .count()
                .subscribe(System.out::println);
    }

    @Test
    public void allTest() {
        Flux.just(3, 5, 1, 6, 9)
                .all(e -> e > 1)
                .subscribe(System.out::println);
    }

    @Test
    public void anyTest() {
        Flux.just(3, 5, 1, 6, 9)
                .any(e -> e > 1)
                .subscribe(System.out::println);
    }

    @Test
    public void hasElementsTest() {
        Flux.just(3, 5, 1, 6, 9)
                .hasElements()
                .subscribe(System.out::println);
    }


    @Test
    public void hasElementTest() {
        Flux.just(3, 5, 1, 6, 9)
                .hasElement(5)
                .subscribe(System.out::println);
    }
}
