package Øving7;

/**
 * Created by KarlPeter on 09.10.2017.
 */
public class TSortKlient {
    public static void main(String[] args) throws Exception {
        TSort tSort = new TSort();
        tSort.topologicalSort("src\\Øving7\\tekstfiler\\L7g5.txt",0);
        System.out.println(tSort.getAllData());
    }
}
