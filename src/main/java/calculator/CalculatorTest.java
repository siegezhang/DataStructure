package calculator;

import calculator.antlr.calculator.CalculatorBaseVisitor;
import calculator.antlr.calculator.CalculatorLexer;
import calculator.antlr.calculator.CalculatorParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class CalculatorTest {
  public static void main(String[] args) {
    String expression =
        """
                      a=12
                      b=a*3
                      a+b
                      a-b
                      """;
    // String expression = "a = 12 \n" + "b = a * 3\n" + "a + b\n" + "a - b\n";
    CalculatorLexer lexer = new CalculatorLexer(CharStreams.fromString(expression));
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    CalculatorParser parser = new CalculatorParser(tokens);
    parser.setBuildParseTree(true);
    ParseTree root = parser.prog();
    CalculatorBaseVisitor<Integer> visitor = new CalculatorVisitorImpl();
    root.accept(visitor);
  }
}
