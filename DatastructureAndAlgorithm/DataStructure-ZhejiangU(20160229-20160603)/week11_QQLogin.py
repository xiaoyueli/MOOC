import math
class Account:
    """docstring for Account"""
    def __init__(self, qq, pw, nextqq = None):
        self._qq = qq
        self._pw = pw
        self._nextqq = nextqq

    def getQQ(self):
        return self._qq

    def getPw(self):
        return self._pw

    def setNext(self, nextqq):
        self._nextqq = nextqq

    def getNext(self):
        return self._nextqq
        
def isPrime(num):
    if num < 2:
        return False
    for divisor in range(2,num):
        if num % divisor == 0:
            return False
    return True

def nextPrime(num):
    while not isPrime(num):
        num += 1

    return num

def buildHashTable(size):
    size = nextPrime(int(size * 1.5))
    table = [None] * size

    return table

def calculateHashcode(table, qq):
    bucket = len(table)
    prime = bucket
    cnt = 5
    while cnt > 0:
        prime = nextPrime(prime)
        cnt -= 1

    coea = prime % bucket
    coeb = prime - bucket

    return (coea * qq - coeb) % prime % bucket

def find(table, hashcode, qq):
    acc = table[hashcode]
    if acc is None:
        return None
    else:
        while acc is not None:
            if acc.getQQ() == qq:
                return acc
            else:
                acc = acc.getNext()
        return None

def insertItem(table, hashcode, acc):
    temp = table[hashcode]
    if temp is None:
        table[hashcode] = acc
    else:
        while temp is not None:
            pre = temp
            temp = temp.getNext()
        pre.setNext(acc)


def readInfo(table, size):
    for dummy in range(size):
        info = input().split()
        comm = info[0]
        qq = int(info[1])
        pw = info[2]
        hashcode = calculateHashcode(table, qq)
        if comm == "N" and qq > 1000 and qq < math.pow(10, 10) - 1 and len(pw) >= 6 and len(pw) <= 16:
            if find(table, hashcode, qq) is None:
                acc = Account(qq, pw)
                insertItem(table, hashcode, acc)
                print("New: OK")
            else:
                print("ERROR: Exist")
        elif comm == "L":
            if qq <= 1000 or qq >= math.pow(10, 10):
                print("ERROR: Not Exist")
            else:
                acc = find(table, hashcode, qq)
                if acc is None:
                    print("ERROR: Not Exist")
                elif acc.getPw() == pw:
                    print("Login: OK")
                elif acc.getPw() != pw:
                    print("ERROR: Wrong PW")
                    

def main():
    size = int(input())

    table = buildHashTable(size)

    readInfo(table, size)

main()

