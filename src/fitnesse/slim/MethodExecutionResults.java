package fitnesse.slim;

import java.util.ArrayList;
import java.util.List;

public class MethodExecutionResults {
  
  private List<MethodExecutionResult> results = new ArrayList<MethodExecutionResult>();
  
  public MethodExecutionResults add(MethodExecutionResult result) {
    results.add(result);
    return this;
  }
  
  public Object returnValue() {
    return results.get(0).returnValue();
  }
}
