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

def mergeSort(poto, candi):

    def merge(poto, other, fir, sec, sec_end):
        idx_f = fir
        idx_s = sec
        idx_o = fir
        while idx_f < sec and idx_s <= sec_end:
            if poto[idx_f] <= poto[idx_s]:
                other[idx_o] = poto[idx_f]
                idx_f += 1
            else:
                other[idx_o] = poto[idx_s]
                idx_s += 1

            idx_o += 1

        while idx_f < sec:
            other[idx_o] = poto[idx_f]
            idx_o += 1
            idx_f += 1

        while idx_s <= sec_end:
            other[idx_o] = poto[idx_s]
            idx_o += 1
            idx_s += 1


            
    
    def mergeSub(poto, other, length):
        idx = 0
        while idx < len(poto)- 2 * length:
            merge(poto, other, idx, idx + length, idx + 2 * length -1)
            idx += 2 * length
        if idx + length < len(poto):
            merge(poto, other, idx, idx + length, len(poto) - 1)
        else:
            while idx < len(poto):
                other[idx] = poto[idx]
                idx += 1

    length = 1
    other = [0] * len(poto)
    flag = False
    if check(poto, candi):
    	return

    while length < len(poto):
        mergeSub(poto, other, length)
        if flag:
            print("Merge Sort")
            printOut(other)
            return 
        if check(candi, other):
            flag = True
        length *= 2
        mergeSub(other, poto, length)
        if flag:
            print("Merge Sort")
            printOut(poto)
            return 
        if check(candi, poto):
            flag = True
        length *= 2



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

    scanMethod(insertSort, mergeSort, poto, candi)

main()

