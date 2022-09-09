package java8.reduce;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReduceTwoObjectAddProp {

  @Test
  public void test() {
    List<InvestInfo> list = new ArrayList<>();
    list.add(new InvestInfo(1, 2));
    list.add(new InvestInfo(null, 2));
    list.add(new InvestInfo(1, null));

    InvestInfo investInfo =
        list.stream()
            .reduce(
                (x, y) ->
                    new InvestInfo(
                        Optional.ofNullable(x.getPrincipal()).orElse(0)
                            + Optional.ofNullable(y.getPrincipal()).orElse(0),
                        Optional.ofNullable(x.getFee()).orElse(0)
                            + Optional.ofNullable(y.getFee()).orElse(0)))
            .orElse(new InvestInfo(0, 0));

    System.out.println(investInfo);
  }

  @Data
  @AllArgsConstructor
  static class InvestInfo {
    Integer principal;
    Integer fee;
  }
}
