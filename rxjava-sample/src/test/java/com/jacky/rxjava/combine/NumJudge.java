package com.jacky.rxjava.combine;

import reactor.core.publisher.Mono;

public interface NumJudge {

    Mono<Object> match(Integer srcNum);
}
