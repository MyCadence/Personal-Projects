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
def swap_text(text):
    substitute = list(text) #to mutate the string, change it into a list
    sub = "" #the string returning
    for i in range(0, len(substitute)-1, 2): #don't iterate to the last pos
    #will bring back error since we are looping through pairs (by 2)
        holder = substitute[i] #substitute the characters by pairs
        substitute[i] = substitute[i+1]
        substitute[i+1] = holder
    for a in substitute:
        sub += a #use for loop to change back into a string
    return sub

def which_day(numbers):
    sum1 = 0 #initializing variables
    sum2 = 0
    day = ""
    for i in range(0, len(numbers)): #iterate through the int list
        if i%2 == 0: #sum of even positions in the list
            sum1 += numbers[i]
        else:
            sum2 += numbers[i] #sum of odd positions in the list
    sum_diff = sum1-sum2
    if sum_diff < 0: #absolute value conditional
        sum_diff = sum_diff*-1
    if sum_diff%7 == 0:#remainder equals day of the week
        day = "Sunday"
    if sum_diff%7 == 1:
        day = "Monday"
    if sum_diff%7 == 2:
        day = "Tuesday"
    if sum_diff%7 == 3:
        day = "Wednesday"
    if sum_diff%7 == 4:
        day = "Thursday"
    if sum_diff%7 == 5:
        day = "Friday"
    if sum_diff%7 == 6:
        day = "Saturday"
    return day

def delete_duplicates(items):
    items_cut = [] #add the updated list into that
    for i in items:#using a for loop to check for duplicates
        if i not in items_cut:#checking for duplicates
            items_cut.append(i)#add to the updated list
    return items_cut

def final_guests(draft_guests, new_guest):
    draft_guests.append(new_guest) #add the guest to the original list
    combined_guests = sorted(draft_guests)#alphabetical order
    return combined_guests
