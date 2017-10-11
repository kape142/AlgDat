package Øving7;

import util.ArrayPrinter;

import java.util.ArrayList;

/**
 * Created by KarlPeter on 04.10.2017.
 */
public class Node {
    private ArrayList<Integer> naboer;
    private int id;
    private int forgj = -1;
    private int dist = -1;
    private int mark = 0;



    Node(int id){
        this.id = id;
        naboer = new ArrayList<>();
    }
    void addKant(int annenNode){
        naboer.add(annenNode);
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getMark() {

        return mark;
    }

    ArrayList<Integer> getNaboer() {
        return naboer;
    }

    int getId() {
        return id;
    }

    public int getForgj() {
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
}
