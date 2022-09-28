import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.junit.Test;

public class GsonTest {
  @Test
  public void test() {
    JsonElement jsonElement = new JsonPrimitive("foo");

    System.out.println(jsonElement.toString());
    System.out.println(jsonElement.getAsString());

    jsonElement = new JsonPrimitive(42);

    System.out.println(jsonElement.toString());
    System.out.println(jsonElement.getAsString());

    jsonElement = new JsonPrimitive(true);

    System.out.println(jsonElement.toString());
    System.out.println(jsonElement.getAsString());

    jsonElement = new JsonObject();
    ((JsonObject) jsonElement).addProperty("foo", "bar");
    ((JsonObject) jsonElement).addProperty("foo2", 42);

    System.out.println(jsonElement.toString());
    System.out.println(jsonElement.getAsString());
  }
}
