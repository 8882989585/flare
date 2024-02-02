# Definition for a binary tree node.
from typing import Optional


class TreeNode669:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def trimBST(self, root: Optional[TreeNode669], low: int, high: int) -> Optional[TreeNode669]:
        bfs = [(root, None, 0)]
        while len(bfs) > 0:
            node, parent, side = bfs.pop(0)
            if node.val < low or node.val > high:
                if parent is not None:
                    if node.left is not None:
                        if side == -1:
                            parent.left = node.left
                        else:
                            parent.right = node.left
                    elif node.right is not None:
                        if side == -1:
                            parent.left = node.right
                        else:
                            parent.right = node.right
                    else:
                        if side == -1:
                            parent.left = None
                        else:
                            parent.right = None
                else:
                    if node.left is not None:
                        if side == -1:
                            parent.left = node.left
                        else:
                            parent.right = node.left
                    elif node.right is not None:
                        if side == -1:
                            parent.left = node.right
                        else:
                            parent.right = node.right
                    else:
                        if side == -1:
                            parent.left = None
                        else:
                            parent.right = None
