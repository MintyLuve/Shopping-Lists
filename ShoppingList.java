/* CITATION: The base code was based off of the audited course
   Introduction to Java Programming: Fundamental Data Structures and Algorithms on EdX
   https://learning.edx.org/course/course-v1:UC3Mx+IT.1.3x+3T2024/home  */

/** A doubly linked list that can: <ul>
 *   <li> Insert from the beginning, end, and sorted </li>
 *   <li> Extract from the beginning or the end </li>
 *   <li> Delete the first or last occurrence of an item </li>
 *   <li> Delete all occurrences of an item </li>
 *   <li> Print the entire list forwards and backwards</li> </ul> */
 public class ShoppingList {
    private Item head;
    private Item tail;

    /** A doubly linked list that can: <ul>
     *   <li> Insert from the beginning, end, and sorted </li>
     *   <li> Extract from the beginning or the end </li>
     *   <li> Delete the first or last occurrence of an item </li>
     *   <li> Delete all occurrences of an item </li>
     *   <li> Print the entire list forwards and backwards</li> </ul> */
    public ShoppingList(){
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
	    Item newItem = new Item(item, price);
        // head (new item) --> old head
	    newItem.setNext(head);
        // if the head exists, sets the previous to new item
	    if (head != null){
	        head.setPrev(newItem);
	    }
	    head = newItem;
        // if there is no tail sets it to new item 
	    if (tail == null){
	        tail = newItem ;
	    }
    }
    
    /**
     * This function adds a new item, and inserts it after the tail.
     * @param item The name of the item you want to insert.
     * @param price The price of the item you want to insert.
     */
    public void insertEnd(String item, double price){
	    Item newitem  = new Item(item, price);
	    if (tail == null){
            // If there is nothing in the list, adds new item
            head = newitem;
	        tail = newitem;
	    } else{
            tail.setNext(newitem);
            newitem.setPrev(tail);
	        tail = newitem;
	   }
    }

    /**
     * This function adds a new item, and inserts it in the list alphabetically.
     * @param item The name of the item you want to insert.
     * @param price The price of the item you want to insert.
     */
    public void insertSortedAlphabetically(String item, double price){
        // Gets the new item
	    Item newItem = new Item(item, price);
        // if the list doesn't exist
        if(head == null){
            // first (new item) <--> old first
            newItem.setNext(head);
            head = newItem;
        }
        // if the item name is less then the first value
        else if (item.compareTo(head.getItem()) < 0){
            // first (new item) --> old first
            newItem.setNext(head);
            head.setPrev(newItem);
            head = newItem;
        } 
        else {
            //current item to use to go through the list
            Item currentItem = head;
            //while there is a next item in the list
            while (currentItem.getNext() != null){
                //if the item name is greater then the current item name, but smaller then the next item name
                if (item.compareTo(currentItem.getItem()) > 0 && item.compareTo(currentItem.getNext().getItem()) <= 0){
                    // current item <--> new item <--> next item
                    Item nextItem = currentItem.getNext();

                    newItem.setNext(nextItem);
                    newItem.setPrev(currentItem);
                    
                    currentItem.setNext(newItem);
                    nextItem.setPrev(newItem);
                    break;
                } else{
                    // moves onto the next item in the list
                    currentItem = currentItem.getNext();
                }
            }  
            // if the new item was not smaller then the last item 
            if (currentItem.getNext() == null) {
                // becomes the last item
                // current <--> new item (tail)
                currentItem.setNext(newItem);
                newItem.setPrev(currentItem);
                tail = newItem;
            }
        }
    }
    
    /**
     * This function removes the first item from the list, and returns its name
     * @return The name of the first item
     */
    public String extractFirstItem(){
	    String item = null;
        // If the head and the next item exists
	    if (head != null && head.getNext() != null){
            // Returns the item name and moves the head up one
            item = head.getItem();
            head = head.getNext();
            head.setPrev(null);
        }
        // If only the head exists
        else if (head != null){
            // Returns item name and deletes the item
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
        // If the tail and the previous item exists
	    if (tail != null && tail.getPrev() != null){
            // Returns the item name and moves the tail back one
            item = tail.getItem();
            tail = tail.getPrev();
            tail.setNext(null);
        }
        // If only the tail exists
        else if (tail != null){
            // Returns item name and deletes the item
            item = tail.getItem();
            tail = null;
            head = null;
        }
	    return item;
    }

    /**
     * This function finds if an item is in the list.
     * @param item The name of the item you want deleted.
     * @return Whether or not the item is in the list
     */
    public boolean isInList(String item){
        Item current = head;
        // If no list exists
        if(head == null && tail == null){ return false; }
        else {
            // While current exists
            while (current != null) {
                // If the item is in the list
                System.out.println("|it:"+item+" cur:"+current.getItem()+"|");
                if (current.getItem().equals(item)){
                    return true;
                }
                // Traverses the list
                current = current.getNext();
            }
        } 
        return false;
    }

    /**
     * This function deletes the first occurrence of the inputted item.
     * @param item The name of the item you want deleted.
     * @return Whether or not the item was successfully deleted
     */
    public boolean deleteFirstOccurrenceOfItem(String item){
        boolean success = false;
        Item current = head;
        // If no list exists
        if(head == null && tail == null){
            return success;
        }
        else {
            // While current exists
            while (current != null && !success) {
                Item prev = current.getPrev();
                Item next = current.getNext();
                // If there is only one item and that should be deleted
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
                // If a item (that isn't head or tail) should be deleted
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
        Item current = tail;
        // If no list exists
        if(head == null && tail == null){
            return success;
        }
        else {
            // While current exists
            while (current != null && !success) {
                Item prev = current.getPrev();
                Item next = current.getNext();
                // If there is only one item and that should be deleted
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
                // If a item (that isn't head or tail) should be deleted
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
        Item current = head;
        // If no list exists
        if(head == null && tail == null){
            return deleted;
        }
        else {
            // While current exists
            while (current != null) {
                Item prev = current.getPrev();
                Item next = current.getNext();
                // If there is only one item and that should be deleted
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
                // If a item (that isn't head or tail) should be deleted
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
	    Item current = head;
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
	    Item current = tail;
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
	    Item current = head;
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