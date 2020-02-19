/*
 * AndExpression.java
 * 
 * Creado en Febrero 17, 2020. 00:26.
 */
package interpreter;

import interfaces.IExpression;

/**
 *
 * @author JavierMéndez 00000181816 & EnriqueMendoza 00000181798
 */
public class AndExpression implements IExpression {
	 
   private IExpression expr1 = null;
   private IExpression expr2 = null;

   public AndExpression(IExpression expr1, IExpression expr2) { 
      this.expr1 = expr1;
      this.expr2 = expr2;
   }

   @Override
   public boolean interpret(String context) {		
      return expr1.interpret(context) && expr2.interpret(context);
   }
}
