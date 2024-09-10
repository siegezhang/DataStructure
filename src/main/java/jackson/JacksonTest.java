package jackson;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.*;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.*;
import lombok.*;
import org.junit.jupiter.api.Test;

public class JacksonTest {

  /**
   * 测试Jackson的简单对象的序列化和反序列化
   *
   * @throws JsonProcessingException
   */
  @Test
  public void test() throws JsonProcessingException {
    Student s = Student.builder().name("小明").build();
    System.out.println(new ObjectMapper().writeValueAsString(s));
    Student student =
        new ObjectMapper().readValue(new ObjectMapper().writeValueAsString(s), Student.class);
    System.out.println(student.getName());
  }

  /**
   * 测试Jackson的List对象的序列化和反序列化
   *
   * @throws JsonProcessingException
   */
  @Test
  public void test1() throws JsonProcessingException {
    Student s = Student.builder().name("小明").build();
    Student s1 = Student.builder().name("小红").build();
    System.out.println(new ObjectMapper().writeValueAsString(Arrays.asList(s, s1)));
    List<Student> students =
        new ObjectMapper()
            .readValue(
                new ObjectMapper().writeValueAsString(Arrays.asList(s, s1)),
                new TypeReference<>() {});
    System.out.println(students);
  }

  @Getter
  @Setter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  @ToString
  public static class Student {
    @JsonProperty("name_stu")
    public String name;
  }

  @Getter
  @Setter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  @ToString
  public static class RawBean {
    public String name;

    @JsonRawValue public String json;
  }

  @Test
  public void whenSerializingUsingJsonRawValue_thenCorrect() throws JsonProcessingException {

    RawBean bean = new RawBean("My bean", "{\"attr\":false}");

    String result = new ObjectMapper().writeValueAsString(bean);
    System.out.println(result);
  }

  public enum TypeEnumWithValue {
    TYPE1(1, "Type A"),
    TYPE2(2, "Type 2");

    private Integer id;
    private String name;

    TypeEnumWithValue(Integer id, String name) {
      this.id = id;
      this.name = name;
    }

    @JsonValue
    public String getName() {
      return name;
    }
  }

  @Getter
  @Setter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  @ToString
  public static class JsonValueBean {
    public TypeEnumWithValue type;
  }

  @Test
  public void whenSerializingUsingJsonValue_thenCorrect() throws IOException {
    String enumAsString =
        new ObjectMapper()
            .writeValueAsString(JsonValueBean.builder().type(TypeEnumWithValue.TYPE1).build());
    System.out.println(enumAsString);
  }

  @Getter
  @Setter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  @ToString
  @JsonRootName(value = "user")
  public static class UserWithRoot {
    public int id;
    public String name;
  }

  @Test
  public void whenSerializingUsingJsonRootName_thenCorrect() throws JsonProcessingException {
    UserWithRoot user = new UserWithRoot(1, "John");
    ObjectMapper mapper = new ObjectMapper();
    mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
    String result = mapper.writeValueAsString(user);
    System.out.println(result);
  }

  public static class BeanWithCreator {
    public int id;
    public String name;

    @JsonCreator
    public BeanWithCreator(@JsonProperty("id") int id, @JsonProperty("theName") String name) {
      this.id = id;
      this.name = name;
    }
  }

  @Test
  public void whenDeserializingUsingJsonCreator_thenCorrect() throws IOException {
    String json = "{\"id\":1,\"theName\":\"My bean\"}";
    BeanWithCreator bean = new ObjectMapper().readerFor(BeanWithCreator.class).readValue(json);
    System.out.println(bean.name);
  }

  public static class BeanWithInject {
    @JacksonInject public int id;

    public String name;
  }

  @Test
  public void whenDeserializingUsingJsonInject_thenCorrect() throws IOException {
    String json = "{\"name\":\"My bean\"}";
    InjectableValues inject = new InjectableValues.Std().addValue(int.class, 1);
    BeanWithInject bean =
        new ObjectMapper().reader(inject).forType(BeanWithInject.class).readValue(json);
    System.out.println(bean.name);
    System.out.println(bean.id);
  }

  @Data
  public static class ExtendableBean {
    public String name;
    private Map<String, String> properties = new HashMap<>();

    @JsonAnySetter
    public void add(String key, String value) {
      properties.put(key, value);
    }
  }

  @Test
  public void whenDeserializingUsingJsonAnySetter_thenCorrect() throws IOException {
    String json =
        "{\"name\":\"My bean\",\"attr2\":\"val2\",\"attr1\":\"val1\",\"hobby\":\"movie\"}";
    ExtendableBean bean = new ObjectMapper().readerFor(ExtendableBean.class).readValue(json);
    System.out.println(bean);
  }

  @Data
  public static class AliasBean {
    @JsonAlias({"fName", "f_name"})
    private String firstName;

    private String lastName;
  }

  @Test
  public void whenDeserializingUsingJsonAlias_thenCorrect() throws IOException {
    String json = "{\"f_name\": \"John\", \"lastName\": \"Green\"}";
    AliasBean aliasBean = new ObjectMapper().readerFor(AliasBean.class).readValue(json);
    System.out.println(aliasBean);
  }

  @AllArgsConstructor
  @NoArgsConstructor
  public static class Zoo {
    public Animal animal;

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
    @JsonSubTypes({
      @JsonSubTypes.Type(value = Dog.class, name = "dog"),
      @JsonSubTypes.Type(value = Cat.class, name = "cat")
    })
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Animal {
      public String name;
    }

    @JsonTypeName("dog")
    @NoArgsConstructor
    public static class Dog extends Animal {
      public double barkVolume;

      public Dog(String name) {
        super(name);
      }
    }

    @JsonTypeName("cat")
    @NoArgsConstructor
    public static class Cat extends Animal {
      boolean likesCream;
      public int lives;

      public Cat(String name) {
        super(name);
      }
    }
  }

  @Test
  public void whenSerializingPolymorphic_thenCorrect() throws JsonProcessingException {
    Zoo.Dog dog = new Zoo.Dog("lacy");
    Zoo zoo = new Zoo(dog);
    String result = new ObjectMapper().writeValueAsString(zoo);
    System.out.println(result);
  }

  @Test
  public void whenDeserializingPolymorphic_thenCorrect() throws IOException {
    String json = "{\"animal\":{\"name\":\"lacy\",\"type\":\"cat\"}}";
    Zoo zoo = new ObjectMapper().readerFor(Zoo.class).readValue(json);
    System.out.println(zoo);
  }

  @AllArgsConstructor
  @NoArgsConstructor
  public static class UnwrappedUser {
    public int id;

    @JsonUnwrapped public Name name;

    @AllArgsConstructor
    @NoArgsConstructor
    public static class Name {
      public String firstName;
      public String lastName;
    }
  }

  @Test
  public void whenSerializingUsingJsonUnwrapped_thenCorrect() throws JsonProcessingException {
    UnwrappedUser user = new UnwrappedUser(1, new UnwrappedUser.Name("John", "Doe"));
    String result = new ObjectMapper().writeValueAsString(user);
    System.out.println(result);
  }

  public static class Views {
    public static class Public {}

    public static class Internal extends Public {}
  }

  @AllArgsConstructor
  @NoArgsConstructor
  public static class Item {
    @JsonView(Views.Public.class)
    public int id;

    @JsonView(Views.Public.class)
    public String itemName;

    @JsonView(Views.Internal.class)
    public String ownerName;
  }

  @Test
  public void whenSerializingUsingJsonView_thenCorrect() throws JsonProcessingException {
    Item item = new Item(2, "book", "John");
    System.out.println(
        new ObjectMapper().writerWithView(Views.Public.class).writeValueAsString(item));
    System.out.println(
        new ObjectMapper().writerWithView(Views.Internal.class).writeValueAsString(item));
  }

  @AllArgsConstructor
  @NoArgsConstructor
  public class ItemWithRef {
    public int id;
    public String itemName;
    @JsonManagedReference public UserWithRef owner;
  }

  @AllArgsConstructor
  @NoArgsConstructor
  @Data
  public class UserWithRef {
    public int id;
    public String name;

    public UserWithRef(int id, String name) {
      this.id = id;
      this.name = name;
    }

    @JsonBackReference public ItemWithRef itemRef;
  }

  @Test
  public void whenSerializingUsingJacksonReferenceAnnotation_thenCorrect()
      throws JsonProcessingException {
    UserWithRef user = new UserWithRef(1, "John");
    ItemWithRef item = new ItemWithRef(2, "book", user);
    user.setItemRef(item);
    String result = new ObjectMapper().writeValueAsString(item);
    System.out.println(result);
  }

  @AllArgsConstructor
  @NoArgsConstructor
  @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "itemId")
  public class ItemWithIdentity {
    public int itemId;
    public String itemName;
    public UserWithIdentity owner;
  }

  @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
  @Data
  public class UserWithIdentity {
    public int userId;
    public String name;
    public List<ItemWithIdentity> userItems;

    public UserWithIdentity(int userId, String name) {
      this.userId = userId;
      this.name = name;
    }
  }

  @Test
  public void whenSerializingUsingJsonIdentityInfo_thenCorrect() throws JsonProcessingException {
    UserWithIdentity user = new UserWithIdentity(1, "John");
    ItemWithIdentity item = new ItemWithIdentity(2, "book", user);
    user.setUserItems(List.of(item));
    System.out.println(new ObjectMapper().writeValueAsString(user));
    System.out.println(new ObjectMapper().writeValueAsString(item));
  }

  @JsonFilter("myFilter")
  @AllArgsConstructor
  @NoArgsConstructor
  public static class BeanWithFilter {
    public int id;
    public String name;
  }

  @Test
  public void whenSerializingUsingJsonFilter_thenCorrect() throws JsonProcessingException {
    BeanWithFilter bean = new BeanWithFilter(1, "My bean");
    FilterProvider filters =
        new SimpleFilterProvider()
            .addFilter("myFilter", SimpleBeanPropertyFilter.filterOutAllExcept("name"));
    String result = new ObjectMapper().writer(filters).writeValueAsString(bean);
    System.out.println(result);
  }

  @Retention(RetentionPolicy.RUNTIME)
  @JacksonAnnotationsInside
  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonPropertyOrder({"name", "id", "dateCreated"})
  @interface CustomAnnotation {}

  @CustomAnnotation
  @AllArgsConstructor
  @NoArgsConstructor
  public static class BeanWithCustomAnnotation {
    public int id;
    public String name;
    public Date dateCreated;
  }

  @Test
  public void whenSerializingUsingCustomAnnotation_thenCorrect() throws JsonProcessingException {
    BeanWithCustomAnnotation bean = new BeanWithCustomAnnotation(1, "My bean", null);
    String result = new ObjectMapper().writeValueAsString(bean);
    System.out.println(result);
  }

  @AllArgsConstructor
  @NoArgsConstructor
  public static class MixInItem {
    public int id;
    public String itemName;
    public MixInUser owner;
  }

  public static class MixInUser {
    public int id;
    public String name;
  }

  @JsonIgnoreType
  public static class MyMixInForIgnoreType {}

  @Test
  public void whenSerializingUsingMixInAnnotation_thenCorrect() throws JsonProcessingException {
    MixInItem item = new MixInItem(1, "book", null);
    String result = new ObjectMapper().writeValueAsString(item);
    System.out.println(result);
    ObjectMapper mapper = new ObjectMapper();
    mapper.addMixIn(MixInUser.class, MyMixInForIgnoreType.class);
    result = mapper.writeValueAsString(item);
    System.out.println(result);
  }

  @AllArgsConstructor
  @Data
  @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
  public static class BeanWithoutIdentityReference {
    private int id;
    private String name;
  }

  @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
  @JsonIdentityReference(alwaysAsId = true)
  @AllArgsConstructor
  @Data
  public static class BeanWithIdentityReference {
    private int id;
    private String name;
  }

  @Test
  public void testJsonIdentityReference() throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    BeanWithoutIdentityReference bean =
        new BeanWithoutIdentityReference(1, "Bean Without Identity Reference Annotation");
    System.out.println(mapper.writeValueAsString(bean));

    BeanWithIdentityReference bean1 =
        new BeanWithIdentityReference(1, "Bean With Identity Reference Annotation");
    System.out.println(mapper.writeValueAsString(bean1));
  }

  @AllArgsConstructor
  @Data
  public static class BeanWithoutAppend {
    private int id;
    private String name;
  }

  @Test
  public void testWithoutJsonAppend() throws JsonProcessingException {
    BeanWithoutAppend bean = new BeanWithoutAppend(2, "Bean Without Append Annotation");
    ObjectWriter writer =
        new ObjectMapper().writerFor(BeanWithoutAppend.class).withAttribute("version", "1.0");
    System.out.println(writer.writeValueAsString(bean));
  }

  @JsonAppend(attrs = {@JsonAppend.Attr(value = "version")})
  @AllArgsConstructor
  @Data
  public static class BeanWithAppend {
    private int id;
    private String name;
  }

  @Test
  public void testWithJsonAppend() throws JsonProcessingException {
    BeanWithAppend bean = new BeanWithAppend(2, "Bean With Append Annotation");
    ObjectWriter writer =
        new ObjectMapper().writerFor(BeanWithAppend.class).withAttribute("version", "1.0");
    System.out.println(writer.writeValueAsString(bean));
  }

  @Data
  @AllArgsConstructor
  @JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
  public static class NamingBean {
    private int id;
    private String beanName;
  }

  @Test
  public void testNamingBean() throws JsonProcessingException {
    NamingBean bean = new NamingBean(3, "Naming Bean");
    System.out.println(new ObjectMapper().writeValueAsString(bean));
  }

  @JsonDeserialize(builder = BeanBuilder.class)
  @AllArgsConstructor
  @NoArgsConstructor
  @Data
  public static class POJOBuilderBean {
    private int identity;
    private String beanName;
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @JsonPOJOBuilder(buildMethodName = "createBean", withPrefix = "construct")
  public static class BeanBuilder {
    private int idValue;
    private String nameValue;

    public BeanBuilder constructId(int id) {
      idValue = id;
      return this;
    }

    public BeanBuilder constructName(String name) {
      nameValue = name;
      return this;
    }

    public POJOBuilderBean createBean() {
      return new POJOBuilderBean(idValue, nameValue);
    }
  }

  @Test
  public void testPOJOBuilder() throws JsonProcessingException {
    String jsonString = "{\"id\":5,\"name\":\"POJO Builder Bean\"}";
    POJOBuilderBean bean = new ObjectMapper().readValue(jsonString, POJOBuilderBean.class);
    System.out.println(bean);
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class TypeIdBean {
    private int id;
    @JsonTypeId private String name;
  }

  @Test
  public void testJsonTypeId() throws JsonProcessingException {
    ObjectMapper mapper =
        new ObjectMapper().enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    TypeIdBean bean = new TypeIdBean(6, "Type Id Bean");
    System.out.println(mapper.writeValueAsString(bean));
  }

  @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
  @JsonTypeIdResolver(BeanIdResolver.class)
  @NoArgsConstructor
  @Data
  public static class AbstractBean {
    private int id;

    protected AbstractBean(int id) {
      this.id = id;
    }
  }

  @Data
  @EqualsAndHashCode(callSuper = false)
  public static class FirstBean extends AbstractBean {
    String firstName;

    public FirstBean(int id, String name) {
      super(id);
      setFirstName(name);
    }
  }

  @Data
  @EqualsAndHashCode(callSuper = false)
  public static class LastBean extends AbstractBean {
    String lastName;

    public LastBean(int id, String name) {
      super(id);
      setLastName(name);
    }
  }

  @Data
  public static class BeanContainer {
    private List<AbstractBean> beans;
  }

  public static class BeanIdResolver extends TypeIdResolverBase {

    private JavaType superType;

    @Override
    public void init(JavaType baseType) {
      superType = baseType;
    }

    @Override
    public JsonTypeInfo.Id getMechanism() {
      return JsonTypeInfo.Id.NAME;
    }

    @Override
    public String idFromValue(Object obj) {
      return idFromValueAndType(obj, obj.getClass());
    }

    @Override
    public String idFromValueAndType(Object obj, Class<?> subType) {
      String typeId = null;
      switch (subType.getSimpleName()) {
        case "FirstBean":
          typeId = "FirstBean";
          break;
        case "LastBean":
          typeId = "LastBean";
      }
      return typeId;
    }

    @Override
    public JavaType typeFromId(DatabindContext context, String id) {
      Class<?> subType = null;
      switch (id) {
        case "FirstBean":
          subType = FirstBean.class;
          break;
        case "LastBean":
          subType = LastBean.class;
      }
      return context.constructSpecializedType(superType, subType);
    }
  }

  @Test
  public void testJsonTypeIdResolver() throws JsonProcessingException {
    FirstBean bean1 = new FirstBean(1, "Bean 1");
    LastBean bean2 = new LastBean(2, "Bean 2");

    List<AbstractBean> beans = new ArrayList<>();
    beans.add(bean1);
    beans.add(bean2);

    BeanContainer serializedContainer = new BeanContainer();
    serializedContainer.setBeans(beans);
    System.out.println(new ObjectMapper().writeValueAsString(serializedContainer));
  }

  @JsonFormat(shape = JsonFormat.Shape.OBJECT)
  @Getter
  public enum Distance {
    KILOMETER("km", 1000),
    MILE("miles", 1609.34),
    METER("meters", 1),
    INCH("inches", 0.0254),
    CENTIMETER("cm", 0.01),
    MILLIMETER("mm", 0.001);

    private String unit;
    private double meters;

    public void setUnit(String unit) {
      this.unit = unit;
    }

    public void setMeters(double meters) {
      this.meters = meters;
    }

    Distance(String unit, double meters) {
      this.unit = unit;
      this.meters = meters;
    }
  }

  @Test
  public void testEnum() throws JsonProcessingException {
    System.out.println(new ObjectMapper().writeValueAsString(Distance.MILE));
  }

  @Test
  public void givenUsingLowLevelApi_whenParsingJsonStringIntoJsonNode_thenCorrect()
      throws IOException {
    String jsonString = "{\"k1\":\"v1\",\"k2\":\"v2\"}";

    ObjectMapper mapper = new ObjectMapper();
    JsonFactory factory = mapper.getFactory();
    JsonParser parser = factory.createParser(jsonString);
    JsonNode actualObj = mapper.readTree(parser);
    System.out.println(actualObj);

    // ObjectMapper mapper = new ObjectMapper();
    // JsonNode actualObj = mapper.readTree(jsonString);
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Keyboard {
    String style;
    String layout;
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ProgrammerNotAnnotated {
    String name;
    String favouriteLanguage;
    @JsonMerge // 去掉该字段返回不一样
    Keyboard keyboard;
  }

  @Test
  public void testJsonMerge() throws IOException {
    String newData = "{\"favouriteLanguage\":\"Java\",\"keyboard\":{\"style\":\"Mechanical\"}}";
    ProgrammerNotAnnotated programmerToUpdate =
        new ProgrammerNotAnnotated("John", "C++", new Keyboard("Membrane", "US"));
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectReader objectReader = objectMapper.readerForUpdating(programmerToUpdate);
    ProgrammerNotAnnotated update = objectReader.readValue(newData);
    System.out.println(update);
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class ObjectWithMap {
    String name;
    @JsonMerge Map<String, String> stringPairs;
  }

  @Test
  public void testJsonMerge2() throws IOException {
    String newData = "{\"stringPairs\":{\"field1\":\"value1\",\"field2\":\"value2\"}}";
    Map<String, String> map = new HashMap<>();
    map.put("field3", "value3");
    ObjectWithMap objectToUpdateWith = new ObjectWithMap("James", map);
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWithMap update = objectMapper.readerForUpdating(objectToUpdateWith).readValue(newData);
    System.out.println(update);
  }
}
