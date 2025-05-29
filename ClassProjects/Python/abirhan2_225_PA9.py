#-------------------------------------------------------------------------------
# Name: Arron Birhanu
# Programming Assignment 9
# Due Date: 11/23/21 12:00pm
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
class Detective:
    def __init__(self, name, cases, solved_cases):#instance object is created
        self.num_cases = len(cases) #len() of list to get the number
        #of cases
        self.num_solved = len(solved_cases)
        #if there are more solved cases than total casesraise dataerror
        if self.num_solved>self.num_cases:
            raise DataError('Solved cases cannot be more than total cases!')
            
        self.name = name #set attributes
        self.cases = cases
        self.solved_cases = solved_cases    
        
        
    def __str__(self): #print statement for each instance object
        return 'Detective {}: total cases {}, solved {}, failed {}.'.format(
            self.name, self.num_cases, self.num_solved,
            self.num_cases-self.num_solved)
            
    def num_failed_cases(self): #returns an int of num of failed cases
        return len(self.cases)-len(self.solved_cases) #remaining unsolved
        #the len() of that is the num of failed cases
        
    def add_new_cases(self, new_cases, new_solved):#updating detective info
        if len(new_solved)>len(new_cases): #if solved surpasses total cases
        #raise a dataerror
            raise DataError('New solved cases cannot be more than new cases!')
        self.new_cases=new_cases #set attributes to the new instance object
        self.new_solved=new_solved
        self.num_cases+=len(self.new_cases) #updating amount (int) of cases
        self.cases+=self.new_cases #udpating list of (str) cases
        lst=[] #list holder
        for i in self.new_solved: #turning the positions into the actual
        #case names by looping through the list that holds the positions
            lst.append(self.new_cases[i-1]) #adding them to our holder
        self.new_solved.clear()#take out the positions
        self.new_solved=lst[:]#put in the names using the holder
        self.solved_cases+=self.new_solved#total solved is updated by the new
        self.num_solved+=len(self.new_solved) #including the amount (int)
        
    def failed_cases(self): #returns the cases that aren't solved
        fail_cases = []#list holder that will be used to return
        for i in self.cases:#loops through the now updated cases
            if i not in self.solved_cases: #if you can't find the name (i)
            #of  case in the list of solved cases, then it's a failed case
                fail_cases.append(i) #add it to the failed list
        return fail_cases #return after loop is complete
        
class DataError(Exception):#responsible for print returns in case of dataerror
    def __init__(self,msg): #instance object is also needed in this class
        self.msg = msg#each instance that runs into data error is accounted for
    def __str__(self):
        return self.msg #print return for each instance with a dataerror
       