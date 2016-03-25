class Stack:
    """docstring for Stack"""
    def __init__(self, lst = None):
        if lst:
            self._lst = lst
        else:
            self._lst = []
            
    def __str__(self):
        ans = ""
        for i in self._lst:
            ans += str(i) + " "
            
        return ans

    def push(self, item):
        self._lst.insert(0, item)

	def pop(self):
		if self._lst:
			return self._lst.pop(0)
		else:
			print "It's empty!"

        return None

    def top(self):
        if self._lst:
            return self._lst[0]
        else:
            print "It's empty!"

        return None
		

    def length(self):
        return len(self._lst)