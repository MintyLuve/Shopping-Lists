/* CITATION: This code was created based off of auditing the course
   Introduction to Java Programming: Fundamental Data Structures and Algorithms on EdX
   https://learning.edx.org/course/course-v1:UC3Mx+IT.1.3x+3T2024/home  */


/**   A singly linked list that can: <ul>
 *   <li> Insert from the beginning, end, and sorted </li>
 *   <li> Extract from the beginning or the end </li>
 *   <li> Delete the first occurrence of a value </li>
 *   <li> Delete all occurrences of a value </li>
 *   <li> Print the entire list </li> </ul>   
 */
public class SinglyLinkedList<E extends Comparable<E>> {
    private Node<E> head;
    private Node<E> tail;
    
    /**
     * A singly linked list that can: <ul>
     *   <li> Insert from the beginning, end, and sorted </li>
     *   <li> Extract from the beginning or the end </li>
     *   <li> Delete the first occurrence of a value </li>
     *   <li> Delete all occurrences of a value </li>
     *   <li> Print the entire list </li> </ul>   
     */
    public SinglyLinkedList(){
        // Creates the head and the tail
	    this.head = null;
	    this.tail = null;
    }
    
    /**
     * This function adds a new node, and inserts it before the head.
     * @param info The value you want to insert.
     */
    public void insertBeginning(E info){
	    Node<E> newNode = new Node<E>(info);
        // head (new node) --> old head
	    newNode.setNext(head);
	    head = newNode;
        // if there is no tail sets it to new node
	    if (tail == null){
	        tail = newNode;
	    }
    }

    /**
     * This function adds a new node, and inserts it after the tail.
     * @param info The value you want to insert.
     */
    public void insertEnd(E info){
	    Node<E> newNode = new Node<E>(info);
        if(tail != null){
            // old tail --> tail (new node)
            tail.setNext(newNode);
            tail = newNode;
        }
        // if there is no tail it sets it to new node
        else {
            tail = newNode;
        }
        // if there is no head sets it to new node
	    if (head == null){
	        head = newNode;
	    }

    }

    /**
     * This function adds a new node, and inserts it in the list either numerically or alphabetically.
     * @param info The value you want to insert.
     */
    public void insertSorted(E info){
        // Gets the new node
	    Node<E> newNode = new Node<E>(info);
        // if the list doesn't exist, or if the info is less then the first value
        if(head == null || info.compareTo(head.getInfo()) < 0){
            // first (new node) --> old first
            newNode.setNext(head);
            head = newNode;
        } else {
            //current node to use to go through the list
            Node<E> currentNode = head;
            //while there is a next node in the list
            while (currentNode.getNext() != null){
                //if the info is greater then the current info, but smaller then the next info
                if (info.compareTo(currentNode.getInfo()) > 0 && info.compareTo(currentNode.getNext().getInfo()) <= 0){
                    // current node --> new node --> next node
                    newNode.setNext(currentNode.getNext());
                    currentNode.setNext(newNode);
                    break;
                } else{
                    // moves onto the next node in the list
                    currentNode = currentNode.getNext();
                }
            }  
            // if the new node was not smaller then the last node 
            if (currentNode.getNext() == null) {
                // becomes the last node
                // current --> new node
                currentNode.setNext(newNode);
            }
        }
    }
    
    /**
     * This function removes the first node from the list, and returns its value
     * @return The value of the first node
     */
    public E extractBeginning(){
        E data = null;
        // If the head exists
        if (head != null) {
            // Return the info then move the head up one
            data = head.getInfo();
            head = head.getNext();
        }
        return data;
    }

    /**
     * This function removes the last node from the list, and returns its value
     * @return The value of the last node
     */
    public E extractEnd(){
	    E data = null;

        // if the tail exists
        if (tail != null) {
            // Return the data
            data = tail.getInfo();
            // if there is only one node, it deletes it and returns data
            if (head == tail){
                head = null;
                tail = null;
                return data;
            }
            // Getting the second to last node
            Node<E> prev = head;
            while (prev.getNext().getNext() != null){
                prev = prev.getNext();
            }

            // Moves the tail back one
            tail = prev;
            tail.setNext(null);
        }
        return data;
    }

    /**
     * This function deletes the first occurrence of the inputted value.
     * @param info The value you want deleted.
     * @return Whether or not the value was successfully deleted
     */
    public boolean deleteFirstOccurrence(E info){
        boolean success = false;
        Node<E> currentNode = head;

        // while the current node is not the last one, and it didn't find the first occurrence
         while (currentNode.getNext() != null && !success){
            Node<E> nextNode = currentNode.getNext();
            // If the current node is the first one, and the current node is the first occurrence
            if(currentNode == head && currentNode.getInfo() == info) {
                // removes the reference to the old head, and makes current the new head
                head = head.getNext();
                currentNode = head;
                // Successfully deletes the first one, ending the loop
                success = true;
            }
            // If the next node is the first occurrence
            else if (nextNode.getInfo() == info){
                // Changes the current node to reference the next next node
                currentNode.setNext(nextNode.getNext());
                // Successfully deletes the first one, ending the loop
                success = true;
            }
            else{
                // Traverses the list
                currentNode = nextNode;
            }
        }
        // returns if the function worked or not
        return success;
    }

    /**
     * This function deletes all occurrences of the inputted value.
     * @param info The value you want deleted.
     * @return How many times the value got deleted.
     */
    public int deleteAllOccurrence(E info){
        int count = 0;
        Node<E> currentNode = head;

        // While the current node is not the last node
        while (currentNode.getNext() != null){
            Node<E> nextNode = currentNode.getNext();
            // If the current node is the first one, and the current node is the first occurrence
            if(currentNode == head && currentNode.getInfo() == info) {
                // removes the reference to the old head, and makes current the new head
                head = head.getNext();
                currentNode = head;
                // increments the count of how many times it was deleted
                count++;
            }
            else if (nextNode.getInfo() == info){
                // Changes the current node to reference the next next node
                currentNode.setNext(nextNode.getNext());
                // increments the count of how many times it was deleted
                count++;
            }
            else{
                // Traverses the list
                currentNode = nextNode;
            }
       }
       // Returns how many times the value got deleted
       return count;
    }


    /**
     * This function prints out the entire linked list.
     */
    public void print(){
	    Node<E> current = head;
	    
        System.out.print("[ ");
        // Traverses the list, printing out the current value
	    while (current != null){
	        System.out.print(current.getInfo() + " ");
	        current = current.getNext();
	    }
	    System.out.println("]");
    }
}
