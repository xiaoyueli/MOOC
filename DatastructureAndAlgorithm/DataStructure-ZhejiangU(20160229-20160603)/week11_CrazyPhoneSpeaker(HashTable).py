import random
class Phone:
    """docstring for Node"""
    def __init__(self, number, amount = 1, next = None):
        self._number = number
        self._amount = amount
        self._next = next

    def setNextPhone(self, phone):
        self._next = phone

    def getNextPhone(self):
        return self._next

    def getNumber(self):
        return self._number

    def add(self):
        self._amount += 1

    def getAmount(self):
        return self._amount
        

def isPrime(number):
    for divisor in range(2, number):
        if number % divisor == 0:
            return False
    return True 

def nextPrime(number):
    number += 1
    while not isPrime(number):
        number += 1
    return number

def creatHashTable(amount):
    bucket = nextPrime(int(amount * 1.5))
    hashtable = [None] * bucket
    prime = bucket
    for dummy in range(5):
        prime = nextPrime(prime)
    coes = iniHashCodeCoefficient(prime)
    return hashtable, prime, coes[0], coes[1]

def iniHashCodeCoefficient(size):
    while True:
        coea = random.randint(1, size)
        if coea != 0:
            break
    coeb = random.randint(0, size)
    return coea, coeb

def calcuateHashCode(number, hashtable_info):
    bucket = len(hashtable_info[0])
    prime = hashtable_info[1]
    coea = hashtable_info[2]
    coeb = hashtable_info[3]

    hashcode = (number * coea + coeb) % prime % bucket

    return hashcode

def insertPhone(hashtable, phone_number, hashcode):
    if hashtable[hashcode] == None:
        hashtable[hashcode] = Phone(phone_number)
    else:
        flag = False
        phone = hashtable[hashcode]
        while True:
            if phone.getNumber() == phone_number:
                phone.add()
                flag = True
                break
            else:
                pre = phone
                phone = phone.getNextPhone()
                if phone == None:
                    break
        if not flag:
            pre.setNextPhone(Phone(phone_number))

def smallerPhoneNumber(ori, other):
    if int(other) < int(ori):
        return other

    return ori

def printOutCrazyPhoneSpeaker(hashtable):
    size = len(hashtable)
    maxAmount = 0
    phone_number = ""
    sameCrazyManCount = 0
    for idx in range(size):
        phone = hashtable[idx]
        while phone != None:
            amount = phone.getAmount()
            if amount > maxAmount:
                maxAmount = amount
                sameCrazyManCount = 1
                phone_number = phone.getNumber()
            elif amount == maxAmount:
                sameCrazyManCount += 1
                phone_number = smallerPhoneNumber(phone_number, phone.getNumber())
                    

            phone = phone.getNextPhone()

    if sameCrazyManCount > 1:
        reslut = phone_number + " " + str(maxAmount) + " " + str(sameCrazyManCount)
    else:
        reslut = phone_number + " " + str(maxAmount)

    print(reslut)

def readPhoneNumber(hashtable_info, total):

    hashtable = hashtable_info[0]

    for dummy in range(total):
        info = input().split()
        phone1 = info[0]
        hashcode = calcuateHashCode(int(phone1[7:]), hashtable_info)
        insertPhone(hashtable, phone1, hashcode)
        phone2 = info[1]
        hashcode = calcuateHashCode(int(phone2[7:]), hashtable_info)
        insertPhone(hashtable, phone2, hashcode)

    return hashtable


def main():
    total = int(input())
    hashtable_info = creatHashTable(total)
    hashtable = readPhoneNumber(hashtable_info, total)
    printOutCrazyPhoneSpeaker(hashtable)

main()
