package nl.craftsmen.microprofile.standalone.examples;

import org.eclipse.microprofile.reactive.messaging.*;
import org.eclipse.microprofile.reactive.messaging.OnOverflow.Strategy;
import org.eclipse.microprofile.reactive.streams.operators.PublisherBuilder;
import org.eclipse.microprofile.reactive.streams.operators.ReactiveStreams;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@SuppressWarnings("unused")
public class ReactiveMessagingExample {

    @Inject
    @Channel("destination")
    @OnOverflow(Strategy.DROP)
    Emitter<Integer> emitter;

    @Outgoing("source")
    public PublisherBuilder<Integer> source() {
        return ReactiveStreams.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }

    @Incoming("source")
    public void square(Integer input) {
        emitter.send(input * input);
    }

    @Incoming("destination")
    public void result(Integer result) {
        System.out.println("Result of square is: " + result);
    }
}
