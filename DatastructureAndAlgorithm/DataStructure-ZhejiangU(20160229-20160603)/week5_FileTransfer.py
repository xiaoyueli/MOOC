"""
有瑕疵，超时运行，需优化
"""
def union(cmp1, cmp2):
    group = cmp1
    group.extend(cmp2)
    return list(set(group))

def contain(group, idx, cmpu):
    if not group:
        return False
    else:
        group = list(group)
        group.insert(0, None)
        if idx < len(group):
            if group[idx] == cmpu:
                return True
            elif cmpu > group[idx]:
                if contain(group[1:], 2 * idx, cmpu):
                    return True
                elif:
                    return contain(group[1:], 2 * idx + 1, cmpu)

    return False




def connect(group, cmp1, cmp2):
    if not group:
        group.append(union([cmp1], [cmp2]))
    else:
        temp_g1 = []
        temp_g2 = []
        for sub_g in list(group):
            if contain(sub_g, 1, cmp1):
                temp_g1 = sub_g
                group.remove(sub_g)
            if contain(sub_g, 1, cmp2):
                temp_g2 = sub_g
                group.remove(sub_g)

        if temp_g1 and temp_g2:
            group.append(union(temp_g1, temp_g2))
        elif temp_g1:
            group.append(union(temp_g1, [cmp2]))
        elif temp_g2:
            group.append(union(temp_g2, [cmp1]))
        else:
            group.append(union([cmp1], [cmp2]))
            
    return group
            

def check(group, cmp1, cmp2):
    for sub_g in group:
        if contain(sub_g, 1, cmp1) and contain(sub_g, 1, cmp2):
            return True

    return False			


def is_connected(group, total):
    cmp_sum = 0
    for sub_g in group:
        cmp_sum += len(sub_g)

    if cmp_sum  == total:
        print("The network is connected.")
    else:
        print("There are " + str(len(group) + total - cmp_sum) + " components.")


def main():

    total = int(input())
    case = input()
    group = []
    while case != "S":
        case = case.split()
        if case[0] == "I":
            group = connect(group, int(case[1]), int(case[2]))
        elif case[0] == "C":
            if check(group, int(case[1]), int(case[2])):
                print("yes")
            else:
                print("no")

        case = input()

    is_connected(group, total)
        
        
        
main()