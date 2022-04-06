package entities.request;

import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class PreferenceInput {

  private List<Item> itens;

  public PreferenceInput() {}

  public List<Item> getItens() {
    return itens;
  }

  public void setItens(List<Item> itens) {
    this.itens = itens;
  }

  public static class Item {

    private String title;
    private String description;
    private Integer quantity;
    private BigDecimal unitPrice;

    public Item() {}

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    public Integer getQuantity() {
      return quantity;
    }

    public void setQuantity(Integer quantity) {
      this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
      return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
      this.unitPrice = unitPrice;
    }

    public PreferenceItemRequest toRequestItem() {
      return PreferenceItemRequest.builder()
          .title(getTitle())
          .description(getDescription())
          .quantity(getQuantity())
          .unitPrice(getUnitPrice())
          .build();
    }
  }

  public PreferenceRequest toClientRequest() {
    List<PreferenceItemRequest> itemRequests = itens.stream()
        .map(Item::toRequestItem)
        .collect(Collectors.toList());
    return PreferenceRequest.builder()
        .items(itemRequests)
        .build();
  }
}
