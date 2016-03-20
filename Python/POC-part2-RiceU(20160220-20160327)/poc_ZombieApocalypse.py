"""
Student portion of Zombie Apocalypse mini-project
"""

import random
import poc_grid
import poc_queue
import poc_zombie_gui

# global constants
EMPTY = 0 
FULL = 1
FOUR_WAY = 0
EIGHT_WAY = 1
OBSTACLE = 5
HUMAN = 6
ZOMBIE = 7


class Apocalypse(poc_grid.Grid):
    """
    Class for simulating zombie pursuit of human on grid with
    obstacles
    """

    def __init__(self, grid_height, grid_width, obstacle_list = None, zombie_list = None, human_list = None):
        """
        Create a simulation of given size with given obstacles,
        humans, and zombies
        """
        poc_grid.Grid.__init__(self, grid_height, grid_width)
        if obstacle_list != None:
            for cell in obstacle_list:
                self.set_full(cell[0], cell[1])
        if zombie_list != None:
            self._zombie_list = list(zombie_list)
        else:
            self._zombie_list = []
        if human_list != None:
            self._human_list = list(human_list)  
        else:
            self._human_list = []
        
    def clear(self):
        """
        Set cells in obstacle grid to be empty
        Reset zombie and human lists to be empty
        """
        poc_grid.Grid.clear(self)
        self._human_list = []
        self._zombie_list = []
        
    def add_zombie(self, row, col):
        """
        Add zombie to the zombie list
        """
        self._zombie_list.append((row, col))
                
    def num_zombies(self):
        """
        Return number of zombies
        """
        return len(self._zombie_list)       
          
    def zombies(self):
        """
        Generator that yields the zombies in the order they were
        added.
        """
        # replace with an actual generator
        for zombie in self._zombie_list:
            yield zombie

    def add_human(self, row, col):
        """
        Add human to the human list
        """
        self._human_list.append((row, col))
        
    def num_humans(self):
        """
        Return number of humans
        """
        return len(self._human_list)
    
    def humans(self):
        """
        Generator that yields the humans in the order they were added.
        """
        # replace with an actual generator
        for human in self._human_list:
            yield human
        
    def compute_distance_field(self, entity_type):
        """
        Function computes and returns a 2D distance field
        Distance at member of entity_list is zero
        Shortest paths avoid obstacles and use four-way distances
        """
        width = self.get_grid_width()
        height = self.get_grid_height()
        visited_field = poc_grid.Grid(height, width)
        dis_field = [[width * height for dummy_col in range(width)] for dummy_row in range(height)]
        boundry = poc_queue.Queue()
        if entity_type == HUMAN: 
            for item in self.humans():
                boundry.enqueue(item)
        elif entity_type == ZOMBIE:
            for item in self.zombies():
                boundry.enqueue(item)
        for item in boundry:
            visited_field.set_full(item[0], item[1])
            dis_field[item[0]][item[1]] = 0
        while len(boundry) != 0:
            cell = boundry.dequeue()
            neighbors = visited_field.four_neighbors(cell[0], cell[1])
            for neighbor in neighbors:
                if visited_field.is_empty(neighbor[0], neighbor[1]) and self.is_empty(neighbor[0], neighbor[1]):
                    visited_field.set_full(neighbor[0], neighbor[1])
                    boundry.enqueue(neighbor)
                    dis_field[neighbor[0]][neighbor[1]] = dis_field[cell[0]][cell[1]] + 1       
        return dis_field
    
    def move_humans(self, zombie_distance_field):
        """
        Function that moves humans away from zombies, diagonal moves
        are allowed
        """
        humans = list(self.humans())
        for human in humans:
            cells_to_go = [human]
            max_dis = zombie_distance_field[human[0]][human[1]]
            neighbors = self.eight_neighbors(human[0], human[1])
            for neighbor in neighbors:
                if self.is_empty(neighbor[0], neighbor[1]):
                    distance = zombie_distance_field[neighbor[0]][neighbor[1]]
                    if distance > max_dis:
                        max_dis = distance
                        cells_to_go = [neighbor]
                    elif distance == max_dis:
                        cells_to_go.append(neighbor)
            cell_to_go = random.choice(cells_to_go)
            self.add_human(cell_to_go[0], cell_to_go[1])
            self._human_list.pop(0)

    
    def move_zombies(self, human_distance_field):
        """
        Function that moves zombies towards humans, no diagonal moves
        are allowed
        """
        zombies = list(self.zombies())
        for zombie in zombies:
            cells_to_go = [zombie]
            min_dis = human_distance_field[zombie[0]][zombie[1]]
            neighbors = self.four_neighbors(zombie[0], zombie[1])
            for neighbor in neighbors:
                if self.is_empty(neighbor[0], neighbor[1]):
                    distance = human_distance_field[neighbor[0]][neighbor[1]]
                    if distance < min_dis:
                        min_dis = distance
                        cells_to_go = [neighbor]                        
                    elif distance == min_dis:
                        cells_to_go.append(neighbor)           
            cell_to_go = random.choice(cells_to_go)
            self.add_zombie(cell_to_go[0], cell_to_go[1])
            self._zombie_list.pop(0)


# Start up gui for simulation - You will need to write some code above
# before this will work without errors

poc_zombie_gui.run_gui(Apocalypse(30, 40))
