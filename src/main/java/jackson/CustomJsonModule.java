package jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;

public class CustomJsonModule extends SimpleModule {

  @Override
  public void setupModule(SetupContext context) {}
}
