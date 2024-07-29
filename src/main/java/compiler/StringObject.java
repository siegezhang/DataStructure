package compiler;

import java.net.URI;
import java.net.URISyntaxException;
import javax.tools.SimpleJavaFileObject;

public class StringObject extends SimpleJavaFileObject {
  private String content = null;

  protected StringObject(String className, String contents) throws URISyntaxException {
    super(new URI(className), Kind.SOURCE);
    this.content = contents;
  }

  @Override
  public CharSequence getCharContent(boolean ignoreEncodingErrors) {
    return content;
  }
}
