/** A doubly linked list that can: <ul>
 *   <li> Insert from the beginning, end, and sorted </li>
 *   <li> Extract from the beginning or the end </li>
 *   <li> Delete the first or last occurrence of an item </li>
 *   <li> Delete all occurrences of an item </li>
 *   <li> Print the entire list forwards and backwards</li> </ul> */
 public class DoublyLinkedList {
    private Node head;
    private Node tail;

    /** A doubly linked list that can: <ul>
     *   <li> Insert from the beginning, end, and sorted </li>
     *   <li> Extract from the beginning or the end </li>
     *   <li> Delete the first or last occurrence of an item </li>
     *   <li> Delete all occurrences of an item </li>
     *   <li> Print the entire list forwards and backwards</li> </ul> */
    public DoublyLinkedList(){
        // Creates the head and the tail
	    this.head = null;
        this.tail = null;
    }
    
    /**
     * This function adds a new item, and inserts it before the head.
     * @param item The name of the item you want to insert.
     * @param price The price of the item you want to insert.
     */
    public void insertBeginning(String item, double price){
	    Node newNode = new Node(item, price);
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
    
    /**
     * This function adds a new item, and inserts it after the tail.
     * @param item The name of the item you want to insert.
     * @param price The price of the item you want to insert.
     */
    public void insertEnd(String item, double price){
	    Node newNode = new Node(item, price);
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

    /**
     * This function adds a new item, and inserts it in the list alphabetically.
     * @param item The name of the item you want to insert.
     * @param price The price of the item you want to insert.
     */
    public void insertSortedAlphabetically(String item, double price){
        // Gets the new node
	    Node newNode = new Node(item, price);
        // if the list doesn't exist
        if(head == null){
            // first (new node) <--> old first
            newNode.setNext(head);
            head = newNode;
        }
        // if the item name is less then the first value
        else if (item.compareTo(head.getItem()) < 0){
            // first (new node) --> old first
            newNode.setNext(head);
            head.setPrev(newNode);
            head = newNode;
        } 
        else {
            //current node to use to go through the list
            Node currentNode = head;
            //while there is a next node in the list
            while (currentNode.getNext() != null){
                //if the item name is greater then the current item name, but smaller then the next item name
                if (item.compareTo(currentNode.getItem()) > 0 && item.compareTo(currentNode.getNext().getItem()) <= 0){
                    // current node <--> new node <--> next node
                    Node nextNode = currentNode.getNext();

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
    
    /**
     * This function removes the first node from the list, and returns its name
     * @return The name of the first item
     */
    public String extractFirstItem(){
	    String item = null;
        // If the head and the next node exists
	    if (head != null && head.getNext() != null){
            // Returns the item name and moves the head up one
            item = head.getItem();
            head = head.getNext();
            head.setPrev(null);
        }
        // If only the head exists
        else if (head != null){
            // Returns item name and deletes the node
            item = head.getItem();
            head = null;
            tail = null;
        }
	    return item;
    }

    /**
     * This function removes the last item from the list, and returns its name
     * @return The name of the last item
     */
    public String extractLastItem(){
	    String item = null;
        // If the tail and the previous node exists
	    if (tail != null && tail.getPrev() != null){
            // Returns the item name and moves the tail back one
            item = tail.getItem();
            tail = tail.getPrev();
            tail.setNext(null);
        }
        // If only the tail exists
        else if (tail != null){
            // Returns item name and deletes the node
            item = tail.getItem();
            tail = null;
            head = null;
        }
	    return item;
    }

    /**
     * This function deletes the first occurrence of the inputted item.
     * @param item The name of the item you want deleted.
     * @return Whether or not the item was successfully deleted
     */
    public boolean deleteFirstOccurrenceOfItem(String item){
        boolean success = false;
        Node current = head;
        // If no list exists
        if(head == null && tail == null){
            return success;
        }
        else {
            // While current exists
            while (current != null && !success) {
                Node prev = current.getPrev();
                Node next = current.getNext();
                // If there is only one node and that should be deleted
                if (head == tail && item == current.getItem()){
                    // Deletes head + tail
                    head = null;
                    tail = null;
                    // Successfully deletes the first one, ending the loop
                    success = true;
                }
                // If the head is the item that should be deleted
                else if (current == head && item == current.getItem()){
                    // null (old head) <-- head
                    next.setPrev(null);
                    head = next;
                    // Successfully deletes the first one, ending the loop
                    success = true;
                }
                // If the tail is the item that should be deleted
                else if (current == tail && item == current.getItem()){
                    // tail --> null (old tail)
                    prev.setNext(null);
                    tail = prev;
                    // Successfully deletes the first one, ending the loop
                    success = true;
                }
                // If a node (that isn't head or tail) should be deleted
                else if (item == current.getItem()){
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

    /**
     * This function deletes the last occurrence of the inputted item.
     * @param item The name of the item you want deleted.
     * @return Whether or not the value was successfully deleted
     */
    public boolean deleteLastOccurrenceOfItem(String item){
        boolean success = false;
        Node current = tail;
        // If no list exists
        if(head == null && tail == null){
            return success;
        }
        else {
            // While current exists
            while (current != null && !success) {
                Node prev = current.getPrev();
                Node next = current.getNext();
                // If there is only one node and that should be deleted
                if (head == tail && item == current.getItem()){
                    // Deletes head + tail
                    head = null;
                    tail = null;
                    // Successfully deletes the first one, ending the loop
                    success = true;
                }
                // If the head is the item that should be deleted
                else if (current == head && item == current.getItem()){
                    // null (old head) <-- head
                    next.setPrev(null);
                    head = next;
                    // Successfully deletes the first one, ending the loop
                    success = true;
                }
                // If the tail is the item that should be deleted
                else if (current == tail && item == current.getItem()){
                    // tail --> null (old tail)
                    prev.setNext(null);
                    tail = prev;
                    // Successfully deletes the first one, ending the loop
                    success = true;
                }
                // If a node (that isn't head or tail) should be deleted
                else if (item == current.getItem()){
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

    /**
     * This function deletes all occurrences of the inputted item.
     * @param item The name of the item you want deleted.
     * @return How many times the item got deleted.
     */
    public int deleteAllOccurrenceOfItem(String item){
        int deleted = 0;
        Node current = head;
        // If no list exists
        if(head == null && tail == null){
            return deleted;
        }
        else {
            // While current exists
            while (current != null) {
                Node prev = current.getPrev();
                Node next = current.getNext();
                // If there is only one node and that should be deleted
                if (head == tail && item == current.getItem()){
                    // Deletes head + tail
                    head = null;
                    tail = null;
                    // Increments the amount of times it was deleted
                    deleted++;
                }
                // If the head is the item that should be deleted
                else if (current == head && item == current.getItem()){
                    // null (old head) <-- head
                    next.setPrev(null);
                    head = next;
                    // Increments the amount of times it was deleted
                    deleted++;
                }
                // If the tail is the item that should be deleted
                else if (current == tail && item == current.getItem()){
                    // tail --> null (old tail)
                    prev.setNext(null);
                    tail = prev;
                    // Increments the amount of times it was deleted
                    deleted++;
                }
                // If a node (that isn't head or tail) should be deleted
                else if (item == current.getItem()){
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
     
    /** 
     * This function prints out the entire linked list from head to tail. 
     */
    public void printForward(){
	    Node current = head;
        String formattedPrice;
	    System.out.print("[ ");
	    while (current != null && current.getNext() != null){
            formattedPrice = String.format("%." + 2 + "f", current.getPrice());
	        System.out.print(current.getItem() + " = $"+ formattedPrice +", ");
	        current = current.getNext();
	    }
        if (current != null){
            formattedPrice = String.format("%." + 2 + "f", current.getPrice());
            System.out.print(current.getItem() + " = $"+ formattedPrice);
        }
	    System.out.println(" ]");
    }

    /** 
     * This function prints out the entire linked list from tail to head. 
     */ 
    public void printBackwards(){
	    Node current = tail;
        String formattedPrice;
        System.out.print("[ ");
	    while (current != null && current.getPrev() != null){
            formattedPrice = String.format("%." + 2 + "f", current.getPrice());
	        System.out.print(current.getItem() + " = $"+ formattedPrice +", ");
            current = current.getPrev();
	    }
        if (current != null){
            formattedPrice = String.format("%." + 2 + "f", current.getPrice());
            System.out.print(current.getItem() + " = $"+ formattedPrice);
        }
	    System.out.println(" ]");
    }

    /** 
     * This function prints out the entire linked list from head to tail, formatted as a shopping list. 
     */
    public void printAsShoppingList(){
	    Node current = head;
        String formattedPrice, formattedItem;
        int itemLength;
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	    System.out.println("\t--Shopping List--");
	    while (current != null && current.getNext() != null){
            formattedPrice = String.format("%." + 2 + "f", current.getPrice());
            itemLength = current.getItem().length()+1;
            formattedItem = String.format("%-" + 20 + "s", current.getItem());
            formattedItem = formattedItem.substring(0, itemLength)+formattedItem.substring(itemLength).replace(' ', '.');
	        System.out.println(formattedItem + "...... $"+ formattedPrice);
	        current = current.getNext();
	    }
        if (current != null){
            formattedPrice = String.format("%." + 2 + "f", current.getPrice());
            itemLength = current.getItem().length()+1;
            formattedItem = String.format("%-" + 20 + "s", current.getItem());
            formattedItem = formattedItem.substring(0, itemLength)+formattedItem.substring(itemLength).replace(' ', '.');
            System.out.print(formattedItem + "...... $"+ formattedPrice);
        }
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}