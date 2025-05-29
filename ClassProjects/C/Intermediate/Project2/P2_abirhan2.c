/* This is the only file you will be editing.
 * - Copyright of Starter Code: Prof. Kevin Andrea, George Mason University.  All Rights Reserved
 * - Copyright of Student Code: You!  
 * - Restrictions on Student Code: Do not post your code on any public site (eg. Github).
 * -- Feel free to post your code on a PRIVATE Github and give interviewers access to it.
 * -- You are liable for the protection of your code from others.
 * - Date: Jan 2023
 */

/* Fill in your Name and GNumber in the following two comment fields
 * Name: Arron Birhanu
 * GNumber: G01315277
 */

#include <stdio.h>
#include <stdlib.h>
#include "common_structs.h"
#include "common_definitions.h"
#include "common_functions.h"
#include "tinysf.h"

// Feel free to add many Helper Functions, Consts, and Definitions!
tinysf_s shiftedBits(tinysf_s number);
int calc_E(Number_s *number);
int calc_Exp(int base, int exponent);
tinysf_s roundNum(Number_s *number, int E);
int roundable(Number_s *number, int E);
void shiftMantissa(Number_s *number, tinysf_s value);
tinysf_s addBits(tinysf_s val1, tinysf_s val2);
tinysf_s subBits(tinysf_s val1, tinysf_s val2);
tinysf_s multBits(tinysf_s val1, tinysf_s val2);
tinysf_s toWhole(tinysf_s value, int E);
tinysf_s getFrac(tinysf_s value);
tinysf_s decimalBits(tinysf_s fraction, int E);
int roundableFrac(tinysf_s value);
tinysf_s roundFrac(tinysf_s value);
int getExp(int E,  tinysf_s value);
int shiftE(int E, tinysf_s value);
tinysf_s addE(int E1, int E2, tinysf_s value);
// ----------Public API Functions (write these!)-------------------

/* toTinySF - Converts a Number Struct (whole and fraction parts) into a TINYSF Value
 *  - number is managed by zuon, DO NOT FREE number.
 *  - Follow the project documentation for this function.
 * Return the TINYSF Value.
 */
tinysf_s toTinySF(Number_s *number) {
  int bias = 7;
  tinysf_s signedF = 0;
  int E = 0;

  tinysf_s frac = number->fraction;
  //check whether it's infinity or NAN
  if(number->is_infinity&&number->is_nan||number->is_nan){
    signedF |= (1<<11);// (set all the exp bits to 1)
    signedF |= (1<<10);
    signedF |= (1<<9);
    signedF |= (1<<8);
    signedF |= (1<<0);//setting the frac to 1 is enough to turn the tinysf_s from Infinity into a NAN

    return signedF;
  }//just nan

  if(number->whole>=255 && number->fraction>=0xc0000000){//if number is >= 255.75
    signedF |= (1<<11);// (set all the exp bits to 1)
    signedF |= (1<<10);
    signedF |= (1<<9);
    signedF |= (1<<8);



    return signedF;
  }//set it to infinity

  if(number->is_infinity&&number->is_nan==0){
    
    signedF |= (1<<11);// (set all the exp bits to 1)
    signedF |= (1<<10);
    signedF |= (1<<9);
    signedF |= (1<<8);



    return signedF;
  }//just infinity
  
  

  if(number->whole==0&&number->fraction==0){
    return 0;
  }//if it's 0

  E = calc_E(number);//find how many times frac needs to be shifted

  if( ( number->whole==0 ) && ( number->fraction != 0 ) ){
    return decimalBits(number->fraction, E);
  }
  //set whole and fraction
  

  //underflow check
  if(E+bias<=0){
    return signedF;
  }

  //overflow
  if(E+bias>=16){
    signedF |= (1<<11);// (set all the exp bits to 1)
    signedF |= (1<<10);
    signedF |= (1<<9);
    signedF |= (1<<8);

    signedF |= (1<<0);//put the fraction as is
    return signedF;
  }

//rounding:
//step 1- is it roundable
//step 2- perform the rounding
  
  if(roundable(number, E)!=0){
    frac = roundNum(number, E);//take the shifted whole bits and slot them into the open frac spot
    signedF |= (E+bias)<<8;//shift the exp into the signedF's exp slot
    signedF |= (frac>>24);//shift the fraction bits back 24 places so first 8 remain
    if(signedF<<24 == 0)
      signedF += (1<<8);//add to th bias once
    return signedF;
  }//if it needs to be rounded (loses 1's not just solely trailing 0's)

  
  frac = (frac>>E);//shift that many times

  frac |= shiftedBits(number->whole);//take the shifted whole bits and slot them into the open frac spot

  signedF |= (E+bias)<<8;//shift the exp into the signedF's exp slot
  signedF |= (frac>>24);//shift the fraction bits back 24 places so first 8 remain

  return signedF; // Replace this Line with your Code!
}




/* toNumber - Converts a TINYSF Value into a Number Struct (whole and fraction parts)
 *  - number is managed by zuon, DO NOT FREE or re-Allocate number.
 *    - (It is already allocated)
 *  - Follow the project documentation for this function.
 * Return 0.
 */
int toNumber(Number_s *number, tinysf_s value) {
  if((value&0x00000fff)==0){
    number->fraction = 0;
    number->is_infinity = 0;
    number->is_negative = value&&0x0000f000;
    number->whole = 0;
    return 0;
  }//if value is 0
  number->is_infinity = (value&0x00000f00)==0x00000f00;
  number->is_negative = value&0x0000f000;
  number->is_nan = ((value&0x00000f00)==0x00000f00) && ((value&0x000000ff)!=0);
  if(number->is_nan==1){
    number->is_infinity = 0;
  }

  shiftMantissa(number, value);
  
  return 0;
}

/* opTinySF - Performs an operation on two tinySF values
 *  - Follow the project documentation for this function.
 * Return the resulting tinysf_s value or -1 if operator was invalid.
 */
tinysf_s opTinySF(char operator, tinysf_s val1, tinysf_s val2) {
  if(operator=='+')
    return addBits(val1, val2);
  if(operator=='-')
    return subBits(val1, val2);
  if(operator=='*')
    return multBits(val1, val2);
  return -1; // Replace this Line with your Code!
}

/* negateTINYSF - Negates a TINYSF Value.
 *  - Follow the project documentation for this function.
 * Return the resulting TINYSF Value
 */
tinysf_s negateTinySF(tinysf_s value) {
  return (value |= (1<<12));
}


//////////////////////////////////////helper functions///////////////////////////////////////////
tinysf_s decimalBits(tinysf_s fraction, int E){

  tinysf_s number = 0;
  number |= (E+7)<<8;
  while(E!=0){
    fraction = fraction<<1;
    E++;
  }//shift to the left until your fraction is in the right place

  //round the fraction bits before shifting them into the new tinysf_s

  if(roundableFrac(fraction)){//if it needs to be rounded then do so
    fraction = roundFrac(fraction);
  }//otherwise skip
  number += fraction;//shift the remaining fraction bits into position

  return number;
  
}//decodes decimal bits

/////////////////////////////////////////////////////////OPERATIONS FUNCTIONS///////////////////////////////////////////////////////////////////////////////

/////////////////adding
tinysf_s addBits(tinysf_s val1, tinysf_s val2){
  
  //special cases
  if( ( (val1&0x00000fff)>=0x00000f01 ) || ( (val2&0x00000fff)>=0x00000f01 ) ){//if at least one of the values is NAN
    return 0x00000f01;//return NAN
  }

  if( ( (val1&0x00001f00)==0x00000f00 ) && ( (val2&0x00001f00)==0x00000f00 ) ){//if both values are posiive infinity
    return 0x00000f00;//return positive infinity
  }

  if( ( (val1&0x00001f00)==0x00001f00 ) && ( (val2&0x00001f00)==0x00001f00 ) ){//if both values are negative infinity
    return 0x00001f00;//return negative infinity
  }

  

   if( ( ( (val1&0x00001f00)==0x00001f00 ) && ( (val2&0x00001f00)==0x00000f00 ) ) || ( ( (val1&0x00001f00)==0x00000f00 ) && ( (val2&0x00001f00)==0x00001f00 ) ) ){
    return 0x00000f01;//return NAN
  }//if one value is positive infinity and the other is negative

  if( ( (val1&0x00000fff) == (val2&0x00000fff) )  &&  ( (val1>>12) != (val2>>12) ) ){//if opposite signs of the same value are added
    return 0;//return 0
  }

  //we can assume if one value is infinity the other must be X because we have checked with the prior special cases for any other outcome
  if( ( (val1&0x00001f00)==0x00000f00 ) || ( (val2&0x00001f00)==0x00000f00 ) ){//if only one value is infinity and the other is a regular number
    return 0x00000f00;//return positive infinity
  }

  if( ( (val1&0x00001f00)==0x00001f00 ) || ( (val2&0x00001f00)==0x00001f00 ) ){//if only one value is negative infinity and the other is a regular number
    return 0x00001f00;//return negative infinity
  }
  
  if( ((val1==0) && (val2==0)) && ( (val1>>12) != (val2>>12) ) )//if both values equal 0 and have opposite signs
    return 0;//just return 0

  if( ((val1==0) && (val2==0)) && ( (val1>>12 == 0) && (val2>>12 == 0) ) )//if both values equal positive 0
    return 0;//just return 0
  
  if( ((val1==0) && (val2==0)) && ( (val1>>12 == 1) && (val2>>12 == 1) ) )//if both values equal negative 0
    return (1<<12);//just return -0

  if(val1==0&&val2!=0)//if adding a valid constant with 0: constant is the answer
    return val2;//val2

  if(val1!=0&&val2==0)//if adding a valid constant with 0: constant is the answer
    return val1;//val1
//////////////////////////////////////end of special cases///////////////////////////////////////////////////

if( ((val1>>12==0) && (val2>>12==0)) || ((val1>>12==1) && (val2>>12==1)) ){//if both values are positive or both negative
  int E1 = ((val1&0x00000f00)>>8)-7; //normalized: E=exp-bias
  int E2 = ((val2&0x00000f00)>>8)-7; //normalized: E=exp-bias
  tinysf_s sum = 0;

  tinysf_s tempVal1 = val1;
  tinysf_s tempVal2 = val2;

  val1 = toWhole(val1, E1); // 1.001 --> 1001.
  val2 = toWhole(val2, E2);

  E1 = getExp(E1, tempVal1);
  E2 = getExp(E2, tempVal2);

  if(E2>E1){
    val2 = val2 << (E2-E1);
    E2 = E1;
  }
  if(E1>E2){
    val1 = val1 << (E1-E2);
    E1 = E2;
  }


  sum = val1 + val2;
//subtract sum by msb then turn it into 
  tinysf_s frac = sum;
  while(frac!=1){
    frac = frac>>1;
    E2++;
  }//adjust E to the new sum

  frac = sum;//reset
  int counter = 0;
  while( (frac&(0x80000000)) !=0x80000000){
    frac = frac<<1;
    counter++;
  }//preparing to subtract the sum by the msb
  
  sum -= calc_Exp(2, 31-counter);
  tinysf_s tempSum = sum;
  counter = 0;//reset counter

  printf("here\n");
  while(tempSum!=0){
    tempSum = tempSum >> 1;
    counter++;
  }
  
  sum = sum << (8-(counter));//add the trailing zero's manually form the loop above
  printf("fraction: %d\n", sum);
  sum |= (E2+7)<<8;//exp
  if( (tempVal1>>12) ==1)
    return sum | (1<<12);
  return sum;
}//return the sum


    return -1;
}

////////////////subtracting
tinysf_s subBits(tinysf_s val1, tinysf_s val2){
  if( ( (val1&0x00000fff)>=0x00000f01 ) || ( (val2&0x00000fff)>=0x00000f01 ) )//if at least one of the values is NAN
    return 0x00000f01;//return NAN

  if( ( (val1&0x00001f00)==0x00001f00 ) && ( (val2&0x00001f00)==0x00001f00 ) ){//if both values are negative infinity
    return 0x00001f00;//return negative infinity
  }
  if( ( (val1&0x00000f00)==0x00000f00 ) && ( (val2&0x00000f00)==0x00000f00 ) ){//if both values are positive infinity
    return 0x00000f01;//return NAN
  }
  if( ( (val1&0x00001f00)==0x00001f00 ) && ( (val2&0x00000f00)==0x0000f00 ) ){//if first value is negative infinity and second is pos
    return 0x00001f00;//return negative infinity
  }
  if( ( (val1&0x00000f00)==0x00000f00 ) && ( (val2&0x00001f00)==0x0001f00 ) ){//if first value is positive infinity and second is neg
    return 0x00001f00;//return negative infinity
  }
  if(val1==val2 && (val1>0) && (val2>0))//two positive values
    return 0;//return 0

  return 0;
}


///////////////////multiplying
tinysf_s multBits(tinysf_s val1, tinysf_s val2){
  int E1 = ((val1&0x00000f00)>>8)-7; //normalized: E=exp-bias
  int E2 = ((val2&0x00000f00)>>8)-7; //normalized: E=exp-bias
  int E = 0;//exponent of both values

  unsigned int product = 0;//product of val1 and val2

  int S = ((val1&0x0000f000)>>12)^((val2&0x0000f000)>>12);//find the Sign bit

  if(val1==0 && val2==0)
    return 0;//both values are 0

  if( ( ((val2&0x00000f00)==0x00000f00) && (val1==0)) || ( ((val1&0x00000f00)==0x00000f00) && (val2==0) )) {//if either val1 or val2 is 0 and the
  //other is infinity, return NAN
    return 0x00000f01|(S<<12);//return NaN
  }
  if( (val2&0x00000f00)==0x00000f00 || (val1&0x00000f00)==0x00000f00 ){//if either values are infinity
    return 0x00000f00|(S<<12);//return infinity along with the sign bit
  }

  if( ( ( (val1==0) || (val1==0x00001000) ) && val2!=0 )  ||  ( val1!=0 && ( (val2==0) || (val2==0x00001000) ) ) )//if either value is +/- 0 return 0
    return (S<<12);//0 times anything but a special is 0 (done after checking for special times 0)
    
  tinysf_s tempVal1 = val1;
  tinysf_s tempVal2 = val2;
  
  val1 = (toWhole(val1, E1));
  val2 = (toWhole(val2, E2));


  E = getExp(E1, tempVal1) + getExp(E2, tempVal2);

  
  product = val1*val2;


  
  

  //E = shiftE(E, product);//account for our new E before proceeding with other operations (exp, rounding, etc.)

  //get the new e then round the fraction

  //final touches to the product bit
 
  E = shiftE(E, product);//the new E

  product = getFrac(product);

  
  if( ((E+7) >= 15) && (product>=0xc0000000)){//if the soon to be exp is >= 1111 and the fraction is .75 or greater
    return ( 0x00000f00 | (S<<12) );//return infinity with the sign bit
  }

  if(roundableFrac(product)){
    product = roundFrac(product);
    product |= (S<<12);//get the sign bit
    product |= (E+7)<<8;//exp
    return product;
  }

  product = product>>24;//put fracs in position - round later if necessary
  product |= (S<<12);//get the sign bit
  product |= (E+7)<<8;//exp

  return product;
}
/////////////////////////////////////////////////////////OPERATIONS FUNCTIONS///////////////////////////////////////////////////////////////////////////////

tinysf_s addE(int E1, int E2, tinysf_s value){//fix the Exponents to get them equal to each other (for adding mainly)

}

int shiftE(int E, tinysf_s value){//fix the E value after multiplying bits
  tinysf_s tempVal = value;
  
  //shift value all the way to the left to make operation easier
  while(tempVal!=1){
    tempVal = tempVal>>1;//shift the value to the right
    E++;//setting our E back into place
  }//until the 1. of the mantissa is left
  return E;//our new E
}

int roundableFrac(tinysf_s value){
  return (value&0x00ffffff)!=0;//trailing values after the 8th spot (rounding mark)
}

tinysf_s roundFrac(tinysf_s value){
    tinysf_s roundedFrac = value;

  if(((roundedFrac)&(1<<23))==0){//if it's less than halfway to the next bit value
    roundedFrac = (roundedFrac>>24);//shift fraction bits all the way back without rounding
    return roundedFrac;
  }//round down

  if( ((roundedFrac)&(1<<23))!=0 && ((roundedFrac)&(0x007fffff))!=0  ){//if it's more than than halfway to the next bit value
    roundedFrac = (roundedFrac>>24);//shift fraction bits all the way back
    roundedFrac += 1<<0;//add one to round up
    return roundedFrac;
  }//round up

  if( ((roundedFrac)&(1<<23))!=0 && ((roundedFrac)&(0x007fffff))==0  ){//if it's exactly halfway between two bit values
    if((roundedFrac&(1<<24))!=0){//if the last fraction bit slot is 1 then round up (even rounding)
      roundedFrac += (1<<24);
      roundedFrac = roundedFrac>>24;
      return roundedFrac;
    }//round up

    if((roundedFrac&(1<<24))==0){//if the last fraction bit slot is 0 then round down (even rounding)
      roundedFrac = roundedFrac>>24;
      return roundedFrac;
    }
  }//halfway point
}

tinysf_s getFrac(tinysf_s value){
  tinysf_s bit = value>>31;
  printf("Value: %d\n", value<<1);
  while(bit==0){
    value = value<<1;
    bit = value>>31;
  }
  printf("Value: %x\n", value<<1 );
  return (value<<1);//shift once more to get all the frac bits alone and kick out the 1. bit
}

int getExp(int E,  tinysf_s value){
  unsigned int frac = (value&0x000000ff);
  int num0 = 0;//number of trailing zero's in frac bits

  tinysf_s number = 0x00000001;
  while( ((frac&0x00000001)==0) && (num0!=8) ){//while there are still trailing zero's or we hit the decimal point
    frac = frac>>1;//shift to the remove trailing zero
    ++num0;//decrement the number of trailing zero's in frac bits
  }


  return E-(8-num0);
}

tinysf_s toWhole(tinysf_s value, int E){
  unsigned int frac = (value&0x000000ff);
  int num0 = 0;//number of trailing zero's in frac bits
  int shifter = 0;
  tinysf_s number = 0x00000001;
  while( ((frac&0x00000001)==0) && (num0!=8) ){//while there are still trailing zero's or we hit the decimal point
    frac = frac>>1;//shift to the remove trailing zero
    ++num0;//decrement the number of trailing zero's in frac bits
  }
  shifter = (8-num0);//how many times we shift to look for frac bits
  number = number<<shifter;

  

  return number+frac;
}//shift bits to hold whole number and fraction with spacing on either side

void shiftMantissa(Number_s *oldNum, tinysf_s value){
  Number_s *number = oldNum;
  int bias = 7;//constant always working with 32 bits and 4 bits of exp
  int E = ((value&0x00000f00)>>8)-bias; //normalized: E=exp-bias
  unsigned int M = 0x00010000;//mantissa
  M |= (value&0x000000ff)<<8;//shift the fraction bits next to the 1. in the mantissa


  if(E==0){//if exponent is 0
    number->fraction = ((value&0x000000ff)<<24);
    number->whole = 0x00000001;
    oldNum = number;
    return;
  }//just return as is

  if(E<0){//if exponent is negative
    while(E!=0){
      M = M>>1;
      E++;
    }
    number->fraction = (M&0x0000ffff)<<16;
    number->whole = (M&0xffff0000)>>16;
    oldNum = number;
    return;
  }

  if(E>0){//if exponent is positive
    while(E!=0){
      M = M<<1;
      E--;
    }
    number->fraction = (M&0x0000ffff)<<16;
    number->whole = (M&0xffff0000)>>16;
    oldNum = number;
    return;
  }

}


tinysf_s roundNum(Number_s *number, int E){
  tinysf_s roundedFrac = number->fraction;
  roundedFrac = roundedFrac>>E;//make space for whole numbers later
  if(((roundedFrac)&(1<<23))==0){//if it's less than halfway to the next bit value
    roundedFrac |= shiftedBits(number->whole);//take the shifted whole bits and slot them into the open frac spot
    
    return roundedFrac;
  }//round down

  if( ( (roundedFrac & (1<<23)) != 0) && ((roundedFrac & (0x004fffff)) != 0 ) ){//if it's more than than halfway to the next bit value

    roundedFrac += shiftedBits(number->whole);//take the shifted whole bits and slot them into the open frac spot
    roundedFrac += (1<<24);//round up by adding 1 to the 8th bit
    return roundedFrac;
  }//round up

  if( ((roundedFrac)&(1<<23))!=0 && ((roundedFrac)&(0x004))==0  ){//if it's exactly halfway between two bit values

    roundedFrac |= shiftedBits(number->whole);//take the shifted whole bits and slot them into the open frac spot

    if((roundedFrac&(1<<24))!=0){//if the last fraction bit slot is 1 then round up (even rounding)
      roundedFrac += (1<<24);
      return roundedFrac;
    }//round up

    if((roundedFrac&(1<<24))==0){//if the last fraction bit slot is 0 then round down (even rounding)
      
      return roundedFrac;
    }
  }//halfway point

  return -1;
}

int roundable(Number_s *number, int E){
  unsigned int tempNum = number->fraction;//binary from right to left instead of left to right
  tempNum = (tempNum>>E);
  return (tempNum&&0x00ffffff);//if there are ones after the 24th bit place then return true
  //true=(it loses values when being shifted E times to make space for the shifted whole num)
}

tinysf_s shiftedBits(unsigned int number){
  unsigned int tempNum = number;
  tinysf_s newNum = 0;
    while(tempNum!=1){
      newNum = (newNum>>1);
      newNum |= ((tempNum & 1)<<31);
      tempNum = (tempNum>>1);
    }
  return newNum;
}//shift the whole number into the fraction

int calc_Exp(int base, int exponent){
  int value = 1;
  int counter = 0;
  while(counter<exponent){
    counter++;
    value *= base;
  }
  return value;
}//calculate exponent

int calc_E(Number_s *number){
    int E = 0;
    int frac = number->fraction;
    if(number->whole==0){
      while( ((frac)&(1<<31)) == 0 ){
        E--;
        frac = frac<<1;
      }
      return --E;
    }

    unsigned int wholeNum = number->whole;

    while(wholeNum!=1){
      wholeNum = (wholeNum>>1);
      E++;
    }
    return E;
}//calculate E
/////////////////////////////helper functions////////////////////////////