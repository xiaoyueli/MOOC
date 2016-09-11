"""
Mini-max Tic-Tac-Toe Player
"""
http://www.codeskulptor.org/#user41_EgVLZ91hYE_10.py

import poc_ttt_gui
import poc_ttt_provided as provided

# Set timeout, as mini-max can take a long time
import codeskulptor
codeskulptor.set_timeout(60)

# SCORING VALUES - DO NOT MODIFY
SCORES = {provided.PLAYERX: 1,
          provided.DRAW: 0,
          provided.PLAYERO: -1}

def mm_move(board, player):
    """
    Make a move on the board.
    
    Returns a tuple with two elements.  The first element is the score
    of the given board and the second element is the desired move as a
    tuple, (row, col).
    """

    emp_squares = board.get_empty_squares()
  
    if emp_squares == []:
        return SCORES[board.check_win()], (-1, -1)
    else:
        strategy = {}
        for square in emp_squares:
            board_child = board.clone()
            board_child.move(square[0], square[1], player)
            winner = board_child.check_win()
            if winner == provided.PLAYERX or winner == provided.PLAYERO:
                if winner == player:
                    return SCORES[winner], square
                else:
                    strategy[SCORES[winner]] = square            
            else:
                child = mm_move(board_child, provided.switch_player(player))    
                strategy[child[0]] = square
        
        if player == provided.PLAYERX:
            opt_s = max(strategy.keys())
        else:
            opt_s = min(strategy.keys())
        
        opt_m = strategy[opt_s]

    return opt_s, opt_m


def move_wrapper(board, player, trials):
    """
    Wrapper to allow the use of the same infrastructure that was used
    for Monte Carlo Tic-Tac-Toe.
    """
    move = mm_move(board, player)
    assert move[1] != (-1, -1), "returned illegal move (-1, -1)"
    return move[1]

# Test game with the console or the GUI.
# Uncomment whichever you prefer.
# Both should be commented out when you submit for
# testing to save time.

#provided.play_game(move_wrapper, 1, False)        
poc_ttt_gui.run_gui(3, provided.PLAYERO, move_wrapper, 1, False)