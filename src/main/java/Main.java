import routes.Routes;
import static spark.Spark.port;

public class Main {

  public static void main(String[] args) {
    port(9988);
    new Routes().init();
  }
}
