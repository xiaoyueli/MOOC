import math
INI_LOC = (0, 0)
LAKE_DIA = 15
ISLAND_SIZE = 100

class Node:
    """docstring for Node"""
    def __init__(self, loc, level = float("inf"), j_dis = float("inf"), pre_cro = None, exit = False):
        self._loc = loc
        self._level = level
        self._j_dis = j_dis
        self._pre_cro = pre_cro
        self._exit = exit

    def get_loc(self):
        return self._loc
    
    def set_dis(self, dis):
        self._j_dis = dis
        
    def get_dis(self):
        return self._j_dis

    def set_level(self, level):
        self._level = level

    def get_level(self):
        return self._level

    def set_visited(self, value):
        self._visited = value

    def set_pre_cro(self, cro):
        self._pre_cro = cro

    def get_pre_cro(self):
        return self._pre_cro

    def is_out(self):
        return self._exit

    def set_is_out(self, value):
        self._exit = value



def to_island(loc, dis):
    to_north = math.fabs(loc[1] - ISLAND_SIZE / 2)
    to_south = math.fabs(loc[1] + ISLAND_SIZE / 2)
    to_east = math.fabs(loc[0] - ISLAND_SIZE / 2)
    to_west = math.fabs(loc[0] + ISLAND_SIZE / 2)

    if dis >= to_north or dis >= to_south \
        or dis >= to_east or dis >= to_west:
        return True

    return False
    
def build_croco_map(nums, j_dis, out_exist):
    left_cros = []
    avai_cros = []
    r_jdis = j_dis + LAKE_DIA / 2
    for dummy in range(nums):
        loc = input().split()
        loc_x = int(loc[0])
        loc_y = int(loc[1])
        cro = Node((loc_x, loc_y))
        dis = compute_dis(cro.get_loc(), INI_LOC)
        if to_island((loc_x, loc_y), j_dis):
            cro.set_is_out(True)
            out_exist[0] = True
        if dis <= r_jdis:
            cro.set_dis(dis)
            cro.set_level(1)
            avai_cros.append(cro)
        else:
            left_cros.append(cro)

    return left_cros, avai_cros

def compute_dis(loc1, loc2):
     dis = math.sqrt((loc1[0] - loc2[0]) ** 2 + (loc1[1] - loc2[1]) ** 2)
     return dis

def way_out(left_lst, in_lst, jump_dis):
    outs = []
    saved = False
    if not left_lst:
    	for cro in in_lst:
    		if cro.is_out():
    			outs.append(cro)
    else:
	    while left_lst:
	        next_lst = []
	        for cro in in_lst:
	            for next in list(left_lst):
	                dis = compute_dis(cro.get_loc(), next.get_loc())
	                if jump_dis >= dis:
	                    next.set_level(cro.get_level() + 1)
	                    next.set_pre_cro(cro)
	                    next_lst.append(next)
	                    left_lst.remove(next)
	                    if next.is_out():
	                        outs.append(next)
	                        saved = True

	        in_lst = next_lst
	        if saved:
	            break

    return outs
def get_path(cro):
    path = []
    while cro:
        last = cro
        path.insert(0, cro.get_loc())
        cro = cro.get_pre_cro()
    f_jump_dis = last.get_dis()
    return f_jump_dis, path
            

def printout(exits):
    if not exits:
        print(0)
    else:
        mini_f_jump = float("inf")
        path = []
        for exit in exits:
            level = exit.get_level()
            info = get_path(exit)		
            dis = info[0]
            if mini_f_jump > dis:
                mini_f_jump = dis
                path = info[1]

        print(level + 1)
        for loc in path:
            print(str(loc[0]) + " " +str(loc[1]))


def main():
    info = input().split()
    num_cros = int(info[0])
    jump_dis = int(info[1])
    out_exist = [False]

    map_info = build_croco_map(num_cros, jump_dis, out_exist)
    left_cros = map_info[0]
    avai_cros = map_info[1]
    if jump_dis + LAKE_DIA / 2 > ISLAND_SIZE / 2:
    	print(1)
    else:
    	if out_exist.pop(0):
		    exits = way_out(left_cros, avai_cros, jump_dis)
		    printout(exits)
    	else:
    		print(0)
main()
