import java.util.*;
public class ExpressionEvaluator{//evaluates the expression, super class evaluator
  
  public static boolean isBalanced(String expr){
    //check if it has same openings as it does closings
    char open = '(';
    char close = ')';
    int count1 = 0;
    int count2 = 0;
    for(int i=0; i<expr.length(); i++){
      if(expr.charAt(i)==open){
        count1++;
      }
      if(expr.charAt(i)==close){
        count2++;
      }
    }
    if(count1!=count2){
      return false;
    }//if number of closed is not equal to number of open paren, return false
    //not balanced
    
    return true;//is balanced
  }
  
  public static Double evaluate(String expr){
    Scanner input = new Scanner(System.in);//(optional) user inputs variable value
    String[] holder = expr.split("[-+*/= ()0123456789.]");//split it into groups
    //of letters. The variables will be put into an array
    String[] updated;//array holder
    int counter = 0;
    for(int i=0; i<holder.length; i++){
       if(holder[i].equals("")){
          continue;
       }
       counter++;//number of elements in the array that isn't empty string
    }
    updated = new String[counter];//initialize it to size counter
    counter=0;
    for(int i=0; i<holder.length; i++){
       if(holder[i].equals("")){
          continue;
       }
       updated[counter] = holder[i];//set each element to its respective variable
       counter++;//next index within the array holder
    }
    holder = updated.clone();//soft copy the holder to the original array
    for(int i=0; i<holder.length; i++){
      System.out.println("What is the value of '"+holder[i]+"'?");
      int a = input.nextInt();
      expr = expr.replaceFirst(holder[i]," "+a+" ");//replace the first occurence
      //of the variable in the expression with the user input
    }
    //now that we've replaced the variables with their respective numeric values
    //we will now proceed with performing the math
    try{
      
      if(expr.equals("()")||expr.equals("( )")){
        throw new RuntimeException();//empty paren is a runtime error
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
                }//make a counter for how many operands there are not including
                //whitespace
                updatedOperand = new String[opCounter];//updated operand array
                opCounter=0;//reset
                for(int b=0; b<arrayOperand.length; b++){
                  if(arrayOperand[b].equals(" ")||arrayOperand[b].equals("")){
                    continue;
                  }
                  updatedOperand[opCounter]=arrayOperand[b];//fill the new array
                  opCounter++;
                }
                arrayOperand = updatedOperand.clone();//light copy
                switch(tempOp){//make a switch statement for the operator
                  
                  case '*'://multiplication
                    double tempAnswer=1.0;
                    for(int a=0; a<arrayOperand.length; a++){
                      tempAnswer*=Double.parseDouble(arrayOperand[a]);
                    }//apply operation until all values in paren are accounted for
                    temp = Double.toString(tempAnswer);//change it back to string
                    expr = expr.replace(expr.substring(j,i+1),temp);
                    //replace the paren expression with the result
                    break;
                    
                  case '+'://addition
                    tempAnswer=0.0;
                    for(int a=0; a<arrayOperand.length; a++){
                      tempAnswer+=Double.parseDouble(arrayOperand[a]);
                    }
                    temp = Double.toString(tempAnswer);
                    expr = expr.replace(expr.substring(j,i+1),temp);
                    break;
                    
                  case '-'://subtraction
                    if(arrayOperand.length==0){
                      throw new RuntimeException();
                    }//if there is no number next to a '-' symbol throw runtime
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
                  
                  case '/'://division
                    if(arrayOperand.length==0){
                      throw new RuntimeException();
                    }//if there is no number next to '/' symbol throw runtime
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
                i-=(i-j);/*after replacing the expression with the value
                we must account for iterating past a shifted close paren
                so we shift our index to the left until we are able to locate any
                missed close paren during the replace statement
                */
                break;
                
              }
            }
          }
        }
        return 0.0;//satisfy syntax requirement
      }
      
      else {
        throw new RuntimeException();
      }//if it doesn't evaluate it must be wrong, throw runtime
      
      
    }
    catch(RuntimeException except){
      throw except;
    }//throw the runtime instance of the runtimeexception class

  }
  
  
}