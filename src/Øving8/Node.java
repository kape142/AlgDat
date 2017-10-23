package Øving8;

import util.ArrayPrinter;

import java.util.ArrayList;

/**
 * Created by KarlPeter on 04.10.2017.
 */
public class Node {
    public static final int infinity = 1000000;
    private ArrayList<Kant> naboer;
    private int id;
    private int forgj = -1;
    private int dist = infinity;
    private int mark = 0;



    Node(int id){
        this.id = id;
        naboer = new ArrayList<>();
    }
    void addKant(Kant k){
        naboer.add(k);
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getMark() {

        return mark;
    }

    ArrayList<Kant> getNaboer() {
        return naboer;
    }

    int getId() {
        return id;
    }

    int getForgj() {
        return forgj;
    }

    int getDist() {
        return dist;
    }

    void setForgj(int forgj) {
        this.forgj = forgj;
    }

    void setDist(int dist) {
        this.dist = dist;
    }

    int getAntNaboer(){
        return naboer.size();
    }

    @Override
    public String toString(){
        return "ID: "+id+", Naboer: "+ ArrayPrinter.printInLine(naboer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return id == node.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    int compareTo(Node other){
        if(dist > other.getDist()){
            return 1;
        }else if (dist ==other.getDist()){
            return 0;
        }else{
            return -1;
        }
    }
}
