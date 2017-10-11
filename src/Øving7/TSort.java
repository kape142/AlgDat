package Øving7;

import util.Queue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by KarlPeter on 09.10.2017.
 */
class TSort {
    private Node[] nodeListe;
    private int markert;
    private ArrayList<Node> sortert;
    void topologicalSort(String filnavn, int rot) throws Exception{
        FileReader l = new FileReader(filnavn);
        BufferedReader leser = new BufferedReader(l);
        String[] lest = cleanup(leser.readLine());
        int noder = Integer.parseInt(lest[0]);
        nodeListe = new Node[noder];
        for (int i = 0; i < noder; i++) {
            nodeListe[i] = new Node(i);
        }
        leser.lines().forEach(this::addKant);
        markert = 0;
        Queue<Node> usjekket = new Queue<>();
        sortert = new ArrayList<>();
        for(Node n : nodeListe){
            usjekket.enqueue(n);
        }
        System.out.println(noder);
        while(markert<noder){
            Node node = usjekket.dequeue();
            visit(node);
        }
    }

    void visit(Node n){
        if(n.getMark()==1){
            return;
        }
        if(n.getMark()==-1){
            throw new UnsupportedOperationException();
        }
        n.setMark(-1);
        ArrayList<Integer> intNaboer = n.getNaboer();
        ArrayList<Node> naboer = new ArrayList<>();
        for(int i : intNaboer){
            naboer.add(nodeListe[i]);
        }
        for (Node nabo : naboer) {
            visit(nabo);
        }
        markert++;
        n.setMark(1);
        sortert.add(n);
    }

    int getDistanceTo(int node){
        return nodeListe[node].getDist();
    }

    String getAllData(){
        StringBuilder s = new StringBuilder("Node\tForgj\tDist\tMarkering\n");

        for (int i = sortert.size()-1; i>=0; i--) {
            Node n = sortert.get(i);
            s.append(n.getId());
            if(n.getId() > 999) {
                s.append("\t");
            }else {
                s.append("\t\t");
            }
            s.append(n.getForgj());
            if(n.getForgj() > 999) {
                s.append("\t");
            }else {
                s.append("\t\t");
            }
            s.append(n.getDist());
            if(n.getDist() > 999) {
                s.append("\t");
            }else {
                s.append("\t\t");
            }
            s.append(n.getMark());
            s.append("\t\t");
            s.append("\n");
        }
        return s.toString();
    }


    private void addKant(String tekst){
        String[] tallTekst = cleanup(tekst);
        nodeListe[Integer.parseInt(tallTekst[0])].addKant(Integer.parseInt(tallTekst[1]));
    }

    private String[] cleanup(String dirtyString){
        String[] dirtyTab = dirtyString.split("[ \t]");
        String[] cleanTab = new String[2];
        int i = 0;
        for(String s : dirtyTab){
            if(!s.trim().equals("")){
                cleanTab[i]=s;
                i++;
            }
        }
        return cleanTab;
    }
}
