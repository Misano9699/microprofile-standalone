package nl.craftsmen.microprofile.standalone.examples;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.eclipse.microprofile.reactive.streams.operators.PublisherBuilder;
import org.eclipse.microprofile.reactive.streams.operators.ReactiveStreams;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@SuppressWarnings("unused")
public class ReactiveMessagingExample {

    @Outgoing("source")
    public PublisherBuilder<Integer> source() {
        return ReactiveStreams.of(1,2,3,4,5,6,7,8,9,10);
    }

    @Incoming("source")
    @Outgoing("destination")
    public Integer square(Integer input) {
        return input * input;
    }

    @Incoming("destination")
    public void result(Integer result) {
        System.out.println("Result of square is: " + result);
    }
}
