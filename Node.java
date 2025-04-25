public class Node<E>{
    private String item;
    private double price;
    private Node<E> next;
    
    // For doubly linked lists only
    private Node<E> prev;

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
    public Node(String item, double price, Node<E> next){
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
    public Node<E> getNext(){
	    return this.next;
    }

    public void setNext(Node<E> next){
	    this.next = next;
    }

    
    /* For doubly linked lists only */
    public Node<E> getPrev(){
	    return this.prev;
    }

    public void setPrev(Node<E> prev){
	    this.prev = prev;
    }

}