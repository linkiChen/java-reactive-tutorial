package com.jacky.rxjava;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

public class ErrorOperatorTest {

    @Test
    public void onErrorReturnTest() {
        Flux.just(1, 2, 3)
                .concatWith(Mono.error(new RuntimeException())) // 拼接一个流
                .onErrorReturn(-1) // 发生错误时发出一个默认值
                .subscribe(System.out::println);
    }

    @Test
    public void onErrorResumeTest() {
        Flux.just(1, 2, 3)
                .concatWith(Mono.error(new RuntimeException())) // 拼接一个流
                .onErrorResume(e -> Flux.just(6, 7)) // 发生错误时切换到另一个流
                .subscribe(System.out::println);
    }

    // 与预期不符
    @Test
    public void onErrorContinueTest() {
        Flux.just(1, 2, 3)
                .concatWith(Mono.error(new RuntimeException())) // 拼接一个流
//                .concatWithValues(5, 6, 7)
                .onErrorContinue((e, o) -> System.out.println("err:" + e.getMessage() + ",obj:" + o))
                .subscribe(System.out::println);
    }

    // 重试是重试了, 但还是会抛出异常
    @Test
    public void retryTest() {
        Flux.just(1, 2, 3)
                .concatWith(Mono.error(new RuntimeException())) // 拼接一个流
                .retry(1) // 重试次数
                .subscribe(System.out::println);
    }

    @Test
    public void retryWhenTest() {
        Flux.just(1, 2, 3)
                .concatWith(Mono.error(new RuntimeException())) // 拼接一个流
                .retryWhen(Retry.fixedDelay(2, Duration.ofSeconds(1))) // 重试次数
                .subscribe(System.out::println);
    }


    // 没明白timeout是要干嘛的
    @Test
    public void timeoutTest() {
        Flux.just(1, 2, 3)
                .delayElements(Duration.ofSeconds(1))
                .timeout(Duration.ofMillis(1100), Mono.just(-1)) // 重试次数
                .subscribe(System.out::println);
    }

    @Test
    public void timeoutToTest() {
        Flux.just(1, 2, 3)
                .delayElements(Duration.ofSeconds(1))
                .timeout(Duration.ofMillis(500), Flux.just(0, 4, 5)) // 重试次数
                .subscribe(System.out::println);
    }
}
