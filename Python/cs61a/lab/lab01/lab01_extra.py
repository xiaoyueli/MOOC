"""Coding practice for Lab 1."""

# While Loops

def factors(n):
    """Prints out all of the numbers that divide `n` evenly.

    >>> factors(20)
    20
    10
    5
    4
    2
    1
    """
    #SO1:
    cnt = n
    while cnt > 0:
        if n % cnt == 0:
            print(cnt)
        cnt -= 1
    

    #SO2:
    for x in range(1, n+1):
        if n % x == 0:
            quo = n // x
            print(quo)


def falling(n, k):
    """Compute the falling factorial of n to depth k.

    >>> falling(6, 3)  # 6 * 5 * 4
    120
    >>> falling(4, 0)
    1
    >>> falling(4, 3)  # 4 * 3 * 2
    24
    >>> falling(4, 1)  # 4
    4
    """
    if k == 0:
        return 1
    else:
        pro = 1
        while k !=0:
            pro= pro * n
            n -= 1
            k -= 1
        return pro
            
        

