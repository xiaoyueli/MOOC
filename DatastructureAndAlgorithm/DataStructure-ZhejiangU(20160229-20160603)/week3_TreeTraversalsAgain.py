import Stack
"""
答案有问题，待解决
"""

def postorderTraveral():
    stack = Stack()
    rt_stack = Stack()
    lst = ""
    total_node = int(input())
    for idx in range(total_node*2):
        temp = input().split()
        if temp[0] == "Push":
            stack.push(int(temp[1]))
            last = "Push"
        elif temp[0] == "Pop":
            if last == "Push":
                lst += str(stack.pop()) + " "
            if last == "Pop" and not rt_stack.length():
                rt_stack.push(stack.pop())
            elif last == "Pop":
                lst += str(rt_stack.pop()) + " "
            last = "Pop"
        else:
            print "Invalid input."

    while rt_stack.length():
        lst += str(rt_stack.pop()) + " "
    while stack.length():
        lst += str(stack.pop()) + " "
    return lst[:-1]


tree = postorderTraveral()
print tree


