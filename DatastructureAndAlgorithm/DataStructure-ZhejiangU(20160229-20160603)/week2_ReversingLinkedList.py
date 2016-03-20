import sys

def readNode():
    node = input()
    node = node.split()
    return node

def readlist(head, length):
    lst = []
    temp = []
    for dummy in range(length):
        node = readNode()
        cur_node = node[0]
        if cur_node == head:
            lst.append(node)
            next_node = node[2]
        elif lst and cur_node == next_node:
            lst.append(node)
            next_node = node[2]
        else:
            temp.append(node)

    while temp:
        node = temp.pop(0)
        if node[0] == next_node:
            lst.append(node)
            next_node = node[2]
        else:
            temp.append(node)

    return lst

def reverse_sublist(lst, sublen):
    front = lst[:sublen]
    rear = lst[sublen:]
    while front:
        node = front.pop(0)
        if rear:
            next_node = rear[0][0]
        else:
            next_node = "-1"
        node[2] = next_node
        rear.insert(0,node)
    return rear

def printList(lst):
    for item in lst:
        print(item[0]+" "+item[1]+" "+item[2])


head = input()
head = head.split()
address = head[0]
length = int(head[1])
sublen = int(head[2])

lst = readlist(address, length)
lst = reverse_sublist(lst, sublen)

printList(lst)





