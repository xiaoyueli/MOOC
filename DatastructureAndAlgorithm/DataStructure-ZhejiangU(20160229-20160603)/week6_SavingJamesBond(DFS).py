import math

ISLAND_DIA = 15
LAKE_SIDE = 100
CENTER = [0, 0]

def buildgraph(num):
    """
    Return a graph with locating all the crocodiles
    Each node is the position of a crocodile and visited status
    """
    graph = []
    for dummy in range(num):
        loc = input().split()
        graph.append([int(loc[0]), int(loc[1]), False])

    return graph

def getdis(pos1, pos2):
    """
    Return the distance of two positions
    """
    dis = math.sqrt((pos1[0] - pos2[0])**2 + (pos1[2] - pos2[1])**2)

    return dis

def scan_cros(graph, loc, dis):
    """
    Return available crocodiles 
    """
    cros = []
    for cro in graph:
        cro_dis = getdis(cro, loc)
        if cro_dis <= dis:
            cros.append(cro)

    return cros

def is_out(pos, dis):
    """
    IF can jump to the land from the postion given return True, otherwise return False 
    """
    north_dis = math.fabs(LAKE_SIDE/2 - pos[1])
    east_dis = math.fabs(LAKE_SIDE/2 - pos[0])
    south_dis = math.fabs(-LAKE_SIDE/2 - pos[1])
    west_dis = math.fabs(-LAKE_SIDE/2 - pos[0])

    if dis >= north_dis or dis >= east_dis or dis >= south_dis or dis >= west_dis:
        return True

    return False


def dfs(graph, cro, dis):
    """
    Visit crocodiles by deep first search 
    """
    cro[2] = True
    if is_out(cro, dis):
        return True
    else:
        cros = scan_cros(graph, cro, dis)
        for new_cro in cros:
            if not new_cro[2]:
                return dfs(graph, new_cro, dis)

        return False




def saved(graph, dis):
    """
    Start jump from the island
    """

    radius = ISLAND_DIA / 2 + dis
    if radius < LAKE_SIDE / 2:
        crocos = scan_cros(graph, CENTER, radius)
    else:
        return True

    for cro in crocos:
        if not cro[2] and dfs(graph, cro, dis):
            return True

    return False
    
def main():
    """
    Save 007
    """
    info = input().split()
    cros = int(info[0])
    dis = int(info[1])

    graph = buildgraph(cros)

    if saved(graph, dis):
        print("Yes")
    else:
        print("No")

main()