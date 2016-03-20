def tree(root, branches=[]):
	for branch in branches:
		assert is_tree(branch)
	return [root] + list(branches)

def branches(tree):
	return tree[1:]

def is_tree(tree):
	if type(tree) != list or len(tree) < 1:
		return False
	for branch in branches(tree):
		if not is_tree(branch):
			return False
	return True

def root(tree):
	return tree[0]

def is_leaf(tree):
	return not branches(tree)

def square_tree(trees):
	return tree(root(trees)**2, [square_tree(branch) for branch in branches(trees)])

def hight(trees):
	if is_leaf(trees):
		return 0
	return max([hight(branch) for branch in branches(trees)])+1
		
def tree_size(trees):
	return sum([tree_size(branch) for branch in branches(trees)]) + 1

def tree_max(trees):
	return max([root(trees)] + [tree_max(branch) for branch in branches(trees)])

def find_path(tree, value):
	if root(tree) == value:
		return [root(tree)]
	else:
		for branch in branches(tree):
			if find_path(branch, value):
				return [root(tree)] + find_path(branch, value) 


	pass

def hailstone_tree(n, hight):
	if hight == 0:
		return tree(n)
	else:
		branches = [hailstone_tree(2*n, hight-1)]
		if (n-1) % 3 == 0 and ((n-1) // 3 )% 2 != 0 and ((n-1) // 3 ) != 1:
			branches += [hailstone_tree((n-1)//3, hight-1)]
		return tree(n, branches)