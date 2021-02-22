package vertx.exchange.rate;

import exchange.rate.RateWorker;
import exchange.rate.StartRateWorkflow;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.MultiMap;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

public class MainVerticle {
    //@Override
    public void start() throws Exception {
        Vertx vertx = Vertx.vertx();

        // Create a Router
        Router router = Router.router(vertx);

        // Mount the handler for all incoming requests at every path and HTTP method
        router.get("/start-workflow").handler(context -> {
            // Get the address of the request
            String address = context.request().connection().remoteAddress().toString();
            // Get the query parameter "name"
            MultiMap queryParams = context.queryParams();
            String name = queryParams.contains("name") ? queryParams.get("name") : "unknown";
            if (true)
            {
                // start worker
                //RateWorker.main(new String[]{});

                // start workflows
                try {
                    StartRateWorkflow.main(new String[]{});
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                // Write a json response
                context.json(
                        new JsonObject()
                                .put("name", name)
                                .put("test ", "test")
                                .put("address", address)
                                .put("message", "Hello " + name + " connected from " + address)
                );
            }
        });

        // Create the HTTP server
        vertx.createHttpServer()
                // Handle every request using the router
                .requestHandler(router)
                // Start listening
                .listen(1234)
                // Print the port
                .onSuccess(server ->
                        System.out.println(
                                "HTTP server started on port " + server.actualPort()
                        )
                );
    }

    public static void main(String[] args) throws Exception {
        new MainVerticle().start();
    }
}
