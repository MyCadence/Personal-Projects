#-------------------------------------------------------------------------------
# Name: Arron Birhanu
# Programming Assignment 3
# Due Date: 10/4/21 12:00pm
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

def rest_time(miles_driven, brake_count):
    hours_rest = 0 #intialized the hours the slothmobile rests
    while miles_driven>0: #loop until there are no miles left
        miles_driven = miles_driven//brake_count#floor division (rounding down)
        if (miles_driven%2 == 0 and miles_driven>1): #if even,
        #increment hours needed to rest
            hours_rest += 1
        else:
            break
    return hours_rest #total hours needed for rest

def which_speedster(feature_values):
    speed_sum = 0 #sum placeholders initialize at 0
    speed_product = 1.0 #product placeholders intialize at 1
    for i in feature_values: #i loops through the list inputted
        if type(i) is float: #float values from the list multiply
            speed_product *= i
        if type(i) is int: #int values from the list summate
            speed_sum += i
    speedster_id = int(speed_product/speed_sum)#product divide by sum depending
    #on the elements' types (float or int)
    return speedster_id #return the id of the speedster

def lifespan(fingers_list, num_toes):
    life_years = 0
    for i in fingers_list:#nested loop to evaluate each element in fingers_list
        while i > 0:#stop when if i ever reaches 0
            if i%num_toes == 0:
                life_years += 1#increment life (in years) 1 if it's a multiple
            i -= 1#if it made it through, check for the next multiple
    return life_years

def wiz_level(spell_list, owl_lower_bound, owl_upper_bound):
    level = 0
    unforgivable_curses = ["Crucio", "Imperio", "Avada_Kedavra"]
    dangerous_curses = ["Confringo", "Sectumsempra", "Fiendfyre"]
    wizarding_level = ""
    mega_spell = ""
    
    for c in spell_list:
        mega_spell += c
        
    for i in spell_list:
        curse = False
        for a in unforgivable_curses:
            if i == a:
                level += 0
                curse = True
                break
        for b in dangerous_curses:
            if i == b and level<owl_lower_bound:
                level += 7
                curse = True
                break
            if i == b and level>=owl_lower_bound:
                level += 5
                curse = True
                break
        if curse==False: level += 10        
        
        
    if level<owl_lower_bound:
        wizarding_level = "Below O.W.L."
    if level>=owl_lower_bound and level<=owl_upper_bound:
        wizarding_level = "On O.W.L."
    if level>owl_upper_bound:
        wizarding_level = "Beyond O.W.L."
    return wizarding_level + " " + "Mega Spell:" + " " + mega_spell
    
