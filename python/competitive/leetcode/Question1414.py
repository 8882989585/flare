class Question1414:
    def findMinFibonacciNumbers(self, k: int) -> int:
        import collections
        import bisect

        # initializing dictionary
        test_dict = collections.OrderedDict()
        x = 0
        y = 1
        while x <= k and y <= k:
            t = y
            y = x + y
            test_dict[y] = y
            x = t
        test_dict = list(test_dict.keys())
        count = 0
        i = k
        while i > 0:
            i = i - test_dict[bisect.bisect_right(test_dict, i) - 1]
            count = count + 1
        return count


print(Question1414().findMinFibonacciNumbers(19))
