
def read_connections(graph, cons):
	for dummy in range(cons):
		con_info = input().split()
		animal = int(con_info[0])
		parter = int(con_info[1])
		inc_len = int(con_info[2])
		graph[animal - 1][parter - 1] = inc_len
		graph[parter - 1][animal - 1] = inc_len 

def build_graph(num):
	graph = []
	for idx_animal in range(num):
		lst_animal = []
		for idx_parter in range(num):
			if idx_animal == idx_parter:
				lst_animal.append(0)
			else:
				lst_animal.append(float("inf"))
		graph.append(lst_animal)

	return graph

def floyd_optimal_connection(graph):
	
	length = len(graph)

	for in_ani in range(length):
		for animal in range(length):
			for parter in range(length):
				if graph[animal][parter] > graph[animal][in_ani] + graph[in_ani][parter]:
					graph[animal][parter] = graph[animal][in_ani] + graph[in_ani][parter]

def compute_optimal_animal(graph):
	
	easier_ani = float("inf")
	best_ani = []
	length = len(graph)
	for animal in range(length):
		is_ok = True
		most_dif = 0
		for parter in range(length):
			if graph[animal][parter] == float("inf"):
				is_ok = False
				break	
			if graph[animal][parter] > most_dif:
				most_dif = graph[animal][parter]
		if is_ok and most_dif < easier_ani:
			easier_ani = most_dif
			best_ani = [animal + 1, most_dif]

	return best_ani

def printout(animal):
	if not animal:
		print(0)
	else:
		print(str(animal[0]) + " " + str(animal[1]))


def main():
	animal_info = input().split()
	total = int(animal_info[0])
	connections = int(animal_info[1])

	g_animal = build_graph(total)
	read_connections(g_animal, connections)

	floyd_optimal_connection(g_animal)

	opt_animal = compute_optimal_animal(g_animal)

	printout(opt_animal)

main()


