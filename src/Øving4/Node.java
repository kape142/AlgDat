package Øving4;

class Node {
    private Node prev;
    private Node next;
    private int value;

    Node(int value){
        this.value = value;
    }

    void setPrev(Node prev){
        this.prev = prev;
    }

    void setNext(Node next){
        this.next = next;
    }

    Node getPrev() {
        return prev;
    }

    Node getNext() {
        return next;
    }

    int getValue() {
        return value;
    }

    public String toString(){
        String s = "";
        s+="Prev: "+prev.getValue()+"\n";
        s+="Value: "+value+"\n";
        s+="Next: "+next.getValue()+"\n";
        s+="\n";
        return s;
    }
}
