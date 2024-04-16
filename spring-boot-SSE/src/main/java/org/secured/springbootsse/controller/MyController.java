package org.secured.springbootsse.controller;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.stream.Stream;

@RestController
@RequestMapping("/sse")
public class MyController {

    @RequestMapping(value = "/send", produces = "text/event-stream")
    public Flux<String> sendEvent() {
        Stream<String> lines = null;
        try {
            lines = Files.lines(Path.of("D:\\learnings\\Rudra\\spring-boot-SSE\\pom.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Flux.fromStream(lines)
//                .filter(line -> !line.isBlank())
                .delayElements(Duration.ofMillis(300));
    }
}
