package fsm;

import org.junit.Test;

public class FiniteStateMachineTest {
  @Test
  public void test() {
    FiniteStateMachineEnum stateEnum = FiniteStateMachineEnum.POWER_OFF;
    stateEnum.pressCool().pressPowerOff().pressCool().pressCool().pressPowerOff();
  }
}
