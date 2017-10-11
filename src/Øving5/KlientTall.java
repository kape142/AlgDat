package Øving5;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by KarlPeter on 14.09.2017.
 */
public class KlientTall {
    public static void main(String[] args) {
        int size = 5000000;
        Random random = new Random();
        int[] tab = new int[size];
        for (int i = 0; i < size; i++) {
            tab[i] = random.nextInt((size*100));
        }
        HashtabellTall h = new HashtabellTall(6001069);
        long t1 = System.nanoTime();
        for(int i : tab){
            h.add(i);
        }
        long t2 = System.nanoTime();
        long min = (t2-t1)/1000000;
        System.out.println("Min hashtabell bruker: "+min);
        HashMap util = new HashMap(size);
        t1 = System.nanoTime();
        for(int i : tab){
            util.put(i%size,i);
        }
        t2 = System.nanoTime();
        long java = (t2-t1)/1000000;
        System.out.println("Javas hashtabell bruker: "+java);
        System.out.println("Java bruker "+((double)java/(double)min)+" ganger så lang tid");
        System.out.println(h.getKollisjoner());
    }
}
