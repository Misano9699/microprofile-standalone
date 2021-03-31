package nl.craftsmen.microprofile.standalone.examples;

import org.eclipse.microprofile.reactive.streams.operators.ReactiveStreams;

public class ReactiveStreamsExample {

    public static void main(String[] args) {
        ReactiveStreams.of("these", "are", "microprofile", "reactive", "stream", "operators")
                .map(String::toUpperCase)
                .filter(s -> s.length() > 5)
                .forEach(System.out::println)
                .run();
    }
}
