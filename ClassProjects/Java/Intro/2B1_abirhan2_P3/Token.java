import java.util.*;
public class Token{
  
  //data fields
  protected Character operator;
  protected Double operand;
  protected boolean isOperator;
  
  //constructor takes in an operator instance of token
  public Token(Character operator){
    this.operator = operator;
    this.operand = 0.0;
    this.isOperator = true;
  }
  
  //constructor takes in an operand instance of token
  public Token(Double operand){
    this.operand = operand;
    this.operator = ' ';
    this.isOperator = false;
  }
  
  //math formula for each operator
  Double applyOperator(Double value1, Double value2){
    if(this.operator=='+'){
      return value1+value2;
    }
    if(this.operator=='/'){
      return value1/value2;
    }
    if(this.operator=='*'){
      return value1*value2;
    }
    if(this.operator=='-'){
      return value1-value2;
    }
    return 0.0;
  }
  
  //identity formulas
  public Double getIdentity(){
    if(operator=='+' || operator=='-'){
      return 0.0;
    }
    if(operator=='*' || operator=='/'){
      return 1.0;
    }
    return 0.0;
  }
  
  //determines if token instance is an operand
  public boolean takesNoOperands(){
    if(operator=='+' || operator=='*'){
      return true;
    }
    else{
      return false;
    }
  }
  
  //get methods below
  public boolean isOperator(){
    return this.isOperator;
  }
  
  public Character getOperator(){
    return this.operator;
  }
  
  public Double getValue(){
    return this.operand;
  }
  
}