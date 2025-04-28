public class Item{
    private String item;
    private double price;
    private Item next;
    private Item prev;

    public Item(){
	    this.next = null;
    }
    public Item(String item, double price){
        this.item = item;
        this.price = price;
	    this.next = null;
        this.prev = null;
    }
    public Item(String item, double price, Item next){
	    this.item = item;
        this.price = price;
	    this.next = next;
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
    public Item getNext(){
	    return this.next;
    }
    public void setNext(Item next){
	    this.next = next;
    }
    public Item getPrev(){
	    return this.prev;
    }
    public void setPrev(Item prev){
	    this.prev = prev;
    }

}