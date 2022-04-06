package transformers;
import com.google.gson.Gson;
import entities.Response;
import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer {

  private Gson gson = new Gson();

  @Override
  public String render(Object model) {
    Response response;
    if(model instanceof Response) {
      response = (Response) model;
    } else {
      response = new Response();
      response.setBody(model);
    }
    return gson.toJson(response);
  }

}
