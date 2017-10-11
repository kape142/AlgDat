package util;

import java.util.ArrayList;

/**
 * Created by KarlPeter on 04.10.2017.
 */
public class Set<E> {
    private ArrayList<E> list;

    public Set(){
        this.list = new ArrayList<>();
    }

    public Set(int initialCapacity){
        this.list = new ArrayList<>(initialCapacity);
    }

    public void add(E e){
        if(list.contains(e)){
            throw new IllegalArgumentException();
        }
        list.add(e);
    }

    public E get(int index){
        return list.get(index);
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
