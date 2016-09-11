import random
# Rock-paper-scissors-lizard-Spock template


# The key idea of this program is to equate the strings
# "rock", "paper", "scissors", "lizard", "Spock" to numbers
# as follows:
#
# 0 - rock
# 1 - Spock
# 2 - paper
# 3 - lizard
# 4 - scissors

# helper functions

def name_to_number(name):
    # delete the following pass statement and fill in your code below

    # convert name to number using if/elif/else
    # don't forget to return the result!
    if name == "rock":
        num = 0
    elif name == "Spock":
        num = 1
    elif name == "paper":
        num = 2
    elif name == "lizard":
        num = 3
    elif name == "scissors":
        num = 4
    else:
        num = None
        print "Invalid choice"
    return num


def number_to_name(number):
    # delete the following pass statement and fill in your code below
    if number == 0:
        result = "rock"
    elif number == 1:
        result = "Spock"
    elif number == 2:
        result = "paper"
    elif number == 3:
        result = "lizard"
    elif number == 4:
        result = "scissors"
    else:
        result = "Invalid number"
    return result
    
    # convert number to a name using if/elif/else
    # don't forget to return the result!
    

def rpsls(player_choice): 
    # delete the following pass statement and fill in your code below
    
    # print a blank line to separate consecutive games
    print ""

    # print out the message for the player's choice
    print "Player chooses ", player_choice

    # convert the player's choice to player_number using the function name_to_number()
    player_number = name_to_number(player_choice)
    # compute random guess for comp_number using random.randrange()
    computer_random = random.randrange(0, 5)
    # convert comp_number to comp_choice using the function number_to_name()
    computer_choice = number_to_name(computer_random)
    # print out the message for computer's choice
    print "Computer chooses ", computer_choice
    # compute difference of comp_number and player_number modulo five
    difference = player_number - computer_random
    # use if/elif/else to determine winner, print winner message
    if difference % 5 == 0:
        print "Player and computer tie!"
    elif difference % 5 == 1 or difference % 5 == 2:
        print "Player wins!"
    elif difference % 5 == 3 or difference % 5 == 4:
        print "Computer wins!"
    else:
        print "Invalid result!"
    
# test your code - THESE CALLS MUST BE PRESENT IN YOUR SUBMITTED CODE
rpsls("rock")
rpsls("Spock")
rpsls("paper")
rpsls("lizard")
rpsls("scissors")

# always remember to check your completed program against the grading rubric



