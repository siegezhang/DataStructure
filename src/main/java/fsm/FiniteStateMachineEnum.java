package fsm;

public enum FiniteStateMachineEnum {
  /** */
  POWER_OFF {
    // 关机->吹风
    @Override
    public FiniteStateMachineEnum pressPowerOff() {
      System.out.println("Power on succeed.");
      return FiniteStateMachineEnum.FAN_ONLY;
    }

    // 无效
    @Override
    public FiniteStateMachineEnum pressCool() {
      System.out.println("Cool failed because power off.");
      return FiniteStateMachineEnum.POWER_OFF;
    }
  },
  /** */
  FAN_ONLY {
    // 吹风->关机
    @Override
    public FiniteStateMachineEnum pressPowerOff() {
      System.out.println("Power off succeed.");
      return FiniteStateMachineEnum.POWER_OFF;
    }

    // 吹风->制冷
    @Override
    public FiniteStateMachineEnum pressCool() {
      System.out.println("Open cool succeed.");
      return FiniteStateMachineEnum.COOL;
    }
  },
  /** */
  COOL {
    // 制冷->关机
    @Override
    public FiniteStateMachineEnum pressPowerOff() {
      System.out.println("Power off succeed.");
      return FiniteStateMachineEnum.POWER_OFF;
    }

    // 制冷->吹风
    @Override
    public FiniteStateMachineEnum pressCool() {
      System.out.println("Close cool succeed");
      return FiniteStateMachineEnum.FAN_ONLY;
    }
  };

  public abstract FiniteStateMachineEnum pressPowerOff();

  public abstract FiniteStateMachineEnum pressCool();
}
