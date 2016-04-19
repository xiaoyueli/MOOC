import math

def connected_set(com_set):
    cnt = 0
    for idx in range(1, len(com_set) + 1):
        if com_set[idx] < 0:
            cnt += 1
    return cnt


def checking(com_set, cmp1, cmp2):
    root1 = find_root(com_set, cmp1)
    root2 = find_root(com_set, cmp2)

    if root1 == root2 and root1 > 0:
        return True
    return False

def find_root(com_set, cmp):
    par = cmp

    while cmp > 0:
        par = cmp
        cmp = com_set[cmp]

    return par

def connecting(com_set, cmp1, cmp2):
    if not checking(com_set, cmp1, cmp2):
        root1 = find_root(com_set, cmp1)
        root2 = find_root(com_set, cmp2)
        val1 = math.fabs(com_set[root1])
        val2 = math.fabs(com_set[root2])
        if val1 > val2:
            com_set[root2] = root1
        elif val1 < val2:
            com_set[root1] = root2
        else:
            com_set[root2] = root1
            com_set[root1] = -(val1 + 1)


def main():
    amo = int(input())

    com_set = {}
    for idx in range(1, amo + 1):
        com_set[idx] = -1

    info = input()
    while info != "S":
        info_lst = info.split() 
        act = info_lst[0]
        cmp1 = int(info_lst[1])
        cmp2 = int(info_lst[2])
        if act == "I":
            connecting(com_set, cmp1, cmp2)
        elif act == "C":
            if checking(com_set, cmp1, cmp2):
                print("yes")
            else:
                print("no")
        info = input()

    total = connected_set(com_set)
    if total == 1:
        print("The network is connected.")
    else:
        print("There are "+ str(total) + " components.")
        
        
main()