EQ = 0
LH = 1
RH = -1
class Tree:
    """docstring for Node"""
    def __init__(self, value, lchild = None, rchild = None, parent = None, balance_factor = 0, isTaller = True):
        self.value = value
        self.lchild = lchild
        self.rchild = rchild
        self.balance_factor = balance_factor
        self.isTaller = isTaller
        self.root = value
        self.parent = parent

    def getRoot(self):
        return self.root

    def getParent(self):
        return self.parent

    def setParent(self, parent):
        self.parent = parent

    def getLchild(self):
        return self.lchild

    def setLchild(self, subtree):
        self.lchild = subtree

    def getRchild(self):
        return self.rchild

    def setRchild(self, subtree):
        self.rchild = subtree

    def getValue(self):
        return self.value

    def getBF(self):
        return self.balance_factor

    def setBF(self, factor):
        self.balance_factor = factor

    def getTaller(self):
        return self.isTaller

    def setTaller(self, status):
        self.isTaller = status

def l_Rotate(tree):
    parent = tree.getParent()
    rchild = tree.getRchild()
    rlchild = rchild.getLchild()
    rchild.setLchild(tree)
    rchild.setParent(parent)
    tree.setRchild(rlchild)
    tree.setParent(rchild)
    tree = rchild

    return tree

def r_Rotate(tree):
    parent = tree.getParent()
    lchild = tree.getLchild()
    lrchild = lchild.getRchild()
    lchild.setRchild(tree)
    lchild.setParent(parent)
    tree.setParent(lchild)
    tree.setLchild(lrchild)
    tree = lchild

    return tree
        
def leftBalance(tree):
    lchild = tree.getLchild()
    if lchild.getBF() == LH:
        tree.setBF(EQ)
        lchild.setBF(EQ)
        tree = r_Rotate(tree)
    else:
        lrchild = lchild.getRchild()
        if lrchild.getBF() == EQ:
            tree.setBF(EQ)
            lchild.setBF(EQ)
        elif lrchild.getBF() == LH:
            tree.setBF(RH)
            lchild.setBF(EQ)
        else:
            lchild.setBF(LH)
            tree.setBF(EQ)
        lrchild.setBF(EQ)
        lchild = l_Rotate(lchild)
        tree.setLchild(lchild)
        tree = r_Rotate(tree)

    return tree


def rightBalance(tree):
    rchild = tree.getRchild()
    if rchild.getBF() == RH:
        tree.setBF(EQ)
        rchild.setBF(EQ)
        tree = l_Rotate(tree)
    else:
        rlchild = rchild.getLchild()
        if rlchild.getBF() == EQ:
            tree.setBF(EQ)
            rchild.setBF(EQ)
        elif rlchild.getBF() == LH:
            tree.setBF(EQ)
            rchild.setBF(RH)
        else:
            tree.setBF(LH)
            rchild.setBF(EQ)
        rlchild.setBF(EQ)
        rchild = r_Rotate(rchild)
        tree.setRchild(rchild)
        tree = l_Rotate(tree)
    return tree

def insertNode(tree, value):
    if not tree:
        newTree = Tree(value)
        return newTree
    else:
        if value == tree.getValue():
            tree.setTaller(False)
        elif value > tree.getValue():
            rchild = insertNode(tree.getRchild(), value)
            tree.setRchild(rchild)
            rchild.setParent(tree)
            if rchild.getTaller():
                if tree.getBF() == EQ:
                    tree.setBF(RH)
                    tree.setTaller(True)
                elif tree.getBF() == LH:
                    tree.setBF(EQ)
                    tree.setTaller(False)
                else:
                    tree = rightBalance(tree)
                    tree.setTaller(False)
        else:
            lchild = insertNode(tree.getLchild(), value)
            tree.setLchild(lchild)
            lchild.setParent(tree)
            if lchild.getTaller():
                if tree.getBF() == EQ:
                    tree.setBF(LH)
                    tree.setTaller(True)
                elif tree.getBF() == RH:
                    tree.setBF(EQ)
                    tree.setTaller(False)
                else:
                    tree = leftBalance(tree)
                    tree.setTaller(False)
        return tree
                

def main():
    nodes = input().split()
    tree = None
    for node in nodes:
        tree = insertNode(tree, int(node))

    print(tree.getRoot())

main()



