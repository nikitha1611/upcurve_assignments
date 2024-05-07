package com.targetindia.programs;

public class BinarySearchTree {

    private class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        public TreeNode(int data) {
            this.data = data;
        }
    }

    TreeNode root = null;

    public void insert(int data) {
        root = insert(root, data);
    }

    private TreeNode insert(TreeNode node, int data) {
        if (node == null) {
            return new TreeNode(data);
        }

        if (data < node.data) {
            node.left = insert(node.left, data);
        } else if (data > node.data) {
            node.right = insert(node.right, data);
        }
        return node;
    }

    public void display() {
        display(root, 0);
        System.out.println();
    }

    private void display(TreeNode node, int level) {
        if (node != null) {
            display(node.right, level + 1);
            System.out.println(" ".repeat(4 * level) + node.data);
            display(node.left, level + 1);
        }
    }

    public void preorderDisplay() {
        preorderDisplay(root);
        System.out.println();
    }

    private void preorderDisplay(TreeNode node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preorderDisplay(node.left);
            preorderDisplay(node.right);
        }
    }

    public void postorderDisplay() {
        postorderDisplay(root);
        System.out.println();
    }

    private void postorderDisplay(TreeNode node) {
        if (node != null) {
            postorderDisplay(node.left);
            postorderDisplay(node.right);
            System.out.print(node.data + " ");
        }
    }

    public void inorderDisplay() {
        inorderDisplay(root);
        System.out.println();
    }

    private void inorderDisplay(TreeNode node) {
        if (node != null) {
            inorderDisplay(node.left);
            System.out.print(node.data + " ");
            inorderDisplay(node.right);
        }
    }

    public void delete(int data) {
        root = delete(root, data);
    }

    private TreeNode delete(TreeNode node, int data) {
        if (node == null) {
            return null;
        }

        if (data < node.data) {
            node.left = delete(node.left, data);
        } else if (data > node.data) {
            node.right = delete(node.right, data);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            node.data = smallest(node.right);
            node.right = delete(node.right, node.data);
        }
        return node;
    }

    private int smallest(TreeNode node) {
        TreeNode current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current.data;
    }

    private int largest(TreeNode node) {
        TreeNode current = node;
        while (current.right != null) {
            current = current.right;
        }
        return current.data;
    }

    public int findDepth(int value) {
        return findDepth(root, value, 0);
    }

    private int findDepth(TreeNode node, int value, int depth) {
        if (node == null) {
            return -1;
        }
        if (node.data == value) {
            return depth;
        }
        int leftDepth = findDepth(node.left, value, depth + 1);
        if (leftDepth != -1) {
            return leftDepth;
        }
        return findDepth(node.right, value, depth + 1);
    }

    public int treeHeight() {
        return treeHeight(root);
    }

    private int treeHeight(TreeNode node) {
        if (node == null) {
            return -1;
        }
        return 1 + Math.max(treeHeight(node.left), treeHeight(node.right));
    }
}
