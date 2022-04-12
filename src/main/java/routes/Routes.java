package routes;

import static spark.Spark.*;

import com.mercadopago.resources.preference.Preference;
import entities.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.PaymentService;
import services.PreferenceService;
import transformers.JsonTransformer;

public class Routes {
  private static final String APPLICATION_JSON = "application/json;charset=UTF-8";
  private static final String GZIP = "gzip";
  private static final String CONTENT_ENCODING = "Content-Encoding";

  public static final String ID = ":id";

  private static final Logger logger = LoggerFactory.getLogger(Routes.class);

  public void init() {
    new ExceptionsTreatment().init();
    new PreferenceRoutes().init();
    new PaymentRoutes().init();

    before((req, res) -> {
      logger.info("********************************");
      logger.info(req.uri());
      logger.info("********************************");
    });

    get("ping", (req, res) -> new Response("pong"), new JsonTransformer());

    get("/temp", (req, res) -> {
      Preference pref = (Preference) new PreferenceService().generate().getBody();
      return new PaymentService().doPayment(pref.getId());
    }, new JsonTransformer());


    after((req, res) -> setResponseHeaders(res));
  }

  protected void setResponseHeaders(spark.Response res) {
    res.type(APPLICATION_JSON);
    res.header(CONTENT_ENCODING, GZIP);
  }

}
