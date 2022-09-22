
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A heap based priority queue where the root is the smallest element
 * (min PQ).
 * 
 * @author Magnus Nielsen
 */
public class MinPQ<Key_Class> {
    private Key_Class[] pq;                    // store keys at index 1..n (not 0 .. n)
    private int count;                         // number of keys currently stored.
    private boolean orderOK;		       // flag to keep internal track of when the heap is ordered / not ordered
    /**
     * Initialize a new queue with the given capacity.
     * @param  initCapacity initial capacity of the internal array
     */
    public MinPQ(int initCapacity) {
	pq = (Key_Class[]) new Object[initCapacity + 1];
	count = 0;
    }

    /**
     * Initialize a new queue with default size 100.
     */

    public MinPQ() {
	this(100);
    }

    /**
     * Initialize a priority queue based on an ArrayList of keys.
     * @param  keys ArrayList of keys.
     */
    public MinPQ(ArrayList<Key_Class> keys) {
	count = keys.size();
	pq = (Key_Class[]) new Object[count + 1];
	for (int i = 0; i < count; i++) {
	    pq[i+1] = keys.get(i);
	}
	fixHeap();
	assert isMinHeap();
    }

    /**
     * Get the size of the queue. 
     */
    public int size() {
	return count;
    }

    /**
     * Check is the queue is empty.
     * @return true if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
	return count == 0;
    }

    /**
     * Get the smallest key in the queue.
     * @return the smallest key in queue.
     * @throws MinPQError if there is no key in the queue.
     */
    public Key_Class findMin() {
	if (isEmpty()) {
	    throw new MinPQError("Priority queue empty");
	}
	return pq[1];
    }

    /**
     * Add a new key to the queue.
     * @param  x the key to be added to the queue.
     */
    public void insert(Key_Class x) {
        if (count == pq.length - 1) {
	        resize(2 * pq.length);
	    }
        int index = count + 1;
        pq[index] = x;
        
        while((parentOf(index) != 0 && index != 0) && greater(parentOf(index), index)){
            swap_indexes(index, parentOf(index));
            index = parentOf(index);
        }
        count++;
        orderOK = true;
    }

    int parentOf(int index) {
        if (index <= 0) {
            return -1;
        }
        return (index - 1) / 2;
      }

    /**
     * Remove and return the smallest key in the queue.
     * @return the smallest key in the queue.
     * @throws MinPQError if the queue is empty. 
     */
    public Key_Class delMin() {
	if (isEmpty()) {
	    throw new MinPQError("Priority queue underflow");
	}
	if (!orderOK) {
	    fixHeap();
	}
    System.out.println("DEL: " + pq[count] );;
	Key_Class min = pq[1];
	swap_indexes(1, count--);
	sink(1);
	pq[count+1] = null;     // remove the old key
	if ((count > 0) && (count == (pq.length - 1) / 4)) { // shrink the array as needed to save memory
	    resize(pq.length / 2);
	}
	assert isMinHeap();
	return min;
    }

    /**
     * Insert the key x on the first available position, without preserving the heap property.
     * Doubles are permitted.
     */
    public void toss(Key_Class x) {
	orderOK = false;
	// Double the size of the queue as needed
	if (count == pq.length - 1) {
	    resize(2 * pq.length);
	}
	// Places the item in the first available position
	pq[++count] = x;		
    }

    //***************************************************************************
    //* 			  Help Functions			       	*
    //***************************************************************************
    // Help function to assist with doubling the size of the internal array.
    private void resize(int capacity) {
	assert capacity > count;
	Key_Class[] temp = (Key_Class[]) new Object[capacity];
	for (int i = 1; i <= count; i++) {
	    temp[i] = pq[i];
	}
	pq = temp;
    }

    //***************************************************************************
    //*         Help functions to aid in restoring the heap property    	*
    //***************************************************************************

    // "Swim" the element at index k to restore heap property for that
    // particular element.
    private void swim(int k) {
	while (k > 1 && greater(k/2, k)) {
	    swap_indexes(k, k/2);
	    k = k/2;
	}
    }

    // "Sink" the element at index k to restore heap property for that
    // particular element.
    private void sink(int k) {
	while (2*k <= count) {
	    int j = 2*k;
	    if (j < count && greater(j, j+1)) {
		j++;
	    }
	    if (!greater(k, j)) {
		break;
	    }
	    swap_indexes(k, j);
	    k = j;
	}
    }

    // Restore the heap property
    private void fixHeap() {
	for (int k = count/2; k >= 1; k--) {
	    sink(k);
	}
	orderOK = true;
    }
    //***************************************************************************
    //* 	      Help functions for comparisons and moves	                *
    //***************************************************************************

    // Compare: is the element at index i greater than the one at
    // index j?
    private boolean greater(int i, int j) {
	return ((Comparable<Key_Class>) pq[i]).compareTo(pq[j]) > 0;
    }

    // Swap the data at two indexes in the internal array.
    private void swap_indexes(int i, int j) {
	Key_Class swap = pq[i];
	pq[i] = pq[j];
	pq[j] = swap;
    }

    // Is pq[1..N] a min heap?
    private boolean isMinHeap() {
	return isMinHeap(1);
    }

    // Is the subtree of pq[1..n] rooted at k a min heap?
    private boolean isMinHeap(int k) {
	if (k > count) {
	    return true;
	}
	int left = 2*k;
	int right = 2*k + 1;
	if (left  <= count && greater(k, left))  {
	    return false;
	}
	if (right <= count && greater(k, right)) {
	    return false;
	}
	return isMinHeap(left) && isMinHeap(right);
    }

}
