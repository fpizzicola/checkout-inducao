package routes;


import static spark.Spark.exception;

import com.google.gson.JsonParser;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import entities.response.Response;
import transformers.JsonTransformer;

public class ExceptionsTreatment extends Routes {

  public void init() {
    exception(MPException.class, (e, req, res) -> {
      Response response = new Response(e.getCause());
      response.setMessage(e.getMessage());

      buildErrorResponse(res, response);
    });

    exception(MPApiException.class, (e, req, res) -> {
      Response response = new Response(e.getCause());
      response.setMessage(e.getMessage());
      response.setBody(JsonParser.parseString(e.getApiResponse().getContent()).getAsJsonObject());
      response.setStatusCode(e.getStatusCode());

      buildErrorResponse(res, response);
    });
  }


  private void buildErrorResponse(spark.Response res, Response response) {
    res.body(new JsonTransformer().render(response));
    res.status(response.getStatusCode() != null ? response.getStatusCode() : 400);
    setResponseHeaders(res);
  }
}
