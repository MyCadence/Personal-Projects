#-------------------------------------------------------------------------------
# Name: Arron Birhanu
# Programming Assignment 1
# Due Date: 09/06/21 12:00pm
#-------------------------------------------------------------------------------
# Honor Code Statement: I received no assistance on this assignment that
# violates the ethical guidelines set forth by professor and class syllabus.
#-------------------------------------------------------------------------------
# References: (list resources used - remember, assignments are individual effort!)
#-------------------------------------------------------------------------------
# Comments and assumptions: A note to the grader as to any problems or 
# uncompleted aspects of the assignment, as well as any assumptions about the
# meaning of the specification.
#-------------------------------------------------------------------------------
# NOTE: width of source code should be <=80 characters to be readable on-screen.
#2345678901234567890123456789012345678901234567890123456789012345678901234567890
# 10 20 30 40 50 60 70 80
#-------------------------------------------------------------------------------
def status(cell_digit): #final digit of the phone number, boolean function
    if cell_digit == 0 or cell_digit == 2 or cell_digit == 4:
        digit = True
    elif cell_digit == 6 or cell_digit == 8:
        digit = True
    else:
        digit = False
    return digit
    
def adjective(lastname_fstletter): #description based off
#of the individual's last name, string function.
#a dictionary would've been perfect for this function!
    if lastname_fstletter == 'A':
        return 'awesome'
    if lastname_fstletter == 'B':
        return 'shocking'
    if lastname_fstletter == 'C':
        return 'hilarious'
    if lastname_fstletter == 'D':
        return 'fascinating'
    if lastname_fstletter == 'E':
        return 'marvelous'
    if lastname_fstletter == 'F':
        return 'unbelievable'
    if lastname_fstletter == 'G':
        return 'funny'
    if lastname_fstletter == 'H':
        return 'epic'
    if lastname_fstletter == 'I':
        return 'thrilling'
    if lastname_fstletter == 'J':
        return 'wonderful'
    if lastname_fstletter == 'K':
        return 'dramatic'
    if lastname_fstletter == 'L':
        return 'intriguing'
    if lastname_fstletter == 'M':
        return 'courageous'
    if lastname_fstletter == 'N':
        return 'beautiful'
    if lastname_fstletter == 'O':
        return 'bracing'
    if lastname_fstletter == 'P':
        return 'lively'
    if lastname_fstletter == 'Q':
        return 'dangerous'
    if lastname_fstletter == 'R':
        return 'impressive'
    if lastname_fstletter == 'S':
        return 'astonishing'
    if lastname_fstletter == 'T':
        return 'interesting'
    if lastname_fstletter == 'U':
        return 'unexpected'
    if lastname_fstletter == 'V':
        return 'surprising'
    if lastname_fstletter == 'W':
        return 'lovely'
    if lastname_fstletter == 'X':
        return 'electrifying'
    if lastname_fstletter == 'Y':
        return 'commoving'
    if lastname_fstletter == 'Z':
        return 'overwhelming'
        
def subject(birth_month): #month of birth, determines subject of the movie,
#string function
    if birth_month == "Jan":
        return "biography"
    if birth_month == "Feb":
        return "history"
    if birth_month == "Mar":
        return "legend"
    if birth_month == "Apr":
        return "life"
    if birth_month == "May":
        return "anecdote"
    if birth_month == "Jun":
        return "revenge"
    if birth_month == "Jul":
        return "mission"
    if birth_month == "Aug":
        return "existence"
    if birth_month == "Sep":
        return "battle"
    if birth_month == "Oct":
        return "chronicle"
    if birth_month == "Nov":
        return "combat"
    if birth_month == "Dec":
        return "fairy tale"
        
def complement(cell_digit): #description of the character in the movie
#based off of the user's last phone number
    if cell_digit == 0:
        return "of an adventurer"
    if cell_digit == 1:
        return "of a warrior"
    if cell_digit == 2:
        return "of a genius"
    if cell_digit == 3:
        return "of a movie star"
    if cell_digit == 4:
        return "of a hero"
    if cell_digit == 5:
        return "of a scientific"
    if cell_digit == 6:
        return "of a dreamer"
    if cell_digit == 7:
        return "of a cowboy"
    if cell_digit == 8:
        return "of a jedi"
    if cell_digit == 9:
        return "of an ogre"
        
def months(cell_digit): #mathematical conversion
    days = (cell_digit)**((cell_digit%2)+1) *(cell_digit)
    if days==0:
        return 1
    month_count = int(days/30) + int(days%30>0) #math ceiling wasn't permitted,
    #so I used the true and false statement to round up all my values, and cut
    #off the decimal values
    return month_count
    
def myMovieLife(lastname_fstletter, birth_month, cell_digit):
    #repeated the conversion portion of months to ensure I didn't receive
    #return errors
    days = int((cell_digit)**((cell_digit%2)+1) *(cell_digit))
    month_count = int(days/30) + int(days%30>0)
    if days==0: #in the case of an outlier answer, there is a response for 0
       return "The " + str(status(cell_digit))+" "+\
            adjective(lastname_fstletter)+" "+\
            subject(birth_month)+" "+complement(cell_digit)+" in "+\
            str(months(cell_digit))+ " month"
    if month_count==1: #deals with plural grammar in the return statements
       return "The " + str(status(cell_digit))+" "+\
            adjective(lastname_fstletter)+" "+\
            subject(birth_month)+" "+complement(cell_digit)+" in "+\
            str(months(cell_digit))+ " month"
    if days>1: #most of the return statements are handled by this conditional
        return "The " + str(status(cell_digit))+" "+\
            adjective(lastname_fstletter)+" "+\
            subject(birth_month)+" "+complement(cell_digit)+" in "+\
            str(months(cell_digit)) + " months"