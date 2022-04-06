package transformers;

import com.google.gson.Gson;

public class ObjectTransformer {

  private Gson gson = new Gson();

  public <T> T toObject(String json, Class<T> clazz) {
    T fromJson = gson.fromJson(json, clazz);
    return fromJson;
  }
}
