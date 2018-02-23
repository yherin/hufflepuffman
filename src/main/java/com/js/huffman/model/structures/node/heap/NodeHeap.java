package com.js.huffman.model.structures.node.heap;

import com.js.huffman.model.structures.node.Node;
import com.js.huffman.model.structures.node.NodePriorityComparator;
import java.util.logging.Logger;

/**
 *
 * @author jack
 */
public class NodeHeap {

    private static final Logger LOG = Logger.getLogger(NodeHeap.class.getName());

    private final static int TOP = 1;
    final Node[] heap;
    final int capacity;
    final double loadFactor = 0.75f;
    private int size;
    int head;
    private final NodePriorityComparator npc;

    public NodeHeap(final NodePriorityComparator npc, final int numberOfNodes) {
        this.npc = npc;
        this.capacity = (numberOfNodes * 2) + 1; //index starting at 1, possible 2 children for a node
        this.heap = new Node[this.capacity];
        this.size = 0;
    }

    public final Node poll() {
        final Node polling = heap[TOP];
        heap[TOP] = heap[size];
        heap[size] = null;
        this.size--;
        if (size > 1) {
            heapify(TOP);
        }
        return polling;
    }

    public final void add(final Node node) {
        size++;
        heap[size] = node;
        final int current = size;
        final int parent = parent(current);
        swapWithParentNodeIfLessThan(current, parent);

    }

    private int left(final int position) {
        return (2 * position);
    }

    private boolean leaf(final int position) {
        boolean firstCondition = (position * 2) >= this.size;
        boolean secondCondition = position < this.size;
        return firstCondition && secondCondition;
    }

    private int right(final int position) {
        return (2 * position) + 1;
    }

    private int parent(final int position) {
        return (position / 2);
    }

    private void heapify(final int position) {
        if (!leaf(position)) {
            heapifyChildNodes(position);
        }
    }

    private void swap(final int x, final int y) {
        final Node temp;
        temp = heap[x];
        heap[x] = heap[y];
        heap[y] = temp;
    }

    private void heapifyChildNodes(final int position) {
        final int left = left(position);
        final int right = right(position);
        final Node l = heap[left];
        final Node parent = heap[position];
        final Node r = heap[right];
        if (hasNoChildren(l, r)) {
            return;
        }
        final boolean leftSmaller = (this.npc.compare(l, parent) == -1) && (l != null);
        final boolean rightSmaller = (this.npc.compare(r, parent) == -1) && (r != null);

        if (leftSmaller || rightSmaller) {
            if (r == null | this.npc.compare(l, r) == -1) {
                swap(left, position);
                heapify(left);
            } else {
                swap(right, position);
                heapify(right);
            }

        }
    }

    private boolean hasNoChildren(final Node l, final Node r) throws IllegalStateException {
        if (l == null) {
            if (r != null) {
                throw new IllegalStateException("Somehow a node had no left child, but a right child.");
            }
            return true;
        }
        return false;
    }

    private void swapWithParentNodeIfLessThan(int current, int parent) {
        final Node c = heap[current];
        final Node p = heap[parent];
        if (p == null) {
            return;
        }
        if (this.npc.compare(c, p) == -1) {
            swap(current, parent);
            final int child = parent;
            final int newParent = parent(child);
            swapWithParentNodeIfLessThan(child, newParent);
        }

    }

    public int getSize() {
        return this.size;
    }

    public boolean contains(Object o) {
        try {
            Node x = (Node) o;
            for (int i = 1; i <= this.size; i++) {
                final Node element = heap[i];
                if (element == null) {
                    return false;
                }
                if (element.equals(x)) {
                    return true;
                }
            }
            return false;
        } catch (ClassCastException e) {
            return false;
        }

    }
}
