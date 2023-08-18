package com.jacky.rxjava.combine;

import reactor.core.publisher.Mono;

public class NumThousand implements NumJudge {
    @Override
    public Mono<Object> match(Integer srcNum) {
        if (1000 <= srcNum && srcNum < 2000) {
            return Mono.defer(() -> Mono.just(srcNum + "在1000到1999之间"));
        }
        return Mono.empty();
    }
}
