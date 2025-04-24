public class Node<E>{
    private E info;
    private Node<E> next;
    
    // For doubly linked lists only
    private Node<E> prev;

    public Node(){
	    this.next = null;
    }

    public Node(E info){
	    this.info = info;
	    this.next = null;

        // For doubly linked lists only
        this.prev = null;
    }
    public Node(E info, Node<E> next){
	    this.info = info;
	    this.next = next;

        // For doubly linked lists only
        this.prev = null;
    }

    public E getInfo(){
	    return this.info;
    }
    public void setInfo(E info){
	    this.info = info;
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