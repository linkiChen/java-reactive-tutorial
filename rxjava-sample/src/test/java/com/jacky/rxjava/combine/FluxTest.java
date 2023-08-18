package com.jacky.rxjava.combine;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;

public class FluxTest {

    @Test
    public void concatMapTest() {
        NumNegative numNegative = new NumNegative();
        NumHundred numHundred = new NumHundred();
        NumThousand numThousand = new NumThousand();
        NumJudge judge = num -> (num > 50 && num < 200) ? Mono.just(num + "在50到200之间") : Mono.empty();

        List<NumJudge> list = Arrays.asList(numHundred, numNegative, numThousand, judge);

        // 参照 DispatcherHandler#handle实现
        Integer testNum = 70;
        Flux.fromIterable(list)
                // 循环查找第一个match结果不为Mono.empty()的元素,然后向下执行
                .concatMap(numJudge -> numJudge.match(testNum))
                .next()
                // 如果循环所有元素执行match的结果都是Mono.empty(),则执行emptyResp()
                .switchIfEmpty(emptyResp())
                .flatMap(res -> {
                    System.out.println("经match等到的结果: " + res);
                    return Mono.just(res + "->经第一次flatMap处理");
                })
                .flatMap(res -> {
                    System.out.println("经一次flatMap等到的结果: " + res);
                    return Mono.just(res + "->经第二次flatMap处理");
                }).log()
                .subscribe();
    }

    private Mono<Object> emptyResp() {
        return Mono.just("没有配置此数据的判断器");
    }
}
