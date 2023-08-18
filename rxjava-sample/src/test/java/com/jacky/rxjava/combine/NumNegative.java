package com.jacky.rxjava.combine;

import reactor.core.publisher.Mono;

public class NumNegative implements NumJudge {


    @Override
    public Mono<Object> match(Integer srcNum) {
        if (srcNum < 0) {
            Mono.defer(() -> Mono.just(srcNum + "是负数"));
        }
        return Mono.empty();
    }
}
