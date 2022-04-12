package routes;
import static routes.Routes.ID;
import static spark.Spark.get;
import static spark.Spark.path;

import entities.response.Response;
import services.PaymentService;
import transformers.JsonTransformer;


public class PaymentRoutes {

  public void init() {

        path("payment", () -> {
          get("/sample", (req, res) -> new Response("sample"), new JsonTransformer());
          get("/"+ID, (req, res) -> new PaymentService().doPayment(req.params(ID)), new JsonTransformer());
        });
  }
}
