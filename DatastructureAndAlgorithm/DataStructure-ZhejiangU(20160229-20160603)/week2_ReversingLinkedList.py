import sys
"""
最后两组测试数据没通过
"""
def readList():
    """
    Read information of single list, coresponding to the format:
    First line: the address of the first node, the number of total nodes and the number should be reversed 
    Lines follow: the address of current node, the value of the node and the address of the next node
    e.g.

    11111 3 2
    22222 2 33333
    33333 3 -1
    11111 1 22222

    output:
    22222 2 11111
    11111 1 33333
    33333 3 -1
    """
    lst_info = input().split()
    head = lst_info[0]
    length = int(lst_info[1])
    reverse = int(lst_info[2])

    single_lst = {}
    for dummy in range(length):
        node_info = input().split()
        addr = node_info[0]
        single_lst[addr] = [node_info[1], node_info[2]]

    return head, single_lst, reverse


def reversingLinkedList(lst_info):
    """
    Reverse every reverse_num items of the single list
    """
    head = lst_info[0]
    lst = lst_info[1]
    reverse_num = lst_info[2]

    length = len(lst)
    times = int(length / reverse_num)
 
    temp_head = head
    front = head
    rear = lst[front][1]

    for dummy_1 in range(times):
        for dummy_2 in range(reverse_num - 1):
            temp = lst[rear][1]
            lst[rear] = [lst[rear][0], front]
            front = rear
            rear = temp       
        if dummy_1 == 0:
            head = front       
        else:
            lst[preseq_rear] = [lst[preseq_rear][0], front]
        front = rear
        if front != "-1":
            rear = lst[front][1]
        lst[temp_head] = [lst[temp_head][0], front]
        preseq_rear = temp_head
        temp_head = front

    for dummy_3 in range(length):
        print(head +" " + lst[head][0] + " " + lst[head][1])
        head = lst[head][1]
  
        
lst_info = readList()
reversingLinkedList(lst_info)





