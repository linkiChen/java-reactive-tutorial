package com.jacky.rxjava;

import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.stream.Stream;

public class SimpleOperatorTest {

    @Test
    public void mapTest() {
        System.out.println("Flux map operation(e * 2 + 2):");
        Flux.just(2, 5, 7)
                .map(ele -> ele * 2)
                .map(ele -> ele + 2)
                .subscribe(System.out::println);
        System.out.println("Stream map operation(e * 2 + 3):");
        Stream.of(1, 4, 6)
                .map(ele -> ele * 2)
                .map(ele -> ele + 3)
                .forEach(System.out::println);
    }

    @Test
    public void filterTest() {
        System.out.println("Flux filter:");
        Flux.just("Jack", "Tom", "Jany", "Jerry")
                .filter(name -> name.startsWith("J"))
                .subscribe(System.out::println);
        System.out.println("Stream filter:");
        Stream.of("Lily", "Lucy", "Tomas")
                .filter(name -> name.startsWith("L"))
                .forEach(System.out::println);
    }

    @Test
    public void flatMapTest() {
        Flux.just("a", "bd", "c")
                .flatMap(s -> Flux.just(s.toUpperCase()))
                .subscribe(System.out::println);
        System.out.println("---------------");

        Flux.just(Arrays.asList(1, 4), Arrays.asList(7, 9))
                .flatMap(i -> Flux.just(i))
                .subscribe(System.out::println);
    }

    @Test
    public void reduceTest() {
        Flux.just(3, 5, 7)
                .reduce((a, b) -> a + b)
                .subscribe(System.out::println);

        Flux.just(3, 5, 2, 1)
                .reduce(2, (a, b) -> a + b)
                .subscribe(System.out::println);
        Flux.just(3, 5, 2, 1)
                .reduceWith(() -> 5, (a, b) -> a + b)
                .subscribe(System.out::println);
    }

    @Test
    public void streamReduceTest() {
        Stream.of(3, 5, 2, 1)
                .reduce((a, b) -> a + b).ifPresent(System.out::println);
        System.out.println(Stream.of(3, 5, 2, 1)
                .reduce(3, (a, b) -> a + b));
        System.out.println(Stream.of(3, 5, 2, 1)
                .reduce(2, (a, b) -> {
                    System.out.println("a:" + a + " b:" + b);
                    return a + b;
                }, (int1, int2) -> {
                    System.out.println("int1:" + int1 + " int2:" + int2);
                    return int1 * int2;
                }));
    }
}
