package com.jothi.play;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jothi.padmanabhan on 4/17/17.
 */
public class BSTInOrder {

    static class Node {
        int data;
        Node l;
        Node r;

        public Node(int i, Node l, Node r) {
            this.data = i;
            this.l = l;
            this.r = r;
        }
    }

    public static void main(String[] args) {
        Node l1 = new Node (1, null, null);
        Node l2 = new Node(3, null, null);
        Node m1 = new Node(2, l1, l2);
        Node l3 = new Node(5, null, null);
        Node root = new Node(4, m1, l3);

        for (int i = 0; i < 5; i++) {
            System.out.println(getIthElement(root, i));
        }

        int i = getIthElement(root, 5);
        System.out.println(i);
    }

    private static int getIthElement(Node root, int i) {
        List<Integer> iList = getNodeToList(root);
        if (iList == null || iList.size() <= i) {
            return -1;
        }
        return iList.get(i);
    }

    private static List getNodeToList(Node node) {
        if (node == null) return null;
        List<Integer> leftList = null;
        List<Integer> rightList = null;

        if (node.l != null) {
            leftList = getNodeToList(node.l);
        }

        if (node.r != null) {
            rightList = getNodeToList(node.r);
        }

        List<Integer> result = new ArrayList<>();
        if (leftList != null) {
            result.addAll(leftList);
        }
        result.add(node.data);
        if (rightList != null) {
            result.addAll(rightList);
        }

        return result;
    }
}
