package entities.response;

public class Response {

  private Object body;

  private Integer statusCode;

  private String message;

  public Response() {}

  public Response(Object body) {
    this.body = body;
  }

  public Response(Object body, Integer statusCode) {
    this.body = body;
    this.statusCode = statusCode;
  }

  public Object getBody() {
    return body;
  }

  public void setBody(Object body) {
    this.body = body;
  }

  public void setStatusCode(Integer statusCode) {
    this.statusCode = statusCode;
  }

  public Integer getStatusCode() {
    return statusCode;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
