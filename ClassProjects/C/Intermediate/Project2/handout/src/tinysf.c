/* This is the only file you will be editing.
 * - Copyright of Starter Code: Prof. Kevin Andrea, George Mason University.  All Rights Reserved
 * - Copyright of Student Code: You!  
 * - Restrictions on Student Code: Do not post your code on any public site (eg. Github).
 * -- Feel free to post your code on a PRIVATE Github and give interviewers access to it.
 * -- You are liable for the protection of your code from others.
 * - Date: Jan 2023
 */

/* Fill in your Name and GNumber in the following two comment fields
 * Name:
 * GNumber:
 */

#include <stdio.h>
#include <stdlib.h>
#include "common_structs.h"
#include "common_definitions.h"
#include "common_functions.h"
#include "tinysf.h"

// Feel free to add many Helper Functions, Consts, and Definitions!

// ----------Public API Functions (write these!)-------------------

/* toTinySF - Converts a Number Struct (whole and fraction parts) into a TINYSF Value
 *  - number is managed by zuon, DO NOT FREE number.
 *  - Follow the project documentation for this function.
 * Return the TINYSF Value.
 */
tinysf_s toTinySF(Number_s *number) {
  return -1; // Replace this Line with your Code!
}

/* toNumber - Converts a TINYSF Value into a Number Struct (whole and fraction parts)
 *  - number is managed by zuon, DO NOT FREE or re-Allocate number.
 *    - (It is already allocated)
 *  - Follow the project documentation for this function.
 * Return 0.
 */
int toNumber(Number_s *number, tinysf_s value) {
  return -1; // Replace this Line with your Code!
}

/* opTinySF - Performs an operation on two tinySF values
 *  - Follow the project documentation for this function.
 * Return the resulting tinysf_s value or -1 if operator was invalid.
 */
tinysf_s opTinySF(char operator, tinysf_s val1, tinysf_s val2) {
  return -1; // Replace this Line with your Code!
}

/* negateTINYSF - Negates a TINYSF Value.
 *  - Follow the project documentation for this function.
 * Return the resulting TINYSF Value
 */
tinysf_s negateTinySF(tinysf_s value) {
  return -1; // Replace this Line with your Code!
}
