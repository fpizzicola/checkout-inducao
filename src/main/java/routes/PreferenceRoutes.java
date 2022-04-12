package routes;

import static spark.Spark.*;
import static routes.Routes.ID;

import entities.request.PreferenceInput;
import services.PreferenceService;
import transformers.JsonTransformer;
import transformers.ObjectTransformer;


public class PreferenceRoutes {

  public void init() {
    path("preference", () -> {
      get("/", (req, res) -> new PreferenceService().generate(), new JsonTransformer());
      get("/"+ID, (req, res) -> new PreferenceService().retrieve(req.params(ID)), new JsonTransformer());
      post("/", (req, res) -> new PreferenceService().generate(new ObjectTransformer().toObject(req.body(), PreferenceInput.class)), new JsonTransformer());
    });
  }
}
