def readNums(nums):
    lst = input().split()

    for idx in range(nums):
        lst[idx] = int(lst[idx])

    return lst

def printList(lst):
    result = ""
    for item in lst[:-1]:
        result += str(item) + " "

    result += str(lst[-1])
    print(result)

def bubbleSort(lst, nums):
    
    for rear in range(nums - 1, -1, -1):
        for idx in range(rear):
            if lst[idx] > lst[idx + 1]:
                temp = lst[idx]
                lst[idx] = lst[idx + 1]
                lst[idx + 1] = temp

    return lst



def insertSort(lst):

    def insertSub(sub, num):
        sub.append(num)
        for idx in range(len(sub) - 1, 0, -1):
            if sub[idx] < sub[idx - 1]:
                temp = sub[idx]
                sub[idx] = sub[idx - 1]
                sub[idx - 1] = temp
            else:
                break;				
    new = []
    for item in lst:
        insertSub(new, item)

    return new

def shellSort(lst, nums):
    shell = nums
    while shell > 1:
        shell //= 2
        if shell % 2 == 0:
            shell -= 1
        for idx in range(shell):
            temp_i = idx
            temp = []
            while temp_i < len(lst):
                temp.append(lst[temp_i])
                temp_i += shell
            temp = insertSort(temp)
            temp_i = idx
            while temp_i < len(lst):
                lst[temp_i] = temp.pop(0)
                temp_i += shell

    return lst

def buildMaxHeap(lst, root, length):
    lson = root * 2 + 1
    value = lst[root]
    while lson <= length - 1:
        next_r = lson
        if lson < length - 1 and lst[lson] < lst[lson + 1]:
            next_r = lson + 1
        if value < lst[next_r]:
            lst[root] = lst[next_r]
            root = next_r
            lson = root * 2 + 1
        else:
            break
    lst[root] = value

def heapSort(lst, nums):

    # (nums - 1) // 2 is the idx of the last item's parent
    # travel all the nodes with children to build MaxHeap
    for root in range((nums - 1) // 2, -1, -1):
        buildMaxHeap(lst, root, nums)


    for rear in range(nums - 1, -1, -1):
        temp = lst[rear]
        lst[rear] = lst[0]
        lst[0] = temp
        buildMaxHeap(lst, 0, rear)

    return lst


def unionSort(lst, nums):

    def union(lst, other, fir, sec, sec_end):
        idx_f = fir
        idx_s = sec
        idx_o = fir
        while idx_f < sec and idx_s <= sec_end:
            if lst[idx_f] <= lst[idx_s]:
                other[idx_o] = lst[idx_f]
                idx_f += 1
            else:
                other[idx_o] = lst[idx_s]
                idx_s += 1

            idx_o += 1

        while idx_f < sec:
            other[idx_o] = lst[idx_f]
            idx_o += 1
            idx_f += 1

        while idx_s <= sec_end:
            other[idx_o] = lst[idx_s]
            idx_o += 1
            idx_s += 1


            
    
    def unionSub(lst, other, length):
        idx = 0
        while idx < len(lst)- 2 * length:
            union(lst, other, idx, idx + length, idx + 2 * length -1)
            idx += 2 * length
        if idx + length < len(lst):
            union(lst, other, idx, idx + length, len(lst) - 1)
        else:
            while idx < len(lst):
                other[idx] = lst[idx]
                idx += 1

    length = 1
    other = [0] * nums

    while length < nums:
        unionSub(lst, other, length)
        length *= 2
        unionSub(other, lst, length)
        length *= 2

    return lst




def main():
    nums = int(input())

    lst = readNums(nums)

    #sorted_list = bubbleSort(lst, nums)
    #sorted_list = insertSort(lst)
    #sorted_list = shellSort(lst, nums)
    sorted_list = heapSort(lst, nums)
    #sorted_list = unionSort(lst, nums)

    printList(sorted_list)

main()
