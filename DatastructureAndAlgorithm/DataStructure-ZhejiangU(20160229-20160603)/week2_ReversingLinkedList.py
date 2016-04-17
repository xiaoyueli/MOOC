
 """
 运行超时
 """

def printout(lst, head):
    
    for dummy in range(len(lst)):
        print(head + " " + " ".join(lst[head]))
        head = lst[head][1]


def reversing_linked_list(head, lst, length, sub_len):

    if length == 1 or sub_len == 1:
        return head

    old_head = head
    next_add = lst[head][1]

    times = length // sub_len
    cnt = 1
    rear = None
    temp_head = None
    while True:
        for dummy in range(sub_len - 1):  
            next_node = lst[next_add]
            temp = next_add
            next_add = next_node[1]
            next_node[1] = head
            head = temp

        if times == 1:
            lst[old_head][1] = next_add
            return head
        
        if rear:
            lst[rear][1] = head
            lst[temp_head][1] = next_add 

        if cnt == 1:
            rear = old_head
            old_head = head
        
        if cnt < times:
            temp_head = next_add
            head = next_add
            next_add = lst[head][1]
        else:
            break

        cnt += 1

    return old_head

def get_linked_list(lst, head):
    new = {}
    while head != "-1":
        new[head] = lst[head]
        head = lst[head][1]

    return new

def build_list(total):

    lst = {}

    for dummy in range(total):
        node_info = input().split()
        addr = node_info[0]
        value = node_info[1]
        next_add = node_info[2]
        lst[addr] = [value, next_add]

    return lst

def main():
    info = input().split()
    head = info[0]
    total_num = int(info[1])
    sublen = int(info[2])

    lst = build_list(total_num)

    linked_list = get_linked_list(lst, head)

    new_head = reversing_linked_list(head, linked_list, len(linked_list), sublen)

    printout(linked_list, new_head)
    
main()




