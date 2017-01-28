package leet114;

import base.TreeNode;

/**
 * Created by sansword on 2017/1/28.
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        } else {
            flatten(root.left);
            flatten(root.right);
            root.right = merge(root.left, root.right);
            root.left = null;
        }
    }

    private TreeNode merge(TreeNode flattenLeft, TreeNode flattenRight) {
        if (flattenLeft == null) {
            return flattenRight;
        } else {
            if (flattenRight != null) {
                TreeNode current = flattenLeft;
                while(current.right != null) {
                    current = current.right;
                }
                current.left = null;
                current.right = flattenRight;
            }
            return flattenLeft;
        }
    }

    public static void main(String[] args) {
        TreeNode testCase = new TreeNode(1);
        new Solution().flatten(testCase);
    }
}