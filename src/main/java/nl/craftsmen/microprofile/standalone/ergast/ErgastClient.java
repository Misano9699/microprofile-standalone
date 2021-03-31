package nl.craftsmen.microprofile.standalone.ergast;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.concurrent.CompletionStage;

@Path("/f1/current/last/results.json")
@RegisterRestClient(baseUri = "http://ergast.com/api")
public interface ErgastClient {

    @GET
    CompletionStage<Result> raceResultsAsync();
}
