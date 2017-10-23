package Øving8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Created by KarlPeter on 09.10.2017.
 */
class Dijkstra {
    private Node[] nodeListe;
    private int start = 0;

    void findRoute(String filnavn, int rot) throws Exception{
        start = rot;
        FileReader l = new FileReader(filnavn);
        BufferedReader leser = new BufferedReader(l);
        String[] lest = cleanup(leser.readLine());
        int noder = Integer.parseInt(lest[0]);
        nodeListe = new Node[noder];
        if(rot>=noder){
            throw new IllegalArgumentException("Rot over antall noder");
        }
        for (int i = 0; i < noder; i++) {
            nodeListe[i] = new Node(i);
        }
        leser.lines().forEach(this::addKant);
        nodeListe[rot].setDist(0);
        PriorityQueue<Node> nodeQueue = new PriorityQueue<>(noder, Node::compareTo);
        Collections.addAll(nodeQueue, nodeListe);
        while(!nodeQueue.isEmpty()){
            Node node = nodeQueue.poll();
            ArrayList<Kant> kanter = node.getNaboer();
            for(Kant k:kanter){
                int alt = k.getLengde()+node.getDist();
                Node n = nodeListe[k.getTilNode()];
                if(alt<n.getDist()){
                    nodeQueue.remove(n);
                    n.setDist(alt);
                    n.setForgj(node.getId());
                    nodeQueue.add(n);
                }
            }
        }
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
            if(n.getForgj()!=-1) {
                s.append(n.getForgj());
            }else if(n.getId()==start){
                s.append("start");
            }
            if(n.getForgj() > 999 || n.getId()==start) {
                s.append("\t");
            }else {
                s.append("\t\t");
            }
            if(n.getDist()>=Node.infinity){
                s.append("nåes ikke");
            }else {
                s.append(n.getDist());
            }
            if(n.getDist() > 999 && n.getDist()!=Node.infinity) {
                s.append("\t");
            }else {
                s.append("\t\t");
            }
            s.append("\n");
        }
        return s.toString();
    }

    private String[] cleanup(String dirtyString){
        String[] dirtyTab = dirtyString.split("[ \t]");
        String[] cleanTab = new String[3];
        int i = 0;
        for(String s : dirtyTab){
            if(!s.trim().equals("")){
                cleanTab[i]=s;
                i++;
            }
        }
        return cleanTab;
    }

    private void addKant(String tekst){
        String[] tallTekst = cleanup(tekst);
        nodeListe[Integer.parseInt(tallTekst[0])].addKant(new Kant(Integer.parseInt(tallTekst[1]),Integer.parseInt
                (tallTekst[2])));
    }
}

