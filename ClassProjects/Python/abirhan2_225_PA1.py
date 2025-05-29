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

#greeting message - from computer to human
print("Welcome to the Sims4 Builder Helper!")

#Information questionnaire - storing variables necessary
#for formula calculations later

lot_width = float(input("What is the width of your lot in Sim units? "))


lot_length = float(input("What is the length of your lot in Sim units? "))


item = input("What is your specific item? ")


item_width = float(input("What is the width of your specific item in" 
" Sim units? "))


item_length = float(input("What is the length of your specific item"
" in Sim units? "))


#utilize the information outputted and incorporate into sim life formulas
item_area_sim = item_width*item_length
lot_area_sim = lot_width*lot_length

#utilize the information outputted and incorporate into real life formulas
item_length_irl = item_length*4
item_width_irl = item_width*4
lot_length_irl = lot_length*4
lot_width_irl = lot_width*4

#by far the hardest formula yet, must find the limit on both length and width
#how many lengths of the item can evenly go onto the lot length
#how many widths of the item can evenly go onto the lot width
#both collected in sims units, feet work just as well
#product of the two will find the greatest area achieveable....
#simply put, the greatest number of items that can evenly fit inside the lot
#!!!written out in the case of plagiarism accusations!!!
items_fit = (int(lot_width/item_width)*int(lot_length/item_length))


item_area_irl = item_length_irl*item_width_irl
lot_area_sqft = lot_width_irl*lot_length_irl

#percentage of the lot covered by the inputted item/lot values
#covered without decimal cutting
percentage_covered_raw = 100*(items_fit*(item_width_irl*item_length_irl))
percentage_covered_raw = percentage_covered_raw/lot_area_sqft
#covered with decimal cutting
percentage_covered = 100*(items_fit*(item_width_irl*item_length_irl))
percentage_covered = int(percentage_covered/lot_area_sqft)

#used percentage_covered_raw instead of percentage_covered because of decimal
#interference with math operations (leaves with 1% +/- from the answer)
percentage_uncovered =  int(100-percentage_covered_raw)
#simply put, using covered to help store the value for uncovered



#tried multiple methods for the refrigerator example to
#come up for units uncovered
#settled with this (will explain):
#math: 4ft * 6ft = 24sqft   24sqft*13200refrigerators = 316,800sqft total
#320,000sqft of lot - 316,800sqft total refrigerators = 3200sqft leftover
#3200sqft leftover/16units^2 = 200  <<<<conversion method
#to convert width or length of sims to ft, multiply by 4
#to convert area of sims to feet, multiply by 16

units_uncovered = items_fit*(item_length_irl*item_width_irl)
#above, we are looking for the total sq feet of the total items within the lot
units_uncovered = lot_area_sqft - units_uncovered
#above, we are finding the difference (uncovered sq feet of space)
#left by total items
units_uncovered = units_uncovered/16
#finally, we are converting from sq feet to sim units

#provide the user with outputs of formula calculations
print('The area of your lot in Sim units is {:d}'
' square units'.format(int(lot_area_sim)))

print('The area of your lot in square feet is {:d}'
' square ft' .format(int(lot_area_sqft)))

print("The {:s} in the real world is about {:d} ft by {:d}"
" ft".format(item, int(item_width_irl), int(item_length_irl)))

print("About {:d} {:s}s can fit in this lot".format(items_fit, item))

print("About {:d}% of the lot is covered by the {:s}"
"s".format(percentage_covered, item))

print("About {:d}"
" square units is left uncovered".format(int(units_uncovered)))

print("About {:d}% of the lot is left uncovered".format(percentage_uncovered))


#the user must have fun!
print("Have fun building!")