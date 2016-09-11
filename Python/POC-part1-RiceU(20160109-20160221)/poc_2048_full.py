"""
Clone of 2048 game.
"""

import poc_2048_gui
import random

# Directions, DO NOT MODIFY
UP = 1
DOWN = 2
LEFT = 3
RIGHT = 4

# Offsets for computing tile indices in each direction.
# DO NOT MODIFY this dictionary.
OFFSETS = {UP: (1, 0),
           DOWN: (-1, 0),
           LEFT: (0, 1),
           RIGHT: (0, -1)}

def merge(line):
    """
    Function that merges a single row or column in 2048.
    """
    # replace with your code
    new_line = [0 for idx in range(len(line))]
    idx = 0
    curr_val = 0
    for val in line:
        if curr_val == val and val != 0:
            new_line[idx] = curr_val + val
            idx += 1
            curr_val = 0
        elif curr_val != val and val != 0:
            if curr_val == 0:
                curr_val = val
            else:
                new_line[idx] = curr_val
                idx += 1
                curr_val = val
                
    if curr_val != 0:
        new_line[idx] = curr_val
        
    return new_line

class TwentyFortyEight:
    """
    Class to run the game logic.
    """

    def __init__(self, grid_height, grid_width):
        # replace with your code
        self._height = grid_height
        self._width = grid_width
        self.reset() 
        self._initial_entry = {UP: 0, DOWN: self._height - 1, LEFT: 0, RIGHT: self._width - 1}  

    def reset(self):
        """
        Reset the game so the grid is empty except for two
        initial tiles.
        """
        # replace with your code
        self._grid = [[0 for dummy_col in range(self._width)] for dummy_row in range(self._height)]
        self.new_tile()
        self.new_tile()

    def new_tile(self):
        """
        Create a new tile in a randomly selected empty
        square.  The tile should be 2 90% of the time and
        4 10% of the time.
        """
        # replace with your code
        create = False
        while not create:
            random.seed()
            row = random.randrange(self._height)
            col = random.randrange(self._width)
            if self._grid[row][col] == 0:
                if random.randrange(10) < 9:
                    self._grid[row][col] = 2
                else:
                    self._grid[row][col] = 4
                create = True

    def get_grid_height(self):
        """
        Get the height of the board.
        """
        # replace with your code
        return self._height

    def get_grid_width(self):
        """
        Get the width of the board.
        """
        # replace with your code
        return self._width
    
    def __str__(self):
        """
        Return a string representation of the grid for debugging.
        """
        # replace with your code
        return "Something is wrong"

    def set_tile(self, row, col, value):
        """
        Set the tile at position row, col to have the given value.
        """
        # replace with your code
        self._grid[row][col] = value

    def get_tile(self, row, col):
        """
        Return the value of the tile at position row, col.
        """
        # replace with your code
        return self._grid[row][col] 

    def move_udlr(self, range1, range2, direction, offset):
        """
        Distinguish UP, DOWN, LEFT, RIGHT to two different groups: UP/DOWN and LEFT/RIGHT.
        """
        tile_changed = False
        for list_num in range(range1):
            new_line = []
            entry = self._initial_entry[direction]
            while entry < range2 and entry >= 0:
                if direction == UP or direction == DOWN:                
                    new_line += [self.get_tile(entry, list_num)]
                else:
                    new_line += [self.get_tile(list_num, entry)]
                entry += OFFSETS[direction][offset]
            new_line = merge(new_line)

            entry = self._initial_entry[direction] 
            for new_entry in range(range2):
                if direction == UP or direction == DOWN:
                    if self.get_tile(entry, list_num) != new_line[new_entry]:
                        tile_changed = True
                    self.set_tile(entry, list_num, new_line[new_entry])
                else:
                    if self.get_tile(list_num, entry) != new_line[new_entry]:
                        tile_changed = True
                    self.set_tile(list_num, entry, new_line[new_entry])
                entry += OFFSETS[direction][offset]      
        return tile_changed 

    def move(self, direction):
        """
        Move all tiles in the given direction and add
        a new tile if any tiles moved.
        """
        # replace with your code
        if direction == UP or direction == DOWN:
            grid_changed = self.move_udlr(self._width, self._height, direction, 0)
        elif direction == LEFT or direction == RIGHT:
            grid_changed = self.move_udlr(self._height, self._width, direction, 1)
        else:
            self.__str__()
        if grid_changed:
            self.new_tile()
        
poc_2048_gui.run_gui(TwentyFortyEight(4, 4))
