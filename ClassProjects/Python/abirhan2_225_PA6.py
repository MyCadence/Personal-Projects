def finalGrade(reading=[[0]],labs=[[0]],PAs=[[0]],mt1=100,mt2=100,fe=250):
    reading.append(0)
    labs.append(0)
    PAs.append(0)#avoid passing the 80 char limit when defining list parameter
    grade = ''#letter grade
    while reading[1]>0:#if there is an option to drop, >0, do it until =0
        reading[0].remove(min(reading[0]))#remove minimum from list
        reading[1] -=1 #contain the iteration since it's a while
    reading_weight = sum(reading[0])/len(reading[0]) #average calc
    reading_weight = round(reading_weight*.5,2) #keep weighted % and round
    
    while labs[1]>0: #repeat from reading[1]>0 loop
        labs[0].remove(min(labs[0]))
        labs[1] -=1
    lab_weight = sum(labs[0])/len(labs[0])
    lab_weight = round(lab_weight,2)
    
    while PAs[1]>0: #repeat from reading[1]>0 loop
        PAs[0].remove(min(PAs[0]))
        PAs[1] -=1
    PA_weight = sum(PAs[0])/len(PAs[0])
    PA_weight = round(PA_weight/1.25,2)
    
    mt1 = mt1/10.0 #mid terms and final divide by 10 round by 2
    round(mt1, 2)
    
    mt2 = mt2/10.0
    round(mt2, 2)
    
    fe = fe/10.0
    round(fe, 2)
    
    total_score = reading_weight+lab_weight+PA_weight+mt1+mt2+fe #total
    
    if total_score>=98: #conditional to find letter grade
        grade = 'A+'
    elif 92<=total_score<98:
        grade = 'A'
    elif 90<=total_score<92:
        grade = 'A-'
    elif 88<=total_score<90:
        grade = 'B+'
    elif 82<=total_score<88:
        grade = 'B'
    elif 80<=total_score<82:
        grade = 'B-'
    elif 78<=total_score<80:
        grade = 'C+'
    elif 72<=total_score<78:
        grade = 'C'
    elif 70<=total_score<72:
        grade = 'C-'
    elif 60<=total_score<70:
        grade = 'D'
    else:
        grade = 'F'
    final_score = (round(total_score,0),grade) #turn into tuple, round total
    return final_score #return tuple
    
def wreadings(grades=[0], drop=0):
#weighted readings calculated using *.5
#repeat reading while loop commands from final grade function
    weighted = 0
    while drop>0:
        grades.remove(min(grades))
        drop -=1
    weighted = sum(grades)/len(grades)
    return round(weighted*.5, 2)
    
def wlabs(grades=[0], drop=0):
#weighted labs calculated using *1
#repeat labs while loop commands from final grade function
    minimum = grades[0]
    hold_list = []
    length = 0
    grades_sum = 0
    appear = 0
    while drop!=0:
        minimum = 200
        for i in grades:
            if i < minimum and i not in hold_list:
                minimum = i
            else:
                appear = 1
                continue
        hold_list.append(minimum)
        drop-=1
    difference = []
    for i in grades:
        if i in hold_list and appear==0:
            continue
        else:
            difference.append(i)
            
    weighted=sum(difference)/len(difference)
    return round(weighted, 2)
    
def wPAs(grades=[0], drop=0):
#weighted PA calculated using *1.25
#repeat PAs while loop commands from final grade function
    minimum = grades[0]
    hold_list = []
    length = 0
    grades_sum = 0
    while drop!=0:
        for i in grades:
            if i < minimum:
                minimum = i
        hold_list.append(minimum)
        drop-=1
    for b in grades:
        if b not in hold_list:
            length+=1
            grades_sum += b
        else:
            continue

    weighted=grades_sum/length
    return round(weighted/1.25, 2)
    
def wexams(mt1=100,mt2=100,fe=250):
    #divide by 10 round by 2 and incorporate all ints into a list
    exam_list = []
    mt1 = mt1/10.0
    round(mt1, 2)
    exam_list.append(mt1)
    mt2 = mt2/10.0
    round(mt2, 2)
    exam_list.append(mt2)
    fe = fe/10.0
    round(fe, 2)
    exam_list.append(fe)
    return exam_list #return as a list