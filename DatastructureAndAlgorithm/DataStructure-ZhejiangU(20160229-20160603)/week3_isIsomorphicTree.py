import sys
class Tree():
    """docstring for Tree"""
    def __init__(self):
        self._lst = []

    def __str__(self):
        ans = ""
        for item in self._lst:
            ans += str(item)+"\n"

        return ans

    def add(self, idx, value, leftchild, rightchild):
        if leftchild == "-":
            left = None
        else:
            left = int(leftchild)

        if rightchild == "-":
            right = None
        else:
            right = int(rightchild)

        self._lst.append((idx, value, left, right))

    def getNode(self, idx):
        return self._lst[idx]

    def getValue(self, idx):
        if idx != None:
            return self._lst[idx][1]
        else:
            return None

    def getLeftChild(self, idx):
        if idx != None:
            return self._lst[idx][2]
        else:
            return None

    def getRightChild(self, idx):
        if idx != None:
            return self._lst[idx][3]
        else:
            return None

def buildTree():
    """
    Build the tree with format:
    first line is the total number of node
    follow lines: each line is the info of a node 
    with first item is value, second item is the index of left child and third item is the index of right child
	
	eg.
	8
	A 1 2
	B 3 4
	C 5 -
	D - -
	E 6 -
	G 7 -
	F - -
	H - -
    """
    total_item = int(input())
    tre = Tree()
    root = {}
    result = None
    for idx in range(total_item):
        temp = input().split()
        tre.add(idx, temp[0], temp[1], temp[2])
        root[idx] = 0

    for idx in range(total_item):
        node = tre.getNode(idx)
        if node[2] != None:
            root[node[2]] = 1
        if node[3] != None:
            root[node[3]] = 1

    for idx in range(total_item):
        if root[idx] == 0:
            result = idx
            break
            
    return result , tre

def isIsomorphic(tree1, tree2):
	"""
	if the left child and right child of tree1 and tree2 interconverts position several times, 
	tree1 and tree2 become the same tree, returns "Yes", otherwise returns "No"
	"""
   	root1 = tree1[0]
    tre1 = tree1[1]
    root2 = tree2[0]
    tre2 = tree2[1]

    if not tre1 and not tre2:
        result = "Yes"
    elif root1 == None and root2 == None:
        result = "Yes"
    elif not tre1 or not tre2 or root1 == None or root2 == None:
        result = "No"
    elif tre1.getValue(root1) != tre2.getValue(root2):
        result = "No"
    else:
        rt1l = tre1.getLeftChild(root1)
        rt1r = tre1.getRightChild(root1)
        rt2l = tre2.getLeftChild(root2)
        rt2r = tre2.getRightChild(root2)

        t1lc = tre1.getValue(rt1l)
        t1rc = tre1.getValue(rt1r)
        t2lc = tre2.getValue(rt2l)
        t2rc = tre2.getValue(rt2r)
        if set([t1lc, t1rc]) == set([t2lc, t2rc]):
      
            result_ll = isIsomorphic((rt1l, tre1), (rt2l, tre2))
            result_rr = isIsomorphic((rt1r, tre1), (rt2r, tre2))

            result_lr = isIsomorphic((rt1l, tre1), (rt2r, tre2))
            result_rl = isIsomorphic((rt1r, tre1), (rt2l, tre2))

            if result_ll == result_rr and result_ll == "Yes":
                result1 = "Yes"
            else:
                result1 = "No"

            if result_lr == result_rl and result_lr == "Yes":
                result2 = "Yes"
            else:
                result2 = "No"

            if result1 == result2 and result1 == "No":
                result = "No"
            else:
                result = "Yes"

        else:
            result = "No"

    return result

def main():
    
    tree1 = buildTree()
    tree2 = buildTree()

    result = isIsomorphic(tree1, tree2)

    print result


main()

				