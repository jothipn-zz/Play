package com.jothi.play;

/**
 * Created by jothi.padmanabhan on 4/9/17.
 */
public class LL<T> {
    private NodeElement<T> head;

    public LL() {
        head = null;
    }

    public void add(T data) {
        if (head == null) {
            NodeElement<T> node = new NodeElement<T>(data, null);
            head = node;
        } else {
            NodeElement<T> node = new NodeElement<T>(data, head);
            head = node;
        }
    }

    public boolean find(T data) {
        NodeElement<T> curr = head;
        while (curr != null) {
            if (curr.data.equals(data)) {
                return true;
            } else {
                curr = curr.next;
            }
        }
        return false;
    }

    public boolean delete (T data) {
        NodeElement<T> curr = head;
        NodeElement<T> prev = head;

        while (curr != null) {
            if (curr.data.equals(data)) {
                prev.next = curr.next;
                return true;
            } else {
                prev = curr;
                curr = curr.next;
            }
        }
        return false;
    }

    public void reverse() {
        NodeElement<T> curr = head;
        NodeElement<T> prev = null;

        while (curr != null) {
            NodeElement<T> next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
    }

    public void print() {
         printInternal(head);
    }

    private void printInternal(NodeElement<T> curr) {
        while (curr != null) {
            System.out.printf(curr.data.toString());
            curr = curr.next;
        }
        System.out.printf("\n");
    }

    private NodeElement<T> reverse1(NodeElement<T> e, int n) {
        NodeElement<T> curr = e;
        NodeElement<T> prev = null;
        int count = 0;
        while (curr != null) {
            NodeElement<T> temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
            count ++;
            if (count == n) {
                break;
            }
        }
        return prev;
    }

    public void swapEveryN(int n) {
        head = swapEveryN(head, n);
    }

    private NodeElement<T> getNodeAfterN(NodeElement<T> head, int n) {
        int count = 1;
        NodeElement<T> curr = head;
        while (curr != null ) {
            curr = curr.next;
            count ++;
            if (count == n)
                return (curr == null) ? null: curr.next;
        }
        return null;
    }

    private NodeElement<T> swapEveryN(NodeElement<T> e, int n) {
        if (e == null || e.next == null)
            return e;

        NodeElement<T> remaining = getNodeAfterN(e, n);

        NodeElement<T> curr = reverse1(e, n);

        if (remaining == null) {
            return curr;
        }

        NodeElement<T> rv = curr;
        while (curr.next != null) {
            curr = curr.next;
        }

        curr.next = swapEveryN(remaining, n);

        return rv;
    }

    static class NodeElement<T> {
        T data;
        NodeElement<T> next;

        public NodeElement(T d, NodeElement n) {
            data = d;
            next = n;
        }
    }

    public static void main(String[] args) {
        LL<Integer> ll = new LL<>();
        for (int i = 0; i < 5; i++)
            ll.add(i);
        ll.print();

        ll.reverse();
        ll.print();

        ll.delete(4);
        ll.print();

        ll.delete(10);
        ll.print();

        ll.head = null;

        ll.add(7);
        ll.add(6);
        ll.add(5);
        ll.add(4);
        ll.add(3);
        ll.add(2);
        ll.add(1);

        ll.print();

        ll.swapEveryN(3);
        ll.print();
    }
}
