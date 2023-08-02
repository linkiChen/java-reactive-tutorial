package com.jacky.rxjava;

import org.junit.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class MergeOperatorTest {

    @Test
    public void zipTest() {
        Flux.zip(Flux.just("a", "b", "c"), Flux.just("A", "B", "C"))
                .subscribe(System.out::println);
        System.out.println();
        Flux.zip(Flux.just("a", "b", "c"), Flux.just("A", "B", "C", "D"))
                .subscribe(System.out::println);

        System.out.println();
        Flux.just("a", "b", "c", "d").zipWith(Flux.just("A", "B", "C"))
                .subscribe(System.out::println);
        System.out.println();

        Flux.zip(Flux.just("a", "b", "c", "d"), Flux.just("A", "B", "C"), Flux.just(1, 2, 3))
                .subscribe(System.out::println);

        Flux.just("a", "b", "c", "d")
                .zipWith(Flux.just("A", "B", "C"))
                .zipWith(Flux.just(1, 2, 3), 3)
                .subscribe(System.out::println);
    }

    @Test
    public void mergeWithTest() {
        Flux.merge(Flux.just("a", "b", "c")
                , Flux.just("d", "e", "f"))
                .subscribe(System.out::println);
    }

    @Test
    public void concatWithTest() throws InterruptedException {
        Flux.concat(Flux.just("a", "b", "c").delayElements(Duration.ofMillis(200))
                , Flux.just("d", "e", "f").delayElements(Duration.ofMillis(100)))
                .subscribe(System.out::println);

        TimeUnit.SECONDS.sleep(10);
    }
}
