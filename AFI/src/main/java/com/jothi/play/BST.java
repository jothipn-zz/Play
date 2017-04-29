package com.jothi.play;

/**
 * Created by jothi.padmanabhan on 4/9/17.
 */
public class BST<T extends Comparable> {

    private Node root;

    public BST() {};

    static class Node<T extends Comparable> {
        T data;
        Node left;
        Node right;

        public Node(T d) {
            data = d;
            left = right = null;
        }
    }

    public void insert(T data) {
        root = insert(root, data);
    }

    private Node insert(Node n, T data) {
        if (n == null) {
            n = new Node(data);
        } else if (data.compareTo(n.data) <= 0) {
            n.left = insert(n.left, data);
        } else {
            n.right = insert(n.right, data);
        }
        return n;
    }

    public boolean find(T data) {
        return find(root, data);
    }

    private boolean find(Node n, T data) {
        if (n == null) return false;

        if (n.data.compareTo(data) == 0)
            return true;
        else if (n.data.compareTo(data) > 0) {
            return find(n.left, data);
        } else {
            return find(n.right, data);
        }
    }

    public void delete(T data) {
        delete(root, data);
    }

    private Node delete(Node n, T data) {

        if (n.data.compareTo(data) == 0) {
            Node l = n.left;
            Node r = n.right;

            if (l == null & r == null)
                return null;
            else if (l == null)
                return r;
            else if (r == null)
                return l;

            Node p1 = r;
            Node p2 = r;

            while (p2.left != null) {
                p2 = p2.left;
            }
            p2.left = l;
            return p1;
        } else if (n.data.compareTo(data) < 0) {
            n.right = delete(n.right, data);
        } else
            n.left= delete(n.left, data);

        return n;
    }

    public void print(Node n) {
        if (n == null)
            return;
        print(n.left);
        System.out.printf(n.data.toString() + "\t");
        print(n.right);
    }

    public static void main(String[] args) {
        BST<Integer> tree = new BST<>();
        tree.insert (5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(9);
        tree.delete(7);
        tree.print(tree.root);
        tree.insert(2);
        tree.print(tree.root);
        tree.delete(3);
        tree.print(tree.root);

        System.out.println(tree.find (7));
        System.out.println(tree.find(-1));
    }
}
