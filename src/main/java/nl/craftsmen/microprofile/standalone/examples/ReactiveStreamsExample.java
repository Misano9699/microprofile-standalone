package nl.craftsmen.microprofile.standalone.examples;

import org.eclipse.microprofile.reactive.streams.operators.CompletionRunner;
import org.eclipse.microprofile.reactive.streams.operators.PublisherBuilder;
import org.eclipse.microprofile.reactive.streams.operators.ReactiveStreams;
import org.eclipse.microprofile.reactive.streams.operators.SubscriberBuilder;

import java.util.List;

public class ReactiveStreamsExample {

    public static void main(String[] args) {

        PublisherBuilder<String> publisherBuilder = ReactiveStreams.of("these", "are", "microprofile", "reactive", "stream", "operators")
                .onError(System.out::println).onComplete(() -> System.out.println("Publisher finished!"));

        SubscriberBuilder<String, List<String>> subscriberBuilder = ReactiveStreams.<String>builder().map(String::toUpperCase)
                .filter(s -> s.length() > 5).toList();

        CompletionRunner<List<String>> completionRunner = publisherBuilder.to(subscriberBuilder);

        completionRunner.run().whenComplete((strings, throwable) -> {
            System.out.println("Strings with more than 5 characters: " + strings);
        });
    }
}
