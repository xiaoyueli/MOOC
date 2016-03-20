# template for "Guess the number" mini-project
# input will come from buttons and an input field
# all output for the game will be printed in the console

import simplegui
import random

secret_number = 0
rangeis = 100
remaining = 0 
# helper function to start and restart the game

def remaining_num(rangeis):
    """
    compute the smallest number to guarantee winning in the specifical range
    """
    cnt = 0
    high = rangeis
    while high != 0:
        high = high // 2
        cnt += 1
    return cnt

def new_game():
    # initialize global variables used in your code here

    # remove this when you add your code    
    global secret_number
    global remaining
    secret_number = random.randrange(rangeis)
    print "New game. Range is [0, ", rangeis, ")"
    remaining = remaining_num(rangeis)
    print "Number of remaining guesses is ", remaining
    print


# define event handlers for control panel
def range100():
    # button that changes the range to [0,100) and starts a new game 
    
    # remove this when you add your code    
    global rangeis 
    rangeis = 100
    new_game()

def range1000():
    # button that changes the range to [0,1000) and starts a new game     
    global rangeis
    rangeis = 1000
    new_game()
    
def input_guess(guess):
    # main game logic goes here	
    
    # remove this when you add your code
    global remaining
    number = int(guess)
    print "Guess was ", number
    remaining -= 1
    print "Number of remaining guesses is ", remaining
    if number == secret_number:
            print "Correct!"
            print
            new_game()
    elif remaining != 0:
        if number > secret_number:
            print "Lower!"
        else:
            print "Higher!"
        print
    else:
        print "You ran out of guesses.  The number was ", secret_number
        print
        new_game()

    
# create frame
frame = simplegui.create_frame("guess_number", 400, 400, 200)
button100 = frame.add_button("Range is [0,100)", range100, 150)
button1000 = frame.add_button("Range is [0,1000)", range1000, 150)
inp = frame.add_input("input number", input_guess, 150)
# register event handlers for control elements and start frame


# call new_game 
new_game()


# always remember to check your completed program against the grading rubric
