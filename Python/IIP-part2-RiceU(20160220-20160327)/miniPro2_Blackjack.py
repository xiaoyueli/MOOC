# Mini-project #6 - Blackjack

import simplegui
import random

# load card sprite - 936x384 - source: jfitz.com
CARD_SIZE = (72, 96)
CARD_CENTER = (36, 48)
card_images = simplegui.load_image("http://storage.googleapis.com/codeskulptor-assets/cards_jfitz.png")

CARD_BACK_SIZE = (72, 96)
CARD_BACK_CENTER = (36, 48)
card_back = simplegui.load_image("http://storage.googleapis.com/codeskulptor-assets/card_jfitz_back.png")    

# initialize some useful global variables
in_play = False
outcome = ""
score = 0

# define globals for cards
SUITS = ('C', 'S', 'H', 'D')
RANKS = ('A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K')
VALUES = {'A':1, '2':2, '3':3, '4':4, '5':5, '6':6, '7':7, '8':8, '9':9, 'T':10, 'J':10, 'Q':10, 'K':10}

# define card class
class Card:
    def __init__(self, suit, rank):
        if (suit in SUITS) and (rank in RANKS):
            self.suit = suit
            self.rank = rank
        else:
            self.suit = None
            self.rank = None
            print "Invalid card: ", suit, rank

    def __str__(self):
        return self.suit + self.rank

    def get_suit(self):
        return self.suit

    def get_rank(self):
        return self.rank

    def draw(self, canvas, pos):
        card_loc = (CARD_CENTER[0] + CARD_SIZE[0] * RANKS.index(self.rank), 
                    CARD_CENTER[1] + CARD_SIZE[1] * SUITS.index(self.suit))
        canvas.draw_image(card_images, card_loc, CARD_SIZE, [pos[0] + CARD_CENTER[0], pos[1] + CARD_CENTER[1]], CARD_SIZE)
        
# define hand class
class Hand:
    def __init__(self):
        self.cards = [] # create Hand object

    def __str__(self):
        string = "Hand Contains"
        for item in self.cards:
            string += " " + str(item)
        return string   # return a string representation of a hand

    def add_card(self, card):
        self.cards.append(card) # add a card object to a hand

    def get_value(self):
        # count aces as 1, if the hand has an ace, then add 10 to hand value if it doesn't bust
        value = 0
        aces = False
        for item in self.cards:
            if item.get_rank() == "A" :
                aces = True
            value += VALUES[item.get_rank()]
        if aces and value <= 21 - 10:
            value += 10
        return value
            # compute the value of the hand, see Blackjack video
    def draw(self, canvas, pos):
        for idx in range(len(self.cards)):
            self.cards[idx].draw(canvas, [pos[0]+CARD_CENTER[0] * idx, pos[1]])
            pos[0] += 50
       
#define deck class 
class Deck:
    def __init__(self):
        self.deck = [[Card(SUITS[dummy_y], RANKS[dummy_x]) for dummy_x in range(len(RANKS))] for dummy_y in range(len(SUITS))]
            # create a Deck object

    def shuffle(self):
        # shuffle the deck 
        for idx in range(len(self.deck)):
            random.shuffle(self.deck[idx])
        # use random.shuffle()

    def deal_card(self):
        suit = random.randrange(len(self.deck))
        while len(self.deck[suit]) == 0:
            suit = random.randrange(len(self.deck))
        return self.deck[suit].pop(0)
            # deal a card object from the deck

    def __str__(self):
        string = "Deck Contains"
        for suit in range(len(self.deck)):
            for rank in range(len(self.deck[suit])):
                string += " " + str(self.deck[suit][rank])
        return string   # return a string representing the deck

#define event handlers for buttons
def deal():
    global outcome, in_play, score
    global deals, player, dealer

    # your code goes here
    if in_play:
        outcome = "You have losted!"
        score -= 1
        in_play = False
    else:
        deals = Deck()
        deals.shuffle()
        player = Hand()
        dealer = Hand()
        player.add_card(deals.deal_card())
        dealer.add_card(deals.deal_card())
        player.add_card(deals.deal_card())
        dealer.add_card(deals.deal_card())
        outcome = "Hit or stand?"
        in_play = True

def hit():
    global deals, score 
    global player, dealer
    global outcome, in_play
        # replace with your code below
 
    # if the hand is in play, hit the player
    if in_play:
        if player.get_value() <= 21:
            player.add_card(deals.deal_card())
            outcome = "Hit or stand?"
        if player.get_value() > 21:
            outcome = "You have busted! Dealer wins! New deal?"
            in_play = False
            score -= 1
    # if busted, assign a message to outcome, update in_play and score
       
def stand():
    global player, dealer
    global outcome, score, in_play

    if player.get_value() > 21:
        outcome = "You have busted! Dealer wins! New deal?"
    else:
        if in_play:
            while dealer.get_value() < 17:
                dealer.add_card(deals.deal_card())
            if dealer.get_value() > 21:
                outcome = "Dealer has busted, you win! New deal?"
                score += 1
            elif dealer.get_value() < player.get_value():
                outcome = "Player wins! New deal?"
                score += 1
            else:
                outcome = "Dealer wins! New deal?"
                score -= 1
            in_play = False

        # replace with your code below
   
    # if hand is in play, repeatedly hit dealer until his hand has value 17 or more

    # assign a message to outcome, update in_play and score

# draw handler    
def draw(canvas):
    # test to make sure that card.draw works, replace with your code below
    canvas.draw_text("Blackjack", [50, 100], 50, "white")
    canvas.draw_text("Scores = " + str(score), [400, 100], 30, "white")
    canvas.draw_text("Dealer", [50, 200], 24, "white")
    dealer.draw(canvas, [50, 220])
    if in_play:
        canvas.draw_image(card_back, CARD_BACK_CENTER, CARD_BACK_SIZE, [50 + CARD_BACK_CENTER[0], 220 + CARD_BACK_CENTER[1]],CARD_BACK_SIZE)
    canvas.draw_text("Player", [50, 350], 24, "white")
    player.draw(canvas, [50, 370])

    canvas.draw_text(outcome, [50, 530], 24, "white")
       
# initialization frame
frame = simplegui.create_frame("Blackjack", 600, 600)
frame.set_canvas_background("Green")

#create buttons and canvas callback
frame.add_button("Deal", deal, 200)
frame.add_button("Hit",  hit, 200)
frame.add_button("Stand", stand, 200)
frame.set_draw_handler(draw)

# get things rolling
deal()
frame.start()

# remember to review the gradic rubric