package routes;

import static spark.Spark.*;

import entities.Response;
import services.PreferenceService;
import transformers.JsonTransformer;

public class Routes {
  public static final String APPLICATION_JSON = "application/json;charset=UTF-8";


  public void init() {
    get("ping", (req, res) -> new Response("pong"), new JsonTransformer());

    path("preference", () -> {
      get("/", (req, res) -> new PreferenceService().generate(), new JsonTransformer());
      post("/", (req, res) -> new PreferenceService(), new JsonTransformer());
    });

    after(
        (req, res) -> {
          res.type(APPLICATION_JSON);
          res.header("Content-Encoding", "gzip");
        });
  }
}
