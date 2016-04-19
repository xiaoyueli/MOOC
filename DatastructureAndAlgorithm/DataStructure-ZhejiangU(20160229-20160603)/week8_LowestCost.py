import math

class Node:
    """docstring for Node"""
    def __init__(self, cost, vill1, vill2):
        self._cost = cost
        self._vill1 = vill1
        self._vill2 = vill2

    def get_cost(self):
        return self._cost

    def get_vill1(self):
        return self._vill1

    def get_vill2(self):
        return self._vill2

        

def initialize_map(num):
    villages = {}
    for idx in range(1, num + 1):
        villages[idx] = -1

    return villages

def insert(heap, node):
    if not heap:
        heap.append(None)
    heap.append(node)
    idx = len(heap) - 1
    par_idx = idx // 2
    cost = node.get_cost()

    while par_idx:
        par_cost = heap[par_idx].get_cost()
        if cost < par_cost:
            heap[idx] = heap[par_idx]
            idx = par_idx
            par_idx //= 2
        else:
            break

    heap[idx] = node

def delete_road(heap, idx):
    if len(heap) == 1 and idx > len(heap) - 1:
        return None

    if len(heap) == 2:
        return heap.pop(1)

    road = heap[idx]
    last = heap.pop(-1)

    son = 2 * idx
    while son <= len(heap) - 1 and son:
        if son < len(heap) - 1 and heap[son].get_cost() > heap[son + 1].get_cost():
            son += 1
        if last.get_cost() > heap[son].get_cost():
            heap[idx] = heap[son]
            idx = son
            son = 2 * idx
        else:
            break
    heap[idx] = last

    return road
        

def read_roads(num):
    miniheap = []
    
    for dummy in range(num):
        info = input().split()
        vill1 = int(info[0])
        vill2 = int(info[1])
        cost = int(info[2])
        node = Node(cost, vill1, vill2)
        insert(miniheap, node)

    return miniheap

def find_root(vill_info, vill):
    pre = vill
    while vill > 0:
        pre = vill
        vill = vill_info[vill]

    return pre

def check(vill_info, vill1, vill2):
    root1 = find_root(vill_info, vill1)
    root2 = find_root(vill_info, vill2)

    if root1 == root2 and root1 > 0:
        return True
    weight_ro1 = vill_info[root1]
    weight_ro2 = vill_info[root2]


    if weight_ro1 == weight_ro2:
        vill_info[root2] = root1
        vill_info[root1] = weight_ro1 - 1
    elif math.fabs(weight_ro1) < math.fabs(weight_ro2):
        vill_info[root1] = root2
    else:
        vill_info[root2] = root1

    return False

def get_lowest_cost(miniheap, vill_info, num):
    
    lst = []
    sum_cost = 0
    while len(lst) < num - 1 and len(miniheap) > 1:
        road = delete_road(miniheap, 1)
        cost = road.get_cost()      
        vill1 = road.get_vill1()
        vill2 = road.get_vill2()
        if not check(vill_info, vill1, vill2):
            lst.append(road)
            sum_cost += cost

    if len(lst) == num - 1:
        return sum_cost
    else:
        return -1

def main():
    info = input().split()
    villages = int(info[0])
    roads = int(info[1])

    vill_group = initialize_map(villages)
    cost_miniheap = read_roads(roads)

    cost = get_lowest_cost(cost_miniheap, vill_group, villages)

    print(cost)
    
main()
