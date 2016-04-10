class Node:
    """docstring for Network"""
    def __init__(self, value, children = None, visited = False):
        self._value = value
        self._children = children
        self._visited = visited

    def __str__(self):
        node = str(self._value) + ": "
        if self._children:
            for child in self._children:
                node += str(child.value())+ str(child.visited())+ " "

        return node

    def children(self):
        return self._children

    def add_child(self, child):

        if not self._children:
            self._children = [child]
        else:
            lst = self._children
            lst.append(child)
            self._children = list(set(lst))
              

    def value(self):
        return self._value

    def visited(self):
        return self._visited

    def set_visited(self, visited):
        self._visited = visited
        
def reset(graph):
    if graph:
        for item in graph:
            item.set_visited(False)

def buildgraph(vertex):
    graph = []
    for idx in range(vertex):
        node = Node(idx + 1)
        graph.append(node)
    
    return graph

def insert_edge(graph, edges):
    
    for dummy in range(edges):
        info = input().split()
        node1 = graph[int(info[0]) - 1]
        node2 = graph[int(info[1]) - 1]
        node1.add_child(node2)
        node2.add_child(node1)

    return graph
        
        

def print_ratio(number, numerator, denominator):
    ratio = 1.0 * numerator / denominator * 100
    result = str(number) + ": " + "%.2f" % ratio + "%"
    print(result)
    


def six_dimen(graph, idx):
    line = [graph[idx - 1]]
    last = graph[idx - 1]
    layers = 0
    number = 1
    while line:
        
        node = line.pop(0)
        node.set_visited(True)
        sons = node.children()
        for son in sons:
            if not son.visited():
                line.append(son)
                number += 1
        if node == last:
            layers += 1
            if line:
                last = line[-1]

        if layers == 6:
            break

    return number   

def main():
    """
    Input initial information
    """

    info = input().split()

    vertex = int(info[0])
    edges = int(info[1])

    graph = buildgraph(vertex)
    graph = insert_edge(graph, edges)
    


    for ver in range(1, vertex + 1):
        total = six_dimen(graph, ver)
        print_ratio(ver, total, vertex)
        reset(graph)

    
                    
main()  
