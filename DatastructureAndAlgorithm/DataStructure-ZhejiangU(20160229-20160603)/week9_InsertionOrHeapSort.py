def check(lst1, lst2):
    for idx in range(len(lst1)):
        if lst1[idx] != lst2[idx]:
            return False
    return True

def printOut(lst):
    result = ""
    for idx in range(len(lst)):
        result += str(lst[idx])
        if idx != len(lst) - 1:
            result += " "
    print(result)


def insertSort(poto, candi):

    def insertSub(sub, num):
        sub.append(num)
        for idx in range(len(sub) - 1, 0, -1):
            if sub[idx] < sub[idx - 1]:
                temp = sub[idx]
                sub[idx] = sub[idx - 1]
                sub[idx - 1] = temp
            else:
                break

    if check(poto, candi):
        return              
    new = []
    flag = False

    while poto:
        insertSub(new, poto.pop(0))
        inte_r = new + poto

        if flag:
            printOut(inte_r)
            return 
        if check(candi, inte_r):
            print("Insertion Sort")
            flag = True

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

def heapSort(lst, candi):

    # (len(lst) - 1) // 2 is the idx of the last item's parent
    # travel all the nodes with children to build MaxHeap
    for root in range((len(lst) - 1) // 2, -1, -1):
        buildMaxHeap(lst, root, len(lst))

    flag = False
    for rear in range(len(lst) - 1, -1, -1):
        if flag:
            printOut(lst)
            return
        if check(lst, candi):
            print("Heap Sort")
            flag = True
        temp = lst[rear]
        lst[rear] = lst[0]
        lst[0] = temp
        buildMaxHeap(lst, 0, rear)



def scanMethod(meth1, meth2, poto, candi):
    meth1(list(poto), list(candi))
    meth2(list(poto), list(candi))

def main():
    nums = int(input())
    poto = input().split()
    candi = input().split()

    for idx in range(nums):
        poto[idx] = int(poto[idx])
        candi[idx] = int(candi[idx])

    scanMethod(insertSort, heapSort, poto, candi)

main()

