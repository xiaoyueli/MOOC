"""
Loyd's Fifteen puzzle - solver and visualizer
Note that solved configuration has the blank (zero) tile in upper left
Use the arrows key to swap this tile with its neighbors
"""

import poc_fifteen_gui

class Puzzle:
    """
    Class representation for the Fifteen puzzle
    """

    def __init__(self, puzzle_height, puzzle_width, initial_grid=None):
        """
        Initialize puzzle with default height and width
        Returns a Puzzle object
        """
        self._height = puzzle_height
        self._width = puzzle_width
        self._grid = [[col + puzzle_width * row
                       for col in range(self._width)]
                      for row in range(self._height)]

        if initial_grid != None:
            for row in range(puzzle_height):
                for col in range(puzzle_width):
                    self._grid[row][col] = initial_grid[row][col]

    def __str__(self):
        """
        Generate string representaion for puzzle
        Returns a string
        """
        ans = ""
        for row in range(self._height):
            ans += str(self._grid[row])
            ans += "\n"
        return ans

    #####################################
    # GUI methods

    def get_height(self):
        """
        Getter for puzzle height
        Returns an integer
        """
        return self._height

    def get_width(self):
        """
        Getter for puzzle width
        Returns an integer
        """
        return self._width

    def get_number(self, row, col):
        """
        Getter for the number at tile position pos
        Returns an integer
        """
        return self._grid[row][col]

    def set_number(self, row, col, value):
        """
        Setter for the number at tile position pos
        """
        self._grid[row][col] = value

    def clone(self):
        """
        Make a copy of the puzzle to update during solving
        Returns a Puzzle object
        """
        new_puzzle = Puzzle(self._height, self._width, self._grid)
        return new_puzzle

    ########################################################
    # Core puzzle methods

    def current_position(self, solved_row, solved_col):
        """
        Locate the current position of the tile that will be at
        position (solved_row, solved_col) when the puzzle is solved
        Returns a tuple of two integers        
        """
        solved_value = (solved_col + self._width * solved_row)

        for row in range(self._height):
            for col in range(self._width):
                if self._grid[row][col] == solved_value:
                    return (row, col)
        assert False, "Value " + str(solved_value) + " not found"

    def update_puzzle(self, move_string):
        """
        Updates the puzzle state based on the provided move string
        """
        zero_row, zero_col = self.current_position(0, 0)
        
        for direction in move_string:

            if direction == "l":
                assert zero_col > 0, "move off grid: " + direction +": "+move_string
                self._grid[zero_row][zero_col] = self._grid[zero_row][zero_col - 1]
                self._grid[zero_row][zero_col - 1] = 0
                zero_col -= 1
            elif direction == "r":
                assert zero_col < self._width - 1, "move off grid: " + direction +": "+move_string
                self._grid[zero_row][zero_col] = self._grid[zero_row][zero_col + 1]
                self._grid[zero_row][zero_col + 1] = 0
                zero_col += 1
            elif direction == "u":
                assert zero_row > 0, "move off grid: " + direction +": "+move_string
                self._grid[zero_row][zero_col] = self._grid[zero_row - 1][zero_col]
                self._grid[zero_row - 1][zero_col] = 0
                zero_row -= 1
            elif direction == "d":
                assert zero_row < self._height - 1, "move off grid: " + direction +": "+move_string
                self._grid[zero_row][zero_col] = self._grid[zero_row + 1][zero_col]
                self._grid[zero_row + 1][zero_col] = 0
                zero_row += 1
            else:
                assert False, "invalid direction: " + direction +": "+move_string

    ##################################################################
    # Phase one methods

    def lower_row_invariant(self, target_row, target_col):
        """
        Check whether the puzzle satisfies the specified invariant
        at the given position in the bottom rows of the puzzle (target_row > 1)
        Returns a boolean
        """
        # replace with your code

        
        # check out that zero tile is positioned at target_tile
        if self._grid[target_row][target_col] != 0:
            return False
        
        # if zero tile is on the last tile, it should return true
        if (target_row, target_col) == (self._height - 1, self._width - 1):
            return True

        #check out that the tiles positioned at the right of the target tile are positioned
        for col in range(target_col + 1, self._width):
            tile = self.current_position(target_row, col)
            if (target_row, col) != tile:
                return False

        #check out that the tiles below the target tile are positioned
        if target_row != self._height - 1:    
            for row in range(target_row + 1, self._height):
                for col in range(self._width):
                    tile = self.current_position(row, col)
                    if (row, col) != tile:
                        return False

        return True


    def position_tile(self, tile, target_row, target_col, path):
        """
        Place tile to the target position
        Return the moving path and the location of the tile up the target position 
        """
        #c opy the target position as zero tile's position
        cur_row = target_row
        cur_col = target_col

        # move up zero tile to the row same with tile
        while cur_row > tile[0]:
            path += "u"
            cur_row -= 1
        # if zero tile is on the right of the tile
        if cur_col > tile[1]:
            # move zero tile to the left of the tile  
            while  cur_col > tile[1]:
                path += "l"
                cur_col -= 1
            # move tile to the same column with the target position
            while cur_col < target_col - 1:
                # if zero tile is on the 0 row, moving it under the tile, otherwise up the tile
                if cur_row > 0:  
                    path += "urrdl"
                else:
                    path += "drrul"
                cur_col += 1
            # move zero tile to the up of tile
            if cur_row < target_row:
                path += "dru"
                cur_col += 1
        # if zero tile is on the left of the tile
        elif cur_col < tile[1]:
            # move zero tile to the right of the tile 
            while cur_col < tile[1]:
                path += "r"
                cur_col += 1
            # move tile to the same column with the target position
            while cur_col > target_col + 1:
                # if zero tile is on the 0 row, moving it under the tile, otherwise up the tile
                if cur_row > 0:
                    path += "ulldr"
                else:
                    path += "dllur"
                cur_col -= 1
            # move zero tile to the up of tile
            if cur_row < target_row:
                if cur_row > 0:
                    path += "ullddru"
                else:
                    path += "dlu"
                cur_col -= 1
        # move tile to the same row with the target position
        while cur_row < target_row - 1:
            if cur_col > 0:
                path += "lddru"
            else:
                path += "rddlu"
            cur_row += 1

        return path, cur_row, cur_col

    def solve_interior_tile(self, target_row, target_col):
        """
        Place correct tile at target position
        Updates puzzle and returns a move string
        """
        # replace with your code
        path = ""
        tile = self.current_position(target_row, target_col)
        if tile == (target_row, target_col):
            return path
        # copy the target position to the zero tile 
        cur_row = target_row
        cur_col = target_col
        if self.lower_row_invariant(target_row, target_col):

            # tile is not on the same row with target position
            if cur_row > tile[0]:
                updated_tile = self.position_tile(tile, target_row, target_col, path)
                path = updated_tile[0]
                cur_row = updated_tile[1]
                cur_col = updated_tile[2]
                path += "ld"
                cur_row += 1
                cur_col -= 1
            else:
                # tile is on the same row of the target position 
                while cur_col > tile[1]:
                    path += "l"
                    cur_col -= 1
                while cur_col < target_col - 1:
                    path += "urrdl"
                    cur_col += 1

            # update the puzzle
            self.update_puzzle(path)
                        
        else:
            assert False, "there is tile positioned after tile " + str(target_row, target_col) + " not be solved"
        
        return path

    def solve_col0_tile(self, target_row):
        """
        Solve tile in column zero on specified row (> 1)
        Updates puzzle and returns a move string
        """
        # replace with your code
        path = ""
        tile = self.current_position(target_row, 0)
        if tile == (target_row, 0):
            return path
        cur_col = 0
        if self.lower_row_invariant(target_row, 0):

            if tile[1] == 0 and tile[0] == target_row - 1:
                path += "u"
            else:
                if tile[0] == target_row - 1 and tile[1] == 1:
                    path += "urulddrulurddlu"
                else:
                    path += "ur"
                    updated_tile = self.position_tile(tile, target_row - 1, 1, path)
                    path = updated_tile[0]
                    path += "ldrulddrulurddlu"

            while cur_col < self._width - 1:
                path += "r"
                cur_col += 1

            self.update_puzzle(path)
        else:
            assert False, "there is tile positioned after tile " + str(target_row, 0) + " not be solved"
        return path

    #############################################################
    # Phase two methods


    def row0_invariant(self, target_col):
        """
        Check whether the puzzle satisfies the row zero invariant
        at the given column (col > 1)
        Returns a boolean
        """
        # replace with your code

        
        # check out that zero tile is positioned at target_tile
        if self._grid[0][target_col] != 0:
            return False
        
        # if zero tile is on the last tile, it should return true
        if (0, target_col) == (self._height - 1, self._width - 1):
            return True

        #check out that tile(1, target_col) is positioned 
        tile = self.current_position(1, target_col)
        if (1, target_col) != tile:
            return False

        #check out that the tiles positioned at the right col of the target tile are positioned
        for col in range(target_col + 1, self._width):
            tile0 = self.current_position(0, col)
            tile1 = self.current_position(1, col)
            if (0, col) != tile0 and (1, col) != tile1:
                return False

        #check out that the tiles below the target tile are positioned
        if self._height != 2:    
            for row in range(2, self._height):
                for col in range(self._width):
                    tile = self.current_position(row, col)
                    if (row, col) != tile:
                        return False

        return True

    def row1_invariant(self, target_col):
        """
        Check whether the puzzle satisfies the row one invariant
        at the given column (col > 1)
        Returns a boolean
        """
        # replace with your code

        # check out that zero tile is positioned at target_tile
        if self._grid[1][target_col] != 0:
            return False
        
        # if zero tile is on the last tile, it should return true
        if (1, target_col) == (self._height - 1, self._width - 1):
            return True        
        
        #check out that the tiles positioned at the right of the target tile are positioned
        for col in range(target_col + 1, self._width):
            tile0 = self.current_position(0, col)
            tile1 = self.current_position(1, col)
            if (0, col) != tile0 and (1, col) != tile1:
                return False

        #check out that the tiles below the target tile are positioned
        if self._height != 2:    
            for row in range(2, self._height):
                for col in range(self._width):
                    tile = self.current_position(row, col)
                    if (row, col) != tile:
                        return False

        return True
    
    def solve_row0_tile(self, target_col):
        """
        Solve the tile in row zero at the specified column
        Updates puzzle and returns a move string
        """
        # replace with your code
        path = ""
        tile = self.current_position(0, target_col)
        if tile == (0, target_col):
            return path
        cur_col = target_col
        if self.row0_invariant(target_col):

            if tile[0] == 0:
                if tile[1] == target_col - 1:
                    path += "ld"
                    cur_col -= 1
                else:
                    if tile[1] < target_col - 1:
                        path += "dlu"
                        cur_col -= 1
                        while cur_col > tile[1]:
                            path += "l"
                            cur_col -= 1

            if tile[0] == 1:
                if tile[1] == target_col - 1:
                    path += "lldruldrurdluldrruld"
                    cur_col -= 1
                else:
                    path += "dl"
                    cur_col -= 1
                    if tile[1] < target_col - 1:     
                        while cur_col > tile[1] + 1:
                            path += "l"
                            cur_col -= 1
                    path += "uldrul"
                    cur_col -= 1
            while cur_col < target_col - 2:
                path += "drrul"
                cur_col += 1
            if cur_col < target_col - 1:
                path += "drruld"
            self.update_puzzle(path)

        else:
            assert False, "there is tile positioned after the row and column of tile " + str(0, target_col) + " not be solved"
                    
        return path

    def solve_row1_tile(self, target_col):
        """
        Solve the tile in row one at the specified column
        Updates puzzle and returns a move string
        """
        # replace with your code
        path = ""
        tile = self.current_position(1, target_col)
        if tile == (1, target_col):
            return path
        cur_col = target_col
        if self.row1_invariant(target_col):
            if tile[0] == 0 and tile[1] == target_col:
                path += "u"
            else:
                while cur_col > tile[1]:
                    path += "l"
                    cur_col -= 1
                if tile[0] == 0:
                    path += "urdl"
                while cur_col < target_col - 1:
                    path += "urrdl"
                    cur_col += 1
                path += "ur"
            self.update_puzzle(path)
        else:
            assert False, "there is tile positioned after the row and column of tile " + str(1, target_col) + " not be solved"
        return path

    ###########################################################
    # Phase 3 methods

    def solve_2x2(self):
        """
        Solve the upper left 2x2 part of the puzzle
        Updates the puzzle and returns a move string
        """
        # replace with your code
        path = ""
        lst = ["u", "l", "d", "r"]
        if self.row1_invariant(1):
            while not self.row0_invariant(0):
                direction = lst.pop(0)
                self.update_puzzle(direction)
                path += direction
                lst.append(direction)
        else:
            assert False, "there is tile positioned after the row and column of tile " + str(1, 1) + " not be solved"
        return path

    def is_solved(self, target_row, target_col):
        """
        Return true if target_position is solved, otherwise return false
        """
        tile = self.current_position(target_row, target_col)
        if tile == (target_row, target_col):
            return True
        return False    

    def process_move_zero(self, target_row, target_col):
        """
        Move zero tile to target_position
        Returns a move string
        """
        path = ""
        
        zero = self.current_position(0, 0)

        if zero == (target_row, target_col):
            return path

        cur_row = zero[0]
        cur_col = zero[1]

        while cur_row < target_row - 1:
            path += "d"
            cur_row += 1

        while cur_col > target_col:
            path += "l"
            cur_col -= 1

        while cur_col < target_col:
            path += "r"
            cur_col += 1

        path += "d"

        self.update_puzzle(path)
        zero = self.current_position(0, 0)


        assert zero == (target_row, target_col), "zero"+str(zero)+" move to " +str((target_row, target_col))+" returned an incorrect moving string: "+ path
        
        return path

    def solve_puzzle(self):
        """
        Generate a solution string for a puzzle
        Updates the puzzle and returns a move string
        """
        # replace with your code
        path_all = ""

        for row in range(self._height - 1, 1, -1):
            for col in range(self._width - 1, -1, -1):
                
                if self.is_solved(row, col):
                    continue

                path = self.process_move_zero(row, col)
  
                assert self.lower_row_invariant(row, col), "tiles after" + str((row, col)) +"not be solved"

                if col > 0:
                    path += self.solve_interior_tile(row, col)
                else:
                    path += self.solve_col0_tile(row)
                path_all += path

        for col in range(self._width - 1, 0, -1):
            if self.is_solved(0, col) and self.is_solved(1, col):
                continue
            path = self.process_move_zero(1, col)
            if col > 1:
                assert self.row1_invariant(col), "tiles after" + str((1, col)) +"not be solved"
                path += self.solve_row1_tile(col)
                assert self.row0_invariant(col), "tiles after" + str((0, col)) +"not be solved"
                path += self.solve_row0_tile(col)

            if col == 1:
                assert self.row1_invariant(1), "tiles after" + str((1, 1)) +"not be solved"
                path += self.solve_2x2()

            path_all += path

        return path_all
    
# Start interactive simulation
poc_fifteen_gui.FifteenGUI(Puzzle(4, 5))
