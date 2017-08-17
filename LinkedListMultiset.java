import java.io.PrintStream;
import java.util.*;

public class LinkedListMultiset<T> extends Multiset<T>
{
	protected Node mHead;
    protected Node mTail;
    protected int mLength;
	
	public LinkedListMultiset() {
		// Implement me!
		mHead = null; 
        mTail = null;
        mLength = 0;
	} // end of LinkedListMultiset()
	

	
	public void add(T item) {
		// Implement me!
		Node newNode = new Node((String) item);
		
		// If head is empty, then list is empty and head and tail references need to be initialised.
		if (mHead == null) {
            mHead = newNode;
            mTail = newNode;
        }
		 // otherwise, add node to the head of list.
		else {
	        Node currNode = mHead;
	        Node parentNode = null;
	      ///if already exist
	        while (currNode != null) {
	        	
	            if (currNode.getValue().
	                    equals(newNode.getValue())) {
	                currNode.addCounter();
	                return;
	            }
	            parentNode = currNode;
	            currNode = currNode.getNext();
	        }
	        parentNode.setNext(newNode);
	    }
	    mLength++;
		return;
	} // end of add()
	
	
	public int search(T item) {
		// Implement me!		
		
		Node currNode = mHead;
		int counter=0;
		
        while (currNode != null) {
            if (currNode.getValue().equals((String) item)) {
                return currNode.getCounter();
            }
            if(currNode.getNext() != null){
            currNode = currNode.getNext();
            }else return 0;
            
        }
		
        
		// doesn't exist, return 0
		return 0;
	} // end of add()
	
	
	public void removeOne(T item) {
		// Implement me!
		
		Node currNode = mHead;
        Node lastNode = null;

        while (currNode != null) {
            if (currNode.getValue().equals((String) item)) {
                currNode.minusCounter();
                if (currNode.getCounter() == 0) {
                    if (currNode == mHead)
                        mHead = currNode.getNext();
                    else
                        lastNode.setNext
                                (currNode.getNext());
                    mLength--;
                }
                return;
            }
            lastNode = currNode;
            currNode = currNode.getNext();
        }
		
	} // end of removeOne()
	
	
	public void removeAll(T item) {
		// Implement me!
		
		 Node currNode = mHead;
	        Node lastNode = null;

	        while (currNode != null) {
	            if (currNode.getValue().equals((String) item)) {
	                if (currNode == mHead)
	                    mHead = currNode.getNext();
	                else
	                    lastNode.setNext(currNode.getNext());
	                mLength--;
	                return;
	            }
	            lastNode = currNode;
	            currNode = currNode.getNext();
	        }
		
	} // end of removeAll()
	
	
	public void print(PrintStream out) {
		// Implement me!
		
		Node currNode = mHead;

        while (currNode != null) {
            out.printf("%s | %d\n", currNode.getValue()
                    , currNode.getCounter());
            currNode = currNode.getNext();
        }
		
	} // end of print()
	
	
	  private class Node {
	        protected String mValue;
	        protected Node mNext;
	        protected Node mPrev;

	        int counter;

	        public Node(String value) {
	            mValue = value;
	            mNext = null;
	            mPrev = null;
	            counter = 1;
	        }

	        public void addCounter() {
	            counter++;
	        }

	        public void minusCounter() {
	            counter--;
	        }

	        public int getCounter() {
	            return counter;
	        }

	        public String getValue() {
	            return mValue;
	        }

	        public Node getNext() {
	            return mNext;
	        }
	        
	        public Node getPrev(){
	        	return mPrev;
	        }

	        public void setValue(String value) {
	            mValue = value;
	        }

	        public void setNext(Node next) {
	            mNext = next;
	        }
	        
	        public void setPrev(Node prev) {
	            mPrev = prev;
	        }
	    }
	
} // end of class LinkedListMultiset