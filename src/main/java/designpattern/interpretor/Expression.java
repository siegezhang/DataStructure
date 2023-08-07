package designpattern.interpretor;

import java.util.Map;

public interface Expression {

  boolean interpret(Map<String, Long> stats);
}
