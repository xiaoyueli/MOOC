def diff(x, y, z):
    """Return whether one argument is the difference between the other two.

    x, y, and z are all integers.

    >>> diff(5, 3, 2) # 5 - 3 is 2
    True
    >>> diff(2, 3, 5) # 5 - 3 is 2
    True
    >>> diff(2, 5, 3) # 5 - 3 is 2
    True
    >>> diff(-2, 3, 5) # 3 - 5 is -2
    True
    >>> diff(-5, -3, -2) # -5 - -2 is -3
    True
    >>> diff(-2, 3, -5) # -2 - 3 is -5
    True
    >>> diff(2, 3, -5)
    False
    >>> diff(10, 6, 4)
    True
    >>> diff(10, 6, 3)
    False
    """
    "*** YOUR CODE HERE ***"
    if x-y == z or y-x ==z:
        return True
    elif y-z == x or z-y == x:
        return True
    elif x-z == y or z-x == y:
        return True
    else:
        return False

def abundant(n):
    """Print all ways of forming positive integer n by multiplying two positive
    integers together, ordered by the first term. Then, return whether the sum
    of the proper divisors of n is greater than n.

    A proper divisor of n evenly divides n but is less than n.

    >>> abundant(12) # 1 + 2 + 3 + 4 + 6 is 16, which is larger than 12
    1 * 12
    2 * 6
    3 * 4
    True
    >>> abundant(14) # 1 + 2 + 7 is 10, which is not larger than 14
    1 * 14
    2 * 7
    False
    >>> abundant(16)
    1 * 16
    2 * 8
    4 * 4
    False
    >>> abundant(20)
    1 * 20
    2 * 10
    4 * 5
    True
    >>> abundant(22)
    1 * 22
    2 * 11
    False
    >>> r = abundant(24)
    1 * 24
    2 * 12
    3 * 8
    4 * 6
    >>> r
    True
    """
    
    sum = 0
    i = 1
    k = n
    while i < k :
        if n % i == 0:
            k = n // i
           # print("k=%d, i= %d"%(k, i))
            print("%d * %d"%(i,k))
            if i != k:
                sum = sum + i + k
            else:
                sum= sum + i
        i += 1
                   
    sum = sum - n
    return sum > n


def amicable(n):
    """Return the smallest amicable number greater than positive integer n.

    Every amicable number x has a buddy y different from x, such that
    the sum of the proper divisors of x equals y, and
    the sum of the proper divisors of y equals x.

    For example, 220 and 284 are both amicable because
    1 + 2 + 4 + 5 + 10 + 11 + 20 + 22 + 44 + 55 + 110 is 284, and
    1 + 2 + 4 + 71 + 142 is 220

    >>> amicable(5)
    220
    >>> amicable(220)
    284
    >>> amicable(284)
    1184
    >>> r = amicable(5000)
    >>> r
    5020
    """
    org = n + 1
    sign = True
    def ami(x):
        k = x
        i = 1
        sum = 0
        while i < k:
            if x % i == 0:
                k= x // i
                if i != k :
                    sum= sum + i + k
                else:
                    sum= sum + i
            i += 1
        sum= sum - x
        return sum
   
    while sign:
        
        buddy= ami(org)
        buddy_ami= ami(buddy)
        if buddy_ami==org and org != buddy:
            sign= False
        else:
            org += 1
      
        
    return org       
        

