package com.jacky.rxjava.combine;

import reactor.core.publisher.Mono;

public class NumHundred implements NumJudge {
    @Override
    public Mono<Object> match(Integer srcNum) {
        if (0 <= srcNum && srcNum <= 100) {
            return Mono.defer(() -> Mono.just(srcNum + "在0到100之间"));
        }
        return Mono.empty();
    }
}
