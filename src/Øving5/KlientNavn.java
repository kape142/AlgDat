package Øving5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by KarlPeter on 14.09.2017.
 */
public class KlientNavn {
    public static void main(String[] args) throws Exception {
        String filnavn = "src\\Øving5\\navn.txt";
        FileReader l = new FileReader(filnavn);
        BufferedReader leser = new BufferedReader(l);
        ArrayList<String> navn = new ArrayList<>();
        leser.lines().forEach(navn::add);
        int elements = navn.size();
        HashtabellNavn h = new HashtabellNavn(elements);
        for(String s : navn){
            h.add(s);
        }
        System.out.println(h);
    }
}
