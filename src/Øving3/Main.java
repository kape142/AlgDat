package Øving3;

import java.util.Random;

/**
 * Created by KarlPeter on 01.11.2017.
 */
public class Main {
    public static void main(String[] args) {
        Sorter sorter = new Sorter();
        Random random = new Random();
        int cap = 100;
        int[] tab = new int[1000000];
        for (int j = 0; j < tab.length; j++) {
            tab[j]= random.nextInt(cap);
        }
        long t = System.nanoTime();
        sorter.sort(tab,20,2);
        System.out.println((System.nanoTime()-t)/1000);
    }
}
