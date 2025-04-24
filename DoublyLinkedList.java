/** A doubly linked list that can: <ul>
 *   <li> Insert from the beginning, end, and sorted </li>
 *   <li> Extract from the beginning or the end </li>
 *   <li> Delete the first or last occurrence of a value </li>
 *   <li> Delete all occurrences of a value </li>
 *   <li> Print the entire list forwards and backwards</li> </ul> */
 public class DoublyLinkedList<E extends Comparable<E>>{
    private Node<E> head;
    private Node<E> tail;

    /** A doubly linked list that can: <ul>
     *   <li> Insert from the beginning, end, and sorted </li>
     *   <li> Extract from the beginning or the end </li>
     *   <li> Delete the first or last occurrence of a value </li>
     *   <li> Delete all occurrences of a value </li>
     *   <li> Print the entire list forwards and backwards</li> </ul> */
    public DoublyLinkedList(){
        // Creates the head and the tail
	    this.head = null;
        this.tail = null;
    }
    
    /** This function adds a new node, and inserts it before the head.
     * @param info The value you want to insert. */
    public void insertBeginning(E info){
	    Node<E> newNode = new Node<E>(info);
        // head (new node) --> old head
	    newNode.setNext(head);
        // if the head exists, sets the previous to new node
	    if (head != null){
	        head.setPrev(newNode);
	    }
	    head = newNode;
        // if there is no tail sets it to new node
	    if (tail == null){
	        tail = newNode;
	    }
    }
    
    /** This function adds a new node, and inserts it after the tail.
     * @param info The value you want to insert. */
    public void insertEnd(E info){
	    Node<E> newNode = new Node<E>(info);
	    if (tail == null){
            // If there is nothing in the list, adds new node
            head = newNode;
	        tail = newNode;
	    } else{
            tail.setNext(newNode);
            newNode.setPrev(tail);
	        tail = newNode;
	   }
    }

    /** This function adds a new node, and inserts it in the list either numerically or alphabetically.
     * @param info The value you want to insert. */
    public void insertSorted(E info){
        // Gets the new node
	    Node<E> newNode = new Node<E>(info);
        // if the list doesn't exist
        if(head == null){
            // first (new node) <--> old first
            newNode.setNext(head);
            head = newNode;
        }
        // if the info is less then the first value
        else if (info.compareTo(head.getInfo()) < 0){
            // first (new node) --> old first
            newNode.setNext(head);
            head.setPrev(newNode);
            head = newNode;
        } 
        else {
            //current node to use to go through the list
            Node<E> currentNode = head;
            //while there is a next node in the list
            while (currentNode.getNext() != null){
                //if the info is greater then the current info, but smaller then the next info
                if (info.compareTo(currentNode.getInfo()) > 0 && info.compareTo(currentNode.getNext().getInfo()) <= 0){
                    // current node <--> new node <--> next node
                    Node<E> nextNode = currentNode.getNext();

                    newNode.setNext(nextNode);
                    newNode.setPrev(currentNode);
                    
                    currentNode.setNext(newNode);
                    nextNode.setPrev(newNode);
                    break;
                } else{
                    // moves onto the next node in the list
                    currentNode = currentNode.getNext();
                }
            }  
            // if the new node was not smaller then the last node 
            if (currentNode.getNext() == null) {
                // becomes the last node
                // current <--> new node (tail)
                currentNode.setNext(newNode);
                newNode.setPrev(currentNode);
                tail = newNode;
            }
        }
    }
    
    /** This function removes the first node from the list, and returns its value
     * @return The value of the first node */
    public E extractBeginning(){
	    E data = null;
        // If the head and the next node exists
	    if (head != null && head.getNext() != null){
            // Returns the info and moves the head up one
            data = head.getInfo();
            head = head.getNext();
            head.setPrev(null);
        }
        // If only the head exists
        else if (head != null){
            // Returns data and deletes the node
            data = head.getInfo();
            head = null;
            tail = null;
        }
	    return data;
    }

    /** This function removes the last node from the list, and returns its value
     * @return The value of the last node */
    public E extractEnd(){
	    E data = null;
        // If the tail and the previous node exists
	    if (tail != null && tail.getPrev() != null){
            // Returns the info and moves the tail back one
            data = tail.getInfo();
            tail = tail.getPrev();
            tail.setNext(null);
        }
        // If only the tail exists
        else if (tail != null){
            // Returns data and deletes the node
            data = tail.getInfo();
            tail = null;
            head = null;
        }
	    return data;
    }

    /** This function deletes the first occurrence of the inputted value.
     * @param info The value you want deleted.
     * @return Whether or not the value was successfully deleted */
    public boolean deleteFirstOccurrence(E info){
        boolean success = false;
        Node<E> current = head;
        // If no list exists
        if(head == null && tail == null){
            return success;
        }
        else {
            // While current exists
            while (current != null && !success) {
                Node<E> prev = current.getPrev();
                Node<E> next = current.getNext();
                // If there is only one node and that should be deleted
                if (head == tail && info == current.getInfo()){
                    // Deletes head + tail
                    head = null;
                    tail = null;
                    // Successfully deletes the first one, ending the loop
                    success = true;
                }
                // If the head is the data that should be deleted
                else if (current == head && info == current.getInfo()){
                    // null (old head) <-- head
                    next.setPrev(null);
                    head = next;
                    // Successfully deletes the first one, ending the loop
                    success = true;
                }
                // If the tail is the data that should be deleted
                else if (current == tail && info == current.getInfo()){
                    // tail --> null (old tail)
                    prev.setNext(null);
                    tail = prev;
                    // Successfully deletes the first one, ending the loop
                    success = true;
                }
                // If a node (that isn't head or tail) should be deleted
                else if (info == current.getInfo()){
                    // prev <--> next (deletes current)
                    prev.setNext(next);
                    next.setPrev(prev);
                    // Successfully deletes the first one, ending the loop
                    success = true;
                }
                // Traverses the list
                current = current.getNext();
            }
        } 
        
        return success;
    }

    /** This function deletes the last occurrence of the inputted value.
     * @param info The value you want deleted.
     * @return Whether or not the value was successfully deleted */
    public boolean deleteLastOccurrence(E info){
        boolean success = false;
        Node<E> current = tail;
        // If no list exists
        if(head == null && tail == null){
            return success;
        }
        else {
            // While current exists
            while (current != null && !success) {
                Node<E> prev = current.getPrev();
                Node<E> next = current.getNext();
                // If there is only one node and that should be deleted
                if (head == tail && info == current.getInfo()){
                    // Deletes head + tail
                    head = null;
                    tail = null;
                    // Successfully deletes the first one, ending the loop
                    success = true;
                }
                // If the head is the data that should be deleted
                else if (current == head && info == current.getInfo()){
                    // null (old head) <-- head
                    next.setPrev(null);
                    head = next;
                    // Successfully deletes the first one, ending the loop
                    success = true;
                }
                // If the tail is the data that should be deleted
                else if (current == tail && info == current.getInfo()){
                    // tail --> null (old tail)
                    prev.setNext(null);
                    tail = prev;
                    // Successfully deletes the first one, ending the loop
                    success = true;
                }
                // If a node (that isn't head or tail) should be deleted
                else if (info == current.getInfo()){
                    // prev <--> next (deletes current)
                    prev.setNext(next);
                    next.setPrev(prev);
                    // Successfully deletes the first one, ending the loop
                    success = true;
                }
                // Traverses the list
                current = current.getPrev();
            }
        }    
        return success;
    }

    /**This function deletes all occurrences of the inputted value.
     * @param info The value you want deleted.
     * @return How many times the value got deleted. */
    public int deleteAllOccurrence(E info){
        int deleted = 0;
        Node<E> current = head;
        // If no list exists
        if(head == null && tail == null){
            return deleted;
        }
        else {
            // While current exists
            while (current != null) {
                Node<E> prev = current.getPrev();
                Node<E> next = current.getNext();
                // If there is only one node and that should be deleted
                if (head == tail && info == current.getInfo()){
                    // Deletes head + tail
                    head = null;
                    tail = null;
                    // Increments the amount of times it was deleted
                    deleted++;
                }
                // If the head is the data that should be deleted
                else if (current == head && info == current.getInfo()){
                    // null (old head) <-- head
                    next.setPrev(null);
                    head = next;
                    // Increments the amount of times it was deleted
                    deleted++;
                }
                // If the tail is the data that should be deleted
                else if (current == tail && info == current.getInfo()){
                    // tail --> null (old tail)
                    prev.setNext(null);
                    tail = prev;
                    // Increments the amount of times it was deleted
                    deleted++;
                }
                // If a node (that isn't head or tail) should be deleted
                else if (info == current.getInfo()){
                    // prev <--> next (deletes current)
                    prev.setNext(next);
                    next.setPrev(prev);
                    // Increments the amount of times it was deleted
                    deleted++;
                }
                // Traverses the list
                current = current.getNext();
            }
        } 
        return deleted;
     }
     
    /** This function prints out the entire linked list from head to tail.  */
    public void printForward(){
	    Node<E> current = head;
	    System.out.print("[ ");
	    while (current != null){
	        System.out.print(current.getInfo() + " ");
	        current = current.getNext();
	    }
	    System.out.println("]");
    }

    /** This function prints out the entire linked list from tail to head. */
    public void printBackwards(){
	    Node<E> current = tail;
        System.out.print("[ ");
	    while (current != null){
	        System.out.print(current.getInfo() + " ");
	        current = current.getPrev();
	    }
	    System.out.println("]");
    }
}