package util;

import java.util.ArrayList;

/**
 * Created by KarlPeter on 04.10.2017.
 */
public class Queue<E> {
    private ArrayList<E> list;

    public Queue(){
        this.list = new ArrayList<>();
    }

    public Queue(int initialCapacity){
        this.list = new ArrayList<>(initialCapacity);
    }

    public void enqueue(E e){
        list.add(e);
    }

    public E dequeue(){
        E e = list.get(0);
        list.remove(0);
        return e;
    }

    public boolean contains(Object o){
        return list.contains(o);
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    @Override
    public String toString() {
        if(isEmpty()){
            return "[]";
        }
        String s = "Set of ";
        String[] tab = list.get(0).getClass().toString().split("\\.");
        s+= tab[tab.length-1]+"s\n";
        s+="Elements:\n";
        for (E e: list){
            s+=e.toString()+"\n";
        }
        return s;
    }
}
