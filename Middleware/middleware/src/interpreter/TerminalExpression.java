/*
 * TerminalExpression.java
 * 
 * Creado en Febrero 17, 2020. 00:25.
 */
package interpreter;

import java.util.regex.Pattern;
import interfaces.IExpression;

/**
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public class TerminalExpression implements IExpression {
	
   private String data;

   public TerminalExpression(String data){
      this.data = data; 
   }

   @Override
   public boolean interpret(String context) {
      return Pattern.matches(data, context);
   }
}
