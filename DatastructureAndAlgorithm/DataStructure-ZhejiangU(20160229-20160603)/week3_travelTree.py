import Stack
"""
Non-recursive method of traveling tree by pre-order, mid-order and post-order sequences
"""
class Tree(object):
    """docstring for Tree"""
    def __init__(self, value, left_child = None , right_child = None):
        self._value = value
        self._left = left_child
        self._right = right_child
        self._tree = [value, left_child, right_child]

    def __str__(self):
        ans = ""
        for item in self._tree:
            if item:
                ans += str(item)
        return ans

    def getLeftChild(self):
        return self._left

    def getRightChild(self):
        return self._right

    def getValue(self):
        return self._value

    def clone(self):
        newTree = Tree(self.getValue(), self.getLeftChild(), self.getRightChild())
        return newTree

    def preOrderTravel(self):
        """
        Travel tree by pre-order sequence
        """
        newTree = self.clone()
        if not newTree:
            return
        stack = Stack()
        while newTree or stack.length():
            while (newTree):
                print newTree.getValue()
                stack.push(newTree)
                newTree = newTree.getLeftChild()

            if stack.length():
                node = stack.pop()
                newTree = node.getRightChild()

    def midOrderTravel(self):
        """
        Travel tree by mid-order sequence
        """
        newTree = self.clone()
        if not newTree:
            return
        stack = Stack()
        while newTree or stack.length():
            while (newTree):
                stack.push(newTree)
                newTree = newTree.getLeftChild()

            if stack.length():
                node = stack.pop()
                print node.getValue()
                newTree = node.getRightChild()
				
    def postOrderTravel(self):
        """
        Travel tree by post-order sequence
        """
        newTree = self.clone()
        if not newTree:
            return
        stack= Stack()
        last = None
        stack.push(newTree)

        while stack.length():
            item = stack.top()
            if not item.getLeftChild() and not item.getRightChild() \
                or last == item.getLeftChild() and not item.getRightChild() \
                or last == item.getRightChild():
                # visit the node who is either leaf node 
                # or node without right child and the left child has been visited
                # or node whose right child is the last one been visited
                print stack.pop().getValue()             
                last = item
            else:
                # push right child into stack first
                if item.getRightChild():
                    stack.push(item.getRightChild())

                # push left child into stack second
                if item.getLeftChild():
                    stack.push(item.getLeftChild())


    def postOrderTravel2(self):
        """
        second method of traveling tree by post-order sequence
        """
        newTree = self.clone()
        if not newTree:
            return

        stack = Stack()
        # push tree into stack twice
        stack.push(newTree)
        stack.push(newTree)

        while stack.length():
            # pop out the first node
            item = stack.pop()

            # first pop pushes node's children into stack
            # second pop visits the node
            # 
            # if item equals the top item of stack means that item's children have not been visited yet
            # then push them into stack
            if stack.length() and item == stack.top():
                if item.getRightChild():
                    stack.push(item.getRightChild())
                    stack.push(item.getRightChild())

                if item.getLeftChild():
                    stack.push(item.getLeftChild())
                    stack.push(item.getLeftChild())
            else:
                print item.getValue()


                    

				


a = Tree(4)    
b = Tree(5)
c = Tree(6)
d = Tree(7)
e = Tree(2, a, b)
f = Tree(3, c, d)
t = Tree(1, e, f)

t.preOrderTravel()
print
t.midOrderTravel()
print
t.postOrderTravel()