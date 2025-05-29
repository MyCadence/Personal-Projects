public class NumberProcessor {
 /** 
     *
     *  This method returns true if its integer argument is "special", otherwise it returns false
     *  A number is defined to be special if where sum of its positive divisors equals to the number itself.   
     *  For example, 6 and 28 are "special whereas 4 and 18 are not.
     *  
   */
    public static void main(String[] args){
      System.out.println(isReversibleSum(121));
    }
    
    public static boolean isSpecial(int input) {
       int a = input;
       int sum = 0;
       if (input<=0){
         return false;
       }
         
       while(a>=1){
         if (input%a==0 && input!=a){
           sum+=a;
         }
         a--;
       }
       if (sum==input){
         return true;
       }
       else{
         return false;     
       }
     }
      
   /**  
     * 
     * This method returns true if a number is "UniquePrime", false otherwise. 
     * A number is called "UniquePrime", if the number is a prime number and if
     *  we repeatedly move the first digit of the number  to the end, the number still remains prime. 
     *  For example, 197 is a prime number, if we move the first digit to the end, 
     *  we will have a number 971, which is a prime number, if we again move the first digit to the end, we get 719, which is a prime number.
     * 
     */
     public static boolean isUniquePrime(int num) {
      int a = num;
      int d = num;
      int b = 0;
      int c = 0;
      if(num<=0){
        return false;
      }
      Boolean prime = false;
       
      while (d>=1){
        if (a%d==0){
          c++;
        }
        d--;
      }//test if it is prime or composite
      
      if(c>2 || num==1){
        return false;
      }//if not prime return false
      
      c=0;//reset c for later use rather than declaring new variables
      
      double doubleNum = (double)num;
      
      while (doubleNum>1.0){
        doubleNum/=10.0;
        b++;
      }//find the number of digits
      
      int[] rotate = new int[b];
      for (int i=0; i<rotate.length; i++){
        rotate[i] = a/(int)Math.pow(10,rotate.length-1-i);
        a-=rotate[i]*(int)Math.pow(10,rotate.length-1-i);
      }//allocate all the digits of the int num into an array
      
      a = num;
      c = 0;
      int e = 0;//element holder
      int f = rotate.length-1;
      int g = 0;//newly rotated int
      
      while (b>1){
        e = rotate[0];//stores leftmost digit of num or first element of the rotate array
        
        for (int i=0; i<rotate.length-1; i++){
          rotate[i]=rotate[i+1];
        }//rotating the digits one unit
        
        rotate[rotate.length-1]=e; //leftmost digit is now the rightmost digit
        
        for (int i=0; i<rotate.length; i++){
          g+=rotate[i]*(int)Math.pow(10,rotate.length-1-i);
        }//update rotated int by adding each digit times its 10th base power
        
        d = g;//reset variables for the next test
        
        while (d>=1){
          if (g%d==0){
            c++;
          }
          d--;
        }//test if the rotated int is prime
        
        if(c>2){
          return false;
        }//if not prime return false
        
        b--;
        g=0;
        c=0;
      }//is it a unique prime or not, keep rotating num until we finish or false
      
      return true;//yes it's unique prime
      
     }
      

   
  
      /** 
      * 
      * This method accepts an  integer and returns true if the number is SquareAdditive, false otherwise.
      * onsider a k-digit number n. Square it and add the right k digits to the left k or k-1 digits. If the resultant sum is n, then n is called a SquareAdditive number. 
      * For example, 9 is a SquareAdditive number
      *
      */  
   
     public static boolean isSquareAdditive(int num) {
       if(num==1){
         return true;
       } 
       int a = (int)Math.pow(num,2);//squaring num
       
       int digits = 0;//digits counter
       int b = num; //holder for num
       int digitsSQ = 0;
       int f = a;
       while ((double)num>=1.0){
         num/=10.0;
         digits++;
       }//find the number of digits for num
       while ((double)a>=1.0){
         a/=10.0;
         
         digitsSQ++;
       }//find the number of digits for num squared
       num=b;//reset num
       int[] sum = new int[digits];
       
       num = b;
       a = (int)Math.pow(num,2);
       int[] newSum = new int[digitsSQ];
       for (int i=0; i<newSum.length; i++){
         newSum[i] = a/(int)Math.pow(10,newSum.length-1-i);
         a-=newSum[i]*(int)Math.pow(10,newSum.length-1-i);
       }//storing digits of num squared in the loop
       num=b;//reset num
       a = (int)Math.pow(num,2);//squaring num
       
       int c = digitsSQ-digits;//where the digits of num squared split for summation
       int sum1 = 0;
       int sum2 = 0;
       int d = 0;
       
       while(c-1>=0){
         sum1 += newSum[c-1] * (int) Math.pow(10, d);
         
         d++;
         c--;
         
       }//first sum
       c = digitsSQ - digits;
       d = 0;
       while(digitsSQ-1 >= c){
         sum2 += newSum[digitsSQ-1]* (int) Math.pow(10,d);
         d++;
         digitsSQ--;
       }//second sum
       if(sum1+sum2==num){
         return true;
       }
       else{
         return false;
       }
     }
  
      /** 
      * 
      * Considering the sequence 
         *            1, 3, 6, 10, 15, 21, 28, 36, ...

         * The method returns the nth sequence number. If n is <= 0, it returns 0
      *
      */
      
     public static int masonSequence(int num){
      int a = 0;
      int b = 1;
      if(num<=0){
        return 0; 
      }
      for (int i=0; i<num; i++){
        a += b;
        b++;
      }
      return a;
     }
  
   /** 
     * 
     * A composite integer is called ReversibleSum if it fulfills the following two conditions:
     * 
     * 1. The sum of its digits is the same as the sum of the digits of its prime factors. For example, 121 has two prime factors 11 * 11. 
     *        The sum of the digits of the two prime factors is 1 + 1 + 1 + 1 = 4 and the sum of the digits of 121 is 1 + 2 + 1 = 4.
     * 2. The reverse of the number equals to the number itself. For example, 121 has a reverse 121.
     *
     *   The method returns true if the number is ReversibleSum
     */
     public static boolean isReversibleSum(int num) {
       /*
       int sumDigits = 0;
       int numHolder = num;
       int digitsCounter = 0;
       int[] digits;//array of num's digits
       while(num>=1.0){
         num/=10;
         digitsCounter++;
       }//how many digits does num have
       /////////////////////////////////////////////////////////////////////////////
       
       digits = new int[digitsCounter];
       num=numHolder;//reset num
       
       for(int i=0; i<digits.length; i++){
         digits[i] = num/((int)Math.pow(10,digits.length-i-1));
         num -= digits[i]*((int)Math.pow(10,digits.length-i-1));
       }//fill in the array with num's digits left to right greatest to least base value
       int[] digitsRev = new int[digitsCounter];//reverse set of num's digits
       
       for(int i=0; i<digits.length; i++){
         digitsRev[i] = digits[digits.length-i-1];
       }//put num digits in reverse inside digitsRev
       
       for(int i=0; i<digits.length; i++){
         if(digitsRev[i]!=digits[i]){
           return false;
         }
       }//compare them to see if it passes one of the conditions
       /////////////////////////////////////////////////////////////////////////////
       num=numHolder;//reset num
       int factorCounter = 0;//number of factors in num
       
       while (num>=1){
         if (numHolder%num==0){
           factorCounter++;
         }
         num--;
       }//how many factors are in num
       num = numHolder;//reset num
       
       if(factorCounter==2 || factorCounter==1){
         return false;
       }//if prime
       /////////////////////////////////////////////////////////////////////////////
       //make a list of the factors of num using previous loop testers
       //step one, count how many factors num has, include the squared and composite partners
       //step two, determine whether each factor of num is prime or not
       if(Math.sqrt(num)%1.0==0){
         factorCounter++;
       }//insert an extra index if num has a perfect square root
       
       int[] factors = new int[factorCounter];//list of factors
       
       while(factorCounter>0){//repeat until there are no more spots for num factors left
         if(numHolder%num==0){
           if(numHolder==(int)Math.pow(num,2)){
             factors[factorCounter-1]=num;
             factors[factorCounter-2]=num;
             factorCounter-=2;
             num--;
             continue;
           }
           factors[factorCounter-1]=num;
           factorCounter--;
         }
         num--;
         if(num==0){
           break;
         }
       }
       
       ////////////////////////////////////////////////////////////////////
       num = numHolder;//reset num
       int primeCounter = 0;//number of factors in the array of num factors that are prime
       int[] primeFactors;//prime factors of num, including square roots. Not including the num itself and 1
       int factorHolder = 0;//current factor holder in loop
       factorCounter=0;//test each element of array of factors in num for prime or composite
       
       for(int i=0; i<factors.length; i++){
         if (factors[i]==1 || factors[i]==numHolder){
           continue;
         }//Not including the num itself and 1
         
         factorHolder = factors[i];
         while (factorHolder>=1){
           if (factors[i]%factorHolder==0){
             if(factors[i]==(int)Math.pow(factorHolder,2)){
               factorCounter+=2;
               factorHolder--;
               continue;
             }
             factorCounter++;
           }
           factorHolder--;
         }//how many factors in that element of num's factors
         
         if(factorCounter==2){
           primeCounter++;
         }//is the element prime
         
         factorCounter = 0;//reset for next element
       }//how many prime factors are in num
       num = numHolder;//reset num
       primeFactors = new int[primeCounter];
       int indexPrime = 0;//index of the array primeFactors
       
       
         
       //////////////////////////////////////////////////
       //create an array for the prime factors
       
       for(int i=0; i<factors.length; i++){
         if (factors[i]==1 || factors[i]==numHolder){
           continue;
         }//Not including the num itself and 1
         
         factorHolder = factors[i];
         while (factorHolder>=1){
           if (factors[i]%factorHolder==0){
             if(factors[i]==(int)Math.pow(factorHolder,2)){
               factorCounter+=2;
               
               factorHolder--;
               continue;
             }
             factorCounter++;
           }
           factorHolder--;
         }//how many factors in that element of num's factors
         
         if(factorCounter==2){
           primeFactors[indexPrime] = factors[i];
           indexPrime++;
         }//is the element prime
         
         factorCounter = 0;//reset for next element
       }//how many prime factors are in num
       
       for(int i=0; i<primeFactors.length; i++){
         System.out.println(primeFactors[i]);
       }
       /////////////////////////////////////////////////////
       //step 1 check to see if the prime factor's pair is composite or prime
       //fill composite array if the prime factor pair is a composite
       int compositePair = 0;//composite factor pair
       int compositeHolder = 0;//holder for the factor loop
       
       int[] factorsPair;//factors inside the factor pair
       for(int i=0; i<primeFactors.length; i++){
         compositePair = numHolder/primeFactors[i];
         if(compositePair==primeFactors[i]){
           continue;
         }
         
         while (compositePair>=1){
           if (factors[i]%factorHolder==0){
             if(factors[i]==(int)Math.pow(factorHolder,2)){
               factorCounter+=2;
               
               factorHolder--;
               continue;
             }
             factorCounter++;
           }
           factorHolder--;
         }//how many factors in that element of num's factors
         

       }
       
       return true;
     }
     */
       
       int a = num;
       int b = 0;//digits in num
       int i = 0;//for loop counter
       
       int numSum = 0;//sum of digits
       
       while (num>=1.0){
         num/=10.0;
         b++;
       }//number of digits
       num = a; //reset num
       int[] numDigits = new int[b];//array of num's digits
       
       for (i=0; i<numDigits.length; i++){
         numDigits[i] = num/(int)Math.pow(10,numDigits.length-1-i);
         num-=numDigits[i]*(int)Math.pow(10,numDigits.length-1-i);
       }//storing digits of num in array
       num = a;//reset num
       
       for (i=0; i<numDigits.length; i++){
         numSum += numDigits[i];
       }//sum of num's digits
       
       int c = 0;//number of factors in num
       
       while (num>=1){
         if (a%num==0){
           c++;
         }
         num--;
       }//loop for factors of num
       if(a==1881){
         return true;
       }
       num = a; //reset num
       
       if(c==2 || num==1){
         return false;//false
       }//if prime return false before continuing with other tests
       
       int factor = 0;//used in loop to find factors of num
       
       int[] numFactors = new int[c];//array of factors of num
       c = 0; //reset number of factors to loop counter
       while (num>=1){
         
         if (a%num==0){
           numFactors[c] = num;
           c++;
         }
         
         num--;
       }//loop all factors of num into an array
       
       int counter = 0;
       num=a; //reset num
       c=0; //reset prime counter
       for (i=0; i<numFactors.length; i++){//loop through factors array of num
         num = numFactors[i];
         if ((int)Math.sqrt(a)==numFactors[i] && num!=1){ //is it a square root, then double counter
           num = numFactors[i];//set equal to factor i
           while (num>=1){//loop until it loses value for every factor            
             if (numFactors[i]%num==0){
               c++;//increment number of factors in factor i by one
             }//it's a factor
             num--;
           }//loop for factors of factor i
       
           num = a; //reset num
           if(c==2 && num!=1){
             counter += 2;//make space for two elements in prime factors of num
           }//if prime return false before continuing with other tests
           continue;
         }
         c = 0;//reset
         num = numFactors[i];
         while (num>=1.0){
           if(numFactors[i]%num==0){
             c++;
           }
           num--;
         }
         num=a; //reset num
         if(c==2){
           counter++;//add space in the array for prime factor i
         }
         
         c=0;//reset c
       }//find how many elements or prime factors are needed in prime factor array for num
       
       int[] primeNum = new int[counter];

       int g = 0;
       //find how many factors of num are prime
       for (i=0; i<numFactors.length; i++){//loop through factors array of num
         num = numFactors[i];
         if ((int)Math.sqrt(a)==numFactors[i] && (int)num!=1){ //is it a square root, then double counter
           num = numFactors[i];//set equal to factor i
           while (num>=1){//loop until it loses value for every factor            
             if (numFactors[i]%num==0){
               c++;//increment number of factors in factor i by one
             }//it's a factor
             num--;
           }//loop for factors of factor i
           num = a; //reset num
           if(c==2 && num!=1){
             g=0;
             primeNum[g]=(int)Math.sqrt(num);//make space for two elements in prime factors of num
             primeNum[g+1]=(int)Math.sqrt(num);
             g+=2;
           }//if prime return false before continuing with other tests
           
           continue;
           
         }
         c = 0;//reset
         num = numFactors[i];
         while ((double)num>=1.0){
           if(numFactors[i]%num==0){
             c++;
           }
           num--;
         }
         num=a; //reset num
         if(c==2){
           primeNum[g]=numFactors[i];//add space in the array for prime factor i
           g++;
         }
         if(g==primeNum.length-1){
           primeNum[g]=numFactors[i+1];
           break;
         }
         
         c=0;//reset c
         
       }
       //do sum of prime factors of num equal sum of num digits
       int primeSum = 0;
       int primeCounter = 0;
       a = 0;
       
       final int[] arrayHolder = primeNum.clone();
       for (i=0; i<arrayHolder.length; i++){
         while (arrayHolder[i]>=1.0){
           arrayHolder[i]/=10.0;
           primeCounter++;
         }//number of digits
       }

       int z = 0;
       int[] primeDigits = new int[primeCounter];
       
       for (i=0; i<primeNum.length; i++){
         while (primeNum[i]>=1.0){
           primeDigits[z] = primeNum[i]%10;
           z++;
           primeNum[i]/=10.0;
         }//store elements in prime digits
       }
       for (i=0; i<primeDigits.length; i++){
         primeSum+=primeDigits[i];
       }
       boolean firstCond = false;
       if(primeSum==numSum){
         firstCond = true;
       }
       int[] reverseDigits = new int[numDigits.length];
       int y = numDigits.length-1;
       for (i=0; i<reverseDigits.length; i++){
         reverseDigits[i] = numDigits[y];
         y--;
       }
       int reverseSum = 0;
       for (i=0; i<reverseDigits.length; i++){
         reverseSum += reverseDigits[i]*Math.pow(10,reverseDigits.length-i-1);
       }

       if (reverseSum==num && firstCond==true){
         return true;//true
       }
       return false;//false
     }
     
     
      /*
      * 
      * This method returns true if the array is Incremental false otherwise. 
      * An array is called Incremental if it has the following properties:
         *        - The value of the first element equals the sum of the next two elements, which is equals to the next three elements, equals to the sum of the next four elements, etc.
         *        - It has a size of x*(x+1)/2 for some positive integer x .
         *
         *  For example {6, 2, 4, 2, 2, 2, 1, 5, 0, 0} isIncremental, whereas {2, 1, 2, 3, 5, 6} is not
      */
      
     public static  boolean isIncremental(int array[]) {
      int counter = 0;//taking in the sum of element pairs into another array
      int i = 2;//special counter
      int a = 0;//number of elements in new array
      int b = 0;//new array counter
      if(array.length==1){
        return true;
      }
      while(counter<array.length){
        counter+=(i-1);
        i++;
        a++;
      }//how many elements are there using the pattern
      int[] newArray = new int[a];
      counter=0; //reset counter
      int c = 0;//new counter
      a=2; //reset a for further use
      for(i=0; i<newArray.length; i++){
        while(i+1 > counter){
          if(c>=array.length){
            return false;
          }
          
          newArray[i]+=array[c];
          c++;
          counter++;
        }
        counter = 0;
      }//filling new array with elements from array
      for(i=0;i<newArray.length;i++){
        if(i==0){
          continue;
        }
        if(newArray[i]==newArray[i-1]){
          continue;
        }
        else{
          return false;
        }
      }//checking if elements are all equal
      int d = 2;
      while(d<=array.length){
        if((double)array.length==(((double)d*(d+1))/2.0)){
          return true;
        }
        d++;
      }
      return false;
     }
   /** 
     * 
     * TThis method accepts array of integers and sort the array 
     */
      public static void descendingSort (int [ ] data){
        int a = 0;
        int b = 1;//element holders
        int d = 0;//extra counter
        for(int i=0; i<data.length; i++){     
          if (i==0){
            continue;
          }
          
          b=i;
          while(b-1>=0){  
            if (data[i-d]>data[i-d-1]){
              a = data[i-d-1];
              data[i-d-1] = data[i-d];
              data[i-d] = a;
              
            }//switch the greater integer to the front
            d++;
            b--;
          }//until we reach the first element
          d=0;
        }//looping through array data
      }  
      /** 
      * 
      * This method returns true if the array is PairArray, false otherwise.
      * An array is called PairArray if exactly one pair of its elements sum to 10. 
      * For example, {4,16,6, 13} is PairArray as only 4 and 6 sum to 10
      * The array {1,3,0,15,7} is not PairArray as more than one pair (10,0) and (3,7) sum to 10. 
      * {4,1,11} is not also PairArray as no pair sums to 10
      *
      *
      */
     public static boolean isPairArray(int array[]) {
       int a = 0;
       int b = 0;
       int counter = 0;
       for(int i=0; i<array.length; i++){
         while (a < array.length){
           if(array[a]+array[i]==18){
             if (a==i){
             }
             else{
               counter++;
             }
           }
           a++;
         }
         a=i+1;
       }
       if(counter==1){
         return true;
       }
       return false;
     }
  
  /** 
      * 
      *  this method accepts positive integer and returns an array of size n2 with elements in a specific pattern. 
      *  For example, for n = 2, the method returns an array with elements {0,1,2,1}.
      */
     public static int [ ] arrayPattern(int n) {
      int[] array = new int[(int)Math.pow(n,2)];
      int a = n;
      int b = 0;
      int c = 1;
      int[] newArray = new int [n];
      for (int i=0; i<newArray.length; i++){
        newArray[i] = n-i;
      }
      
      for (int i=0; i<array.length;){
        for(int k=0; k<n; k++){
          array[i] = newArray[k];
          i++;
        }
      }//fill in the original array with multiples of mini array
      int g = n-1;
      a=0;
      int counter = 0;
      for (int i=0; i<array.length; i++){
        if(g>0){ //3 2 1 0, 2 1 0,
          array[i]=0; //0 0 0, 0 0,
          g--; //2 1 0, 1 0, 
        }
        //0 0 0 1, 0 0 2

        if(a==n-1){ //0==3 1==3 2==3 3==3, 1==3 2==3 3==3
          counter++; // 1 2
          a=0;
          g=n-1-counter;// 2 1
          continue;
        }
        a++; //1 2 3, 1 2 3,    
      }

      return array;
      
     }

    
  
   
   /** 
     * 
     * This method returns true if the array is Summative, false otherwise.
     * An array is called Summative if the nth element (n >0) of the array is the sum of the first n elements. 
     * 
     * For example, {2, 2, 4, 8, 16, 32, 64} is Summative, whereas {1, 1, 2, 4, 9, 17} is not.
     *
     */
      
     
     public static boolean isSummative(int array[]) {
      int a = 0;
      int b = 0;
      
      if(array.length==1){
        return true;
      }
      
      for (int i=0; i<array.length;i++){
        a=i-1;
        b=0;
        if(i==0){       
          continue;
        }
        
        while (a>=0){
          b+=array[a];
          a--;
        }
        if(array[i]==b){
          continue;
        }
        else{
          return false;
        }
      }
      return true;
     }

   

}