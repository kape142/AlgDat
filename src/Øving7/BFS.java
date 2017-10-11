package Øving7;

import util.ArrayPrinter;
import util.Set;
import util.Queue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by KarlPeter on 04.10.2017.
 */
class BFS {
    private Node[] nodeListe;
    void findShortestRoutes(String filnavn, int rot) throws Exception{
        FileReader l = new FileReader(filnavn);
        BufferedReader leser = new BufferedReader(l);
        String[] lest = cleanup(leser.readLine());
        int noder = Integer.parseInt(lest[0]);
        int kanter = Integer.parseInt(lest[1]);
        nodeListe = new Node[noder];
        for (int i = 0; i < noder; i++) {
            nodeListe[i] = new Node(i);
        }
        leser.lines().forEach(this::addKant);
        Queue<Node> queue = new Queue<>(noder);
        nodeListe[rot].setDist(0);
        queue.enqueue(nodeListe[rot]);
        while(!queue.isEmpty()){
            Node node = queue.dequeue();
            ArrayList<Integer> naboer = node.getNaboer();
            Node[] naboListe = new Node[naboer.size()];
            for (int i = 0; i < naboListe.length; i++) {
                naboListe[i] = nodeListe[naboer.get(i)];
            }
            for(Node nabo: naboListe){
                if(nabo.getDist()==-1){
                    queue.enqueue(nabo);
                    nabo.setDist(node.getDist()+1);
                    nabo.setForgj(node.getId());
                }
            }
        }
    }

    int getDistanceTo(int node){
        return nodeListe[node].getDist();
    }

    String getAllData(){
        StringBuilder s = new StringBuilder("Node\tForgj\tDist\n");
        for (Node n : nodeListe) {
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
            s.append("\n");
        }
        return s.toString();
    }

    Node[] getNodeListe(){
        return nodeListe;
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
