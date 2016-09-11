# implementation of card game - Memory

import simplegui
import random

deck = []
exposed = []
state = 0
turn = 0
# helper function to initialize globals
def new_game():
    """
    initial the game
    """
    global deck, exposed, turn, state
    deck = range(8)
    deck.extend(deck) 
    random.shuffle(deck)
    exposed = [False for dummy in range(len(deck))]
    turn = 0
    state = 0
    
# define event handlers
def mouseclick(pos):
    """
    update the status of card changed
    """
    # add game state logic here
    global match_1, match_2
    global turn, state
    card_th = pos[0] / 50
    if not exposed[card_th]:
        exposed[card_th] = True
        if state == 0:
            state = 1
            match_1 = card_th
        elif state == 1:
            state = 2
            match_2 = card_th
            turn += 1
        else:      
            if deck[match_1] == deck[match_2]:
                exposed[match_1] = True
                exposed[match_2] = True
            else:
                exposed[match_1] = False
                exposed[match_2] = False
            state = 1
            match_1 = card_th
            
        label.set_text("Turns = "+ str(turn))
                  
# cards are logically 50x100 pixels in size    
def draw(canvas):
    """
    update the deck as a whole
    """
    size = 50
    for idx in range(len(deck)):
        canvas.draw_text(str(deck[idx]),[idx*size + 10, 70], 60, 'white')
        if not exposed[idx]:
            canvas.draw_polygon([(idx*size, 0), (idx*size + size, 0), (idx*size + size, 100), (idx*size, 100)], 1, 'black', 'green')
    label.set_text("Turns = "+ str(turn))
    
# create frame and add a button and labels
frame = simplegui.create_frame("Memory", 800, 100)
frame.add_button("Reset", new_game)
label = frame.add_label("Turns = 0")

# register event handlers
frame.set_mouseclick_handler(mouseclick)
frame.set_draw_handler(draw)

# get things rolling
new_game()
frame.start()


# Always remember to review the grading rubric