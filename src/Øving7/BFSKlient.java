package Øving7;


public class BFSKlient {
    public static void main(String[] args) throws Exception{
        BFS bfs = new BFS();
        String[] filnavn = {"src\\Øving7\\tekstfiler\\L7g1.txt","src\\Øving7\\tekstfiler\\L7g2.txt","src\\Øving7\\tekstfiler\\L7g3.txt","src\\Øving7\\tekstfiler\\L7g5.txt"};
        for(String fil : filnavn){
            System.out.println(fil.split("[\\\\]")[3]);
            bfs.findShortestRoutes(fil,5);
            System.out.println(bfs.getAllData());
        }
    }
}
