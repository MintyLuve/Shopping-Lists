public class Node{
    private String item;
    private double price;
    private Node next;
    
    // For doubly linked lists only
    private Node prev;

    public Node(){
	    this.next = null;
    }

    public Node(String item, double price){
        this.item = item;
        this.price = price;
	    this.next = null;

        // For doubly linked lists only
        this.prev = null;
    }
    public Node(String item, double price, Node next){
	    this.item = item;
        this.price = price;
	    this.next = next;

        // For doubly linked lists only
        this.prev = null;
    }

    public String getItem(){
	    return this.item;
    }
    public void setItem(String item){
	    this.item = item;
    }
    public double getPrice(){
	    return this.price;
    }
    public void setPrice(double price){
	    this.price = price;
    }
    public Node getNext(){
	    return this.next;
    }

    public void setNext(Node next){
	    this.next = next;
    }

    
    /* For doubly linked lists only */
    public Node getPrev(){
	    return this.prev;
    }

    public void setPrev(Node prev){
	    this.prev = prev;
    }

}