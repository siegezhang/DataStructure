import java.io.*;
import java.util.stream.Stream;

public class Test {
  public static final String ACTION_SOURCE_BASE_PATH =
      "D:\\projects\\qmhis\\qmhis-biz\\src\\main\\java\\com\\allin\\qmhis\\biz\\action";
  public static final String ACTION_DEST_BASE_PATH =
      "D:\\projects\\qmpat\\qmpat-biz\\src\\main\\java\\com\\allin\\qmpat\\biz\\action";
  public static final String REQUEST_RESPONSE__SOURCE_BASE_PATH =
      "D:\\projects\\qmhis\\qmhis-facade\\src\\main\\java\\com\\allin\\qmhis\\facade\\bean";
  public static final String REQUEST_RESPONSE__DEST_BASE_PATH =
      "D:\\projects\\qmpat\\qmpat-facade\\src\\main\\java\\com\\allin\\qmpat\\facade\\bean";

  public static final String SUFFIX = ".java";

  public static void main(String[] args) {
    Stream.of(
                    new JavaFile("UpdateSysApptSchedulingCategoryAction","UpdateSysApptSchedulingCategoryRequest","UpdateSysApptSchedulingCategoryResponse"),
                    new JavaFile("UpdateSysDepartmentAction","UpdateSysDepartmentRequest","UpdateSysDepartmentResponse"),
                    new JavaFile("UpdateSysDepartmentEmployeeAction","UpdateSysDepartmentEmployeeRequest","UpdateSysDepartmentEmployeeResponse"),
                    new JavaFile("UpdateSysEmployeeAction","UpdateSysEmployeeRequest","UpdateSysEmployeeResponse"),
                    new JavaFile("UpdateSysEmployeeRoleAction","UpdateSysEmployeeRoleRequest","UpdateSysEmployeeRoleResponse"),
                    new JavaFile("UpdateSysEmrProgressItemAction","UpdateSysEmrProgressItemRequest","UpdateSysEmrProgressItemResponse"),
                    new JavaFile("UpdateSysEmrProgressTypeAction","UpdateSysEmrProgressTypeRequest","UpdateSysEmrProgressTypeResponse"),
                    new JavaFile("UpdateSysHospitalAction","UpdateSysHospitalRequest","UpdateSysHospitalResponse"),
                    new JavaFile("UpdateSysHospitalDepartmentAction","UpdateSysHospitalDepartmentRequest","UpdateSysHospitalDepartmentResponse"),
                    new JavaFile("UpdateSysHospitalDepartmentListAction","UpdateSysHospitalDepartmentListRequest","UpdateSysHospitalDepartmentResponse"),
                    new JavaFile("UpdateSysMessageTemplateAction","UpdateSysMessageTemplateRequest","UpdateSysMessageTemplateResponse"),
                    new JavaFile("UpdateSysPatientSourceAction","UpdateSysPatientSourceRequest","UpdateSysPatientSourceResponse"),
                    new JavaFile("UpdateSysPatientTagAction","UpdateSysPatientTagRequest","UpdateSysPatientTagResponse"),
                    new JavaFile("UpdateSysRegisterTypeAction","UpdateSysRegisterTypeRequest","UpdateSysRegisterTypeResponse"),
                    new JavaFile("UpdateSysRoleInfoAction","UpdateSysRoleInfoRequest","UpdateSysRoleInfoResponse"),
                    new JavaFile("UpdateSysStandardCategoryAction","UpdateSysStandardCategoryRequest","UpdateSysStandardCategoryResponse"),
                    new JavaFile("UpdateSysStandardItemAction","UpdateSysStandardItemRequest","UpdateSysStandardItemResponse"),
                    new JavaFile("UpdateSysStationRadioAction","UpdateSysStationRadioRequest","UpdateSysStationRadioResponse"),
                    new JavaFile("UpdateSysStationRadioEnableStatusAction","UpdateSysStationRadioRequest","UpdateSysStationRadioResponse"),
                    new JavaFile("UpdateSysTreatFeeCategoryAction","UpdateSysTreatFeeCategoryRequest","UpdateSysTreatFeeCategoryResponse"),
                    new JavaFile("UpdateSysTreatFeeItemAction","UpdateSysTreatFeeItemRequest","UpdateSysTreatFeeItemResponse"),
                    new JavaFile("UpdateSysWhiteListAction","UpdateSysWhiteListRequest","UpdateSysWhiteListResponse")
                    )
        .forEach(
            e -> {
              try {
                writeJavaFile(e);
              } catch (IOException ex) {
                System.out.println(ex);
                throw new RuntimeException(ex);
              }
            });
  }

  private static void writeJavaFile(JavaFile e) throws IOException {
    writeJavaFile(ACTION_SOURCE_BASE_PATH, e.actionName, ACTION_DEST_BASE_PATH);
    writeJavaFile(
        REQUEST_RESPONSE__SOURCE_BASE_PATH, e.requestName, REQUEST_RESPONSE__DEST_BASE_PATH);
    writeJavaFile(
        REQUEST_RESPONSE__SOURCE_BASE_PATH, e.responseName, REQUEST_RESPONSE__DEST_BASE_PATH);
  }

  private static void writeJavaFile(String sourceBasePath, String e, String destBasePath)
      throws IOException {
    BufferedReader reader =
        new BufferedReader(new FileReader(new File(sourceBasePath, e + SUFFIX)));
    BufferedWriter writer = new BufferedWriter(new FileWriter(new File(destBasePath, e + SUFFIX)));
    String line;
    while ((line = reader.readLine()) != null) {
      line = line.replace("qmhis", "qmpat");
      writer.write(line);
      writer.newLine();
    }
    writer.flush();
  }


  public  record JavaFile(String actionName, String requestName, String responseName) { }
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class Test {
  public static void main(String[] args) throws UnsupportedEncodingException {
    String mytest = System.getProperty("org.glassfish.web.rfc2109_cookie_names_enforced");
    System.out.println(mytest);
    System.out.println(new String(mytest.getBytes("GBK")));
    byte[] bytes1 = "阿".getBytes(StandardCharsets.UTF_8);
    byte[] bytes2 = "阿".getBytes("GBK");
    System.out.println(System.getProperty("file.encoding"));
    System.out.println("你好");
  }
}
