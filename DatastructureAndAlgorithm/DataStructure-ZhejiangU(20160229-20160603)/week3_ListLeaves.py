import Stack
"""
list all the leaves in the order of top down, and left to right.
"""

class Tree(object):
    """docstring for Tree"""
    def __init__(self):
        self._lst = []
        
    def __str__(self):
        str_t = ""
        for item in self._lst:
            str_t += str(item) + "\n"

        return str_t

    def add(self, idx, leftchild, rightchild):
        if leftchild != "-":
            left = int(leftchild)
        else:
            left = None

        if rightchild != "-":
            right = int(rightchild)
        else:
            right = None

        self._lst.append((idx, left, right))

    def getNode(self, idx):
        return self._lst[idx]

    def getLeftChild(self, idx):
        if idx != None:
            return self._lst[idx][1]
        else:
            return None

    def getRightChild(self, idx):
        if idx != None:
            return self._lst[idx][2]
        else:
            return None


def buildTree():
    total_node = int(input())
    tree = Tree()
    roots = {}
    for idx in range(total_node):
        temp = input().split()
        tree.add(idx, temp[0], temp[1])
        roots[idx] = 0

    for idx in range(total_node):
        temp = tree.getNode(idx)
        if temp[1] != None:
            roots[temp[1]] = 1

        if temp[2] != None:
            roots[temp[2]] = 1

    for idx in range(total_node):
        if roots[idx] == 0:
            root = idx
            break
            
    return root, tree
        
def levelOrderListLeaves(info_tree):
    """
    list all the leaves in the order of top down, and left to right.
    """
    root = info_tree[0]
    tree = info_tree[1]
    node_lst = []
    leaves = ""
    while node_lst or root != None:
        leftChild = tree.getLeftChild(root)
        rightChild = tree.getRightChild(root)
        if leftChild == rightChild and leftChild == None:
            leaves += str(root) + " " 
            
        if leftChild != None:
            node_lst.append(leftChild)
        if rightChild != None:
            node_lst.append(rightChild)
        if node_lst != []:
            root = node_lst.pop(0)
        else:
            root = None

    return leaves[:-1]

def preOrderlistLeaves(info_tree):
    """
    travel tree by pre-order sequence
    return indices of leaves
    """
    root = info_tree[0]
    tree = info_tree[1]
    stack = Stack()
    leaves = ""
    while stack.length() or root != None:
        
        while root != None:
            stack.push(root)
            leftChild = tree.getLeftChild(root)
            rightChild = tree.getRightChild(root)
            if leftChild == rightChild and leftChild == None:
                leaves += str(root) + " "
            root = leftChild

        if stack.length():
            node = stack.pop()
            root = tree.getRightChild(node)

    return leaves[:-1]

def main():
    
    tree = buildTree()

    idxOfleaves = levelOrderListLeaves(tree)

    print idxOfleaves

main()