def ageGroup(info):
    group = [0] * 51

    for age in info:
        group[age] += 1

    result = {}
    for age in range(51):
        if group[age] != 0:
            result[age] = group[age]

    return result

def printOut(group):
    
    ages = sorted(group.keys())

    for age in ages:
        print(str(age) + ":" + str(group[age]))

def main():
    total = int(input())
    info = input().split()

    for idx in range(total):
        info[idx] = int(info[idx])

    group = ageGroup(info)

    printOut(group)

main()

