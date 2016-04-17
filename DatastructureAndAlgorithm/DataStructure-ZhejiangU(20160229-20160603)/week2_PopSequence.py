def check_seq(cap, nums):
    seq = input().split()

    imi = []
    pop = 0
    top = 0
    num = 1
    while True:
        if imi:
            top = imi[0]
        else:
            top = 0
        if top < int(seq[pop]):
            imi.insert(0, num)
            num += 1
            if len(imi) > cap:
                return False
        elif top == int(seq[pop]):
            imi.pop(0)
            pop += 1
        else:
            return False
        
        if num > nums and not imi:
            break

    return True


def main():
    info = input().split()
    cap = int(info[0])
    nums = int(info[1])
    cases = int(info[2])


    for dummy in range(cases):
        if check_seq(cap, nums):
            print("YES")
        else:
            print("NO")
main()