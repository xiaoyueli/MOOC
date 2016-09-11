"""
Planner for Yahtzee
Simplifications:  only allow discard and roll, only score against upper level
"""

# Used to increase the timeout, if necessary
import codeskulptor
codeskulptor.set_timeout(20)

def gen_all_sequences(outcomes, length):
    """
    Iterative function that enumerates the set of all sequences of
    outcomes of given length.
    """
    
    answer_set = set([()])
    for dummy_idx in range(length):
        temp_set = set()
        for partial_sequence in answer_set:
            for item in outcomes:
                new_sequence = list(partial_sequence)
                new_sequence.append(item)
                temp_set.add(tuple(new_sequence))
        answer_set = temp_set
    return answer_set


def score(hand):
    """
    Compute the maximal score for a Yahtzee hand according to the
    upper section of the Yahtzee score card.

    hand: full yahtzee hand

    Returns an integer score 
    """
    max_sco = 0
    for item in set(hand):
        temp = item * hand.count(item)
        if temp > max_sco:
            max_sco = temp
    return max_sco

def expected_value(held_dice, num_die_sides, num_free_dice):
    """
    Compute the expected value based on held_dice given that there
    are num_free_dice to be rolled, each with num_die_sides.

    held_dice: dice that you will hold
    num_die_sides: number of sides on each die
    num_free_dice: number of dice to be rolled

    Returns a floating point expected value
    """
    outcomes = []
    for idx in range(1, num_die_sides + 1):
        outcomes.append(idx)
    all_seq =gen_all_sequences(outcomes, num_free_dice)
    exp_sco = 0
    for item in all_seq:
        held_dice_copy = list(held_dice)
        held_dice_copy.extend(item)
        exp_val = score(tuple(held_dice_copy))
        exp_sco += exp_val*1.0/len(all_seq)
    return exp_sco

def gen_all_holds(hand):
    """
    Generate all possible choices of dice from hand to hold.

    hand: full yahtzee hand

    Returns a set of tuples, where each tuple is dice to hold
    """
    result_sets =set([])
    len_range = len(hand)
    for length in range(len_range + 1):
        subs = set([()])
        for dummy_idx in range(length):
            temp = set()
            for new_item in subs:
                for item in hand:
                    new_sub = list(new_item)
                    if new_sub.count(item) < hand.count(item):
                        new_sub.append(item)
                    temp.add(tuple(sorted(new_sub)))
                    subs = temp
        result_sets = result_sets.union(subs)
    return result_sets

def strategy(hand, num_die_sides):
    """
    Compute the hold that maximizes the expected value when the
    discarded dice are rolled.

    hand: full yahtzee hand
    num_die_sides: number of sides on each die

    Returns a tuple where the first element is the expected score and
    the second element is a tuple of the dice to hold
    """
    held_sets = gen_all_holds(hand)
    max_sco = 0
    held_dices =()
    for sub_hand in held_sets:
        temp_sco = expected_value(sub_hand, num_die_sides,len(hand) - len(sub_hand))
        if temp_sco > max_sco:
            max_sco = temp_sco
            held_dices = sub_hand
    return max_sco, held_dices


def run_example():
    """
    Compute the dice to hold and expected score for an example hand
    """
    num_die_sides = 6
    hand = (1, 1, 1, 5, 6)
    hand_score, hold = strategy(hand, num_die_sides)
    print "Best strategy for hand", hand, "is to hold", hold, "with expected score", hand_score
    
    
run_example()


#import poc_holds_testsuite
#poc_holds_testsuite.run_suite(gen_all_holds)
                                       
    
    
    



