package nl.craftsmen.microprofile.standalone.examples;

import nl.craftsmen.microprofile.standalone.ergast.Driver;
import nl.craftsmen.microprofile.standalone.ergast.ErgastClient;
import nl.craftsmen.microprofile.standalone.ergast.RaceResult;
import nl.craftsmen.microprofile.standalone.mdc.MdcLogContext;
import org.eclipse.microprofile.context.ManagedExecutor;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Query;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@GraphQLApi
@ApplicationScoped
@Path("/")
public class DriversPerRaceController {

    private static final Logger logger = LoggerFactory.getLogger(DriversPerRaceController.class);

    @Inject
    ManagedExecutor managedExecutor;

    @Inject
    MdcLogContext mdcLogContext;

    @RestClient
    ErgastClient ergastClient;

    @Query
    @GET()
    @Path("/drivers")
    @Produces("application/json")
    public CompletionStage<List<Driver>> drivers() throws ExecutionException, InterruptedException {
        logger.info("Storing context data");
        MDC.put("context", "context data");
        mdcLogContext.setContext(MDC.getCopyOfContextMap());
        return managedExecutor.supplyAsync(() -> {
            MDC.setContextMap(mdcLogContext.getContext());
            logger.info("Retrieving race");
            return ergastClient.raceResultsAsync();
        }).thenApplyAsync(result -> {
            MDC.setContextMap(mdcLogContext.getContext());
            logger.info("retrieving drivers from race");
            return result.thenApplyAsync(result1 -> result1.getMrData().getRaceTable().getRaces().stream()
                    .flatMap(race -> race.getResults().stream()).map(RaceResult::getDriver)
                    .collect(Collectors.toList()));
        }).get();
    }

    @Mutation
    @POST
    @Path("/addDriver")
    public Driver addDriver(Driver driver) {
        // additional code to store the driver
        logger.info("driver added");
        return driver;
    }
}
