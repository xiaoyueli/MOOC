"""
最大随机答案错误
"""

def isPrime(number):
	if number < 2:
		return False
	for divisor in range(2, number):
		if number % divisor == 0:
			return False

	return True

def nextPrime(number):
	while not isPrime(number):
		number += 1

	return number

def readData(table, size):
	data_info = input().split()
	pos_info = {}
	for idx in range(size):
		data_info[idx] = int(data_info[idx])
		hashcode = data_info[idx] % len(table)
		position = insertItem(table, data_info[idx], hashcode)
		if position == None:
			pos_info[data_info[idx]] = "-"
		else:
			pos_info[data_info[idx]] = str(position)
	return data_info, pos_info
	
def insertItem(table, data, hashcode):
	if table[hashcode] == False:
		table[hashcode] = True
		return hashcode
	else:
		cnt = 1
		visited = [False] * len(table)
		while True:
			if visited[hashcode] == True:
				return None
			hashcode += cnt * cnt
			hashcode %= len(table)
			if table[hashcode] == False:
				table[hashcode] = True
				return hashcode
			else:
				cnt += 1
			visited[hashcode] = True

def printOutDataPosition(data_infos):
	data_info = data_infos[0]
	pos_info = data_infos[1]

	result = ""
	for data in data_info:
		result += pos_info[data] + " "

	print(result[:-1])

def main():
	info = input().split()

	table_size = int(info[0])
	data_size = int(info[1])

	table_size = nextPrime(table_size)

	hash_table = [False] * table_size
	data_infos = readData(hash_table, data_size)

	printOutDataPosition(data_infos)

main()
