package com.jacky.rxjava;

import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.stream.Collectors;

public class CollectOperatorTest {

    @Test
    public void collectListTest() {
        Flux.just("ab", "ui", "tx")
                .collectList()
                .subscribe(System.out::println);

        System.out.println();
        Flux.just("ab", "ui", "tx")
                .collectSortedList()
                .subscribe(System.out::println);
    }

    @Test
    public void collectMapTest() {
        Flux.range(1, 10)
                .collectMap(e -> e % 2 == 0 ? "even" : "odd", e -> e)
                .subscribe(System.out::println);

        Flux.range(1, 10)
                .collectMultimap(e -> e % 2 == 0 ? "even" : "odd", e -> e)
                .subscribe(System.out::println);
    }

    @Test
    public void collectTest() {
        Flux.just(1, 3, 5, 7, 3, 9, 9)
                .collect(Collectors.toSet())
                .subscribe(System.out::println);

        Flux.range(1, 5)
                .collect(() -> "Sup", (s, e) -> {
                    System.out.println(s + ":" + e);
                })
                .subscribe(System.out::println); // 这里打印的是Supplier产生的结果

    }
}
