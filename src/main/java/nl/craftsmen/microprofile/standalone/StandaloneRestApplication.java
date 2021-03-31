package nl.craftsmen.microprofile.standalone;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/")
public class StandaloneRestApplication extends Application {
}
