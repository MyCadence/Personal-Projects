import java.util.*;
public class ExpressionEvaluator2 extends ExpressionEvaluator{//sub class
  
  public static Double evaluate(String expr){//refer to the super class evaluate
    Scanner input = new Scanner(System.in);
    String[] holder = expr.split("[-+*/= ()0123456789.]");
    String[] updated;
    int counter = 0;
    for(int i=0; i<holder.length; i++){
       if(holder[i].equals("")){
          continue;
       }
       counter++;
    }
    updated = new String[counter];
    counter=0;
    for(int i=0; i<holder.length; i++){
       if(holder[i].equals("")){
          continue;
       }
       updated[counter] = holder[i];
       counter++;
    }
    holder = updated.clone();
    for(int i=0; i<holder.length; i++){
      System.out.println("What is the value of '"+holder[i]+"'?");
      int a = input.nextInt();
      expr = expr.replaceFirst(holder[i]," "+a+" ");
    }
    try{
      
      if(expr.equals("()")||expr.equals("( )")){
        throw new RuntimeException();
      }
      
      
      else if(isBalanced(expr)){
        Stack<String> paren = new Stack<>();
        
        for(int i=0; i<expr.length(); i++){
          if(expr.charAt(i)=='('){
            paren.push("(");
          }
        }//stack of open paren
          
        for(int i=0; i<expr.length(); i++){//loop through whole expression
          if(paren.empty()){
            return new Double(Double.parseDouble(expr));
          }
          if(expr.charAt(i)==')'){//till next close paren
            for(int j=i; j>=0; j--){//loop backwards from close paren
              if(expr.charAt(j)=='('){//till next open paren
                String temp = expr.substring(j+1,i);//substring not including parentheses
                paren.pop();//take away one open parentheses, closer to solving the problem
                char tempOp = temp.charAt(0);
                if(tempOp!='*'&&tempOp!='+'&&tempOp!='-'&&tempOp!='/'){
                  throw new RuntimeException();//if the first character of the expr isn't an operator, it's invalid
                }
                temp = temp.substring(1,temp.length());//take out the operator now that we've stored it
                String[] arrayOperand = temp.split(" ");//list of operands
                String[] updatedOperand;
                int opCounter=0;
                for(int b=0; b<arrayOperand.length; b++){
                  if(arrayOperand[b].equals(" ")||arrayOperand[b].equals("")){
                    continue;
                  }
                  opCounter++;
                }
                updatedOperand = new String[opCounter];
                opCounter=0;
                for(int b=0; b<arrayOperand.length; b++){
                  if(arrayOperand[b].equals(" ")||arrayOperand[b].equals("")){
                    continue;
                  }
                  updatedOperand[opCounter]=arrayOperand[b];
                  opCounter++;
                }
                arrayOperand = updatedOperand.clone();
                switch(tempOp){
                  
                  case '*':
                    double tempAnswer=1.0;
                    for(int a=0; a<arrayOperand.length; a++){
                      tempAnswer*=Double.parseDouble(arrayOperand[a]);
                    }
                    temp = Double.toString(tempAnswer);
                    expr = expr.replace(expr.substring(j,i+1),temp);
                    break;
                    
                  case '+':
                    tempAnswer=0.0;
                    for(int a=0; a<arrayOperand.length; a++){
                      tempAnswer+=Double.parseDouble(arrayOperand[a]);
                    }
                    temp = Double.toString(tempAnswer);
                    expr = expr.replace(expr.substring(j,i+1),temp);
                    break;
                    
                  case '-':
                    if(arrayOperand.length==0){
                      throw new RuntimeException();
                    }
                    if(arrayOperand.length==1){
                      tempAnswer = -1.0*Double.parseDouble(arrayOperand[0]);
                      System.out.println(tempAnswer);
                      temp = Double.toString(tempAnswer);
                      expr = expr.replace(expr.substring(j,i+1),temp);
                      break;
                    }
                    tempAnswer=Double.parseDouble(arrayOperand[0]);
                    for(int a=1; a<arrayOperand.length; a++){
                      tempAnswer-=Double.parseDouble(arrayOperand[a]);
                    }
                    temp = Double.toString(tempAnswer);
                    expr = expr.replace(expr.substring(j,i+1),temp);
                    break;
                  
                  case '/':
                    if(arrayOperand.length==0){
                      throw new RuntimeException();
                    }
                    if(arrayOperand.length==1){
                      tempAnswer = 1.0/Double.parseDouble(arrayOperand[0]);
                      temp = Double.toString(tempAnswer);
                      expr = expr.replace(expr.substring(j,i+1),temp);
                      break;
                    }
                    tempAnswer=Double.parseDouble(arrayOperand[0]);
                    for(int a=1; a<arrayOperand.length; a++){
                      tempAnswer/=Double.parseDouble(arrayOperand[a]);
                    }
                    temp = Double.toString(tempAnswer);
                    expr = expr.replace(expr.substring(j,i+1),temp);
                    
                    break;
                }
                i-=(i-j);
                break;
              }
            }
          }
        }
        return 0.0;
      }
      
      else {
        throw new RuntimeException();
      }
      
      
    }
    catch(RuntimeException except){
      throw except;
    }

  }
  
}