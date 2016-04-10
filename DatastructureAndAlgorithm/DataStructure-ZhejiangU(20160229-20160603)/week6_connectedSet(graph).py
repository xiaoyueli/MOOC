def buildG(vertex):
	"""
	Build graph with adjcent matrix by one dimension array
	Initialize the graph without edges
	"""
	graph = []
	for dummy in range(vertex*(vertex + 1) // 2):
		graph.append(0)

	return graph

def insertEdge(graph):
	"""
	Initialize the graph with edges
	"""
    edge = input().split()
    row = int(edge[0])
    col = int(edge[1])
    if col > row:
        temp = row
        row = col
        col = temp
    graph[row * (row + 1) // 2 + col + 1 ] = 1 

def printConnected(group):
	"""
	Print out the connected vertex group
	"""
    string = "{ "
    for item in group:
        string += str(item) + " "

    string += "}"
    print(string)


def DFS(graph, ver, visited):
	"""
	Visit the graph by deep first search
	Return connected vertex group 
	"""
    visited[ver] = True
    connected_set = [ver]
    for idx in range(ver):
        if graph[ver * (ver + 1) // 2 + idx + 1] == 1 and not visited[idx]:
            connected_set.extend(DFS(graph, idx, visited))
    for idx in range(ver + 1, len(visited)):
        if graph[idx * (idx + 1) // 2 + ver + 1] == 1 and not visited[idx]:
            connected_set.extend(DFS(graph, idx, visited))
        
    return connected_set

def BFS(graph, line, visited):
	"""
	Visit the graph by broad first search
	Return connected vertex group
	"""
    connected_set = []
    while line:
        ver = line.pop(0)
        if not visited[ver]:
            visited[ver] = True
            connected_set.append(ver)
            for idx in range(ver):
                if graph[ver * (ver + 1) // 2 + idx + 1] == 1 and not visited[idx]:
                    line.append(idx)
            for idx in range(ver + 1, len(visited)):
                if graph[idx * (idx + 1) // 2 + ver + 1] == 1 and not visited[idx]:
                    line.append(idx)

    return connected_set
        

    

def printDFS(graph, vertex):
    visited = []
    for dummy in range(vertex):
        visited.append(False)
    
    for ver in range(vertex):
        if not visited[ver]:
            printConnected(DFS(graph, ver, visited))

def printBFS(graph, vertex):
    visited = []
    for dummy in range(vertex):
        visited.append(False)
    
    for ver in range(vertex):
        if not visited[ver]:
            line = [ver]
            printConnected(BFS(graph, line, visited))


                    

def main():
    info = input().split()
    vertex = int(info[0])
    edge = int(info[1])
    graph = buildG(vertex)

    for idx in range(edge):
        insertEdge(graph)

    printDFS(graph, vertex)
    printBFS(graph, vertex)

main()
