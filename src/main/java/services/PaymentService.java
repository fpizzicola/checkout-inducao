package services;

import com.mercadopago.client.merchantorder.MerchantOrderClient;
import com.mercadopago.client.merchantorder.MerchantOrderCreateRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentOrderRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.merchantorder.MerchantOrder;
import com.mercadopago.resources.payment.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaymentService extends BaseService {

  private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

  public PaymentService() {
    setup();
  }

  public Payment doPayment(String prefId) throws MPException, MPApiException {
    MerchantOrder merchantOrder;
    try {
      MerchantOrderClient moClient = new MerchantOrderClient();
      merchantOrder =
          moClient.create(MerchantOrderCreateRequest.builder().preferenceId(prefId).build());
    } catch (MPException | MPApiException e) {
      logger.error("Error to create MO", e);
      throw e;
    }

    logger.info(String.format("********************* PrefID: %s |-> OrderID: %s:", prefId, merchantOrder.getId()));

    PaymentCreateRequest request =
        PaymentCreateRequest.builder()
            .order(PaymentOrderRequest.builder().id(merchantOrder.getId()).build())
            .build();

    try {
      PaymentClient client = new PaymentClient();
      return client.create(request);
    } catch (MPException | MPApiException e) {
      logger.error("Error to create payment", e);
      throw e;
    }
  }
}
