import java.io.PrintStream;
import java.util.*;


public class SortedLinkedListMultiset<T> extends Multiset<T>
{
	protected Node mHead;
    protected Node mTail;
    protected Node mNew;
    protected int mLength;
    
	public SortedLinkedListMultiset() {
		// Implement me!
		mHead = null; 
        mTail = null;
        mNew = null;
        mLength = 0;
	} // end of SortedLinkedListMultiset()
	
	
	public void add(T item) {
		// Implement me!/////////////////////////////////////////////////////////////////
		Node newNode = new Node((String) item);
		Node currNode = mHead;
		
		// If head is empty, then list is empty and head and tail references need to be initialised.
		if (mHead == null) {
            mHead = newNode;
            mTail = newNode;
        }
		
		//if not, add new node in sorted way
		else {
			//check if node already exist      
			while (currNode != null) { 
			        	
	            if (currNode.getValue().equals(newNode.getValue())) {
	            	currNode.addCounter();
			        mLength++; /////////////////increase if counter increase?
			        return;
			        }
			    currNode = currNode.getNext();
			            
			 }
			        
			currNode = mHead;
			
			//if new node is smallest one
			if(newNode.getValue().compareTo(mHead.getValue())<0){
				
				 newNode.setNext(mHead);
			     mHead.setPrev(newNode);
		         mHead = newNode;
		         mLength++;
			     return;
			}
			
			//if new node is biggest one
			if(newNode.getValue().compareTo(mTail.getValue())>0){
				
				 newNode.setPrev(mTail);
			     mTail.setNext(newNode);
		         mTail = newNode;
		         mLength++;
			     return;
			}
			
			///else add new node in sorted way
			while (currNode != null){
				
				if (currNode.getValue().compareTo(newNode.getValue())>0) {

					newNode.newCounter();
					
					Node tempNode = new Node((String) item);
					tempNode = currNode.getPrev();
					
					newNode.setNext(currNode);
					newNode.setPrev(tempNode);
					currNode.setPrev(newNode);
					tempNode.setNext(newNode);
					
					mLength++;
				    return;
					
				}
				currNode = currNode.getNext();
			}
	         
		}
		// Implement me!
		
	} // end of add()
	
	
	/**
	 * Searches for an element in the multiset.
	 * 
	 * @param item Element to search for.
	 * @return The number of instance of the element in the multiset.  If element is not in the multiset, return 0.
	 */
	public int search(T item) {
		// Implement me!		
		Node currNode = mHead;
		int counter=0;

		while(currNode != null){
			if(currNode.getValue().equals(item)){
				return currNode.getCounter();
			}
			currNode = currNode.getNext();
		}
		
		return 0;
		
	} // end of search()
	
	
	/**
	 * Remove one instance of element from the beginning of list.  If element doesn't exist, method just returns.
	 * 
	 * @param item Element to remove. 
	 */
	public void removeOne(T item) {
		// Implement me!
		
		Node currNode = mHead;
		
		// check if value is head node
        if (currNode.getValue().equals((String) item)) {
        	currNode.minusCounter();
        	
        	if(currNode.getCounter()==0){
        		// check if length of 1
            	if (mLength == 1) {
            		mHead = mTail= null;
            		mLength--;
            		return;
            	}
            	else {
            		mHead = currNode.getNext();
            		mHead.setPrev(null);
            		currNode = null;
            		mLength--;
            		return;
            	}
            
        	}
            
        }
        
        
        // search for value in rest of list
        else {
        	
            currNode = currNode.getNext();

            while (currNode != null) {
            	
                if (currNode.getValue().equals((String) item)) {
                	if(currNode.getCounter()>0)
                		currNode.minusCounter();
                	
                	if(currNode.getCounter()==0){
                    	Node prevNode = currNode.getPrev();
                    	prevNode.setNext(currNode.getNext());
                    
                    	// check if tail
                    	if (currNode.getNext() != null) {
                    		currNode.getNext().setPrev(prevNode);
                    	}
                    	else {
                    		mTail = prevNode;
                    	
                    	}
                    	mLength--;
                	}
                    currNode = null; //successfully remove, end loop
                    
                    
                    return;
                }
 
                currNode = currNode.getNext();
            }	
        }

	} // end of removeOne()
	
	
	
	/**
	 * Remove all instances of element from the multiset.  If element doesn't exist, method just returns.
	 * 
	 * @param item Element to remove. 
	 */
	public void removeAll(T item) {///////////////////////////////////////////////////////// use remove one, remove regardless of counter
		// Implement me!
		Node currNode = mHead;
        Node prevNode = null;
        Node nextNode = null;
        
        
        while (currNode != null) {
            if (currNode.getValue().equals((String) item)) {
            	
            	currNode.resetCounter();
            	
                if (currNode == mHead){ /////for Head
                    mHead = currNode.getNext();
                	mHead.setPrev(null);
                	currNode = null;
                	mLength--;
                	return;
                }
                
                if (currNode == mTail){  /// for Tail
                	mTail = currNode.getPrev();
                	mTail.setNext(null);
                	currNode = null;
                	mLength--;
                	return;
                	
                }
                
                ////////////////////////////////////////////////////
                prevNode=currNode.getPrev();
                nextNode=currNode.getNext();
                
                prevNode.setNext(nextNode);
                nextNode.setPrev(prevNode);
                currNode.setNext(null);
                currNode.setPrev(null);
                mLength--;
                return;
                
            }
            
            currNode = currNode.getNext();
        }
	} // end of removeAll()
	
	
	
	/**
	 * Prints out all the elements and the number of instances of each to 'out' PrintStream.
	 * 
	 * @param out PrintStream to print out to.
	 */
	public void print(PrintStream out) {
		// Implement me!
		Node currNode = mHead;

        while (currNode != null) {
            out.printf("%s | %d\n", currNode.getValue()
                    , currNode.getCounter());
            currNode = currNode.getNext();
        }
	} // end of print()
	
	
	//Node type, inner private class.
	private class Node {
		
        protected String mValue;
        protected Node mNext;
        protected Node mPrev;
        protected Node mNode;

        int counter;

        public Node(String value) {
            mValue = value;
            mNext = null;
            mPrev = null;
            mNode = null;
            counter = 1;
        }

        
        public void addCounter() {
            counter++;
        }

        public void minusCounter() {
            counter--;
        }
        
        public void newCounter(){
        	counter=1;
        }
        
        public void resetCounter(){
        	counter=0;
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
        

        /**
        public void setValue(String value) {
            mValue = value;
        }
        
        */

        public void setNext(Node next) {
            mNext = next;
        }
        
        public void setPrev(Node prev) {
            mPrev = prev;
        }
        
        
        
    }
	
} // end of class SortedLinkedListMultiset