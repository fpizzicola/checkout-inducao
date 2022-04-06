package services;

import com.mercadopago.MercadoPagoConfig;

public class BaseService {

  public void setup() {
    MercadoPagoConfig.setAccessToken("TEST-6872270283744409-121519-168015f674e9a8974875152d85703fb7-687809950");
  }
}
