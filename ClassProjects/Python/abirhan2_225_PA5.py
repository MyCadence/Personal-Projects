#-------------------------------------------------------------------------------
# Name: Arron Birhanu
# Programming Assignment 5
# Due Date: 10/18/21 12:00pm
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
def group_together(players, team):
    storage_list = list(players) #deep copy players
    players.clear()#empty out the list
    group_together=[]#list within a list holder
    for i in range(0,len(storage_list),team):#repeat till players is full    
        group_together.append(storage_list[i:team])#interval
        #between start to end of each teams' inner list
        team+=team#must update the maximum interval
        players += list(group_together)#add the list into players
        group_together.clear()#next list


def monitor_bets(playerInfo, bets):
    bet_total = 0#placeholder for the total bet amount
    for i in playerInfo:#nested for loop
        for b in bets:#checks each other for similar values
            if b[0]==i[1]:#at these indexes
                bet_total+=b[1]#if true then add to the total bet
        if bet_total==0:
            continue #in the case of the value being an outlier (0), continue
        else:
            i.append(round(bet_total,2))#must round just in case
        bet_total = 0 #reset the total to find the next index's total bet

def compress_info(playerTrack):
    for i in range(0, len(playerTrack)):#nested loop
        for x in range(0,len(playerTrack)):#comparing the values within
        #the list to one another
            if playerTrack[x][0]==playerTrack[i][0] and x!=i:
            #conditional does not include the values comparing themseleves at
            #the same index and must be equal in their first index
                playerTrack[i][1]+=playerTrack[x][1]#add their second index
                playerTrack[x][0]=0#nullify the list's first index to idenfity
                #later in a for loop

    storage_list = list(playerTrack)#substitute list for playerTrack
    playerTrack.clear()#clear to update it later on in the for loop
    for a in range(0,len(storage_list)):#loop to identify the first index
        if storage_list[a][0]>0: 
            playerTrack.append(storage_list[a])#if it passes, then put it
            #in the updated list
    for b in playerTrack:
        b[1] = round(b[1],2)

def set_of_games(games, searchNum):
    name_games = {''}#placeholder for the game names found
    for key, values in games.items():
        if values[1] >= searchNum: #conditional to find whether
        #the dic value is greater than the players who won the game serachNum
            name_games.add(values[0]) #if true add those values
    name_games.pop()#remove the empty quotes to avoid test case error
    return name_games#return the set
