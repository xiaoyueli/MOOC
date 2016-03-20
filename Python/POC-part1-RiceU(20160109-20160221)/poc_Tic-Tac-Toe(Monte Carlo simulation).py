"""
Monte Carlo Tic-Tac-Toe Player
"""

import random
import poc_ttt_gui
import poc_ttt_provided as provided

# Constants for Monte Carlo simulator
# You may change the values of these constants as desired, but
#  do not change their names.
NTRIALS = 100         # Number of trials to run
SCORE_CURRENT = 1.0 # Score for squares played by the current player
SCORE_OTHER = 1.0   # Score for squares played by the other player


# Add your functions here.
def mc_trial(board, player):
    """
    Play the game until the game is over
    """
    if board.check_win() == None:
        empty_list = board.get_empty_squares()
        square = random.choice(empty_list)
        board.move(square[0], square[1], player)
        mc_trial(board, provided.switch_player(player))
    else:
        return


def mc_update_scores(scores, board, player):
    """
    Record the scores for the completed game
    """
    def update_scores(winner, other):
        """
        Record the scores from the winner's perspective
        """
        for row in range(board.get_dim()):
            for col in range(board.get_dim()):
                if board.square(row, col) == winner:
                    scores[row][col] += SCORE_CURRENT
                elif board.square(row, col) == other:
                    scores[row][col] -= SCORE_OTHER
    if board.check_win() == player:
        update_scores(player, provided.switch_player(player))
    elif board.check_win() == provided.switch_player(player):
        update_scores(provided.switch_player(player), player)


def get_best_move(board, scores):
    """
    Compute the max score of the empty squares
    """
    empty_list = board.get_empty_squares()
    if empty_list != []:
        row = empty_list[0][0]
        col = empty_list[0][1]
        max_score = scores[row][col]
        for _tuple in set(empty_list):
            score = scores[_tuple[0]][_tuple[1]]
            if score > max_score:
                max_score = score
                row = _tuple[0]
                col = _tuple[1]
        return (row, col)
    else:
        print "The board is full!!"


def mc_move(board, player, trials):
    """
    Compute the optimum step by using Monte Carlo simulation
    """
    scores = [ [0 for dummy_col in range(board.get_dim())] for dummy_row in range(board.get_dim())]
    for dummy in range(trials):
        test_board = board.clone()
        mc_trial(test_board, player)
        mc_update_scores(scores, test_board, player)
    return get_best_move(board, scores)
    
                    
# Test game with the console or the GUI.  Uncomment whichever 
# you prefer.  Both should be commented out when you submit 
# for testing to save time.

#provided.play_game(mc_move, NTRIALS, False)        
poc_ttt_gui.run_gui(3, provided.PLAYERX, mc_move, NTRIALS, False)
