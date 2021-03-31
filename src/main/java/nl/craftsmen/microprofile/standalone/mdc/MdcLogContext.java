package nl.craftsmen.microprofile.standalone.mdc;

import javax.enterprise.context.RequestScoped;
import java.util.HashMap;
import java.util.Map;

@RequestScoped
public class MdcLogContext {

    private Map<String, String> context = new HashMap<>();

    public Map<String, String> getContext() {
        return context;
    }

    public void setContext(Map<String,String> context) {
        this.context = context;
    }
}
