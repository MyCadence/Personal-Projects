#-------------------------------------------------------------------------------
# Name: Arron Birhanu
# Programming Assignment 8
# Due Date: 11/15/21 12:00pm
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

def locateEmergency(hospFile, polFile):
    fileEditor = open(hospFile)
    fileEditor.seek(84)
    lines = fileEditor.readlines()
    mydict = {}
    hospital = []
    description = []
    jurisdiction = []
    web_address = []
    street_number = []
    street_name = []
    city = []
    zipcode_hosp = []
    phone_hosp = []
    station_name = []
    station_address = []
    zipcode_police = []
    phone_police = []
    final_address = []
    new_lines=[]
    police_lines=[]
    for line in lines:
        new_lines.append(line.split(","))
        new_lines[-1][-1] = new_lines[-1][-1][:-1]
    for i in new_lines:
        if i[0]=="HOSP":
            hospital.append('Hospital')
        elif i[0]=="UC":
            hospital.append('Urgent Care')
        description.append(i[1])
        jurisdiction.append(i[2])
        web_address.append(i[3])
        street_number.append(i[4])
        street_name.append(i[5])
        city.append(i[6])
        zipcode_hosp.append(i[7])
        phone_hosp.append(i[8])
        final_address.append(i[4]+' '+i[5])
    fileEditor.close()
    fileEditor = open(polFile)
    fileEditor.seek(47)
    lines = fileEditor.readlines()
    fileEditor.close()
    for line in lines:
        police_lines.append(line.split(","))
        police_lines[-1][-1] = police_lines[-1][-1][:-1]

    for i in police_lines:
        station_name.append(i[0])
        station_address.append(i[1])
        zipcode_police.append(i[2])
        phone_police.append(i[3])
    zipcode = zipcode_hosp+zipcode_police
    finalzip=[]
    final_lines = new_lines+police_lines
    for i in zipcode:
        if i in finalzip:
            continue
        else:
            finalzip.append(i)
    for b in finalzip:
        mydict[b]=[]
    for key in mydict.keys():
        for i in range(0, len(final_lines)):
            if len(final_lines[i])==9 and final_lines[i][7]==key:
                print(True)
                mydict[key].append([hospital[i],description[i],
                            web_address[i],final_address[i], phone_hosp[i]])
            elif len(final_lines[i])==4 and final_lines[i][2]==key:
                print(True)
                mydict[key].append(['Police Station', station_name[i],
                station_address[i], phone_police[i]])
            print(mydict)
    return mydict
locateEmergency('hosp1.csv','police1.csv')