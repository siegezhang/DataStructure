package jvmlearning.javaagent;

import com.sun.tools.attach.*;

import java.io.IOException;
import java.util.List;

public class JVMTIThread {
  public static void main(String[] args)
      throws IOException, AttachNotSupportedException, AgentLoadException,
          AgentInitializationException {
    List<VirtualMachineDescriptor> list = VirtualMachine.list();
    for (VirtualMachineDescriptor vmd : list) {
      if (vmd.displayName().endsWith("AccountMain")) {
        VirtualMachine virtualMachine = VirtualMachine.attach(vmd.id());
        virtualMachine.loadAgent("D:\\test\\MyAgent.jar");
        System.out.println("ok");
        virtualMachine.detach();
      }
    }
  }
}
