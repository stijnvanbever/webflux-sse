package be.spider.webfluxtesting.sse;

import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import javax.print.attribute.standard.Media;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Stream;

@RestController
@RequestMapping("/controller")
public class SseController {

    @GetMapping(value = "flux-sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<String>> streamEvents() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> ServerSentEvent.<String> builder()
                        .id(String.valueOf(sequence))
                        .data("SSE - " + LocalTime.now().toString())
                        .build());
    }

    @GetMapping(value = "flux", produces =
            MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> getData() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(Objects::toString);
    }
}
