/* CITATION: The base code was based off of the audited course
   Introduction to Java Programming: Fundamental Data Structures and Algorithms on EdX
   https://learning.edx.org/course/course-v1:UC3Mx+IT.1.3x+3T2024/home  */

 public class ShoppingList {
    private Item head;
    private Item tail;

    public ShoppingList(){
	    this.head = null;
        this.tail = null;
    }
    
    public void insertBeginning(String item, double price){
	    Item newItem = new Item(item, price);
	    newItem.setNext(head);
	    if (head != null){
	        head.setPrev(newItem);
	    }
	    head = newItem;
	    if (tail == null){
	        tail = newItem ;
	    }
    }

    public void insertEnd(String item, double price){
	    Item newitem  = new Item(item, price);
	    if (tail == null){
            head = newitem;
	        tail = newitem;
	    } else{
            tail.setNext(newitem);
            newitem.setPrev(tail);
	        tail = newitem;
	   }
    }

    public void insertSortedAlphabetically(String item, double price){
	    Item newItem = new Item(item, price);
        if(head == null){
            newItem.setNext(head);
            head = newItem;
            tail = newItem;
        }
        else if (item.compareTo(head.getItem()) < 0){
            newItem.setNext(head);
            head.setPrev(newItem);
            head = newItem;
        } 
        else {
            Item currentItem = head;
            while (currentItem.getNext() != null){
                if (item.compareTo(currentItem.getItem()) > 0 && item.compareTo(currentItem.getNext().getItem()) <= 0){
                    Item nextItem = currentItem.getNext();

                    newItem.setNext(nextItem);
                    newItem.setPrev(currentItem);
                    
                    currentItem.setNext(newItem);
                    nextItem.setPrev(newItem);
                    break;
                } else{
                    currentItem = currentItem.getNext();
                }
            }  
            if (currentItem.getNext() == null) {
                currentItem.setNext(newItem);
                newItem.setPrev(currentItem);
                tail = newItem;
            }
        }
    }
    
    public String extractFirstItem(){
	    String item = null;
	    if (head != null && head.getNext() != null){
            item = head.getItem();
            head = head.getNext();
            head.setPrev(null);
        }
        else if (head != null){
            item = head.getItem();
            head = null;
            tail = null;
        }
	    return item;
    }

    public String extractLastItem(){
	    String item = null;
	    if (tail != null && tail.getPrev() != null){
            item = tail.getItem();
            tail = tail.getPrev();
            tail.setNext(null);
        }
        else if (tail != null){
            item = tail.getItem();
            tail = null;
            head = null;
        }
	    return item;
    }

    public double extractPrice(String item){
        double price = 0;
        Item current = head;
        if(head == null && tail == null){
            return price;
        }
        else {
            while (current != null) {
                Item prev = current.getPrev();
                Item next = current.getNext();
                if (head.equals(tail) && current.getItem().equals(item)){
                    head = null;
                    tail = null;
                    return current.getPrice();
                }
                else if (current.equals(head) && current.getItem().equals(item)){
                    next.setPrev(null);
                    head = next;
                    return current.getPrice();
                }
                else if (current.equals(tail) && current.getItem().equals(item)){
                    prev.setNext(null);
                    tail = prev;
                    return current.getPrice();
                }
                else if (current.getItem().equals(item)){
                    prev.setNext(next);
                    next.setPrev(prev);
                    return current.getPrice();
                }
                current = current.getNext();
            }
        } 
        return price;
    }

    public boolean hasItems(){
        if(head == null && tail == null){ return false; }
        else { return true; }
    }

    public boolean isInList(String item){
        Item current = head;
        if(head == null && tail == null){ return false; }
        else {
            while (current != null) {
                if (current.getItem().equals(item)){
                    return true;
                }
                current = current.getNext();
            }
        } 
        return false;
    }

    public void deleteAllItemsInList(){
        head = null;
        tail = null;
    }

    public boolean deleteFirstOccurrenceOfItem(String item){
        boolean success = false;
        Item current = head;
        if(head == null && tail == null){
            return success;
        }
        else {
            while (current != null && !success) {
                Item prev = current.getPrev();
                Item next = current.getNext();
                if (head == tail && item == current.getItem()){
                    head = null;
                    tail = null;
                    success = true;
                }
                else if (current == head && item == current.getItem()){
                    next.setPrev(null);
                    head = next;
                    success = true;
                }
                else if (current == tail && item == current.getItem()){
                    prev.setNext(null);
                    tail = prev;
                    success = true;
                }
                else if (item == current.getItem()){
                    prev.setNext(next);
                    next.setPrev(prev);
                    success = true;
                }
                current = current.getNext();
            }
        } 
        
        return success;
    }

    public boolean deleteLastOccurrenceOfItem(String item){
        boolean success = false;
        Item current = tail;
        if(head == null && tail == null){
            return success;
        }
        else {
            while (current != null && !success) {
                Item prev = current.getPrev();
                Item next = current.getNext();
                if (head == tail && item == current.getItem()){
                    head = null;
                    tail = null;
                    success = true;
                }
                else if (current == head && item == current.getItem()){
                    next.setPrev(null);
                    head = next;
                    success = true;
                }
                else if (current == tail && item == current.getItem()){
                    prev.setNext(null);
                    tail = prev;
                    success = true;
                }
                else if (item == current.getItem()){
                    prev.setNext(next);
                    next.setPrev(prev);
                    success = true;
                }
                current = current.getPrev();
            }
        }    
        return success;
    }

    public int deleteAllOccurrenceOfItem(String item){
        int deleted = 0;
        Item current = head;
        if(head == null && tail == null){
            return deleted;
        }
        else {
            while (current != null) {
                Item prev = current.getPrev();
                Item next = current.getNext();
                if (head == tail && item == current.getItem()){
                    head = null;
                    tail = null;
                    deleted++;
                }
                else if (current == head && item == current.getItem()){
                    next.setPrev(null);
                    head = next;
                    deleted++;
                }
                else if (current == tail && item == current.getItem()){
                    prev.setNext(null);
                    tail = prev;
                    deleted++;
                }
                else if (item == current.getItem()){
                    prev.setNext(next);
                    next.setPrev(prev);
                    deleted++;
                }
                current = current.getNext();
            }
        } 
        return deleted;
     }
     
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

    public void printAsShoppingList(String title){
	    Item current = head;
        String formattedPrice, formattedItem;
        int itemLength;
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	    System.out.println("\t--"+title+"--");
	    while (current != null && current.getNext() != null){
            formattedPrice = String.format("%." + 2 + "f", current.getPrice());
            itemLength = current.getItem().length();
            formattedItem = String.format("%-" + 20 + "s", current.getItem());
            formattedItem = formattedItem.substring(0, itemLength)+formattedItem.substring(itemLength).replace(' ', '.');
	        System.out.println(formattedItem + "...... $"+ formattedPrice);
	        current = current.getNext();
	    }
        if (current != null){
            formattedPrice = String.format("%." + 2 + "f", current.getPrice());
            itemLength = current.getItem().length();
            formattedItem = String.format("%-" + 20 + "s", current.getItem());
            formattedItem = formattedItem.substring(0, itemLength-1)+formattedItem.substring(itemLength-1).replace(' ', '.');
            System.out.print(formattedItem + "...... $"+ formattedPrice);
        }
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}