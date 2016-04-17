INF = float("inf")

class Node(object):
    """docstring for Node"""
    def __init__(self, city, sum_dis, sum_fare):
        self._city = city
        self._sum_dis = sum_dis
        self._sum_fare = sum_fare

    def get_dis(self):
        return self._sum_dis
    
    def set_dis(self, value):
        self._sum_dis = value

    def get_fare(self):
        return self._sum_fare
    
    def set_fare(self, value):
        self._sum_fare = value

    def get_city(self):
        return self._city

def delete_city(heap, idx):
    """
    Remove and return the node of idx position from heap
    keep the properties of heap
    """
    if len(heap) == 1 or idx > len(heap) - 1:
        return None
    elif len(heap) - 1 == idx:
        return heap.pop(idx)
    else:
        root = heap[idx]
        last = heap.pop(-1)
        l_son = 2 * idx
        r_son = 2 * idx + 1
        while l_son < len(heap):
            if r_son < len(heap):
                if heap[l_son].get_dis() < heap[r_son].get_dis():
                    next_one = l_son
                else:
                    next_one = r_son
            else:
                next_one = l_son

            if heap[next_one].get_dis() < last.get_dis():
                heap[idx] = heap[next_one]
                idx = next_one
                l_son = 2 * idx
                r_son = 2 * idx + 1
            else:
                break

        heap[idx] = last
                
    return root

def get_city_info(heap, root, value):
    """
    Search the node whose value equals "value" from "root"
    If it exists in the heap remove it and return it
    Otherwise create a new node setting value as "value", distance as "INF" and fare as "INF"
    """
    if len(heap) == 1 or len(heap) - 1 < root:
        return None
    elif heap[root].get_city() == value:
        return delete_city(heap, root)
    else:
        l_son = get_city_info(heap, 2 * root, value)
        r_son = get_city_info(heap, 2 * root + 1, value)
        if not l_son and not r_son:
            return Node(value, INF, INF)
        elif l_son:
            return l_son
        else:
            return r_son

def travel_plan(graph, depa, dest, cities, mini_heap):
    """
    Compute the shortest way from depa to dest
    Print the total distance and total fare
    If there are more than one path, print the cheapest one
    """
    ready_cities = []
    left_cities = []
    for idx in range(cities):
        left_cities.append(idx)
    ready_cities.append(depa)
    left_cities.remove(depa)

    while len(ready_cities) < cities:
        next_city = delete_city(mini_heap, 1)
        city = next_city.get_city()
        sum_dis = next_city.get_dis()
        sum_fare = next_city.get_fare()
        ready_cities.append(city)
        left_cities.remove(city)
        if city == dest:
            print(str(sum_dis) + " " + str(sum_fare))
            break
        for idx in left_cities:
            row = city
            col = idx
            if idx > city:
                row = idx
                col = city
            road_info = graph[row * (row + 1) // 2 + col + 1]
            dis = road_info[0]
            fare = road_info[1]
            depa_to_idx = get_city_info(mini_heap, 1, idx)
            if sum_dis + dis < depa_to_idx.get_dis():
                depa_to_idx.set_dis(sum_dis + dis)
                depa_to_idx.set_fare(sum_fare + fare)
            elif sum_dis + dis == depa_to_idx.get_dis() and \
                sum_fare + fare < depa_to_idx.get_fare():
                depa_to_idx.set_fare(sum_fare + fare)
            insert(mini_heap, depa_to_idx)                

def insert(heap, city):
    """
    Insert the city to the minimum heap
    """
    if not heap:
        heap.append(None)
    heap.append(city)
    idx = len(heap) - 1
    p_idx = idx // 2

    while p_idx:
        if city.get_dis() < heap[p_idx].get_dis():
            heap[idx] = heap[p_idx]
            idx = p_idx
            p_idx //= 2
        else:
            break

    heap[idx] = city

def read_road_info(graph, roads, root):
    """
    Read the road information
    Read a minimum heap keeping the city connected to departure city
    """
    mini_heap = []
    for dummy in range(roads):
        r_info = input().split()
        depa = int(r_info[0])
        dest = int(r_info[1])
        dis = int(r_info[2])
        fare = int(r_info[3])
        if depa < dest:
            temp = depa
            depa = dest
            dest = temp
        graph[depa * (depa + 1) // 2 + dest + 1] = (dis, fare)
        if depa == root:
            city = Node(dest, dis, fare)
            insert(mini_heap, city)
        elif dest == root:
            city = Node(depa, dis, fare)
            insert(mini_heap, city)

    return mini_heap

def build_map(num):
    """
    Initialize the city-road graph
    """
    graph = []

    for dummy in range(num * (1 + num) // 2):
        graph.append((INF, INF))

    return graph

def main():
    info = input().split()
    cities = int(info[0])
    roads = int(info[1])
    depa = int(info[2])
    dest = int(info[3])

    graph = build_map(cities)
    mini_heap = read_road_info(graph, roads, depa)
    travel_plan(graph, depa, dest, cities, mini_heap)
    
main()
    
