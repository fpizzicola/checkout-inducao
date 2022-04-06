package routes;

import static spark.Spark.*;

import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import entities.request.PreferenceInput;
import entities.response.Response;
import services.PreferenceService;
import transformers.JsonTransformer;
import transformers.ObjectTransformer;

public class Routes {
  public static final String APPLICATION_JSON = "application/json;charset=UTF-8";


  public void init() {
    get("ping", (req, res) -> new Response("pong"), new JsonTransformer());

    path("preference", () -> {
      get("/", (req, res) -> new PreferenceService().generate(), new JsonTransformer());
      get("/:id", (req, res) -> new PreferenceService().retrieve(req.params(":id")), new JsonTransformer());
      post("/", (req, res) -> new PreferenceService().generate(new ObjectTransformer().toObject(req.body(), PreferenceInput.class)), new JsonTransformer());
    });

    exception(MPException.class, (e, req, res) -> {
      Response response = new Response(e.getCause());
      response.setMessage(e.getMessage());

      buildErrorResponse(res, response);
    });

    exception(MPApiException.class, (e, req, res) -> {
      Response response = new Response(e.getCause());
      response.setMessage(e.getMessage());
      response.setStatusCode(e.getStatusCode());

      buildErrorResponse(res, response);
    });

    after(
        (req, res) -> {
          setResponseHeaders(res);
        });
  }

  private void setResponseHeaders(spark.Response res) {
    res.type(APPLICATION_JSON);
    res.header("Content-Encoding", "gzip");
  }

  private void buildErrorResponse(spark.Response res, Response response) {
    res.body(new JsonTransformer().render(response));
    res.status(response.getStatusCode() != null ? response.getStatusCode() : 400);
    setResponseHeaders(res);
  }
}
