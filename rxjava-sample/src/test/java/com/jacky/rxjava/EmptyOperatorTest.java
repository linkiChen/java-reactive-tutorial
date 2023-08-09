package com.jacky.rxjava;

import org.junit.Test;
import reactor.core.publisher.Flux;

public class EmptyOperatorTest {

    @Test
    public void defaultEmpty() {
        Flux.empty()
                .defaultIfEmpty("default")
                .subscribe(System.out::println);
    }

    @Test
    public void emptySwitch() {
        Flux.empty()
                .switchIfEmpty(Flux.just(1,2,3))
                .subscribe(System.out::println);

    }
}
