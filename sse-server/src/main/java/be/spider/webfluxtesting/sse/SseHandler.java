package be.spider.webfluxtesting.sse;

import org.springframework.http.CacheControl;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
public class SseHandler {
    public Mono<ServerResponse> produceEvents(ServerRequest request) {
        Flux<ServerSentEvent<String>> sseFlux = Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> ServerSentEvent.builder("Flux - " + sequence).event("newsse").id(sequence.toString()).build());
        return ServerResponse.ok().cacheControl(CacheControl.noCache()).body(BodyInserters.fromServerSentEvents(sseFlux));
    }

}
