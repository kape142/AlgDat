package Øving8;

/**
 * Created by KarlPeter on 09.10.2017.
 */
class DijkstraKlient{
    public static void main(String[] args) throws Exception {
        Dijkstra d = new Dijkstra();
        d.findRoute("src\\Øving8\\tekstfiler\\vg3.txt",0);
        System.out.println(d.getAllData());
    }
}
