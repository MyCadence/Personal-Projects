#-------------------------------------------------------------------------------
# Name: Arron Birhanu
# Programming Assignment 7
# Due Date: 11/08/21 12:00pm
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

def even_divide_count(num1,num2,counter=0):#store a default parameter to count
    if(num1//num2)==0:
        return counter # in case it manages to reach all the way to 0
    if(num1//num2)%2==0: #if even
        counter += 1 # if it results in an even after dividing, count + 1
        return even_divide_count(int(num1//num2), num2, counter) #repeat
    elif(num1//num2)%2!=0: #if it results in an odd, return the count
        return counter 
    return counter #formality

def add_sum(xs, ys):#paremeters
    if len(xs)==0:#if we reach the end of xs
        return ys #return ys our final list
    n = sum(xs[0]) #n holds the sum of each element within xs
    ys.append(n) #incoporate n - the sum of element xs - inside ys list
    return add_sum(xs[1:],ys) # repeat and move on to the next index in xs
    
def swap_chars(str1, str2, n, count=1): # default counter paremeter
    if (len(str1)-(n*count))<0: #if we've reached the end of string 1
        return (str1,str2) #exit with a tuple
    list1 = list(str1) #make list holders to edit string 1 and 2
    list2 = list(str2)
    holder = list(str1)   #third list accounts for list1 alteration
    list1[(n*count)-1] = list2[(n*count)-1] #exchange values, -1 for index
    #*count determines which nth in list1 we put list2's element
    list2[(n*count)-1] = holder[(n*count)-1]#third list represents list 1
    str1 = "".join(list1)#combine the edited lists with.join
    str2 = "".join(list2)   
    count +=1 #increment in the case of a recursion
    return swap_chars(str1,str2, n, count) #repeat

def modify_order(some_list, mod_list=[], y=0, x=-1): #default para(newlst,x,y)
    new_list=[]#empty holder
    if len(mod_list)==len(some_list): #if the new list reaches len of original
        new_list=list(mod_list) #holds the newly made list
        mod_list*=0 #empty list to prepare for another possible function call
        return new_list#returns modified list using a holder
    mod_list.append(some_list[y])#adds from index 0 to the end
    if len(mod_list)==len(some_list):#after [y] is appended, check to see if
    #if modified_list len has reached some_list len
        new_list=list(mod_list) #same as previous conditonal case block
        mod_list*=0
        return new_list
    mod_list.append(some_list[x])#if both conditionals passed, it's safe to
    #append the values of some_list to mod list from index -1 to the beginning
    y+=1 #increment the next index values we'll obtain
    x-=1
    return modify_order(some_list,mod_list,y,x)#repeat
    
def subtract_inc(num1, num2, num3=None, turn=False, numtotal=0,num1lost=0):
#turn determines whether num2 is finished interacting with num1
#numtotal is total recursions and num1lost is responsible for incrementing
#all lost values of num1 to be able to reset num1 before interacting with num3
    if num1-num2>=0 and turn==False: #if not negative and it's num2's turn
        numtotal+=1#increment num1
        num1-=num2 #num1 - num2
        num1lost+=num2#num1lost increments to prepare for the num1 reset later
        num2+=1#increment num2
        return subtract_inc(num1,num2,num3,turn,numtotal,num1lost)#repeat
    elif num1-num2<0 and turn==False:#this time, we use this conditional & turn
    #to ask num1 to interact with num2
        num1+=num1lost#HERE reset num1 to its original value
        num1lost=0 #no longer necessary to hold num1's lost value so we reset
        turn=True #switch the interaction from num2 to num3
    if num3!=None: #since default was given as none, we take that into account
    #before moving forward with the num3 interaction
        if num1-num3>=0: #if not negative, do same thing as we did for num2
            numtotal+=1
            num1-=num3
            num1lost+=num3
            num3+=1
            return subtract_inc(num1,num2,num3,turn,numtotal,num1lost)

    return numtotal #if all those conditionals have been performed, exit
    #with the total counts - numtotal