"""
第二组数据未通过
"""

def buildTree(nodes):
    info = input().split()
    tree = {}
    root = int(info[0])
    tree[root] = [None, None, 0]
    for idx in range(1, nodes):
        insertNode(tree, root, int(info[idx]))
    return tree, root

def insertNode(tree, root, value):
    assert tree, "Tree can not be empty."
    if value < root:
        if tree[root][0] == None:
            tree[root][0] = value
            tree[value] = [None, None, 0]
        else:
            insertNode(tree, tree[root][0], value)
    elif value > root:
        if tree[root][1] == None:
            tree[root][1] = value
            tree[value] = [None, None, 0]
        else:
            insertNode(tree, tree[root][1], value)
    else:
        assert False, "The same node already exists."	
    
def isSameTree(proto, root, new_tseq):
    tree = dict(proto)
    for nodeValue in new_tseq:
        if not checkNode(tree, root, int(nodeValue)):
            return False
        
    return True 

def checkNode(tree, root, nodeValue):
    if root == nodeValue and not tree[root][2]:
        tree[root][2] = 1
        return True
    elif root < nodeValue and tree[root][2]:
        return checkNode(tree, tree[root][1], nodeValue)
    elif root > nodeValue and tree[root][2]:
        return checkNode(tree, tree[root][0], nodeValue)
    else:
        return False


def main():
    info = input().split()
    nodes_num = int(info[0])

    while nodes_num != 0:
        if len(info) > 1:
            trees_num = int(info[1])
        proto_tree = buildTree(nodes_num)
        tree = proto_tree[0]
        root = proto_tree[1]
        for _ in range(trees_num):
            tree_seq = input().split()
            if isSameTree(tree, root, tree_seq):
                print("Yes")
            else:
                print("No")

        info = input().split()
        nodes_num = int(info[0])
main()
