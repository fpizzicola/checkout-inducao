package services;

import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import entities.request.PreferenceInput;
import entities.response.Response;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PreferenceService extends BaseService {

  private static final Logger logger = LoggerFactory.getLogger(PreferenceService.class);

  public PreferenceService() {
    setup();
  }

  public Response generate() throws MPException, MPApiException {
    // Cria um objeto de preferência
    PreferenceClient client = new PreferenceClient();

    // Cria um item na preferência
    List<PreferenceItemRequest> items = new ArrayList<>();
    PreferenceItemRequest item =
        PreferenceItemRequest.builder()
            .title("Meu produto")
            .quantity(1)
            .unitPrice(new BigDecimal("100"))
            .build();
    items.add(item);

    PreferenceRequest request = PreferenceRequest.builder().items(items).build();

    return generate(request);
  }

  public Response generate(PreferenceInput input) throws MPException, MPApiException {
    return generate(input.toClientRequest());
  }

  public Response generate(PreferenceRequest request) throws MPException, MPApiException {
    PreferenceClient client = new PreferenceClient();

    try {
      Preference preference = client.create(request);
      return new Response(preference, preference.getResponse().getStatusCode());
    } catch (MPException | MPApiException e) {
      logger.error("error on create pref", e);
      throw e;
    }
  }

  public Object retrieve(String preferenceId) throws MPException, MPApiException {
    PreferenceClient client = new PreferenceClient();
    try {
      return client.get(preferenceId);
    } catch (MPException | MPApiException e) {
      logger.error("error on retrieve pref", e);
      throw e;
    }
  }
}
